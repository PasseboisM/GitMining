package presentation.ui.main;

import common.exception.TargetNotFoundException;
import common.service.GitUser;
import common.service.GitUserTest;
import logic.service.LogInHelper;

public class LogInHelperTest implements LogInHelper{

	
	@Override
	public GitUser tryLogIn(String login, String password) throws TargetNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("do this");
		GitUserTest gitUserTest = new GitUserTest();
		gitUserTest.setName("LiuQing");
		if(password.equals(gitUserTest.getPassword())){
			System.out.println(gitUserTest.toString());
			return gitUserTest;
		}else{
			throw new TargetNotFoundException();
		}
		
		
	}

	@Override
	public void logOut() {
		System.out.println("Logging out");
		
	}
	
}
