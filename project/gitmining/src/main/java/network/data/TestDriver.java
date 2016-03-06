package network.data;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import network.api.service.ApiMakerService;
import network.api.service.UserApiMaker;
import network.api.stub.ApiMakerService_stub;
import network.connection.HTTPConnection;
import network.connection.service.HTTPConnectionService;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import com.google.gson.Gson;

import common.model.GitUserBeans;
import common.service.GitUser;

public class TestDriver {

	@Test
	public void testUserService() throws ClientProtocolException, IOException {
		
		ApiMakerService apiMaker = new ApiMakerService_stub();
		
		UserApiMaker userApi = apiMaker.getUserApiMaker();
		
		String api1 = userApi.makeUserAPI("XRiver");
		
		HTTPConnectionService con = HTTPConnectionService.getInstance();
		
		String json1 = con.do_get(api1);
		
		Gson gson = new Gson();
		
		GitUser user1 = gson.fromJson(json1, GitUserBeans.class);
		
		assertEquals("XRiver",user1.getLogin());
		assertEquals(12857246,user1.getId());
	}
	
}
