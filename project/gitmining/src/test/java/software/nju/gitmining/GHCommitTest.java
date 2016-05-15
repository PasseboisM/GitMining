package software.nju.gitmining;

import java.io.IOException;

import org.junit.Test;

import common.enumeration.attribute.Language;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import network.data.GHTAnalysisDataSourceDefault;

public class GHCommitTest {

//	@Test
	public void test() throws NetworkException, DataCorruptedException, IOException {
//		SpecificDataSource source = new GHSpecificDataSource();
//		source.getSpecificRepo("rubinius/rubinius");
//		System.out.println();
//		source.getSpecificRepo("facebook/fbctf");
		GHTAnalysisDataSourceDefault source = new GHTAnalysisDataSourceDefault();
		source.recommendUsers(Language.C_SHARP);
	}

}
