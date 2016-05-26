package common.service;

public class GitUserTest implements GitUser {
	private String name;
	private String avatar_url;
	private String password="123456";
	
	public String getPassword(){
		return password;
	}
	@Override
	public String getLogin() {
		return null;
	}

	@Override
	public int getId() {
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getFollowers() {
		return 0;
	}

	@Override
	public boolean checkValidity() {
		return false;
	}

	@Override
	public String getAvatar_url() {
		return avatar_url;
	}

	@Override
	public String getGravatar_id() {
		return null;
	}

	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public String getHtml_url() {
		return null;
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
		return null;
	}

	@Override
	public String getLocation() {
		return null;
	}

	@Override
	public String getEmail() {
		return null;
	}

	@Override
	public String getBio() {
		return null;
	}

	@Override
	public int getPublic_repos() {
		return 0;
	}

	@Override
	public int getPublic_gists() {
		return 0;
	}

	@Override
	public int getFollowing() {
		return 0;
	}

	@Override
	public String getCreated_at() {
		return null;
	}

	@Override
	public String getUpdated_at() {
		return null;
	}

	
	public void setImage_url(String a){
		avatar_url= a ;
	}
	
	public void setName(String a){
		name = a;
	}
}
