package logic.calc.python;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import chart_data.radar.RepositoryRanks;

public class PythonRunner {
	
	public static List<String> runPython(String fileName,String...paras) throws IOException, InterruptedException {
		String folderName = "python/";
		String[] args = new String[3];
		args[0] = "python";
		args[1] = folderName+fileName;
		
		File f = File.createTempFile(""+System.currentTimeMillis(), ".params");
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < paras.length; i++) {
			bw.write(paras[i]);
			bw.newLine();
		}
		
		bw.close();
		fw.close();
		
		args[2] = f.getAbsolutePath();
		Process process = Runtime.getRuntime().exec(args);
		
		InputStream inputStream = process.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		String line;
		List<String> resultList = new ArrayList<>();
		
		try {
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				resultList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}
	

	public static void main(String[] args) {
		try {
			Gson gson = new Gson();
			String headers = gson.toJson(RepositoryRanks.defaultHeaders);
			long t1 = System.currentTimeMillis();
			List<String> result = PythonRunner.runPython("statistic_single_repo_rank.py",
					headers,
					"{\"id\":1734,\"name\":\"aasm\",\"full_name\":\"aasm/aasm\",\"owner\":{\"login\":\"aasm\",\"id\":1910612,\"avatar_u"
					+ "rl\":\"https://avatars.githubusercontent.com/u/1910612?v\u003d3\",\"gravatar_id\":\"\",\"url\""
					+ ":\"https://api.github.com/users/aasm\",\"html_url\":\"https://github.com/aasm\",\""
					+ "followers_url\":\"https://api.github.com/users/aasm/followers\",\"following_url\":\"https://api.g"
					+ "ithub.com/users/aasm/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/aasm"
					+ "/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/aasm/starred{/owner}{/repo}\",\"su"
					+ "bscriptions_url\":\"https://api.github.com/users/aasm/subscriptions\",\"organizations_url\":\"https:"
					+ "//api.github.com/users/aasm/orgs\",\"repos_url\":\"https://api.github.com/users/aasm/repos\",\"e"
					+ "vents_url\":\"https://api.github.com/users/aasm/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/aasm/received_events\",\"type\":\"Organization\",\"site_admin\":false},\"private\":false,\"html_url\":\"https://github.com/aasm/aasm\",\"description\":\"AASM - State machines for Ruby classes (plain Ruby, ActiveRecord, Mongoid, MongoMapper)\",\"fork\":false,\"url\":\"https://api.github.com/repos/aasm/aasm\",\"keys_url\":\"https://api.github.com/repos/aasm/aasm/keys{/key_id}\",\"collaborators_url\":\"https://api.github.com/repos/aasm/aasm/collaborators{/collaborator}\",\"teams_url\":\"https://api.github.com/repos/aasm/aasm/teams\",\"hooks_url\":\"https://api.github.com/repos/aasm/aasm/hooks\",\"issue_events_url\":\"https://api.github.com/repos/aasm/aasm/issues/events{/number}\",\"events_url\":\"https://api.github.com/repos/aasm/aasm/events\",\"assignees_url\":\"https://api.github.com/repos/aasm/aasm/assignees{/user}\",\"branches_url\":\"https://api.github.com/repos/aasm/aasm/branches{/branch}\",\"tags_url\":\"https://api.github.com/repos/aasm/aasm/tags\",\"blobs_url\":\"https://api.github.com/repos/aasm/aasm/git/blobs{/sha}\",\"git_tags_url\":\"https://api.github.com/repos/aasm/aasm/git/tags{/sha}\",\"git_refs_url\":\"https:"
					+ "//api.github.com/repos/aasm/aasm/git/refs{/sha}\",\"statuses_url\":\"https://api.github.com/repos/aasm/aasm/"
					+ "statuses/{sha}\",\"languages_url\":\"https://api.github.com/repos/aasm/aasm/languages\",\"stargazers_url\":\"https"
					+ "://api.github.com/repos/aasm/aasm/stargazers\",\"contributors_url\":\"https://api.github.com/repos/aasm/aasm/co"
					+ "ntributors\",\"subscription_url\":\"https://api.github.com/repos/aasm/aasm/subscription\",\"commits_url\":\"https"
					+ "://api.github.com/repos/aasm/aasm/commits{/sha}\",\"git_commits_url\":\"https://api.github.com/repos/aasm/aasm"
					+ "/git/commits{/sha}\",\"comments_url\":\"https://api.github.com/repos/aasm/aasm/comments{/number}\",\""
					+ "issue_comment_url\":\"https://api.github.com/repos/aasm/aasm/issues/comments{/number}\",\"con"
					+ "tents_url\":\"https://api.github.com/repos/aasm/aasm/contents/{+path}\",\"compare_url\":\"https:/"
					+ "/api.github.com/repos/aasm/aasm/compare/{base}...{head}\",\"merges_url\":\"https://api.github.com"
					+ "/repos/aasm/aasm/merges\",\"archive_url\":\"https://api.github.com/repos/aasm/aasm/{archive_for"
					+ "mat}{/ref}\",\"downloads_url\":\"https://api.github.com/repos/aasm/aasm/downloads\",\"issues_url"
					+ "\":\"https://api.github.com/repos/aasm/aasm/issues{/number}\",\"pulls_url\":\"https://api.github.c"
					+ "om/repos/aasm/aasm/pulls{/number}\",\"milestones_url\":\"https://api.github.com/repos/aasm/aasm/"
					+ "milestones{/number}\",\"notifications_url\":\"https://api.github.com/repos/aasm/aasm/notifications"
					+ "{?since,all,participating}\",\"labels_url\":\"https://api.github.com/repos/aasm/aasm/labels{/name"
					+ "}\",\"releases_url\":\"https://api.github.com/repos/aasm/aasm/releases{/id}\",\"created_at\":\"20"
					+ "08-02-28T20:40:04Z\",\"updated_at\":\"2015-12-01T23:57:45Z\",\"pushed_at\":\"2015-11-18"
					+ "T19:43:02Z\",\"git_url\":\"git://github.com/aasm/aasm.git\",\"ssh_url\":\"git@github.com:aasm/a"
					+ "asm.git\",\"clone_url\":\"https://github.com/aasm/aasm.git\",\"svn_url\":\"https://github.com/aas"
					+ "m/aasm\",\"homepage\":\"\",\"size\":3467,\"stargazers_count\":2133,\"watchers_count\":2133,\""
					+ "language\":\"Ruby\",\"has_issues\":true,\"has_downloads\":true,\"has_wiki\":true,\"has_pages\":true,\"forks_c"
					+ "ount\":328,\"open_issues_count\":49,\"forks\":328,\"open_issues\":49,\"watchers\":2133,\"defaul"
					+ "t_branch\":\"master\",\"permissions\":{\"admin\":false,\"push\":false,\"pull\":true},\"organizati"
					+ "on\":{\"login\":\"aasm\",\"id\":1910612,\"avatar_url\":\"https://avatars.githubusercont"
					+ "ent.com/u/1910612?v\u003d3\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/use"
					+ "rs/aasm\",\"html_url\":\"https://github.com/aasm\",\"followers_url\":\"https://api.github.com"
					+ "/users/aasm/followers\",\"following_url\":\"https://api.github.com/users/aasm/following{/other"
					+ "_user}\",\"starred_url\":\"https://api.github.com/users/aasm/starred{/owner}{/repo}\",\"organiz"
					+ "ations_url\":\"https://api.github.com/users/aasm/orgs\",\"repos_url\":\"https://api.github.com/u"
					+ "sers/aasm/repos\",\"events_url\":\"https://api.github.com/users/aasm/events{/privacy}\",\"receiv"
					+ "ed_events_url\":\"https://api.github.com/users/aasm/received_events\",\"type\":\"Organizat"
					+ "ion\",\"site_admin\":false},\"network_count\":328,\"subscribers_count\":34}"
);
			System.out.println(System.currentTimeMillis()-t1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
