package data.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Sorts;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.NetworkException;
import common.param_obj.RepositorySearchParam;
import common.service.Repository;
import data.db.core.CollectionHelper;
import data.db.core.ConnectionPool;
import data.db.core.GitCollections;
import data.db.service.DBRepoService;
import network.service.GHDataSource;
import network.service.NetworkServiceFactory;



public class DBRepoServiceDefault implements DBRepoService {

	ConnectionPool cp = ConnectionPool.getInstance();
	GHDataSource GHService = NetworkServiceFactory.getInstance().getGHDataSource();
	
	@Override
	public int getNumOfRepo() {
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> repository = CollectionHelper.getCollection(
				base, GitCollections.REPOSITORY);
		int result = (int) repository.count();
		cp.releaseDatabase(base);
		return result;
	}

	@Override
	public List<String> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard)
			throws IndexOutOfBoundsException {
		assert page>0&& numPerPage>0;
		
		ArrayList<String> result = new ArrayList<>(numPerPage);
		
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> repository = CollectionHelper.getCollection(
				base, GitCollections.REPOSITORY);
		FindIterable<Document> repos = repository.find();
		
		switch (sortStandard) {
		case FORKS_DESCENDING:
			repos = repos.sort(Sorts.descending("forks_count"));
			break;
		case NO_SORT:
			break;
		case STARS_DESCENDING:
			repos = repos.sort(Sorts.descending("stargazers_count"));
			break;
		default:
			cp.releaseDatabase(base);
			return result;
		}
		
		try {
			repos.forEach(new Block<Document>(){
				int current = 0;
				int min = numPerPage * (page-1) + 1;
				int max = numPerPage * page;
				@Override
				public void apply(Document arg0) {
					current ++;
					if (current<=max&&current>=min) {
						result.add(arg0.toJson());
					}
				}
			});
		} catch (Exception e) {
			throw new IndexOutOfBoundsException();
		} finally {
			cp.releaseDatabase(base);
		}
		if (result.size()==0) throw new IndexOutOfBoundsException();
		
		return result;
	}

	@Override
	public List<String> searchRepository(RepositorySearchParam params) {
		List<String> result = new ArrayList<String>(200);
		List<Repository> fromNet = null;
		try {
			fromNet = GHService.searchRepository(params);
		} catch (NetworkException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static void main(String[] args) {
		DBRepoServiceDefault ti = new DBRepoServiceDefault();
		List<String> sl = ti.getRepositories(3, 20, RepoSortStadard.NO_SORT);

		for (String s:sl) {
			System.out.println(s);
		}
		System.out.println("Size:"+sl.size());
	}
}
