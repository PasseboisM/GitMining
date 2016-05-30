package data.service;

import common.exception.TargetNotFoundException;
import data.manage.SpecificDataGetterNetwork;

/**
 * @author xjh14
 *
 */
public abstract class SpecificDataGetter {
	
	public abstract String getSpecificRepo(String fullName) throws TargetNotFoundException;

	public abstract String getSpecificGitUser(String login) throws TargetNotFoundException;
	
	public static SpecificDataGetter getInstance() {
		return SpecificDataGetterNetwork.getInstance();
	}
	
}
