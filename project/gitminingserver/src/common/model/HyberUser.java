package common.model;

import org.kohsuke.github.GHUser;

import common.service.GitUser;

public class HyberUser implements GitUser {

	
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
	  
	
	@SuppressWarnings("deprecation")
	public HyberUser(GHUser ghUser) {
		login = ghUser.getLogin();
		id = ghUser.getId();
		avatar_url = ghUser.getAvatarUrl();
		gravatar_id = ghUser.getGravatarId();
		site_admin = false;
		try {
			url = ghUser.getUrl().toString();
			html_url = ghUser.getHtmlUrl().toString();
			
			name = ghUser.getName();
			blog = ghUser.getBlog();
			location = ghUser.getLocation();
			email = ghUser.getEmail();
			public_repos = ghUser.getPublicRepoCount();
			public_gists = ghUser.getPublicGistCount();
			followers = ghUser.getFollowersCount();
			following = ghUser.getFollowingCount();
			created_at = ghUser.getCreatedAt().toString();
			updated_at = ghUser.getUpdatedAt().toString();
		} catch (Exception e) {}
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

}
