package network.connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import network.connection.service.HTTPConnectionService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HTTPConnection extends HTTPConnectionService {

	
	/* (non-Javadoc)
	 * @see network.connection.HTTPConnectionService#do_post(java.lang.String, java.util.List)
	 */
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
    /* (non-Javadoc)
	 * @see network.connection.HTTPConnectionService#do_get(java.lang.String)
	 */
    public String do_get(String url) throws ClientProtocolException, IOException {
        String body = "{}";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            body = EntityUtils.toString(entity);
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        return body;
    }
}
