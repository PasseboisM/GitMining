package network.service;

import java.util.List;

import common.enumeration.attribute.Language;
import common.exception.NetworkException;

public interface GHTAnalysisDataSource {
	public List<String> recommendRepositories(Language language) throws NetworkException;
	public List<String> recommendUsers(Language language) throws NetworkException;
}
