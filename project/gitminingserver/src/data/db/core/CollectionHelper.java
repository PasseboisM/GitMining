package data.db.core;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CollectionHelper {

	public static final String REPOSITORY = "repository";
	
	public static MongoCollection<Document> getCollection(
			MongoDatabase database, GitCollections collection) {
		return database.getCollection(collection.getName());
	}
	
}
