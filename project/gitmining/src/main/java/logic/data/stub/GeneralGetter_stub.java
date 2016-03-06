package logic.data.stub;

import java.util.ArrayList;
import java.util.List;

import common.enumeration.sort_standard.RepoSortStadard;
import common.enumeration.sort_standard.UserSortSandard;
import common.exception.DataTransferException;
import common.service.GitUser;
import common.service.Repository;
import common.service.RepositoryMin;
import common.util.DataSourceConsumer;
import common.util.ObjChannel;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import logic.service.GeneralGetter;

public class GeneralGetter_stub implements GeneralGetter {

	DataServiceFactory factory = DataServiceFactory.getInstance();
	MassiveDataGetter massiveDataGetterstub = factory.getMassiveDataGetter();
	SpecificDataGetter specificDataGetter = factory.getSpecificDataGetter();
	
	List<Repository> repository = new ArrayList<Repository>();
	
	public GeneralGetter_stub() {
		
		ObjChannel<RepositoryMin> channel = massiveDataGetterstub.getRepoMinInfo();
		DataSourceConsumer<RepositoryMin> consumer = new DataSourceConsumer<RepositoryMin>(channel);
		List<RepositoryMin> repositoryMins = consumer.getData();
		for (RepositoryMin repositoryMin : repositoryMins) {
			repository.add(specificDataGetter.getSpecificRepo(repositoryMin));
		}
	}
	
	public List<Repository> getRepositories(int page, int numPerPage, RepoSortStadard sortStandard) {
		return repository.subList(numPerPage*(page-1), numPerPage*page);
	}

	public int getNumOfRepositories() {
		return repository.size();
	}

	public int getNumOfUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<GitUser> getUsers(int page, int numPerPage, UserSortSandard sortStandard) {
		// TODO Auto-generated method stub
		return null;
	}

}
