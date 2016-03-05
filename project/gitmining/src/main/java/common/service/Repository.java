package common.service;

/**
 * 
 * @author River
 * 
 * Repository数据提供的详情获取接口
 * TODO 还有好多Repository的信息条目！
 */
public interface Repository extends RepositoryMin {
	
	public RepositoryOwner getOwner();
	
	public String getHtml_url();

	public String getDescription();

	public boolean isFork();

	public String getUrl();

	public String getFork_url();

	public String getKeys_url();

	public String getCollaborators_url();

	public String getTeams_url();

	public String getHooks_url();

	public String getIssue_events_url() ;

	public String getEvents_url();

	public String getAssignees_url() ;

	public String getBranches_url();

	public String getTags_url();

	public String getBlobs_url();

	public String getGit_tags_url();

	public String getGit_refs_url();

	public String getTree_url();

	public String getStatuses_url();

	public String getLanguages_url() ;

	public String getStargazers_url();

	public String getContributors_url() ;

	public String getSubscibers_url();

	public String getSubscription_url();

	public String getCommits_url();

	public String getGit_commits_url();

	public String getComments_url();

	public String getIssue_comment_url();

	public String getContents_url();

	public String getCompare_url();

	public String getMerges_url();

	public String getArchive_url();

	public String getDownloads_url();

	public String getIssues_url();

	public String getPulls_url();

	public String getMilestones_url();

	public String getNotifications_url();

	public String getLabels_url();

	public String getReleases_url();

	public String getCreated_at();

	public String getUpdated_at();

	public String getPushed_at();

	public String getGit_url();

	public String getSsh_url();

	public String getClone_url();

	public String getSvn_url();

	public String getHomepage();

	public int getSize();

	public int getStargazers_count();

	public int getWatchers_count();

	public String getLanguage();

	public boolean isHas_issues();

	public boolean isHas_downloads();

	public boolean isHas_wiki();

	public boolean isHas_pages();

	public int getForks_count();

	public int getOpen_issues_count();

	public int getOpen_issues();

	public int getWatchers();

	public String getDefault_branch();

	public RepositoryPermission getPermissions();

	public RepositoryOrganization getOrganization();

	public int getNetwork_count();

	public int getSubscribers_count();
}