package common.service;

/**
 * 
 * @author xjh14,wza14
 * 
 * GitUser（即项目的参与协作者）数据提供的详情获取接口
 * 
 */
public interface GitUser extends GitUserMin {

	public String getAvatar_url();
	public String getGravatar_id();
	public String getUrl();
	public String getHtml_url();
	public String getFollowers_url();
	public String getFollowing_url();
	public String getGists_url();
	public String getStarred_url();
	public String getSubscriptions_url();
	public String getOrganizations_url();
	public String getRepos_url();
	public String getEvents_url();
	public String getReceived_events_url();
	public String getType();
	public boolean isSite_admin();
	public String getBlog();
	public String getLocation();
	public String getEmail();
	public String getBio();
	public int getPublic_repos();
	public int getPublic_gists();
	public int getFollowing();
	public String getCreated_at();
	public String getUpdated_at();
}
