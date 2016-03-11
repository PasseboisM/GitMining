package software.nju.gitmining;

import static org.junit.Assert.*;
import network.api.service.ApiMakerService;
import network.api.service.NetworkTester;

import org.junit.Test;

public class NetworkTesterTest {

	@Test
	public void test() {
		NetworkTester[] testers = ApiMakerService.getNetworkTesters();
		for(NetworkTester tester:testers) {
			assertEquals(tester.test(),true);
		}
	}

}
