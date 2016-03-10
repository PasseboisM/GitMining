package network.connection;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import network.api.stub.UserApiMaker_stub;
import network.connection.service.HTTPConnectionService;

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

public class HTTPConnection extends HTTPConnectionService {

	
	/* (non-Javadoc)
	 * @see network.connection.HTTPConnectionService#do_post(java.lang.String, java.util.List)
	 */
	@SuppressWarnings("deprecation")
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
    @SuppressWarnings("deprecation")
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
    
//    public String do_get_performanceTest(String url) throws ClientProtocolException, IOException {
//        String body = "{}";
//        long[] timeP = new long[10];int i = 0;
//        
//        timeP[i++] = System.currentTimeMillis();
//        
//        DefaultHttpClient httpclient = new DefaultHttpClient();
//        timeP[i++] = System.currentTimeMillis();
//        System.out.println("Create client:"+(timeP[i-1]-timeP[i-2])+"ms");
//        
//        try {
//            HttpGet httpget = new HttpGet(url);
//            timeP[i++] = System.currentTimeMillis();
//            System.out.println("Create httpget:"+(timeP[i-1]-timeP[i-2])+"ms");
//            
//            HttpResponse response = httpclient.execute(httpget);
//            timeP[i++] = System.currentTimeMillis();
//            System.out.println("execute httpget:"+(timeP[i-1]-timeP[i-2])+"ms");
//            
//            HttpEntity entity = response.getEntity();
//            timeP[i++] = System.currentTimeMillis();
//            System.out.println("make entity:"+(timeP[i-1]-timeP[i-2])+"ms");
//            
//            body = EntityUtils.toString(entity);
//            timeP[i++] = System.currentTimeMillis();
//            System.out.println("toString:"+(timeP[i-1]-timeP[i-2])+"ms");
//            
//        } finally {
//            httpclient.getConnectionManager().shutdown();
//            timeP[i++] = System.currentTimeMillis();
//            System.out.println("shut down:"+(timeP[i-1]-timeP[i-2])+"ms");
//            
//            System.out.println("Total:"+(timeP[i-1]-timeP[0])+"ms\n\n");
//        }
//        return body;
//    }
//    
//    public static void main(String[] args) throws ClientProtocolException, IOException {
//		HTTPConnection con = new HTTPConnection();
//		con.do_get_performanceTest(new UserApiMaker_stub().makeUserAPI("XRiver"));
//		con.do_get_performanceTest(new UserApiMaker_stub().makeUserAPI("kylin1"));
//		con.do_get_performanceTest(new UserApiMaker_stub().makeUserAPI("Summer222"));
//		con.do_get_performanceTest(new UserApiMaker_stub().makeUserAPI("iDimple"));
//	}
    
}
