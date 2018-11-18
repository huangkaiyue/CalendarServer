package com.hibernate.db;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HiberSql {
	
	public static void Insert(Object obj) throws Exception {		
		Session session = HibernateUtil.getSession();// 创建session (代表一个会话，与数据库连接的会话)
		Transaction tx = session.beginTransaction();// 开启事务
		session.save(obj);//保存-数据库
		tx.commit();// 提交事务
		HibernateUtil.closeSession();// 关闭

	}

    public static List<Object> ScanTable(String tablename){
    	List<Object> list =null;
		Session session = HibernateUtil.getSession();// 创建session (代表一个会话，与数据库连接的会话)
		Transaction tx = session.beginTransaction();// 开启事务
		list= session.createQuery("from "+tablename).list();//HQL创建查询语句
		tx.commit();// 提交事务
		HibernateUtil.closeSession();// 关闭
		return list;
    }
    

}
