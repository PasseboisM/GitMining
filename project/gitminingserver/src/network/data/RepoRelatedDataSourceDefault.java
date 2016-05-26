package network.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

import common.model.HyberUser;
import common.service.GitUser;
import network.service.RepoRelatedDataSource;

public class RepoRelatedDataSourceDefault implements RepoRelatedDataSource {

	
	private GitHub gh = null;

	@Override
	public List<GitUser> listContributors(String fullName) throws IOException {
		GHRepository ghRepository = gh.getRepository(fullName);
		return listUsers(ghRepository.listContributors());
	}

	@Override
	public List<GitUser> listCollaborators(String fullName) throws IOException {
		GHRepository ghRepository = gh.getRepository(fullName);
		return listUsers(ghRepository.listCollaborators());
	}

	private <T extends GHUser> List<GitUser> listUsers(PagedIterable<T> pagedIterable) {
		List<GitUser> result = new ArrayList<>();
		pagedIterable.forEach(user->{
			GitUser hyberUser =  new HyberUser(user);
			result.add(hyberUser);
		});
		return result;
	}
	
	public RepoRelatedDataSourceDefault() {
		this.gh = GHNetworkServiceFactory.getGitHub();
	}

}
