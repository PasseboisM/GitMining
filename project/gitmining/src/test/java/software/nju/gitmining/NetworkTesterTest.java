package software.nju.gitmining;

import static org.junit.Assert.*;
import network.api.service.ApiMakerService;
import network.api.service.NetworkTester;

import org.junit.Test;

/**
 * 测试NetworkTester的功能（本测试用例仅当网络连接正常且GitMiningAPI服务正常时才可通过）
 * @author xjh14
 * Ver: 1.0
 */
public class NetworkTesterTest {

	@Test
	public void test() {
		NetworkTester[] testers = ApiMakerService.getInstance().getTestConnectionApiMaker().getTestConnectionApis();
		for(NetworkTester tester:testers) {
			assertEquals(tester.test(),true);
		}
	}

}
