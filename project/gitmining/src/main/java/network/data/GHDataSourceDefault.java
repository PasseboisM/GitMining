package network.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHRepositorySearchBuilder;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GHUserSearchBuilder;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import common.enumeration.attribute.Language;
import common.enumeration.sort_standard.RepoSortStadard;
import common.exception.NetworkException;
import common.model.HyberRepository;
import common.model.HyberUser;
import common.param_obj.RepositorySearchParam;
import common.param_obj.UserSearchParam;
import common.service.GitUser;
import common.service.Repository;
import network.service.GHDataSource;

public class GHDataSourceDefault implements GHDataSource {

	
	
	private GitHub gh;

	public GHDataSourceDefault() {
		this.gh = GHNetworkServiceFactory.getGitHub();
	}
	
	@Override
	public List<Repository> searchRepository(RepositorySearchParam repositorySearchParam) {
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
		List<Repository> repositories = new ArrayList<>();
		builder.list().asList().forEach((GHRepository ghRepo)->{
			repositories.add(new HyberRepository(ghRepo));
		});
		return repositories;
	}

	@Override
	public List<GitUser> searchUser(UserSearchParam userSearchParam) {
		GHUserSearchBuilder builder = gh.searchUsers();
		String loginName = userSearchParam.getLoginName();
		builder.q(loginName);
		List<GitUser> users = new ArrayList<>();
		builder.list().forEach((GHUser ghUser)->{
			users.add(new HyberUser(ghUser));
		});
		return users;
	}

	@Override
	public boolean isCredentialValid(String login, String password) throws NetworkException {
		Properties properties = new Properties();
		properties.setProperty("login", login);
		properties.setProperty("password", password);
		GitHubBuilder builder = GitHubBuilder.fromProperties(properties);
		boolean isCredentialValid = false;
		try {
			GitHub github = builder.build();
			isCredentialValid = github.isCredentialValid();
		} catch (Exception e) {
			throw new NetworkException();
		}
		return isCredentialValid;
	}
	
	

}
