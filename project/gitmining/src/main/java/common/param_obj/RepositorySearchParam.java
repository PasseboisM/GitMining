package common.param_obj;

import common.enumeration.attribute.Category;
import common.enumeration.attribute.Language;
import common.service.RepositoryMin;

/**
 * @author xjh14
 * 搜索Repository时使用的参数封装类，用于提高接口灵活性、简洁度。
 * 当前包含内容：语言种类，内容分类，其他关键词。
 */
public class RepositorySearchParam {

	private Language[] langs;
	private Category[] cates;
	private String[] keywords;
	
	public RepositorySearchParam(Language[] langs, Category[] cates,
			String[] keywords) {
		super();
		this.langs = langs;
		this.cates = cates;
		this.keywords = keywords;
	}

	public Language[] getLangs() {
		return langs;
	}

	public Category[] getCates() {
		return cates;
	}

	public String[] getKeywords() {
		return keywords;
	}
	
	public boolean matches(RepositoryMin minInfo) {
		//TODO Create a checker for RepoMin.
		return false;
	}
}
