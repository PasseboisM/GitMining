package data.db;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import common.enumeration.sort_standard.UserSortSandard;
import common.param_obj.UserSearchParam;
import data.db.core.CollectionHelper;
import data.db.core.ConnectionPool;
import data.db.core.GitCollections;
import data.db.service.DBUserService;

public class DBUserServiceDefault implements DBUserService {

	private ConnectionPool cp = ConnectionPool.getInstance();
	
	
	
	@Override
	public List<String> getUsers(int page, int numPerPage, UserSortSandard sortStandard)
			throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchUsers(UserSearchParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumOfUser() {
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> userColl =
				CollectionHelper.getCollection(base, GitCollections.USER);
		int result = (int) userColl.count();
		
		cp.releaseDatabase(base);
		return result;
	}

}
