package common.model.beans;

import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import common.service.RepositoryMin;

public class BeansTranslator {
	
	/**
	 * 获取接口对应的Beans模型
	 * TODO 将职责分散给各个接口
	 * @param imp 接口类型
	 * @return Beans类型
	 */
	@SuppressWarnings("rawtypes")
	public static Class getBeans(Class imp) {
		if(imp==Repository.class) {
			return RepositoryBeans.class;
		} else if(imp==RepositoryMin.class) {
			return RepositoryMinBeans.class;
		} else if(imp==GitUserMin.class) {
			return GitUserMinBeans.class;
		} else if (imp==GitUser.class) {
			return GitUserBeans.class;
		}  else {
			return null;
		}
	}
	
	/*public static Repository getRepository(GHRepository ghRepository){
		HyberRepository.HyberOwner owner =getOwner(ghRepository);
		boolean admin = ghRepository.hasAdminAccess();
		boolean push = ghRepository.hasPushAccess();
		boolean pull = ghRepository.hasPullAccess();
		HyberRepository.HyberPermission permissions = new HyberRepository.HyberPermission(admin, push, pull);
		HyberRepository.HyberOrganization organization = getOrganization(ghRepository);
		int id = ghRepository.getId();
		String name = ghRepository.getName();
		String full_name = ghRepository.getFullName();
		boolean isPrivate = ghRepository.isPrivate();
		String html_url = ghRepository.getHtmlUrl().toString();
		String description = ghRepository.getDescription();
		boolean fork = ghRepository.isFork();
		String created_at = null;
		String updated_at = null;
		try {
			created_at = ghRepository.getCreatedAt().toString();
			updated_at = ghRepository.getUpdatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pushed_at = ghRepository.getPushedAt().toString();
		String git_url = ghRepository.getGitTransportUrl();
		String ssh_url = ghRepository.getSshUrl();
		String svn_url = ghRepository.getSvnUrl();
		String homepage = ghRepository.getHomepage();
		int size = ghRepository.getSize();
		int stargazers_count = ghRepository.getWatchers();
		int watchers_count = ghRepository.getWatchers();
		String language = ghRepository.getLanguage();
		boolean has_issues = ghRepository.hasIssues();
		boolean has_downloads = ghRepository.hasDownloads();
		boolean has_wiki = ghRepository.hasWiki();
		int forks_count = ghRepository.getForks();
		int open_issues_count = ghRepository.getOpenIssueCount();
		int forks = ghRepository.getForks();
		int open_issues = ghRepository.getOpenIssueCount();
		int watchers = ghRepository.getWatchers();
		String default_branch = ghRepository.getDefaultBranch();
		int network_count = ghRepository.getNetworkCount();
		int subscribers_count = ghRepository.getSubscribersCount();
		HyberRepository hyberRepo = new HyberRepository(id, name, full_name, owner, isPrivate, html_url, description, fork, html_url, created_at, updated_at, pushed_at, git_url, ssh_url, svn_url, homepage, size, stargazers_count, watchers_count, language, has_issues, has_downloads, has_wiki, forks_count, open_issues_count, forks, open_issues, watchers, default_branch, permissions, organization, network_count, subscribers_count);
		return hyberRepo;
	}*/

	/*private static HyberRepository.HyberOwner getOwner(GHRepository ghRepository) {
		GHUser ghUser = null;
		try {
			ghUser = ghRepository.getOwner();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String login = ghUser.getLogin();
		int id = ghUser.getId();
		String avatar_url = ghUser.getAvatarUrl();
		@SuppressWarnings("deprecation")
		String gravatar_id = ghUser.getGravatarId();
		String url = ghUser.getUrl().toString();
		String html_url = ghUser.getHtmlUrl().toString();
		return new HyberRepository.HyberOwner(login, id, avatar_url, gravatar_id, url, html_url);
	}
	
	private static HyberRepository.HyberOrganization getOrganization(GHRepository ghRepository) {
		GHUser ghUser = null;
		try {
			ghUser = ghRepository.getOwner();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String login = ghUser.getLogin();
		int id = ghUser.getId();
		String avatar_url = ghUser.getAvatarUrl();
		@SuppressWarnings("deprecation")
		String gravatar_id = ghUser.getGravatarId();
		String url = ghUser.getUrl().toString();
		String html_url = ghUser.getHtmlUrl().toString();
		return new HyberRepository.HyberOrganization(login, id, avatar_url, gravatar_id, url, html_url);
	}*/
	
	/*public static GitUser getUser(GHUser ghUser) throws IOException{
		String login = ghUser.getLogin();
		int id = ghUser.getId();
		String name = ghUser.getName();
		int followers = ghUser.getFollowersCount();
		String avatar_url = ghUser.getAvatarUrl();
		@SuppressWarnings("deprecation")
		String gravatar_id = ghUser.getGravatarId();
		String url = ghUser.getUrl().toString();
		String html_url  = ghUser.getHtmlUrl().toString();
		String blog = ghUser.getBlog();
		String location  = ghUser.getLocation();
		String email = ghUser.getEmail();
		int public_repos = ghUser.getPublicRepoCount();
		int public_gists = ghUser.getPublicGistCount();
		int following = ghUser.getFollowingCount();
		String created_at = ghUser.getCreatedAt().toString();
		String updated_at = ghUser.getUpdatedAt().toString();
		GitUser hyberUser = new HyberUser(login, id, name, followers, avatar_url, gravatar_id, url, html_url, blog, location, email, public_repos, public_gists, following, created_at, updated_at);
		return hyberUser;
	}*/
}
