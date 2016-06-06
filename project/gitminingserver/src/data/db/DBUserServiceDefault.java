package data.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Filters.*;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortStandard;
import common.exception.TargetNotFoundException;
import common.param_obj.UserSearchParam;
import data.db.core.CollectionHelper;
import data.db.core.ConnectionPool;
import data.db.core.GitCollections;
import data.db.service.DBUserService;

public class DBUserServiceDefault implements DBUserService {

	private ConnectionPool cp = ConnectionPool.getInstance();
	
	
	
	@Override
	public List<String> getUsers(int page, int numPerPage, UserSortStandard sortStandard)
			throws IndexOutOfBoundsException {
		ArrayList<String> result = new ArrayList<String>(numPerPage);
		
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> userColl =
				CollectionHelper.getCollection(base, GitCollections.USER);

		FindIterable<Document> found = userColl.find();

		sort(sortStandard,found);
		
		found.forEach(new Block<Document>(){
			int count = 0;
			int MIN_INDEX = (page-1)*numPerPage+1;
			int MAX_INDEX = page * numPerPage;
			@Override
			public void apply(Document arg0) {
				count++;
				if (count<=MAX_INDEX&&count>=MIN_INDEX) {
					result.add(arg0.toJson());
				}
			}
		});
		
		cp.releaseDatabase(base);
		
		if (result.size()==0) throw new IndexOutOfBoundsException();
		
		return result;
	}

	@Override
	public List<String> searchUsers(UserSearchParam param) {
		final int MAX_RETURN = 200;
		
		ArrayList<String> result = new ArrayList<>(MAX_RETURN);
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> userColl =
				CollectionHelper.getCollection(base, GitCollections.USER);
		
		Bson filter = null;
		for (String keyword:param.getKeywords()) {
			if (filter==null) {
				filter = getKeywordMatch(keyword);
			} else {
				filter = and(filter,getKeywordMatch(keyword));
			}
		}
		if (filter==null) filter = new Document();
		
		FindIterable<Document> found = userColl.find(filter);
		
		sort(param.getSortStandard(), found);
		
		found.forEach(new Block<Document>(){
			int count = 0;
			@Override
			public void apply(Document arg0) {
				if ((count++)<MAX_RETURN) {
					result.add(arg0.toJson());
				}
			}
		});
		
		cp.releaseDatabase(base);
		
		return result;
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
	
	private static void sort(UserSortStandard sortStandard, FindIterable<Document> users) {
		switch (sortStandard) {
		case FOLLOWER_DESCENDING:
			users.sort(Sorts.descending("followers"));
			break;
		case NO_SORT:
			break;
		default:
			break;

		}
	}

	@Override
	public String getUser(String login) throws TargetNotFoundException {
		if (login==null) throw new TargetNotFoundException();
		
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> userColl =
				CollectionHelper.getCollection(base, GitCollections.USER);
		FindIterable<Document> found = userColl.find(eq("login",login));
		
		try {
			String result = found.first().toJson();
			return result;
		} catch (Exception e) {
			throw new TargetNotFoundException();
		} finally {
			cp.releaseDatabase(base);
		}
	}

	private static Bson getKeywordMatch(String keyword) {
		return or(
				regex("login",keyword),
				regex("name",keyword),
				regex("location",keyword),
				regex("email",keyword)
				);
	}
	
}
