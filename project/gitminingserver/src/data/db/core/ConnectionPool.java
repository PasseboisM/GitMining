package data.db.core;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class ConnectionPool {

	public static final int CONNECTION_COUNT = 45;
	
	private static ConnectionPool instance = new ConnectionPool();
	
	private AtomicInteger availableCount;
	private MongoClient[] clients = new MongoClient[CONNECTION_COUNT];
	private MongoDatabase[] bases = new MongoDatabase[CONNECTION_COUNT];
	private AtomicBoolean[] available = new AtomicBoolean[CONNECTION_COUNT];
			
			
			
	private ConnectionPool() {
		availableCount = new AtomicInteger(CONNECTION_COUNT);
		for (int i=0;i<CONNECTION_COUNT;i++) {
			ArrayList<MongoCredential> credentialList = new ArrayList<>();
			credentialList.add(MongoCredential.createCredential(
					"gitminer", "gitmining", "teammole".toCharArray()));
			clients[i] = new MongoClient(
					new ServerAddress("10.19.74.64", 27017), credentialList);
			bases[i] = clients[i].getDatabase("gitmining");
			available[i] = new AtomicBoolean(true);
		}
	}
	
	public synchronized MongoDatabase getDatabase() {
		while (availableCount.get()==0){
		}
		
		for (int i=0;i<CONNECTION_COUNT;i++) {
			if (available[i].get()==true) {
				available[i].set(false);
				availableCount.decrementAndGet();
				return bases[i];
			}
		}
		
		return null;
	}

	public synchronized void releaseDatabase(MongoDatabase database) {
		for (int i=0;i<CONNECTION_COUNT;i++) {
			if (database==bases[i]&&available[i].get()==false) {
				available[i].set(true);
				availableCount.incrementAndGet();
				return;
			}
		}
	}

	
	public static ConnectionPool getInstance() {
		return instance;
	}
	
}
