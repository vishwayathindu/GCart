/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.saletotaldao;

import com.vish.gcart.entity.Salefinaltable;
import com.vish.gcart.entity.Saletable;
import com.vish.gcart.itembean.ItemDataBean;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author vishwa_p
 */
public class SaleTotalDAO {
    public static void addSaleTotal() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        SessionFactory next = new Configuration().configure().buildSessionFactory();
        Session session = next.openSession();
        session.beginTransaction();
        Salefinaltable saleTotalItem = new Salefinaltable();
        saleTotalItem.setTotal(0);
        saleTotalItem.setDate(date);
        session.save(saleTotalItem);
        session.getTransaction().commit();
        session.close();
        next.close();
        
    }
    public static void updateTotal(int cartTotal) {
        System.out.println("---------------------start updating total-------------------------");
        SessionFactory se = new Configuration().configure().buildSessionFactory();
        Session session = se.openSession();
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Salefinaltable SET total=:cartTotalValue WHERE total=0");
        System.out.println("---------------------middele updating total-------------------------");
        query.setParameter("cartTotalValue", cartTotal);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        se.close();
        System.out.println("---------------------end updating total-------------------------");

        if (result > 0) {
            System.out.println("cartToatal updated");
        }
        

    }
    
    
}
