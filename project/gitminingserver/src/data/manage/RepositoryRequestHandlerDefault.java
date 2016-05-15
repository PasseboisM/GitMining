package data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.service.RepositoryRequestHandler;

class RepositoryRequestHandlerDefault extends RepositoryRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		// TODO Auto-generated method stub
		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (httpRequest.getParameter("isTest").equals("true")) {
				out.println("{\"id\":1734,\"name\":\"aasm\",\"full_name\":\"aasm/aasm\",\"owner\":{\"login\":\"aasm\",\"id\":1910612,\"avatar_url\":\"https://avatars.githubusercontent.com/u/1910612?v\u003d3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/aasm\",\"html_url\":\"https://github.com/aasm\",\"followers_url\":\"https://api.github.com/users/aasm/followers\",\"following_url\":\"https://api.github.com/users/aasm/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/aasm/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/aasm/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/aasm/subscriptions\",\"organizations_url\":\"https://api.github.com/users/aasm/orgs\",\"repos_url\":\"https://api.github.com/users/aasm/repos\",\"events_url\":\"https://api.github.com/users/aasm/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/aasm/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/aasm/aasm\",\"description\":\"AASM - State machines for Ruby classes (plain Ruby, ActiveRecord, Mongoid, MongoMapper)\",\"fork\":false,\"url\":\"https://api.github.com/repos/aasm/aasm\",\"keys_url\":\"https://api.github.com/repos/aasm/aasm/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/aasm/aasm/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/aasm/aasm/teams\",\"hooks_url\":\"https://api.github.com/repos/aasm/aasm/hooks\",\"issue_events_url\":\"https://api.github.com/repos/aasm/aasm/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/aasm/aasm/events\",\"assignees_url\":\"https://api.github.com/repos/aasm/aasm/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/aasm/aasm/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/aasm/aasm/tags\",\"blobs_url\":\"https://api.github.com/repos/aasm/aasm/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/aasm/aasm/git/tags{/sha}\",\"git_refs_url\":\"https://api.github.com/repos/aasm/aasm/git/refs{/sha}\",\"statuses_url\":\"https://api.github.com/repos/aasm/aasm/statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/aasm/aasm/languages\",\"stargazers_url\":\"https://api.github.com/repos/aasm/aasm/stargazers\",\"contributors_url\":\"https://api.github.com/repos/aasm/aasm/contributors\",\"subscription_url\":\"https://api.github.com/repos/aasm/aasm/subscription\",\"commits_url\":\"https://api.github.com/repos/aasm/aasm/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/aasm/aasm/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/aasm/aasm/comments{/number}\",\"issue_comment_url\":\"https://api.github.com/repos/aasm/aasm/issues/comments{/number}\",\"contents_url\":\"https://api.github.com/repos/aasm/aasm/contents/{+path}\",\"compare_url\":\"https://api.github.com/repos/aasm/aasm/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com/repos/aasm/aasm/merges\",\"archive_url\":\"https://api.github.com/repos/aasm/aasm/{archive_format}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/aasm/aasm/downloads\",\"issues_url\":\"https://api.github.com/repos/aasm/aasm/issues{/number}\",\"pulls_url\":\"https://api.github.com/repos/aasm/aasm/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/aasm/aasm/milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/aasm/aasm/notifications{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/aasm/aasm/labels{/name}\",\"releases_url\":\"https://api.github.com/repos/aasm/aasm/releases{/id}\",\"created_at\":\"2008-02-28T20:40:04Z\",\"updated_at\":\"2015-12-01T23:57:45Z\",\"pushed_at\":\"2015-11-18T19:43:02Z\",\"git_url\":\"git://github.com/aasm/aasm.git\",\"ssh_url\":\"git@github.com:aasm/aasm.git\",\"clone_url\":\"https://github.com/aasm/aasm.git\",\"svn_url\":\"https://github.com/aasm/aasm\",\"homepage\":\"\",\"size\":3467,\"stargazers_count\":2133,\"watchers_count\":2133,\"language\":\"Ruby\",\"has_issues\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":true,\"forks_count\":328,\"open_issues_count\":49,\"forks\":328,\"open_issues\":49,\"watchers\":2133,\"default_branch\":\"master\",\"permissions\":{\"admin\":false,\"push\":false,\"pull\":true},\"organization\":{\"login\":\"aasm\",\"id\":1910612,\"avatar_url\":\"https://avatars.githubusercontent.com/u/1910612?v\u003d3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/aasm\",\"html_url\":\"https://github.com/aasm\",\"followers_url\":\"https://api.github.com/users/aasm/followers\",\"following_url\":\"https://api.github.com/users/aasm/following{/other_user}\",\"starred_url\":\"https://api.github.com/users/aasm/starred{/owner}{/repo}\",\"organizations_url\":\"https://api.github.com/users/aasm/orgs\",\"repos_url\":\"https://api.github.com/users/aasm/repos\",\"events_url\":\"https://api.github.com/users/aasm/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/aasm/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"network_count\":328,\"subscribers_count\":34}");
			} else {
				out.println("{\"message\":\"Not testing. Request not handled.\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.println("You can use the parameter 'isTest=true' to get a repository JSON for testing.");
		}
		
		out.close();
	}

}
