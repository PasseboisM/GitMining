package data.service;

import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import common.service.GitUser;
import common.service.Repository;
import data.manage.RecommendDataGetterDefault;

public abstract class RecommendDataGetter {
	public abstract List<Repository> getRecommendRepos(Language language) throws NetworkException;
	public abstract List<GitUser> getRecommendUsers(Language language) throws NetworkException;
	public static RecommendDataGetter getInstance() {
		return RecommendDataGetterDefault.getInstance();
	}
}
