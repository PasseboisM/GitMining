package network.api.service;

import network.connection.service.HTTPConnectionService;

public class NetworkTester {

	private String url;
	private String expected;
	
	public NetworkTester(String url,String expected) {
		this.url = url;
		this.expected = expected;
	}
	
	/**
	 * Returns whether the network service is avaliable.
	 * @return
	 */
	public boolean test() {
		HTTPConnectionService netService = HTTPConnectionService.getInstance();
		try {
			String get = netService.do_get(url);
			if(get.equals(expected)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
}
