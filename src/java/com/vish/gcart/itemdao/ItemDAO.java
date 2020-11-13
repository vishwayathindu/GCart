/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.itemdao;

import com.vish.gcart.entity.Itemtable;
import com.vish.gcart.itembean.ItemDataBean;
import com.vish.gcart.itembean.ItemInputBean;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author vishwa_p
 */
public class ItemDAO {

    public static String addItem(ItemInputBean inputBean) {

        SessionFactory next = new Configuration().configure().buildSessionFactory();
        Session session = next.openSession();
        session.beginTransaction();
        Itemtable item = new Itemtable();
        item.setItemName(inputBean.getItemName());
        item.setQuantity(inputBean.getQuntity());
        item.setUnitPrice(inputBean.getUnitPrice());
        item.setDiscountMethord(inputBean.getDiscountMethord());
        item.setDiscountType(inputBean.getDiscountType());
        item.setDiscount(inputBean.getDiscount());
        session.save(item);
        session.getTransaction().commit();
        session.close();
        next.close();

        return "itemAdded";

    }

    public static List<ItemDataBean> selectAllItem() {

        System.out.println("----------------------------------------------selecting item---------------------------");

        SessionFactory se = new Configuration().configure().buildSessionFactory();

        String hql = "from Itemtable";
        Session session = se.openSession();
        Query query = session.createQuery(hql);
        List<ItemDataBean> item = new ArrayList();

        Iterator itRe = query.iterate();
        while (itRe.hasNext()) {
            ItemDataBean dataBean = new ItemDataBean();
            Itemtable agenttmp = (Itemtable) itRe.next();
            dataBean.setItemId(agenttmp.getItemId());
            dataBean.setItemName(agenttmp.getItemName());
            dataBean.setQuntity(agenttmp.getQuantity());
            dataBean.setUnitPrice(agenttmp.getUnitPrice());
            dataBean.setDiscountType(agenttmp.getDiscountType());
            dataBean.setDiscountMethord(agenttmp.getDiscountMethord());
            dataBean.setDiscount(agenttmp.getDiscount());

            item.add(dataBean);
        }
        session.close();
        se.close();
        System.out.println("----------------------------------------------selecting item finish---------------------------");

        return item;
    }

    public static Itemtable selectAitem(Integer itemId) {
        SessionFactory se = new Configuration().configure().buildSessionFactory();
        Session session = se.openSession();
        Itemtable agenttmp = (Itemtable) session.get(Itemtable.class, itemId);
        session.close();
        se.close();
        return agenttmp;

    }

    public static int callProcedure(Integer itemId, int quntity) {
        SessionFactory se = new Configuration().configure().buildSessionFactory();
        Session session = se.openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("CALL GetDiscount(:itemcodeNEW ,:qty)");
        System.out.println(itemId+""+quntity);
        query.setParameter("itemcodeNEW", itemId);
        query.setParameter("qty", quntity);
        List results = query.list();
        session.getTransaction().commit();
        session.close();
        se.close();
        System.out.println("---------------------Total discount-------------------------"+results);
        int discountvalue =(int) results.get(0);
        return discountvalue;
    }
}
