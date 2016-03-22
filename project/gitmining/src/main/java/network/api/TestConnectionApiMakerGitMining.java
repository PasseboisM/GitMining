package network.api;

import network.api.service.NetworkTester;
import network.api.service.TestConnectionApiMaker;

public class TestConnectionApiMakerGitMining implements TestConnectionApiMaker{

	/**
	 * 获取网络测试对象实例，用于检测网络连接是否良好
	 * @return 网络测试对象实例
	 */
	public NetworkTester[] getTestConnectionApis() {
		NetworkTester[] result = {
				new NetworkTester("http://gitmining.net/api/user/rubinius/item/login", "rubinius"),
				new NetworkTester("http://gitmining.net/api/repository/ai/r18n/item/created_at", "2008-12-24T11:15:15Z")
		};
		return result;
	}

}
