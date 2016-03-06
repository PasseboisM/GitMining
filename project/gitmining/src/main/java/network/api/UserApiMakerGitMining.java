package network.api;

import common.service.GitUserMin;

import network.api.service.UserApiMaker;

/**
 * 
 * @author xjh14
 * 实际上使用了部分来自Github的API，因为GitMining的API不好用。
 */
public class UserApiMakerGitMining implements UserApiMaker {

	public String makeUserAPI(GitUserMin source) {
		return this.makeUserAPI(source.getLogin());
	}

	
	public String makeUserAPI(String login) {
		return "https://api.github.com/users/"+login;
	}

}
