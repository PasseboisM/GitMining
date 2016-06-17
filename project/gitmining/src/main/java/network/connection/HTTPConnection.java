package network.connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import common.exception.NetworkException;
import network.connection.service.HTTPConnectionService;

@SuppressWarnings("deprecation")
public class HTTPConnection extends HTTPConnectionService {
	
	@SuppressWarnings("resource")
	public String do_post(String url, List<NameValuePair> name_value_pair) throws IOException {
        String body = "{}";
		DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpPost httpost = new HttpPost(url);
            httpost.setEntity(new UrlEncodedFormEntity(name_value_pair, StandardCharsets.UTF_8));
            HttpResponse response = httpclient.execute(httpost);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
	
	
	public String do_get(String url) throws NetworkException {
        String body = "{}";
        HttpClient httpclient = HttpClients.createMinimal();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
			throw new NetworkException();
		} catch (IOException e) {
			throw new NetworkException();
		} finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
	    
}
