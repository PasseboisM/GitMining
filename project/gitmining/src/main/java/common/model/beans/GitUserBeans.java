package common.model.beans;

import com.google.gson.Gson;

import common.service.GitUser;

/**
 * 
 * @author wza14
 *
 * GitUser的详细数据实现对象，按照JavaBeans规范建立
 * 
 */
public class GitUserBeans implements GitUser {
	/*
	 * {
	 * "login":"rubinius",
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
	 * "site_admin":false,
	 * "name":"Rubinius",
	 * "blog":"http://rubini.us",
	 * "location":"Everywhere",
	 * "email":"community@rubini.us",
	 * "bio":"Solve Hard Problems™",
	 * "public_repos":60,
	 * "public_gists":0,
	 * "followers":0,
	 * "following":0,
	 * "created_at":"2010-06-29T18:39:32Z",
	 * "updated_at":"2015-10-03T23:07:15Z"
	 * }
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
	private String name;
	private String blog;
	private String location;
	private String email;
	private String bio;
	private int public_repos;
	private int public_gists;
	private int followers;
	private int following;
	private String created_at;
	private String updated_at;
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
	public String getName() {
		return name;
	}
	public String getBlog() {
		return blog;
	}
	public String getLocation() {
		return location;
	}
	public String getEmail() {
		return email;
	}
	public String getBio() {
		return bio;
	}
	public int getPublic_repos() {
		return public_repos;
	}
	public int getPublic_gists() {
		return public_gists;
	}
	public int getFollowers() {
		return followers;
	}
	public int getFollowing() {
		return following;
	}
	public String getCreated_at() {
		return created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	@Override
	public boolean checkValidity() {
		
		boolean loginValid = (login!=null) && (!login.equals(""));
		boolean nameValid = (name!=null) && (!name.equals(""));
		boolean followersValid = followers > -1;
		boolean idValid = id > -1;
		boolean createdAtValid = (created_at!=null) && 
				(created_at.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		boolean typeValid = (type!=null) && (type.equals("User")||type.equals("Organization"));
		
		return loginValid 
				&& nameValid
				&& followersValid 
				&& idValid 
				&& createdAtValid
				&& typeValid;
	}
	@Override
	public String toString() {
		return "GitUserBeans [login=" + login + ", id=" + id + ", name=" + name
				+ ", followers=" + followers + ", created_at=" + created_at
				+ "]";
	}
	
//	public static void main(String[] args) {
//		String s = "{\"login\":\"kaze\",\"id\":53155,\"avatar_url\":\"htt"
//				+ "ps://avatars.githubusercontent.com/u/53155?v\u003d3\",\"gr"
//				+ "avatar_id\":\"\",\"url\":\"https://api.git"
//				+ "hub.com/users/kaze\",\"html_url\":\"https://git"
//				+ "hub.com/kaze\",\"followers_url\":\"https://api.github"
//				+ ".com/users/kaze/followers\",\"following_url\":\"https://api.gith"
//				+ "ub.com/users/kaze/following{/other_user}\",\"gists_url\":\"https://ap"
//				+ "i.github.com/users/kaze/gists{/gist_id}\",\"starred_url\":\"ht"
//				+ "tps://api.github.com/users/kaze/starred{/owner}{/repo}\",\"subscrip"
//				+ "tions_url\":\"https://api.github.com/users/kaz"
//				+ "e/subscriptions\",\"organizations_url\":\"https://api.githu"
//				+ "b.com/users/kaze/orgs\",\"repos_url\":\"https://api.github"
//				+ ".com/users/kaze/repos\",\"events_url\":\"https://api.git"
//				+ "hub.com/users/kaze/events{/privacy}\",\"received_ev"
//				+ "ents_url\":\"https://api.github.com/users/kaze/recei"
//				+ "ved_events\",\"type\":\"User\",\"site_admin\":false,\"public_r"
//				+ "epos\":33,\"public_gists\":5,\"followers\":2,\"follow"
//				+ "ing\":3,\"created_at\":\"2009-02-09T22:18:56Z\",\"updated_at\":\"2"
//				+ "015-11-21T19:10:01Z\"}";
//		Gson gson = new Gson();
//		GitUserBeans test = gson.fromJson(s,GitUserBeans.class);
//		System.out.println(test.checkValidity());
//	}
}
