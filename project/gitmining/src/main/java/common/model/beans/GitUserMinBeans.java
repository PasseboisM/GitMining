package common.model.beans;

import common.service.GitUserMin;

public class GitUserMinBeans implements GitUserMin {

	private String login;
	private int id;
	private String name;
	private int followers;
	
	public String getLogin() {
		return login;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getFollowers() {
		return followers;
	}

	@Override
	public boolean checkValidity() {
		boolean loginValid = (login!=null) && (!login.equals(""));
		boolean nameValid = (name!=null) && (!name.equals(""));
		boolean followersValid = followers > -1;
		boolean idValid = id > -1;
		return loginValid && nameValid && followersValid && idValid;
	}

	@Override
	public String toString() {
		return "GitUserMinBeans [login=" + login + ", id=" + id + ", name="
				+ name + ", followers=" + followers + "]";
	}
	
	

}
