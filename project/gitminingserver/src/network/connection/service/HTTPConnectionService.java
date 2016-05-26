package network.connection.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;

import common.exception.NetworkException;
import network.connection.HTTPConnection;

public abstract class HTTPConnectionService {

	public abstract String do_post(String url, List<NameValuePair> name_value_pair)
			throws IOException;

	/**
	 * 使用get方式获取网页内容
	 * @param url 网页url
	 * @return 网页内容
	 * @throws NetworkException 网络出现异常
	 */
	public abstract String do_get(String url) throws NetworkException;
	
	public static HTTPConnectionService getInstance() {
		return new HTTPConnection();
	}

}