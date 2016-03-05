package common.model;

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
	
	

}
