package data.db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Sorts;
import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.TargetNotFoundException;
import common.param_obj.RepositorySearchParam;
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
		
		sort(sortStandard, repos);
		
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
		/**
		Language[]; Category[]; String[] keywords;RepoSortStadard;
		 */
		
		List<String> result = new ArrayList<String>(200);
		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> repoColl = CollectionHelper.getCollection(
				base, GitCollections.REPOSITORY);
		
		FindIterable<Document> found = repoColl.find();
		Bson[] partialFilter = new Bson[3];
		
		partialFilter[0] = null;
		for (Language lang:params.getLangs()) {
			System.out.println(lang);
			if (partialFilter[0]==null) {
				partialFilter[0] = eq("language",lang.getName());
			} else {
				if (lang==Language.ALL||lang==Language.OTHERS) {
					partialFilter[0] = new Document();	
					break;
				}
				partialFilter[0] = or(partialFilter[0],eq("language",lang.getName()));
			}
		}
		if (partialFilter[0]==null) partialFilter[0] = new Document();
		
		partialFilter[1] = null;
		for (Category cat:params.getCates()) {
			
			if (cat==Category.ALL||cat==Category.OTHERS) {
				partialFilter[1] = new Document();	
				break;
			}
			
			if (partialFilter==null) {
				partialFilter[1] = regex("description",cat.getName());
			} else {
				partialFilter[1] = and(partialFilter[1],regex("description",cat.getName()));
			}
		}
		if (partialFilter[1]==null) partialFilter[1] = new Document();
		
		partialFilter[2] = null;
		for (String keyword:params.getKeywords()) {
			if (partialFilter[2]==null) {
				partialFilter[2] = or(regex("full_name",keyword),regex("description",keyword));
			} else {
				partialFilter[2] = and (partialFilter[2],
						or(regex("full_name",keyword),regex("description",keyword)));
			}
		}
		if (partialFilter[2]==null) partialFilter[2] = new Document();
		found.filter(and(partialFilter[0],partialFilter[1],partialFilter[2]));
		
		sort(params.getSortStandard(),found);
		
		found.forEach(new Block<Document>() {
			int count = 0;
			final int MAX_RETURN = 200;
			@Override
			public void apply(Document arg0) {
				if(count>=MAX_RETURN) {
					return;
				}
				else {
					result.add(arg0.toJson());
					count++;
				}
			}
		});
		
		cp.releaseDatabase(base);
		
		return result;
	}


	@Override
	public String getRepository(String fullName) throws TargetNotFoundException {

		MongoDatabase base = cp.getDatabase();
		MongoCollection<Document> repoColl = CollectionHelper.getCollection(
				base, GitCollections.REPOSITORY);
		
		FindIterable<Document> found = repoColl.find(eq("full_name",fullName));
		try {
			return found.first().toJson();
		} catch (Exception e) { // No repository found.
			throw new TargetNotFoundException();
		} finally {
					cp.releaseDatabase(base);	
		}
	}
	
	public static void main(String[] args) {
		DBRepoServiceDefault ti = new DBRepoServiceDefault();
		MongoDatabase base = ti.cp.getDatabase();
		MongoCollection<Document> repository = CollectionHelper.getCollection(
				base, GitCollections.REPOSITORY);
		Bson filt = null;
		filt = eq("language","Java");
		FindIterable<Document> result = 
				repository.find();
		result.filter(filt);
		System.out.println(result==null);
		System.out.println(result.first().toJson());	
		ti.cp.releaseDatabase(base);
	}

	private static void sort(RepoSortStadard sortStandard, FindIterable<Document> repos) {
		switch (sortStandard) {
		case FORKS_DESCENDING:
			repos = repos.sort(Sorts.descending("forks_count"));
			break;
		case NO_SORT:
			break;
		case STARS_DESCENDING:
			repos = repos.sort(Sorts.descending("stargazers_count"));
			break;

		}
	}

}
