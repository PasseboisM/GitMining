package software.nju.gitmining;

import static org.junit.Assert.*;

import network.service.MassiveDataSource;
import network.service.NetworkServiceFactory;

import org.junit.Test;

import common.exception.NetworkException;
import common.service.Repository;
import common.util.ObjChannel;
import data.storage.input.DataInputDefault;
import data.storage.service.DataStorageInput;

public class StorageInputTest {

	@Test
	public void test() throws NetworkException {
		MassiveDataSource source =  NetworkServiceFactory.getInstance().getMassiveDataSource();
		ObjChannel<Repository> chan = source.getRepoInfo();
		
		DataStorageInput input = new DataInputDefault();
		input.saveRepository(chan);
	}

}
