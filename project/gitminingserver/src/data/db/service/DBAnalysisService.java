package data.db.service;

import org.bson.Document;

public interface DBAnalysisService {
	
	public static final String USER_HASH = "user_hash";
	
	public void ensureUser(String userHash);
	
	public Document getUserIndex(String userHash);
	
	public void updateIndex(String userHash, Document indexes);
	
}
