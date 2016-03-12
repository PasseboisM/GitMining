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
	
	//TODO 应该把搜索策略搬出Parameter类，成为独立的Strategy
	public int matches(RepositoryMin minInfo) {
		
		int matchCount = 0;
		
		for(Language lang:langs) {
			if(lang==minInfo.getMainLanguage()) {
				matchCount += 5;
				break;
			}
		}
		
		for(Category cate:cates) {
			for(Category minCate:minInfo.getCategories()) {
				if(cate==minCate) {
					matchCount++;
					break;
				}
			}
		}
		
		for(String keyword:keywords) {
			if(minInfo.getFull_name().contains(keyword)&&(!keyword.equals(""))) {
				matchCount += 10;
			}
		}
		
		return matchCount;
	}
}
