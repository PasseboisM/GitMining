package data.manage;

import java.util.HashMap;
import java.util.Map;

import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.Repository;

public class DataBuffer {

	private static Map<String,Repository> repoBuffer = new HashMap<>();
	private static Map<String,GitUser> userBuffer = new HashMap<>();
	
	
	public static void addRepository(Repository repo) {
		repoBuffer.put(repo.getFull_name(), repo);
	}
	
	public static Repository searchRepository(String fullName) throws TargetNotFoundException {
		Repository result = repoBuffer.get(fullName);
		if (result==null) {
			throw new TargetNotFoundException();
		} else {
			return result;
		}
	}
	
	public static void addUser(GitUser user) {
		userBuffer.put(user.getLogin(), user);
	}
	
	public static GitUser searchUser(String login) throws TargetNotFoundException {
		GitUser result = userBuffer.get(login);
		if(result==null) {
			throw new TargetNotFoundException();
		} else {
			return result;
		}
	}
	
}
