package network.connection.service;

import java.io.IOException;
import java.util.List;

import network.connection.HTTPConnection;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;

public abstract class HTTPConnectionService {

	public abstract String do_post(String url, List<NameValuePair> name_value_pair)
			throws IOException;

	public abstract String do_get(String url) throws ClientProtocolException,
			IOException;
	
	public static HTTPConnectionService getInstance() {
		return new HTTPConnection();
	}

}