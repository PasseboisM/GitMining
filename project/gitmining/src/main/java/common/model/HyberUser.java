package common.model;

import java.io.IOException;

import org.kohsuke.github.GHUser;

import common.service.GitUser;

public class HyberUser implements GitUser {

	private GHUser ghUser;
	
	public HyberUser(GHUser ghUser) {
		this.ghUser = ghUser;
	}

	@Override
	public String getLogin() {
		return ghUser.getLogin();
	}

	@Override
	public int getId() {
		return ghUser.getId();
	}

	@Override
	public String getName() {
		try {
			return ghUser.getName();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getFollowers() {
		try {
			return ghUser.getFollowersCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean checkValidity() {
		boolean loginValid = (getLogin()!=null) && (!getLogin().equals(""));
		boolean nameValid = (getName()!=null) && (!getName().equals(""));
		boolean followersValid = getFollowers() > -1;
		boolean idValid = getId() > -1;
		boolean createdAtValid = (getCreated_at()!=null) && 
				(getCreated_at().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"));
		
		return loginValid 
				&& nameValid
				&& followersValid 
				&& idValid 
				&& createdAtValid;
	}

	@Override
	public String getAvatar_url() {
		return ghUser.getAvatarUrl();
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getGravatar_id() {
		return ghUser.getGravatarId();
	}

	@Override
	public String getUrl() {
		return ghUser.getUrl().toString();
	}

	@Override
	public String getHtml_url() {
		return ghUser.getHtmlUrl().toString();
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
		try {
			return ghUser.getBlog();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getLocation() {
		try {
			return ghUser.getLocation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getEmail() {
		try {
			return ghUser.getEmail();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getBio() {
		return null;
	}

	@Override
	public int getPublic_repos() {
		try {
			return ghUser.getPublicRepoCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getPublic_gists() {
		try {
			return ghUser.getPublicGistCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int getFollowing() {
		try {
			return ghUser.getFollowingCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String getCreated_at() {
		try {
			return ghUser.getCreatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUpdated_at() {
		try {
			return ghUser.getUpdatedAt().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
