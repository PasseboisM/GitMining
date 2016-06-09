package data.analysis.service;

import data.analysis.manage.RequestRecorderDefault;

public class AnalysisServiceFactory {
	private static RequestRecorder recorder = new RequestRecorderDefault();
	
	public static RequestRecorder getRecorder() {
		return recorder;
	}
	
}
