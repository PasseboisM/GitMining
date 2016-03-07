package network.data.sources;

import java.util.List;

import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import common.util.Writable;

	/**
	 * 自行注册、解注册的纯数据传送源
	 * TODO 修改为继承GeneralProcessSource的类
	 * @author xjh14
	 * @param <T>
	 */
public class PureDataTransSource<T> implements Runnable {

		List<T> data;
		Writable<T> target;
		
		ObjChannel<T> chan;
		MultiSourceSwitch<T> sourceSwitch;
		boolean isSwitch;
		
		public PureDataTransSource(List<T> data,ObjChannel<T> chan) {
			this.data = data;
			target = chan;
			
			isSwitch = false;
			this.chan = chan;
		}
		
		public PureDataTransSource(List<T> data,MultiSourceSwitch<T> sourceSwitch) {
			this.data = data;
			target = sourceSwitch;
			
			this.isSwitch = true;
			sourceSwitch.register(this);
			this.sourceSwitch = sourceSwitch;
		}
		
		public void run() {
			target.writeObj(data);
			close();
		}
		
		private void close() {
			if(isSwitch) {
				sourceSwitch.deregister(this);
			} else {
				chan.close();
			}
		}
	}