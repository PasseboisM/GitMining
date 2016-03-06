package logic.data;

import org.junit.Test;

import logic.data.stub.GeneralGetter_stub;
import logic.service.GeneralGetter;

public class TestDriver {
	@Test
	public void testChannel() throws Exception {
		GeneralGetter generalGetter = new GeneralGetter_stub();
		System.out.println(generalGetter.getNumOfRepositories());
	}
}
