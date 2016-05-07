package common.model;

import com.google.gson.annotations.SerializedName;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.service.Repository;
import common.service.RepositoryOrganization;
import common.service.RepositoryOwner;
import common.service.RepositoryPermission;

public class HyberRepository implements Repository{

	private int id;
	private String name;
	private String full_name;
	private RepositoryOwner owner;
	@SerializedName("private") private boolean isPrivate;
	private String html_url;
	private String description;
	private boolean fork;
	private String url;
	private String created_at;
	private String updated_at;
	private String pushed_at;
	private String git_url;
	private String ssh_url;
	private String svn_url;
	private String homepage;
	private int size;
	private int stargazers_count;
	private int watchers_count;
	private String language;
	private boolean has_issues;
	private boolean has_downloads;
	private boolean has_wiki;
	private int forks_count;
	private int open_issues_count;
	private int forks;
	private int open_issues;
	private int watchers;
	private String default_branch;
	private RepositoryPermission permissions;
	private RepositoryOrganization organization;
	private int network_count;
	private int subscribers_count;
	
	

	public HyberRepository(int id, String name, String full_name, RepositoryOwner owner, boolean isPrivate,
			String html_url, String description, boolean fork, String url, String created_at, String updated_at,
			String pushed_at, String git_url, String ssh_url, String svn_url, String homepage, int size,
			int stargazers_count, int watchers_count, String language, boolean has_issues, boolean has_downloads,
			boolean has_wiki, int forks_count, int open_issues_count, int forks, int open_issues, int watchers,
			String default_branch, RepositoryPermission permissions, RepositoryOrganization organization,
			int network_count, int subscribers_count) {
		this.id = id;
		this.name = name;
		this.full_name = full_name;
		this.owner = owner;
		this.isPrivate = isPrivate;
		this.html_url = html_url;
		this.description = description;
		this.fork = fork;
		this.url = url;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.pushed_at = pushed_at;
		this.git_url = git_url;
		this.ssh_url = ssh_url;
		this.svn_url = svn_url;
		this.homepage = homepage;
		this.size = size;
		this.stargazers_count = stargazers_count;
		this.watchers_count = watchers_count;
		this.language = language;
		this.has_issues = has_issues;
		this.has_downloads = has_downloads;
		this.has_wiki = has_wiki;
		this.forks_count = forks_count;
		this.open_issues_count = open_issues_count;
		this.forks = forks;
		this.open_issues = open_issues;
		this.watchers = watchers;
		this.default_branch = default_branch;
		this.permissions = permissions;
		this.organization = organization;
		this.network_count = network_count;
		this.subscribers_count = subscribers_count;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFull_name() {
		return full_name;
	}

	public RepositoryOwner getOwner() {
		return owner;
	}
	
	public boolean isPrivate() {
		return isPrivate;
	}
	
	public static class HyberOwner implements RepositoryOwner {
		/*
		 * "owner":
		 * {"login":"rubinius",
		 * "id":317747,
		 * "avatar_url":"https://avatars.githubusercontent.com/u/317747?v\u003d3",
		 * "gravatar_id":"",
		 * "url":"https://api.github.com/users/rubinius",
		 * "html_url":"https://github.com/rubinius",
		 * "followers_url":"https://api.github.com/users/rubinius/followers",
		 * "following_url":"https://api.github.com/users/rubinius/following{/other_user}",
		 * "gists_url":"https://api.github.com/users/rubinius/gists{/gist_id}",
		 * "starred_url":"https://api.github.com/users/rubinius/starred{/owner}{/repo}",
		 * "subscriptions_url":"https://api.github.com/users/rubinius/subscriptions",
		 * "organizations_url":"https://api.github.com/users/rubinius/orgs",
		 * "repos_url":"https://api.github.com/users/rubinius/repos",
		 * "events_url":"https://api.github.com/users/rubinius/events{/privacy}",
		 * "received_events_url":"https://api.github.com/users/rubinius/received_events",
		 * "type":"Organization",
		 * "site_admin":false}
		 */
		private String login;
		private int id;
		private String avatar_url;
		private String gravatar_id;
		private String url;
		private String html_url;
		
		
		
		public HyberOwner(String login, int id, String avatar_url, String gravatar_id, String url, String html_url) {
			super();
			this.login = login;
			this.id = id;
			this.avatar_url = avatar_url;
			this.gravatar_id = gravatar_id;
			this.url = url;
			this.html_url = html_url;
		}

		public String getLogin() {
			return login;
		}

		public int getId() {
			return id;
		}

		public String getAvatar_url() {
			return avatar_url;
		}

		public String getGravatar_id() {
			return gravatar_id;
		}

		public String getUrl() {
			return url;
		}

		public String getHtml_url() {
			return html_url;
		}

		public String getFollowers_url() {
			return null;
		}

		public String getFollowing_url() {
			return null;
		}

		public String getGists_url() {
			return null;
		}

		public String getStarred_url() {
			return null;
		}

		public String getSubscriptions_url() {
			return null;
		}

		public String getOrganizations_url() {
			return null;
		}

		public String getRepos_url() {
			return null;
		}

		public String getEvents_url() {
			return null;
		}

		public String getReceived_events_url() {
			return null;
		}

		public String getType() {
			return null;
		}

		public boolean isSite_admin() {
			return false;
		}
	}
	
	public static class HyberPermission implements RepositoryPermission {
		/*"permissions":{
		 * 				"admin":false,
		 * 				"push":false,
		 * 				"pull":true
		 * 				},
		 */
		
		private boolean admin;
		private boolean push;
		private boolean pull;
		
		
		
		public HyberPermission(boolean admin, boolean push, boolean pull) {
			this.admin = admin;
			this.push = push;
			this.pull = pull;
		}
		public boolean isAdmin() {
			return admin;
		}
		public boolean isPush() {
			return push;
		}
		public boolean isPull() {
			return pull;
		}
	}
	
	public static class HyberOrganization implements RepositoryOrganization {
		private String login;
		private int id;
		private String avatar_url;
		private String gravatar_id;
		private String url;
		private String html_url;
		
		
		public HyberOrganization(String login, int id, String avatar_url, String gravatar_id, String url, String html_url) {
			this.login = login;
			this.id = id;
			this.avatar_url = avatar_url;
			this.gravatar_id = gravatar_id;
			this.url = url;
			this.html_url = html_url;
		}
		public String getLogin() {
			return login;
		}
		public int getId() {
			return id;
		}
		public String getAvatar_url() {
			return avatar_url;
		}
		public String getGravatar_id() {
			return gravatar_id;
		}
		public String getUrl() {
			return url;
		}
		public String getHtml_url() {
			return html_url;
		}
		public String getFollowers_url() {
			return null;
		}
		public String getFollowing_url() {
			return null;
		}
		public String getGist_url() {
			return null;
		}
		public String getStarred_url() {
			return null;
		}
		public String getSubscriprions_url() {
			return null;
		}
		public String getOrganizations_url() {
			return null;
		}
		public String getRepos_url() {
			return null;
		}
		public String getEvents_url() {
			return null;
		}
		public String getReceived_events_url() {
			return null;
		}
		public String getType() {
			return null;
		}
		public boolean isSite_admin() {
			return false;
		}
	}


	public String getHtml_url() {
		return html_url;
	}

	public String getDescription() {
		return description;
	}

	public boolean isFork() {
		return fork;
	}

	public String getUrl() {
		return url;
	}

	public String getFork_url() {
		return null;
	}

	public String getKeys_url() {
		return null;
	}

	public String getCollaborators_url() {
		return null;
	}

	public String getTeams_url() {
		return null;
	}

	public String getHooks_url() {
		return null;
	}

	public String getIssue_events_url() {
		return null;
	}

	public String getEvents_url() {
		return null;
	}

	public String getAssignees_url() {
		return null;
	}

	public String getBranches_url() {
		return null;
	}

	public String getTags_url() {
		return null;
	}

	public String getBlobs_url() {
		return null;
	}

	public String getGit_tags_url() {
		return null;
	}

	public String getGit_refs_url() {
		return null;
	}

	public String getTree_url() {
		return null;
	}

	public String getStatuses_url() {
		return null;
	}

	public String getLanguages_url() {
		return null;
	}

	public String getStargazers_url() {
		return null;
	}

	public String getContributors_url() {
		return null;
	}

	public String getSubscibers_url() {
		return null;
	}

	public String getSubscription_url() {
		return null;
	}

	public String getCommits_url() {
		return null;
	}

	public String getGit_commits_url() {
		return null;
	}

	public String getComments_url() {
		return null;
	}

	public String getIssue_comment_url() {
		return null;
	}

	public String getContents_url() {
		return null;
	}

	public String getCompare_url() {
		return null;
	}

	public String getMerges_url() {
		return null;
	}

	public String getArchive_url() {
		return null;
	}

	public String getDownloads_url() {
		return null;
	}

	public String getIssues_url() {
		return null;
	}

	public String getPulls_url() {
		return null;
	}

	public String getMilestones_url() {
		return null;
	}

	public String getNotifications_url() {
		return null;
	}

	public String getLabels_url() {
		return null;
	}

	public String getReleases_url() {
		return null;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public String getPushed_at() {
		return pushed_at;
	}

	public String getGit_url() {
		return git_url;
	}

	public String getSsh_url() {
		return ssh_url;
	}

	public String getClone_url() {
		return null;
	}

	public String getSvn_url() {
		return svn_url;
	}

	public String getHomepage() {
		return homepage;
	}

	public int getSize() {
		return size;
	}

	public int getStargazers_count() {
		return stargazers_count;
	}

	public int getWatchers_count() {
		return watchers_count;
	}

	public String getLanguage() {
		return language;
	}

	public boolean isHas_issues() {
		return has_issues;
	}

	public boolean isHas_downloads() {
		return has_downloads;
	}

	public boolean isHas_wiki() {
		return has_wiki;
	}

	public boolean isHas_pages() {
		return false;
	}

	public int getForks_count() {
		return forks_count;
	}

	public int getOpen_issues_count() {
		return open_issues_count;
	}

	public int getForks() {
		return forks;
	}

	public int getOpen_issues() {
		return open_issues;
	}

	public int getWatchers() {
		return watchers;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public RepositoryPermission getPermissions() {
		return permissions;
	}

	public RepositoryOrganization getOrganization() {
		return organization;
	}

	public int getNetwork_count() {
		return network_count;
	}

	public int getSubscribers_count() {
		return subscribers_count;
	}

	@Override
	public Language getMainLanguage() {
		return Language.getLanguage(this.getLanguage());
	}

	@Override
	public Category[] getCategories() {
		return Category.getCategories(this);
	}

	@Override
	public boolean checkValidity() {
		boolean fullNameValid = (full_name!=null) && (full_name.split("/").length==2);
		boolean nameValid = (name!=null) && (!name.equals(""));
		boolean idValid = id > -1;
		boolean starsValid = stargazers_count > -1;
		boolean forksValid = forks_count > -1;
		boolean createdAtValid = (created_at!=null) && 
				(created_at.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		boolean updatedAtValid = (updated_at!=null) && 
				(updated_at.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		
		return fullNameValid && nameValid && idValid && starsValid && forksValid
				&& createdAtValid && updatedAtValid;
	}

}
