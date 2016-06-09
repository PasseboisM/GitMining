package data.analysis.service;

import javax.servlet.http.HttpServletRequest;

public interface RequestRecorder {
	
	public void record(HttpServletRequest request);
}
