package logic.data;

import static org.junit.Assert.*;

import org.junit.Test;

import network.service.MassiveDataSource;
import network.service.stub.MassiveDataSource_stub;

public class TestDriver {
	@Test
	public void testChannel() throws Exception {
		MassiveDataSource massiveDataSourcestub = new MassiveDataSource_stub();
		massiveDataSourcestub.getRepoInfo();
	}
}
