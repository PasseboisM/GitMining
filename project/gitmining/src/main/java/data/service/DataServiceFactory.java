package data.service;

import data.BasicDataServiceFactory;

/**
 * 
 * @author xjh14
 * 
 * 为Data层外层提供的获取Data服务的接口
 * 
 * TODO 设计Data层服务接口并填在本接口内
 * 
 */
public abstract class DataServiceFactory {

	public abstract MassiveDataGetter getMassiveDataGetter();
	
	public abstract SpecificDataGetter getSpecificDataGetter();
	
	public static DataServiceFactory getInstance() {
		return new BasicDataServiceFactory();
	}
	
}
