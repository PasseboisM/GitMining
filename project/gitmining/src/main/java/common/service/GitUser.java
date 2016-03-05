package common.service;

/**
 * 
 * @author River
 * 
 * GitUser（即项目的参与协作者）数据提供的详情获取接口
 * TODO 按照Github的User模型列出所需的Getter方法（可以参考JSON数据的条目）
 * 
 */
public interface GitUser {
	public String getLogin();
	public int getId();
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
	public String getName();
	public String getBlog();
	public String getLocation();
	public String getEmail();
	public String getBio();
	public int getPublic_repos();
	public int getPublic_gists();
	public int getFollowers();
	public int getFollowing();
	public String getCreated_at();
	public String getUpdated_at();
}
