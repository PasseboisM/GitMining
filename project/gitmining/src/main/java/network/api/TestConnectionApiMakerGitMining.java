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
				new NetworkTester("http://106.75.5.61:8080/GitMiningServer/api?action=test", "{\"message\":\"Connected.\"}")
		};
		return result;
	}
}
