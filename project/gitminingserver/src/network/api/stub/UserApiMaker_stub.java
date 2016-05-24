package network.api.stub;

import common.service.GitUserMin;
import network.api.service.UserApiMaker;

public class UserApiMaker_stub implements UserApiMaker {

	public String makeUserAPI(GitUserMin source) {
		return "https://api.github.com/users/"+source.getLogin();
	}

	public String makeUserAPI(String login) {
		return "https://api.github.com/users/"+login;
	}

	@Override
	public String[] allUserAPIs() {
		return null;
	}

}
