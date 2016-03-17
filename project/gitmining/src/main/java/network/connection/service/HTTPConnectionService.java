package network.connection.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;

import common.exception.NetworkException;
import network.connection.HTTPConnection;

public abstract class HTTPConnectionService {

	public abstract String do_post(String url, List<NameValuePair> name_value_pair)
			throws IOException;

	public abstract String do_get(String url) throws NetworkException;
	
	public static HTTPConnectionService getInstance() {
		return new HTTPConnection();
	}

}