package data.storage.service;

import data.storage.StorageServiceFactoryDefault;

public abstract class StorageServiceFactory {

	public abstract DataStorageInput getInput();
	
	public abstract DataStorageOutput getOutput();
	
	public static StorageServiceFactory getInstance() {
		return StorageServiceFactoryDefault.getInstance();
	}
}
