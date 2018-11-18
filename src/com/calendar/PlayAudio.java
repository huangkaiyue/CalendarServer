package com.calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.common.FileInter;
import com.opensymphony.xwork2.ActionSupport;

public class PlayAudio extends ActionSupport{
	public String playMusic() throws Exception{
		String method = ServletActionContext.getRequest().getParameter("method");		
		System.out.println("ActionSupport Method :"+ServletActionContext.getRequest().getMethod()+"--->"+"getParameter:"+method);
		if(method.equals("down")){
			HttpServletRequest request =ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			ServletActionContext.setRequest(request);
			String fileName = request.getParameter("fileName");
			FileInter.downloadFile(ServletActionContext.getResponse(),ServletActionContext.getServletContext(),fileName,fileName);
			return NONE	;
		}
		return NONE	;
	}
}
