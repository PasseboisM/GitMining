package network.data;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import common.exception.NetworkException;
import common.model.beans.GitUserBeans;
import common.model.beans.GitUserMinBeans;
import common.model.beans.RepositoryBeans;
import common.service.GitUser;
import common.service.GitUserMin;
import common.service.Repository;
import network.api.service.ApiMakerService;
import network.api.service.RepoApiMaker;
import network.api.service.UserApiMaker;
import network.api.stub.ApiMakerService_stub;
import network.connection.service.HTTPConnectionService;

public class TestDriver {

	@Test
	public void testUserService() throws ClientProtocolException, IOException, NetworkException {

		ApiMakerService apiMaker = new ApiMakerService_stub();
		UserApiMaker userApi = apiMaker.getUserApiMaker();
		String api1 = userApi.makeUserAPI("XRiver");
		HTTPConnectionService con = HTTPConnectionService.getInstance();
		String json1 = con.do_get(api1);
		Gson gson = new Gson();
		
		GitUser user1 = gson.fromJson(json1, GitUserBeans.class);
		assertEquals("XRiver", user1.getLogin());
		assertEquals(12857246, user1.getId());
		
		GitUserMin gitUserMin = gson.fromJson(json1, GitUserMinBeans.class);
		assertEquals("XRiver", gitUserMin.getLogin());
		assertEquals(12857246, gitUserMin.getId());
	}
	
	@Test
	public void testRepoService() throws Exception {
		ApiMakerService apiMaker = new ApiMakerService_stub();
		RepoApiMaker repoApi = apiMaker.getRepoApiMaker();
		String api2 = repoApi.makeRepoNamesApi();
		HTTPConnectionService con = HTTPConnectionService.getInstance();
		String json2 = con.do_get(api2);
		Gson gson = new Gson();
		
		Type listTypeType = new TypeToken<List<String>>(){}.getType();
		List<String> repoLists = gson.fromJson(json2, listTypeType);
		assertEquals("mojombo/grit", repoLists.get(0));
		
		String api3 = repoApi.makeRepoInfoApi(repoLists.get(0));
		String json3 = con.do_get(api3);
		
		Repository repository = gson.fromJson(json3, RepositoryBeans.class);
		assertEquals(1, repository.getId());
		
//		RepositoryMin repositoryMin = gson.fromJson(json3, RepositoryMinBeans.class);
		
		
	}


}
