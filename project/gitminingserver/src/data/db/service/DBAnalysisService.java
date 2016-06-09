package data.db.service;

import org.bson.Document;

public interface DBAnalysisService {

	public void ensureUser(String userHash);
	
	public Document getUserIndex(String userHash);
	
	public void updateIndex(String userHash, Document indexes);
	
}
