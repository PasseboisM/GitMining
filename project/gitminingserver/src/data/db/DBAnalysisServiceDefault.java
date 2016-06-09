package data.db;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import data.db.core.CollectionHelper;
import data.db.core.ConnectionPool;
import data.db.core.GitCollections;
import data.db.service.DBAnalysisService;

public class DBAnalysisServiceDefault implements DBAnalysisService {

	ConnectionPool cp = ConnectionPool.getInstance();
		
	@Override
	public void ensureUser(String userHash) {
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> analysisColl = 
				CollectionHelper.getCollection(base, GitCollections.ANALYSIS);
		
		FindIterable<Document> found = analysisColl.find(eq(USER_HASH,userHash));
		try {
			Document temp = found.first();
		} catch (Exception e) {//Not found
			analysisColl.insertOne(new Document(USER_HASH,userHash));
		}
		
		cp.releaseDatabase(base);
	}

	@Override
	public Document getUserIndex(String userHash) {
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> analysisColl = 
				CollectionHelper.getCollection(base, GitCollections.ANALYSIS);
		
		FindIterable<Document> found = analysisColl.find(eq(USER_HASH,userHash));
		
		Document result = found.first();
		
		
		cp.releaseDatabase(base);
		return result;
	}

	@Override
	public void updateIndex(String userHash, Document indexes) {
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> analysisColl = 
				CollectionHelper.getCollection(base, GitCollections.ANALYSIS);
		
		analysisColl.replaceOne(eq(USER_HASH,userHash), indexes);
		
		cp.releaseDatabase(base);
	}
}
