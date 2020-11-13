/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.vish.gcart.entity.Itemtable;
import com.vish.gcart.itembean.ItemDataBean;
import com.vish.gcart.itembean.ItemInputBean;
import com.vish.gcart.itemdao.ItemDAO;
import com.vish.gcart.saledao.saleDAO;
import com.vish.gcart.saletotaldao.SaleTotalDAO;
import com.vish.gcart.util.NewHibernateUtil;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;

import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author vishwa_p
 */
public class CartAction extends ActionSupport implements ModelDriven<Object> {

  

    ItemInputBean inputBean = new ItemInputBean();
    String result = "";
    
    String pdfName = "";
    String printFileName = null;
    JasperReport jreport;

    public String next() {
        System.out.println("----------------redirected to item page-----------------");
        retrive();
        return "next";
    }

    public String add() {

        System.out.println("--------------- adding a item---------------" + inputBean.getItemName() + "------" + inputBean.getDiscountType() + "------" + inputBean.getDiscountMethord() + "------" + inputBean.getDiscount());
        if (!"".equals(inputBean.getItemName())) {
            ItemDAO.addItem(inputBean);
            System.out.println("---------------- item adding doing-----------------");

        }
        System.out.println("---------------- item adding End-----------------");
        return "itemAdded";
    }

    public String pdfr() {
        String jasperlocation = ServletActionContext.getServletContext().getRealPath("/WEB-INF/Reports/saleIDReport.jrxml");
        System.out.println("Compiling Report Design ...");
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        SessionImplementor sim = (SessionImplementor) session;
        try {
            /**
             * Compile the report to a file name same as the JRXML file name
             */
            Map parameters = new HashMap();
            parameters.put("ID", 39);
            jreport = JasperCompileManager.compileReport(jasperlocation);
            System.out.println("---------compiled the report---------");
            JasperPrint print = JasperFillManager.fillReport(jreport, parameters, sim.connection());
            System.out.println("---------1 compiled the report---------");
            byte[] outputFile = JasperExportManager.exportReportToPdf(print);
            ByteArrayInputStream fileInputStream = new ByteArrayInputStream(outputFile);
            inputBean.setPdfStream(fileInputStream);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done compiling!!! ...");

        return "pdf";
    }

    public String pdf() {

        System.out.println("--------------- pdf---------------");

        return "pdfCompleted";
    }

    public String retrive() {
        System.out.println("----------Item retrive call----------");

        List<ItemDataBean> itemList = ItemDAO.selectAllItem();

        inputBean.setGridModel(itemList);

        System.out.println("----------Item retrive Ended----------");

        return "itemRetrived";
    }

    public String take() {
        System.out.println("----------Item details retrive call----------");

        System.out.println("--------ItemID-------------" + inputBean.getItemId());
        System.out.println("--------quntity-------------" + inputBean.getQuntity());
        int totalDiscount = ItemDAO.callProcedure(inputBean.getItemId(), inputBean.getQuntity());
        inputBean.setDiscount(totalDiscount);

        Itemtable itemData = ItemDAO.selectAitem(inputBean.getItemId());

        inputBean.setItemId(itemData.getItemId());
        inputBean.setItemName(itemData.getItemName());
        inputBean.setQuntity(itemData.getQuantity());
        inputBean.setUnitPrice(itemData.getUnitPrice());
        System.out.println("-----------------item recived-----------------" + itemData);

        return "itemDetailRetrived";
    }

    public String execute() throws Exception {
        System.out.println("----------------execute-----------------");
        return null;

    }

    public String payFor() throws Exception {
        System.out.println("----------------pay for item start working-----------------");

        Gson g = new Gson();
        List<ItemDataBean> cartItemList = g.fromJson(inputBean.getCartData(), new TypeToken<List<ItemDataBean>>() {
        }.getType());
        SaleTotalDAO.addSaleTotal();
        int cartTotal = 0;
        for (ItemDataBean saleRecord : cartItemList) {
            System.out.println(saleRecord);
            saleDAO.addsaleItem(saleRecord);
            cartTotal = cartTotal + (saleRecord.getQuntity() * saleRecord.getUnitPrice());
        }
        System.out.println("$$$$$$$$$$$$$$$$$$ " + cartTotal + "$$$$$$$$$$$$$");
        SaleTotalDAO.updateTotal(cartTotal);
        System.out.println("\"----------------pay for item End working-----------------\"");

        return "itemPaid";

    }

    @Override
    public Object getModel() {
        return inputBean;
    }

}
