package common.model.filter;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import common.exception.DataTransferException;
import common.util.MultiSourceSwitch;
import common.util.ObjChannel;
import common.util.Writable;

	/**
	 * 自行注册，能够接受JSON输入，转换后输出到管道或集线器
	 * @author xjh14
	 *
	 * @param <T>
	 */
public class JSONStringRPOFilter<T> extends GeneralProcessFilter<String, T> {

		private static final int page = 20;
		
		private ObjChannel<String> input = null;
		private Class<T> objectiveClass = null;
		private Writable<T> output = null;
		
		private boolean isSwitch = false;
		private ObjChannel<T> chan = null;
		MultiSourceSwitch<T> sourceSwitch = null;
		
		private Gson gson = new Gson(); 
		
		public JSONStringRPOFilter(ObjChannel<String> input, Class<T> objectiveClass,
				ObjChannel<T> output) {
			super(input,output,page);
			this.input = input;
			this.objectiveClass = objectiveClass;
			this.output = output;
			
			isSwitch = false;
			this.chan = output;
		}
		
		public JSONStringRPOFilter(ObjChannel<String> input, Class<T> objectiveClass,
				MultiSourceSwitch<T> output) {
			super(input,output,page);
			this.input = input;
			this.objectiveClass = objectiveClass;
			this.output = output;
			
			isSwitch = true;
			this.sourceSwitch = output;
			output.register(this);
		}
//		
//		public void run() {
//			while(input.hasMore()) {
//				try {
//					List<String> jsons = input.getObj(page);
//					List<T> partialResult = process(jsons);
//					output.writeObj(partialResult);
//				} catch (DataTransferException e) {
//					closeExceptionally();
//					return;
//				}
//			}
//			close();
//		}

		@Override
		public List<T> process(List<String> get) {
			List<T> result = new ArrayList<T>(page);
			for(String json: get) {
				//TODO noise!
				if(json.equals("")) continue;
				result.add(gson.fromJson(json, objectiveClass));
			}
			return result;
		}
	}