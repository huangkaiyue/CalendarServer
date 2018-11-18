package com.calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.db.CalendarData;
import com.hibernate.db.HiberSql;
import com.hibernate.db.HibernateUtil;
import com.start.server.ConfigServer;

public class CalDataInterface {
	
	public static int InsertCalendarData(String author,String textData,String filename){
		int ret =0;
		CalendarData calData = new CalendarData();
		calData.setAuthor(author);
		calData.setTextData(textData);
		calData.setPlayAudio(filename);
		Date day=new Date();    
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(day);
		calData.setTdate(dateString);
		
		try {
			HiberSql.Insert(calData);
			System.out.println("insert ok :"+author);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("insert failed :"+e.getMessage());
			ret=-1;
			HibernateUtil.closeSession();// 关闭
		}
		return ret;
	}
	
	 private static List<Object> ScanCalendarData(String dateStr){
		Session session = HibernateUtil.getSession();// 创建session (代表一个会话，与数据库连接的会话)
		Transaction tx = session.beginTransaction();// 开启事务
		String sql = "from CalendarData al where al.Tdate=?";
//		Date day=new Date();
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		String dateString = formatter.format(day);

		Query query =session.createQuery(sql);//HQL创建查询语句
		query.setParameter(0,dateStr);
		
		List<Object>list=query.list();
//		if(list!=null&&list.size()!=0){
//			Iterator iterator = list.iterator();
//			while(iterator.hasNext()){
//				cal = (CalendarData) iterator.next();
//				System.out.println(cal.toString()); 
//			}
//		}
		tx.commit();// 提交事务
		HibernateUtil.closeSession();// 关闭
		return  list;
		
	}
	public static String AckCalendarData(String dateStr){
		String jsondata="";
		CalendarData cal =null; 
		List<Object>list=ScanCalendarData(dateStr);
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		if(list!=null&&list.size()!=0){
			Iterator iterator = list.iterator();
			while(iterator.hasNext()){
				cal = (CalendarData) iterator.next();
				System.out.println(cal.toString()); 
				JSONObject js = new JSONObject();
				js.put("author", cal.getAuthor());
				js.put("textData", cal.getTextData());
				js.put("playAudio", ConfigServer.getInstance().getDownUrl()+"/"+cal.getPlayAudio());
				js.put("playAudios", ConfigServer.getInstance().getHttpsDownUrl()+cal.getPlayAudio());
				js.put("Tdate", cal.getTdate());
				jarr.add(js);
			}
		}
		json.put("textdata", jarr);
		jsondata =json.toString();
		
		
		
		return jsondata;
	}
	
}
