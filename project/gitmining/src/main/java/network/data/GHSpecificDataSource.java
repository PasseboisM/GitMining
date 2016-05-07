package network.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.DataCorruptedException;
import common.exception.NetworkException;
import common.model.beans.BeansTranslator;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;
import network.service.SpecificDataSource;

public class GHSpecificDataSource implements SpecificDataSource{

	private GitHub gh = null;
	public Repository getSpecificRepo(RepositoryMin source) throws NetworkException, DataCorruptedException {
		return this.getSpecificRepo(source.getFull_name());
	}

	public Repository getSpecificRepo(String fullName) throws NetworkException, DataCorruptedException {
		GHRepository repository = null;
		Repository hyberRepo = null;
		try {
			repository = gh.getRepository(fullName);
			hyberRepo = BeansTranslator.getRepository(repository);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hyberRepo;
	}

	public GitUser getSpecificUser(GitUserMin source) throws NetworkException {
		return this.getSpecificUser(source.getLogin());
	}

	public GitUser getSpecificUser(String login) throws NetworkException {
		GHUser ghUser = null;
		GitUser hyberUser = null;
		try {
			ghUser = gh.getUser(login);
			hyberUser =  BeansTranslator.getUser(ghUser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hyberUser;
	}
	
	public List<GHUser> searchUser(UserSearchParam userSearchParam){
		GHUserSearchBuilder builder = gh.searchUsers();
		String loginName = userSearchParam.getLoginName();
		builder.q(loginName);
		return builder.list().asList();
	}
	
	public List<GHRepository> searchRepository(RepositorySearchParam repositorySearchParam){
		GHRepositorySearchBuilder builder = gh.searchRepositories();
		Language[] langs = repositorySearchParam.getLangs();
		String[] keywords = repositorySearchParam.getKeywords();
		RepoSortStadard sortStandard = repositorySearchParam.getSortStandard();
		for (String string : keywords) {
			builder.q(string);
		}
		for (Language language : langs) {
			builder.language(language.getName());
		}
		builder.sort(sortStandard.getSort());
		return builder.list().asList();
	}
	
	public GHSpecificDataSource() {
		try {
			File propertyFile = new File(System.getProperty("user.dir"), ".github");
			GitHubBuilder builder = GitHubBuilder.fromPropertyFile(propertyFile.getPath());
			this.gh = builder.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
