package data.storage;

import data.storage.service.DataStorageInput;
import data.storage.service.DataStorageOutput;
import data.storage.service.StorageServiceFactory;

public class StorageServiceFactoryDefault extends StorageServiceFactory {

	private static final StorageServiceFactoryDefault instance = new StorageServiceFactoryDefault();
	
	
	
	private StorageServiceFactoryDefault(){
		// TODO Init matters
	}
	
	public static StorageServiceFactoryDefault getInstance() {
		return instance;
	}

	@Override
	public DataStorageInput getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataStorageOutput getOutput() {
		// TODO Auto-generated method stub
		return null;
	}
}
