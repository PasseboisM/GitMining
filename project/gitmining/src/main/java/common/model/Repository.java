package common.model;

import com.google.gson.Gson;

/**
 * 
 * @author River
 *
 * Repository详情数据模型，采用JavaBeans规范
 * @TODO 里面的数据项还未全部填好
 */
public class Repository {
	
//	private Obj_id _id;
	private int id;
	private String full_name;
	private String name;
	private Owner owner;
	/*
	 * 未完待续
	 */
	
	
//	public Obj_id get_id() {
//		return _id;
//	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getFull_name() {
		return full_name;
	}
	public Owner getOwner() {
		return owner;
	}
//	class Obj_id {
//		private int timestamp;
//		private int machineIdentifier;
//		private int processIdentifier;
//		private int counter;
//		public int getTimestamp() {
//			return timestamp;
//		}
//		public int getMachineIdentifier() {
//			return machineIdentifier;
//		}
//		public int getProcessIdentifier() {
//			return processIdentifier;
//		}
//		public int getCounter() {
//			return counter;
//		}	
//	}
	
	class Owner {
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
		public String getType() {
			return type;
		}
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
		Repository re = gson.fromJson(s, Repository.class);
		
		System.out.println("id:" + re.getId());
		System.out.println("n:" + re.getName());
		System.out.println("fn:" + re.getFull_name());
		
		
		System.out.println(gson.toJson(re));
	}
	
	
}
