package common.service;

public class GitUserTest implements GitUser {
	private String name;
	private String avatar_url;
	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getFollowers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean checkValidity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAvatar_url() {
		return avatar_url;
	}

	@Override
	public String getGravatar_id() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHtml_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFollowers_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFollowing_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGists_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStarred_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSubscriptions_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrganizations_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRepos_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEvents_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReceived_events_url() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSite_admin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getBlog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPublic_repos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPublic_gists() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFollowing() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCreated_at() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdated_at() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setImage_url(String a){
		avatar_url= a ;
	}
	
	public void setName(String a){
		name = a;
	}
}
