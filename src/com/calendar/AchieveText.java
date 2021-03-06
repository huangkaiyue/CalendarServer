package com.calendar;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.common.HttpServletUtils;
import com.opensymphony.xwork2.ActionSupport;

public class AchieveText extends ActionSupport {
	public String AchieveText() throws IOException{
		String boby=HttpServletUtils.getRequestBoby(ServletActionContext.getRequest());
		if(boby==null||boby.equals("")){
			AckRequestFailed(403,"无效的请求参数");
			return NONE;
		}
		MsgRequest req =(MsgRequest)JSONObject.toBean(JSONObject.fromObject(boby),MsgRequest.class);
		if(req.getMsgtype().equals("getdate")){
				String json =CalDataInterface.AckCalendarData(req.getDateStr());
				HttpServletUtils.AckRequestResponse(ServletActionContext.getResponse(),json);
		}

		return NONE;
	}
	private void AckRequestFailed(int result,String resdata) throws IOException{
		JSONObject obj = new JSONObject();
		obj.put("msgtype", "request");
		obj.put("resdata", resdata);
		obj.put("result", result);
		System.out.println(obj.toString());
		String jsondata =obj.toString();
		HttpServletUtils.AckRequestResponse(ServletActionContext.getResponse(),jsondata);
	}
}
