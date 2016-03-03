package common.service;

/**
 * 
 * @author River
 * 
 * Repository数据提供的详情获取接口
 * 
 */
public interface Repository {

	//	public Obj_id get_id() {
	//		return _id;
	//	}
	public int getId();

	public String getName();

	public String getFull_name();

	public RepositoryOwner getOwner();
	
	public boolean isPrivate();
	//	class Obj_id {
	//		private int timestamp;
	//		private int machineIdentifier;
	//		private int processIdentifier;
	//		private int counter;
	//		public int getTimestamp() {
	//			return timestamp;
	//		}
	//		public int getMachineIdentifier() {
	//			return machineIdentifier;
	//		}
	//		public int getProcessIdentifier() {
	//			return processIdentifier;
	//		}
	//		public int getCounter() {
	//			return counter;
	//		}	
	//	}

}