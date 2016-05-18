package common.model;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import com.google.gson.annotations.SerializedName;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.service.Repository;
import common.service.RepositoryOrganization;
import common.service.RepositoryOwner;
import common.service.RepositoryPermission;

public class HyberRepository implements Repository{

	
	
	
	private RepositoryOwner owner;
	private RepositoryPermission permissions;
	private RepositoryOrganization organization;
	
	
	private int id;
	private String name;
	private String full_name;
	@SerializedName("private") private boolean isPrivate;
	private String html_url;
	private String description;
	private boolean fork;
	private String url;
	private String fork_url;
	private String keys_url;
	private String collaborators_url;
	private String teams_url;
	private String hooks_url;
	private String issue_events_url;
	private String events_url;
	private String assignees_url;
	private String branches_url;
	private String tags_url;
	private String blobs_url;
	private String git_tags_url;
	private String git_refs_url;
	private String tree_url;
	private String statuses_url;
	private String languages_url;
	private String stargazers_url;
	private String contributors_url;
	private String subscibers_url;
	private String subscription_url;
	private String commits_url;
	private String git_commits_url;
	private String comments_url;
	private String issue_comment_url;
	private String contents_url;
	private String compare_url;
	private String merges_url;
	private String archive_url;
	private String downloads_url;
	private String issues_url;
	private String pulls_url;
	private String milestones_url;
	private String notifications_url;
	private String labels_url;
	private String releases_url;
	private String created_at;
	private String updated_at;
	private String pushed_at;
	private String git_url;
	private String ssh_url;
	private String clone_url;
	private String svn_url;
	private String homepage;
	private int size;
	private int stargazers_count;
	private int watchers_count;
	private String language;
	private boolean has_issues;
	private boolean has_downloads;
	private boolean has_wiki;
	private boolean has_pages;
	private int forks_count;
	private int open_issues_count;
	private int forks;
	private int open_issues;
	private int watchers;
	private String default_branch;
	private int network_count;
	private int subscribers_count;
	


	public HyberRepository(GHRepository ghRepository) {
		GHUser ghOwner = null;
		try {
			ghOwner = ghRepository.getOwner();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.owner = new HyberOwner(ghOwner);
		boolean admin = ghRepository.hasAdminAccess();
		boolean push = ghRepository.hasPushAccess();
		boolean pull = ghRepository.hasPullAccess();
		this.permissions = new HyberPermission(admin, push, pull);
		this.organization = new HyberOrganization(ghOwner);
		
		
		id =  ghRepository.getId();
		name =  ghRepository.getName();
		full_name = ghRepository.getFullName();
		isPrivate =ghRepository.isPrivate();
		html_url=ghRepository.getHtmlUrl().toString();
		description=ghRepository.getDescription();
		fork=ghRepository.isFork();
		url=ghRepository.getUrl().toString();
		try {
			created_at=ghRepository.getCreatedAt().toString();
			updated_at=ghRepository.getUpdatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			pushed_at=ghRepository.getPushedAt().toString();
		} catch (NullPointerException e) {
			pushed_at=null;
		}
		git_url=ghRepository.getGitTransportUrl();
		ssh_url=ghRepository.getSshUrl();
		svn_url=ghRepository.getSvnUrl();
		homepage=ghRepository.getHomepage();
		size=ghRepository.getSize();
		stargazers_count=ghRepository.getWatchers();
		watchers_count=ghRepository.getWatchers();
		language=ghRepository.getLanguage();
		has_issues=ghRepository.hasIssues();
		has_downloads= ghRepository.hasDownloads();
		has_wiki=ghRepository.hasWiki();
		has_pages=false;
		forks_count=ghRepository.getForks();
		open_issues_count=ghRepository.getOpenIssueCount();
		forks=ghRepository.getForks();
		open_issues=ghRepository.getOpenIssueCount();
		watchers= ghRepository.getWatchers();
		default_branch=ghRepository.getDefaultBranch();
		network_count=ghRepository.getNetworkCount();
		subscribers_count=ghRepository.getSubscribersCount();
	}

	
	
	class HyberOwner implements RepositoryOwner {
		
		
		private String login;
		private int id;
		private String avatar_url;
		private String gravatar_id;
		private String url;
		private String html_url;
		private String followers_url;
		private String following_url;
		private String gists_url;
		private String starred_url;
		private String subscriptions_url;
		private String organizations_url;
		private String repos_url;
		private String events_url;
		private String received_events_url;
		private String type;
		private boolean site_admin;
		
		@SuppressWarnings("deprecation")
		public HyberOwner(GHUser ghOwner) {
			login=ghOwner.getLogin();
			id=ghOwner.getId();
			avatar_url=ghOwner.getAvatarUrl();
			gravatar_id=ghOwner.getGravatarId();
			url=ghOwner.getUrl().toString();
			html_url=ghOwner.getHtmlUrl().toString();
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
			return followers_url;
		}
		public String getFollowing_url() {
			return following_url;
		}
		public String getGists_url() {
			return gists_url;
		}
		public String getStarred_url() {
			return starred_url;
		}
		public String getSubscriptions_url() {
			return subscriptions_url;
		}
		public String getOrganizations_url() {
			return organizations_url;
		}
		public String getRepos_url() {
			return repos_url;
		}
		public String getEvents_url() {
			return events_url;
		}
		public String getReceived_events_url() {
			return received_events_url;
		}
		public String getType() {
			return type;
		}
		public boolean isSite_admin() {
			return site_admin;
		}

	}
	
	class HyberPermission implements RepositoryPermission {
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
	
	class HyberOrganization implements RepositoryOrganization {

		
		private String login;
		private int id;
		private String avatar_url;
		private String gravatar_id;
		private String url;
		private String html_url;
		private String followers_url;
		private String following_url;
		private String gist_url;
		private String starred_url;
		private String subscriprions_url;
		private String organizations_url;
		private String repos_url;
		private String events_url;
		private String received_events_url;
		private String type;
		private boolean site_admin;
		
		@SuppressWarnings("deprecation")
		public HyberOrganization(GHUser ghOwner) {
			login=ghOwner.getLogin();
			id=ghOwner.getId();
			avatar_url=ghOwner.getAvatarUrl();
			gravatar_id=ghOwner.getGravatarId();
			url=ghOwner.getUrl().toString();
			html_url=ghOwner.getHtmlUrl().toString();
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
			return followers_url;
		}
		public String getFollowing_url() {
			return following_url;
		}
		public String getGist_url() {
			return gist_url;
		}
		public String getStarred_url() {
			return starred_url;
		}
		public String getSubscriprions_url() {
			return subscriprions_url;
		}
		public String getOrganizations_url() {
			return organizations_url;
		}
		public String getRepos_url() {
			return repos_url;
		}
		public String getEvents_url() {
			return events_url;
		}
		public String getReceived_events_url() {
			return received_events_url;
		}
		public String getType() {
			return type;
		}
		public boolean isSite_admin() {
			return site_admin;
		}
		
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
		return fork_url;
	}

	public String getKeys_url() {
		return keys_url;
	}

	public String getCollaborators_url() {
		return collaborators_url;
	}

	public String getTeams_url() {
		return teams_url;
	}

	public String getHooks_url() {
		return hooks_url;
	}

	public String getIssue_events_url() {
		return issue_events_url;
	}

	public String getEvents_url() {
		return events_url;
	}

	public String getAssignees_url() {
		return assignees_url;
	}

	public String getBranches_url() {
		return branches_url;
	}

	public String getTags_url() {
		return tags_url;
	}

	public String getBlobs_url() {
		return blobs_url;
	}

	public String getGit_tags_url() {
		return git_tags_url;
	}

	public String getGit_refs_url() {
		return git_refs_url;
	}

	public String getTree_url() {
		return tree_url;
	}

	public String getStatuses_url() {
		return statuses_url;
	}

	public String getLanguages_url() {
		return languages_url;
	}

	public String getStargazers_url() {
		return stargazers_url;
	}

	public String getContributors_url() {
		return contributors_url;
	}

	public String getSubscibers_url() {
		return subscibers_url;
	}

	public String getSubscription_url() {
		return subscription_url;
	}

	public String getCommits_url() {
		return commits_url;
	}

	public String getGit_commits_url() {
		return git_commits_url;
	}

	public String getComments_url() {
		return comments_url;
	}

	public String getIssue_comment_url() {
		return issue_comment_url;
	}

	public String getContents_url() {
		return contents_url;
	}

	public String getCompare_url() {
		return compare_url;
	}

	public String getMerges_url() {
		return merges_url;
	}

	public String getArchive_url() {
		return archive_url;
	}

	public String getDownloads_url() {
		return downloads_url;
	}

	public String getIssues_url() {
		return issues_url;
	}

	public String getPulls_url() {
		return pulls_url;
	}

	public String getMilestones_url() {
		return milestones_url;
	}

	public String getNotifications_url() {
		return notifications_url;
	}

	public String getLabels_url() {
		return labels_url;
	}

	public String getReleases_url() {
		return releases_url;
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
		return clone_url;
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
		return has_pages;
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
		boolean fullNameValid = (getFull_name()!=null) && (getFull_name().split("/").length==2);
		boolean nameValid = (getName()!=null) && (!getName().equals(""));
		boolean idValid = getId() > -1;
		boolean starsValid = getStargazers_count() > -1;
		boolean forksValid = getForks_count() > -1;
		boolean createdAtValid = (getCreated_at()!=null) && 
				(getCreated_at().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		boolean updatedAtValid = (getUpdated_at()!=null) && 
				(getUpdated_at().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		
		return fullNameValid && nameValid && idValid && starsValid && forksValid
				&& createdAtValid && updatedAtValid;
	}

}
