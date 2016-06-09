package common.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class JSONHelper {

	private static Gson gson = new Gson();
	
	public static void printJSONList(PrintWriter p,List<String> s) {
		p.println('[');
		for (int i=0;i<s.size();i++) {
			p.print(s.get(i));
			if (i!=s.size()-1) p.println(',');
		}
		p.println(']');
	}
	
	public static <E> List<String> toJSONList(List<E> objList) {
		List<String> result = new ArrayList<String>(objList.size());
		for (E obj:objList) {
			result.add(gson.toJson(obj));
		}
		return result;
	}
	
}
