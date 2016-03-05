package common.service;

/**
 * 
 * @author River
 * 
 * Repository数据提供的详情获取接口
 * TODO 还有好多Repository的信息条目！
 */
public interface Repository extends RepositoryMin {
	
	public RepositoryOwner getOwner();
}