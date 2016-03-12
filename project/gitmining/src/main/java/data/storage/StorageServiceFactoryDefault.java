package data.storage;

import data.storage.input.DataInputDefault;
import data.storage.output.DataOutputDefault;
import data.storage.service.DataStorageInput;
import data.storage.service.DataStorageOutput;
import data.storage.service.StorageServiceFactory;

public class StorageServiceFactoryDefault extends StorageServiceFactory {

	private static final StorageServiceFactoryDefault instance = new StorageServiceFactoryDefault();
	
	
	
	private StorageServiceFactoryDefault(){
		// TODO Init matters? Or nothing?
	}
	
	public static StorageServiceFactoryDefault getInstance() {
		return instance;
	}

	@Override
	public DataStorageInput getInput() {
		return new DataInputDefault();
	}

	@Override
	public DataStorageOutput getOutput() {
		return new DataOutputDefault();
	}
}
