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
	
	private int id;
	private String full_name;
	private String name;
	private RepositoryOwner owner;
	@SerializedName("private") private boolean isPrivate;
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
