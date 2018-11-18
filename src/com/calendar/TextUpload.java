package com.calendar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.start.server.ConfigServer;

public class TextUpload extends ActionSupport{ 
	private String author="" ; 
	private String textData="" ;  
	private List<File> file1 ;  
	private List<String> file1FileName ; // 文件名 
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String textData) {
		this.textData = textData;
	}

	public List<File> getFile1() {
		return file1;
	}

	public void setFile1(List<File> file1) {
		this.file1 = file1;
	}
	public List<String> getFile1FileName() {
		return file1FileName;
	}

	public void setFile1FileName(List<String> file1FileName) {
		this.file1FileName = file1FileName;
	}
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub

		// 获取上传的目录路径			
		if(file1==null){
			System.out.println("not upload file ......");
			return NONE;
		}
		String serverSelect = ServletActionContext.getRequest().getParameter("serverSelect").toString();
		if(serverSelect.equals("1")){
			System.out.println("upload is localserver");
		}else{
			System.out.println("upload is aliyun server");
		}
		System.out.println("author:"+author);
		System.out.println("textData:"+textData);
		System.out.println("audio name:"+file1FileName.get(0));
		String saveDir = ConfigServer.getInstance().getCacheDir();
		if(CalDataInterface.InsertCalendarData(author, textData, file1FileName.get(0))==0){
			SaveRecvFile(saveDir,file1.get(0),file1FileName.get(0));
		}
		return NONE;
	}
	
	private void SaveRecvFile(String saveDir,File file,String destname) throws IOException{
        OutputStream os = new FileOutputStream(new File(saveDir,destname));  
        InputStream is = new FileInputStream(file);  
        byte[] buf = new byte[1024];  
        int length = 0 ;  
          
        while(-1 != (length = is.read(buf) ) )  {  
            os.write(buf, 0, length) ;  
        }  
        is.close();  
        os.close();  
	}
	
}
