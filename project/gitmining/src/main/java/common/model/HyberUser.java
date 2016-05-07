package common.model;

import common.service.GitUser;

public class HyberUser implements GitUser {

	private String login;
	private int id;
	private String name;
	private int followers;
	private String avatar_url;
	private String gravatar_id;
	private String url;
	private String html_url;
	private String blog;
	private String location;
	private String email;
	private int public_repos;
	private int public_gists;
	private int following;
	private String created_at;
	private String updated_at;
	

	public HyberUser(String login, int id, String name, int followers, String avatar_url, String gravatar_id,
			String url, String html_url, String blog, String location, String email, int public_repos, int public_gists,
			int following, String created_at, String updated_at) {
		this.login = login;
		this.id = id;
		this.name = name;
		this.followers = followers;
		this.avatar_url = avatar_url;
		this.gravatar_id = gravatar_id;
		this.url = url;
		this.html_url = html_url;
		this.blog = blog;
		this.location = location;
		this.email = email;
		this.public_repos = public_repos;
		this.public_gists = public_gists;
		this.following = following;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getFollowers() {
		return this.followers;
	}

	@Override
	public boolean checkValidity() {
		boolean loginValid = (login!=null) && (!login.equals(""));
		boolean nameValid = (name!=null) && (!name.equals(""));
		boolean followersValid = followers > -1;
		boolean idValid = id > -1;
		boolean createdAtValid = (created_at!=null) && 
				(created_at.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		
		return loginValid 
				&& nameValid
				&& followersValid 
				&& idValid 
				&& createdAtValid;
	}

	@Override
	public String getAvatar_url() {
		return this.avatar_url;
	}

	@Override
	public String getGravatar_id() {
		return this.gravatar_id;
	}

	@Override
	public String getUrl() {
		return this.url;
	}

	@Override
	public String getHtml_url() {
		return this.html_url;
	}

	@Override
	public String getFollowers_url() {
		return null;
	}

	@Override
	public String getFollowing_url() {
		return null;
	}

	@Override
	public String getGists_url() {
		return null;
	}

	@Override
	public String getStarred_url() {
		return null;
	}

	@Override
	public String getSubscriptions_url() {
		return null;
	}

	@Override
	public String getOrganizations_url() {
		return null;
	}

	@Override
	public String getRepos_url() {
		return null;
	}

	@Override
	public String getEvents_url() {
		return null;
	}

	@Override
	public String getReceived_events_url() {
		return null;
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public boolean isSite_admin() {
		return false;
	}

	@Override
	public String getBlog() {
		return this.blog;
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getBio() {
		return null;
	}

	@Override
	public int getPublic_repos() {
		return this.public_repos;
	}

	@Override
	public int getPublic_gists() {
		return this.public_gists;
	}

	@Override
	public int getFollowing() {
		return this.following;
	}

	@Override
	public String getCreated_at() {
		return this.created_at;
	}

	@Override
	public String getUpdated_at() {
		return this.updated_at;
	}

}
