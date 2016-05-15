package logic.service;

import java.io.IOException;
import java.util.List;

public interface RepoRelatedListGetter {
	public List<String> getRelatedRepoNames(String fullName);
	public List<String> getContributorNames(String fullName) throws IOException;
	public List<String> getCollaboratorNames(String fullName) throws IOException;
}
