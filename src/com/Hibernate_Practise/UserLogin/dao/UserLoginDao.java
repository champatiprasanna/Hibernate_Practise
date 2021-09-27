package com.Hibernate_Practise.UserLogin.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.Hibernate_Practice.UserLogin.pojo.UserLoginPojo;

import java.util.*;


public class UserLoginDao {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		Configuration conref = new Configuration();
		conref.configure("Hibernate.cfg.xml");
		SessionFactory sfref = conref.buildSessionFactory();
		Session sref = sfref.openSession();
		//Transaction tref = sref.beginTransaction();
		
		//Methods in criteria - eq, gt, lt, ge, ne, le, between, like
		Criteria crefa = sref.createCriteria(UserLoginPojo.class);
		//crefa.add(Restrictions.eq("salary", 340949));
		//crefa.add(Restrictions.gt("salary", 340949));
		//crefa.add(Restrictions.lt("salary", 340949));
		//crefa.add(Restrictions.ge("salary", 340949));
		//crefa.add(Restrictions.ne("age", 35));
		//crefa.add(Restrictions.le("salary", 340949));
		//crefa.add(Restrictions.between("age", 35, 40));
		crefa.add(Restrictions.like("name", "John"));
		
		List lrefa = crefa.list();
		Iterator itrefa = lrefa.iterator();
		while(itrefa.hasNext()) {
			UserLoginPojo upa = (UserLoginPojo)itrefa.next();
			System.out.println(upa.getName() +" " + upa.getSalary());
		}
		
		
		//To get the data in Ascending  - ascending and Descending order -desc
		Criteria crefo = sref.createCriteria(UserLoginPojo.class);
		crefo.addOrder(Order.asc("age"));
		List lrefo = crefo.list();
		Iterator itrefo = lrefo.iterator();
		while(itrefo.hasNext()) {
			UserLoginPojo upo = (UserLoginPojo)itrefo.next();
			System.out.println(upo.getName() + " " + upo.getAge());
		}
		
		
		
		//Projection -property 
		Criteria crefp = sref.createCriteria(UserLoginPojo.class);
		crefp.setProjection(Projections.property("salary"));
		List lrefc = crefp.list();
		Iterator itrefp = lrefc.iterator();
		while(itrefp.hasNext()){
			Integer pro = (Integer)itrefp.next();
			System.out.println(pro);
		}
		
		//To get the rowCount
		Criteria crefm = sref.createCriteria(UserLoginPojo.class);
		crefm.setProjection(Projections.rowCount());
		List lrefm = crefm.list();
		System.out.println("Row Count is:" + lrefm.get(0) );
		
		//To get the average 
		Criteria crefs = sref.createCriteria(UserLoginPojo.class);
		crefs.setProjection(Projections.avg("salary"));
		List lrefs = crefs.list();
		System.out.println("The average of salary is:" + lrefs.get(0) );
		
		//To get the average 
		Criteria crefM = sref.createCriteria(UserLoginPojo.class);
		crefM.setProjection(Projections.max("age"));
		List lrefM = crefM.list();
		System.out.println("The max of age is:" + lrefM.get(0) );
	
		
		
	
		//To get values between first and max - Pagination
		
		Criteria crefr = sref.createCriteria(UserLoginPojo.class);
		crefr.setFirstResult(2);
		crefr.setMaxResults(3);
		List lref = crefr.list();
		Iterator itref = lref.iterator();
		while(itref.hasNext()) {
			UserLoginPojo up = (UserLoginPojo)itref.next();
			System.out.println(up.getId() + " " + up.getSalary());
		}
		
		
		System.out.println("Data from the table!");
		//tref.commit();
		sref.close();
		sfref.close();
	}

}
