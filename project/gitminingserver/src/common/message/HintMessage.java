package common.message;

import com.google.gson.Gson;

public class HintMessage {
	
	public String message = null;
	
	public HintMessage(String message) {
		this.message = message;
	}
	
	public String toJSON() {
		return new Gson().toJson(this);
	}
	
}
