package common.model.filter;

import java.util.List;

import common.exception.DataTransferException;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import common.util.Writable;

public abstract class GeneralProcessFilter<I,T> implements Runnable {

	protected int page = 20;
	
	private ObjChannel<I> input = null;
	private Writable<T> output = null;
	
	private boolean isSwitch = false;
	private ObjChannel<T> chan = null;
	MultiSourceSwitch<T> sourceSwitch = null;
	
	//用于提醒子类在构造器中必须调用本类的含多个参数的构造器
	@SuppressWarnings("unused")
	private GeneralProcessFilter(){}
	
	public GeneralProcessFilter(ObjChannel<I> input, ObjChannel<T> output, int page) {
		this.input = input;
		this.output = output;
		this.page = page;
		
		isSwitch = false;
		this.chan = output;
	}
	
	public GeneralProcessFilter(ObjChannel<I> input, MultiSourceSwitch<T> output, int page) {
		this.input = input;
		this.output = output;
		this.page = page;
		
		isSwitch = true;
		this.sourceSwitch = output;
		output.register(this);
	}
	
	
	public void run() {
		while(input.hasMore()) {
			try {
				List<I> inputObjects = input.getObj(page);
				List<T> partialResult = process(inputObjects);
				output.writeObj(partialResult);
			} catch (DataTransferException e) {
				closeExceptionally();
				return;
			}
		}
		close();
	}
	
	public abstract List<T> process(List<I> get);

	protected void close() {
		if(isSwitch) {
			sourceSwitch.deregister(this);
		} else {
			chan.close();
		}
	}
	
	protected void closeExceptionally() {
		if(isSwitch) {
			sourceSwitch.declareException(this);
		} else {
			chan.closeWithException();
		}
	}
}
