/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.saledao;

import com.vish.gcart.entity.Itemtable;
import com.vish.gcart.entity.Saletable;
import com.vish.gcart.itembean.ItemDataBean;
import com.vish.gcart.itembean.ItemInputBean;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author vishwa_p
 */
public class saleDAO {
    public static String addsaleItem(ItemDataBean saleRecord) {
        SessionFactory next = new Configuration().configure().buildSessionFactory();
        Session session = next.openSession();
        session.beginTransaction();
        Saletable saleItem = new Saletable();
        saleItem.setItem(saleRecord.getItemName());
        saleItem.setQuantity(saleRecord.getQuntity());
        saleItem.setPrice(saleRecord.getUnitPrice()*saleRecord.getQuntity());
        session.save(saleItem);
        session.getTransaction().commit();
        session.close();
        next.close();
        
        return "sales record added";
        
    }
    
}
