package server.data.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.message.HintMessage;
import data.service.DataServiceFactory;
import data.service.MassiveDataGetter;
import data.service.SpecificDataGetter;
import server.data.service.UserRequestHandler;

class UserRequestHandlerDefault extends UserRequestHandler {

	private MassiveDataGetter massive = 
			DataServiceFactory.getInstance().getMassiveDataGetter();
	private SpecificDataGetter specific =
			DataServiceFactory.getInstance().getSpecificDataGetter();
	
	private Gson gson = new Gson();
	
	@Override
	public void handleRequest(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		PrintWriter out = null;
		try {
			out = httpResponse.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		String type = httpRequest.getParameter("type");
		if (type==null) {
			out.print(new HintMessage("Parameter (type) is necessary!").toJSON());
			out.close();
		}
		
		switch (type) {
		case "stat":
			printTypeStat(httpRequest, out);
			break;
		case "data":
			printTypeData(httpRequest, out);
			break;
		default:
			out.println(new HintMessage("Unknown value for parameter (type).").toJSON());
			break;
		}
		
		out.close();

	}

	private void printTypeData(HttpServletRequest httpRequest, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

	private void printTypeStat(HttpServletRequest httpRequest, PrintWriter out) {
		// TODO Auto-generated method stub
		
	}

}
