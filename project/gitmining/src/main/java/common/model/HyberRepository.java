package common.model;

import java.io.IOException;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.service.Repository;
import common.service.RepositoryOrganization;
import common.service.RepositoryOwner;
import common.service.RepositoryPermission;

public class HyberRepository implements Repository{

	
	
	private GHRepository ghRepository;
	private GHUser ghOwner;
	
	private RepositoryOwner owner;
	private RepositoryPermission permission;
	private RepositoryOrganization organization;
	


	public HyberRepository(GHRepository ghRepository) {
		this.ghRepository = ghRepository;
		try {
			this.ghOwner = ghRepository.getOwner();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.owner = new HyberOwner(ghOwner);
		boolean admin = ghRepository.hasAdminAccess();
		boolean push = ghRepository.hasPushAccess();
		boolean pull = ghRepository.hasPullAccess();
		this.permission = new HyberPermission(admin, push, pull);
		this.organization = new HyberOrganization(ghOwner);
	}

	public int getId() {
		return ghRepository.getId();
	}

	public String getName() {
		return ghRepository.getName();
	}

	public String getFull_name() {
		return ghRepository.getFullName();
	}

	public RepositoryOwner getOwner() {
		return owner;
	}
	
	public boolean isPrivate() {
		return ghRepository.isPrivate();
	}
	
	class HyberOwner implements RepositoryOwner {
		
		
		private GHUser ghOwner;
		
		public HyberOwner(GHUser ghOwner) {
			this.ghOwner = ghOwner;
		}

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
		
		public String getLogin() {
			return this.ghOwner.getLogin();
		}

		public int getId() {
			return this.ghOwner.getId();
		}

		public String getAvatar_url() {
			return this.ghOwner.getAvatarUrl();
		}

		@SuppressWarnings("deprecation")
		public String getGravatar_id() {
			return this.ghOwner.getGravatarId();
		}

		public String getUrl() {
			return this.ghOwner.getUrl().toString();
		}

		public String getHtml_url() {
			return this.ghOwner.getHtmlUrl().toString();
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

		private GHUser ghOwner;
		
		public HyberOrganization(GHUser ghOwner) {
			this.ghOwner = ghOwner;
		}
		public String getLogin() {
			return this.ghOwner.getLogin();
		}

		public int getId() {
			return this.ghOwner.getId();
		}

		public String getAvatar_url() {
			return this.ghOwner.getAvatarUrl();
		}

		@SuppressWarnings("deprecation")
		public String getGravatar_id() {
			return this.ghOwner.getGravatarId();
		}

		public String getUrl() {
			return this.ghOwner.getUrl().toString();
		}

		public String getHtml_url() {
			return this.ghOwner.getHtmlUrl().toString();
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
		return ghRepository.getHtmlUrl().toString();
	}

	public String getDescription() {
		return ghRepository.getDescription();
	}

	public boolean isFork() {
		return ghRepository.isFork();
	}

	public String getUrl() {
		return ghRepository.getUrl().toString();
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
		try {
			return ghRepository.getCreatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUpdated_at() {
		try {
			return ghRepository.getUpdatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getPushed_at() {
		return ghRepository.getPushedAt().toString();
	}

	public String getGit_url() {
		return ghRepository.getGitTransportUrl();
	}

	public String getSsh_url() {
		return ghRepository.getSshUrl();
	}

	public String getClone_url() {
		return null;
	}

	public String getSvn_url() {
		return ghRepository.getSvnUrl();
	}

	public String getHomepage() {
		return ghRepository.getHomepage();
	}

	public int getSize() {
		return ghRepository.getSize();
	}

	public int getStargazers_count() {
		return ghRepository.getWatchers();
	}

	public int getWatchers_count() {
		return ghRepository.getWatchers();
	}

	public String getLanguage() {
		return ghRepository.getLanguage();
	}

	public boolean isHas_issues() {
		return ghRepository.hasIssues();
	}

	public boolean isHas_downloads() {
		return ghRepository.hasDownloads();
	}

	public boolean isHas_wiki() {
		return ghRepository.hasWiki();
	}

	public boolean isHas_pages() {
		return false;
	}

	public int getForks_count() {
		return ghRepository.getForks();
	}

	public int getOpen_issues_count() {
		return ghRepository.getOpenIssueCount();
	}

	public int getForks() {
		return ghRepository.getForks();
	}

	public int getOpen_issues() {
		return ghRepository.getOpenIssueCount();
	}

	public int getWatchers() {
		return ghRepository.getWatchers();
	}

	public String getDefault_branch() {
		return ghRepository.getDefaultBranch();
	}

	public RepositoryPermission getPermissions() {
		return permission;
	}

	public RepositoryOrganization getOrganization() {
		return organization;
	}

	public int getNetwork_count() {
		return ghRepository.getNetworkCount();
	}

	public int getSubscribers_count() {
		return ghRepository.getSubscribersCount();
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
