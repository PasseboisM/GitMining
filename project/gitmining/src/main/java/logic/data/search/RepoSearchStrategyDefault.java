package logic.data.search;

import common.enumeration.attribute.Language;
import common.param_obj.RepositorySearchParam;
import common.service.RepositoryMin;
import logic.data.search.service.RepositorySearchStrategy;

public class RepoSearchStrategyDefault implements RepositorySearchStrategy {

	@Override
	public double match(RepositoryMin obj, RepositorySearchParam params) {
		
		double matchCount = 0;
		
		boolean languageMatched = false;
		Language mainLang = obj.getMainLanguage();
		for(Language lang:params.getLangs()) {
			// Other属性弱于其他语言，对相关系数增加3.0
			if (Language.OTHERS == mainLang) {
				matchCount += 3.0;
				languageMatched = true;
				break;
			} else if (lang == mainLang) {
				matchCount += 5.0;
				languageMatched = true;
				break;
			} else if (lang == Language.ALL) {
				languageMatched = true;
				matchCount += 1.0;
				break;
			}
		}
		
		boolean categoryMatched = true;
//		for(Category cate:cates) {
//			for(Category minCate:minInfo.getCategories()) {
//				if(cate==minCate) {
//					matchCount++;
//					break;
//				}
//			}
//		}
		
		boolean keywordMatched = false;
		
		if(params.getKeywords().length==0) {
			keywordMatched = true;
		} else {
			for(String keyword:params.getKeywords()) {
				if(containssIgnoreCase(obj.getFull_name(),keyword)&&(!keyword.equals(""))) {
					matchCount += 10;
					keywordMatched = true;
				} else if(keyword.equals("")) {
					keywordMatched = true;
				}
			}
		}
		

		//If the object cannot match the choice params, it dosen't match the whole search param.
		boolean choicesMatched = languageMatched && categoryMatched;
		
		if(choicesMatched&&keywordMatched) {
			return matchCount;
		} else {
			return 0.0;
		}
		
	}

	private boolean containssIgnoreCase(String full_name, String keyword) {
		return full_name.toLowerCase().contains(keyword.toLowerCase());
	}

}
