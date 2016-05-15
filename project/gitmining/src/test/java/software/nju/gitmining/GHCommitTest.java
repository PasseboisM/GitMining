package software.nju.gitmining;

import java.io.IOException;

import org.junit.Test;

import common.enumeration.attribute.Language;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import network.data.GHAnalysisDataSourceDefault;

public class GHCommitTest {

	@Test
	public void test() throws NetworkException, DataCorruptedException, IOException {
//		SpecificDataSource source = new GHSpecificDataSource();
//		source.getSpecificRepo("rubinius/rubinius");
//		System.out.println();
//		source.getSpecificRepo("facebook/fbctf");
		GHAnalysisDataSourceDefault source = new GHAnalysisDataSourceDefault();
		source.recommendRepositories(Language.COMMON_LISP);
	}

}
