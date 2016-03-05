package common.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import common.service.Repository;
import common.service.RepositoryMin;
import common.service.RepositoryOwner;

/**
 * 
 * @author River
 *
 * Repository详情数据模型，采用JavaBeans规范
 * TODO 里面的数据项还未全部填好
 */
public class RepositoryBeans implements Repository {
	
	
	/*
	 * {
	 * "id":27,
	 * "name":"rubinius",
	 * "full_name":"rubinius/rubinius",
	 * "owner":{
	 * 			"login":"rubinius",
	 * 			"id":317747,
	 * 			"avatar_url":"https://avatars.githubusercontent.com/u/317747?v\u003d3",
	 * 			"gravatar_id":"",
	 * 			"url":"https://api.github.com/users/rubinius",
	 * 			"html_url":"https://github.com/rubinius",
	 * 			"followers_url":"https://api.github.com/users/rubinius/followers",
	 * 			"following_url":"https://api.github.com/users/rubinius/following{/other_user}",
	 * 			"gists_url":"https://api.github.com/users/rubinius/gists{/gist_id}",
	 * 			"starred_url":"https://api.github.com/users/rubinius/starred{/owner}{/repo}",
	 * 			"subscriptions_url":"https://api.github.com/users/rubinius/subscriptions",
	 * 			"organizations_url":"https://api.github.com/users/rubinius/orgs",
	 * 			"repos_url":"https://api.github.com/users/rubinius/repos",
	 * 			"events_url":"https://api.github.com/users/rubinius/events{/privacy}",
	 * 			"received_events_url":"https://api.github.com/users/rubinius/received_events",
	 * 			"type":"Organization",
	 * 			"site_admin":false
	 * 			},
	 * "private":false,
	 * "html_url":"https://github.com/rubinius/rubinius",
	 * "description":"The Rubinius Language Platform",
	 * "fork":false,
	 * "url":"https://api.github.com/repos/rubinius/rubinius",
	 * "forks_url":"https://api.github.com/repos/rubinius/rubinius/forks",
	 * "keys_url":"https://api.github.com/repos/rubinius/rubinius/keys{/key_id}",
	 * "collaborators_url":"https://api.github.com/repos/rubinius/rubinius/collaborators{/collaborator}",
	 * "teams_url":"https://api.github.com/repos/rubinius/rubinius/teams",
	 * "hooks_url":"https://api.github.com/repos/rubinius/rubinius/hooks",
	 * "issue_events_url":"https://api.github.com/repos/rubinius/rubinius/issues/events{/number}",
	 * "events_url":"https://api.github.com/repos/rubinius/rubinius/events",
	 * "assignees_url":"https://api.github.com/repos/rubinius/rubinius/assignees{/user}",
	 * "branches_url":"https://api.github.com/repos/rubinius/rubinius/branches{/branch}",
	 * "tags_url":"https://api.github.com/repos/rubinius/rubinius/tags",
	 * "blobs_url":"https://api.github.com/repos/rubinius/rubinius/git/blobs{/sha}",
	 * "git_tags_url":"https://api.github.com/repos/rubinius/rubinius/git/tags{/sha}",
	 * "git_refs_url":"https://api.github.com/repos/rubinius/rubinius/git/refs{/sha}",
	 * "trees_url":"https://api.github.com/repos/rubinius/rubinius/git/trees{/sha}",
	 * "statuses_url":"https://api.github.com/repos/rubinius/rubinius/statuses/{sha}",
	 * "languages_url":"https://api.github.com/repos/rubinius/rubinius/languages",
	 * "stargazers_url":"https://api.github.com/repos/rubinius/rubinius/stargazers",
	 * "contributors_url":"https://api.github.com/repos/rubinius/rubinius/contributors",
	 * "subscribers_url":"https://api.github.com/repos/rubinius/rubinius/subscribers",
	 * "subscription_url":"https://api.github.com/repos/rubinius/rubinius/subscription",
	 * "commits_url":"https://api.github.com/repos/rubinius/rubinius/commits{/sha}",
	 * "git_commits_url":"https://api.github.com/repos/rubinius/rubinius/git/commits{/sha}",
	 * "comments_url":"https://api.github.com/repos/rubinius/rubinius/comments{/number}",
	 * "issue_comment_url":"https://api.github.com/repos/rubinius/rubinius/issues/comments{/number}",
	 * "contents_url":"https://api.github.com/repos/rubinius/rubinius/contents/{+path}",
	 * "compare_url":"https://api.github.com/repos/rubinius/rubinius/compare/{base}...{head}",
	 * "merges_url":"https://api.github.com/repos/rubinius/rubinius/merges",
	 * "archive_url":"https://api.github.com/repos/rubinius/rubinius/{archive_format}{/ref}",
	 * "downloads_url":"https://api.github.com/repos/rubinius/rubinius/downloads",
	 * "issues_url":"https://api.github.com/repos/rubinius/rubinius/issues{/number}",
	 * "pulls_url":"https://api.github.com/repos/rubinius/rubinius/pulls{/number}",
	 * "milestones_url":"https://api.github.com/repos/rubinius/rubinius/milestones{/number}",
	 * "notifications_url":"https://api.github.com/repos/rubinius/rubinius/notifications{?since,all,participating}",
	 * "labels_url":"https://api.github.com/repos/rubinius/rubinius/labels{/name}",
	 * "releases_url":"https://api.github.com/repos/rubinius/rubinius/releases{/id}",
	 * "created_at":"2008-01-12T16:46:52Z",
	 * "updated_at":"2015-11-29T08:06:23Z",
	 * "pushed_at":"2015-11-30T19:17:09Z",
	 * "git_url":"git://github.com/rubinius/rubinius.git",
	 * "ssh_url":"git@github.com:rubinius/rubinius.git",
	 * "clone_url":"https://github.com/rubinius/rubinius.git",
	 * "svn_url":"https://github.com/rubinius/rubinius",
	 * "homepage":"http://rubini.us",
	 * "size":176039,
	 * "stargazers_count":2520,
	 * "watchers_count":2520,
	 * "language":"Ruby",
	 * "has_issues":true,
	 * "has_downloads":true,
	 * "has_wiki":false,
	 * "has_pages":false,
	 * "forks_count":588,
	 * "open_issues_count":210,
	 * "forks":588,
	 * "open_issues":210,
	 * "watchers":2520,
	 * "default_branch":"master",
	 * "permissions":{
	 * 				"admin":false,
	 * 				"push":false,
	 * 				"pull":true
	 * 				},
	 * "organization":{
	 * 				"login":"rubinius",
	 * 				"id":317747,
	 * 				"avatar_url":"https://avatars.githubusercontent.com/u/317747?v\u003d3",
	 * 				"gravatar_id":"",
	 * 				"url":"https://api.github.com/users/rubinius",
	 * 				"html_url":"https://github.com/rubinius",
	 * 				"followers_url":"https://api.github.com/users/rubinius/followers",
	 * 				"following_url":"https://api.github.com/users/rubinius/following{/other_user}",
	 * 				"gists_url":"https://api.github.com/users/rubinius/gists{/gist_id}",
	 * 				"starred_url":"https://api.github.com/users/rubinius/starred{/owner}{/repo}",
	 * 				"subscriptions_url":"https://api.github.com/users/rubinius/subscriptions",
	 * 				"organizations_url":"https://api.github.com/users/rubinius/orgs",
	 * 				"repos_url":"https://api.github.com/users/rubinius/repos",
	 * 				"events_url":"https://api.github.com/users/rubinius/events{/privacy}",
	 * 				"received_events_url":"https://api.github.com/users/rubinius/received_events",
	 * 				"type":"Organization",
	 * 				"site_admin":false
	 * 				},
	 * "network_count":588,
	 * "subscribers_count":130
	 * }
	 */
	private int id;
	private String name;
	private String full_name;
	private RepositoryOwner owner;
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
//	private Permissions permissions;
//	private Organization organization;
	private int network_count;
	private int subscribers_count;
	
	/*
	 * 未完待续
	 */
	

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
	
	
	class OwnerBeans implements RepositoryOwner {
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
		
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getLogin()
		 */
		public String getLogin() {
			return login;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getId()
		 */
		public int getId() {
			return id;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getAvatar_url()
		 */
		public String getAvatar_url() {
			return avatar_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getGravatar_id()
		 */
		public String getGravatar_id() {
			return gravatar_id;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getUrl()
		 */
		public String getUrl() {
			return url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getHtml_url()
		 */
		public String getHtml_url() {
			return html_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getFollowers_url()
		 */
		public String getFollowers_url() {
			return followers_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getFollowing_url()
		 */
		public String getFollowing_url() {
			return following_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getGists_url()
		 */
		public String getGists_url() {
			return gists_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getStarred_url()
		 */
		public String getStarred_url() {
			return starred_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getSubscriptions_url()
		 */
		public String getSubscriptions_url() {
			return subscriptions_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getOrganizations_url()
		 */
		public String getOrganizations_url() {
			return organizations_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getRepos_url()
		 */
		public String getRepos_url() {
			return repos_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getEvents_url()
		 */
		public String getEvents_url() {
			return events_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getReceived_events_url()
		 */
		public String getReceived_events_url() {
			return received_events_url;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#getType()
		 */
		public String getType() {
			return type;
		}
		/* (non-Javadoc)
		 * @see common.model.RepositoryOwner#isSite_admin()
		 */
		public boolean isSite_admin() {
			return site_admin;
		}
	}
	
	public static void main(String[] args) {
		String s = "{"
				+ "\"id\":27,\"name\":\"rubinius\",\"full_name\":\"rubinius/rubinius\""
				+ ",\"owner\":{\"login\":\"rubinius\",\"id\":317747,\"avatar_url\":\"https://avatars.githubuser"
				+ "content.com/u/317747?v\u003d3\",\"gravatar_id\":\"\",\"url\":\"http"
				+ "s://api.github.com/users/rubinius\",\"html_url\":\"https://github.com/rubinius\","
				+ "\"followers_url\":\"https://api.github.com/users/rubinius/followers\",\"following_url\":"
				+ "\"https://api.github.com/users/rubinius/following{/other_user}\",\"gists_url\":"
				+ "\"https://api.github.com/users/rubinius/gists{/gist_id}\",\"starred_url\":"
				+ "\"https://api.github.com/users/rubinius/starred{/owner}{/repo}\","
				+ "\"subscriptions_url\":\"https://api.github.com/users/rubinius/subscriptions\","
				+ "\"organizations_url\":\"https://api.github.com/users/rubinius/orgs\","
				+ "\"repos_url\":\"https://api.github.com/users/rubinius/repos\","
				+ "\"events_url\":\"https://api.github.com/users/rubinius/events{/privacy}\","
				+ "\"received_events_url\":\"https://api.github.com/users/rubinius/received_events\""
				+ ",\"type\":\"Organization\",\"site_admin\":false}"
				+ "}";
		
		Gson gson = new Gson();
		RepositoryMin re = gson.fromJson(s, RepositoryBeans.class);
		
		System.out.println("id:" + re.getId());
		System.out.println("n:" + re.getName());
		System.out.println("fn:" + re.getFull_name());
		
		
		System.out.println(gson.toJson(re));
	}
	public int getStars() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getForkNum() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
