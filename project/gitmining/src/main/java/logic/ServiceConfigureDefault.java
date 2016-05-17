package logic;

import logic.service.ServiceConfigure;

public class ServiceConfigureDefault implements ServiceConfigure {
	
	@Override
	public boolean checkNetwork() {
		//TODO  实现网络连接（到GitServer服务器）连通情况测试
		return true;
	}	
}
