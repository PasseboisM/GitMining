package chart_data;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import common.enumeration.attribute.Language;

/**
 * Type: GeneralStatistics
 * 系统中所有项目的语言分布情况。
 * */
public class LanguageCounts {
	
	public List<LanguageCount> languageCounts = new LinkedList<>();
	
	public void addLanguageCount(Language language, int repositoryCount) {
		languageCounts.add(new LanguageCount(language, repositoryCount));
	}
	
	public Iterator<LanguageCount> getLanguageCount() {
		return languageCounts.listIterator();
	}
	
	public int getNumOfLanguages() {
		return languageCounts.size();
	}
	
	public class LanguageCount {
		public final Language language;
		public final int repositoryCount;
		public LanguageCount(Language language, int repositoryCount) {
			super();
			this.language = language;
			this.repositoryCount = repositoryCount;
		}
	}
}
