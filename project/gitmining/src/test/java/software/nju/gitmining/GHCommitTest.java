package software.nju.gitmining;

import org.junit.Test;

import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import network.data.GHSpecificDataSource;
import network.service.SpecificDataSource;

public class GHCommitTest {

	@Test
	public void test() throws NetworkException, DataCorruptedException {
		SpecificDataSource source = new GHSpecificDataSource();
		source.getSpecificRepo("rubinius/rubinius");
		System.out.println();
		source.getSpecificRepo("facebook/fbctf");
	}

}
