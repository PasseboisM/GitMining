package software.nju.gitmining;

import common.exception.NetworkException;
import network.data.GHSpecificDataSource;
import network.service.SpecificDataSource;

public class GHCommitTest {

//	@Test
	public void test() throws NetworkException {
		SpecificDataSource source = new GHSpecificDataSource();
		source.getSpecificUser("rubinius");
	}

}
