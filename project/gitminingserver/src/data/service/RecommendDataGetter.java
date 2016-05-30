package data.service;

import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;
import data.manage.RecommendDataGetterDefault;

public abstract class RecommendDataGetter {
	public abstract List<String> getRecommendRepos(Language language) throws NetworkException;
	public abstract List<String> getRecommendUsers(Language language) throws NetworkException;
	public static RecommendDataGetter getInstance() {
		return RecommendDataGetterDefault.getInstance();
	}
}
