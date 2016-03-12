package logic.service;

import common.exception.NetworkException;

public interface ServiceConfigure {

	/**
	 * 设置系统是否使用在线模式
	 * @param true/false分别为启动/取消在线模式
	 * @throws NetworkException 网络环境异常，不能提供服务
	 */
	public void setOnlineActive(boolean useOnlineMode) throws NetworkException;
	
}
