/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.itembean;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author vishwa_p
 */
public class ItemInputBean {

    private Integer itemId;
    private String itemName;
    private int quntity;
    private int unitPrice;
    private String discountType;
    private String discountMethord;
    private int discount;
    private String cartData;
    
    private List<ItemDataBean> pdfreportlist;
    private HashMap parameterMap;
    private ByteArrayInputStream excelStream;
    private ByteArrayInputStream pdfStream;
    private ByteArrayInputStream zipStream;
    private String reporttype;
    private String branchListString;

    public List<ItemDataBean> getPdfreportlist() {
        return pdfreportlist;
    }

    public void setPdfreportlist(List<ItemDataBean> pdfreportlist) {
        this.pdfreportlist = pdfreportlist;
    }

    public HashMap getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(HashMap parameterMap) {
        this.parameterMap = parameterMap;
    }

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
    }

    public ByteArrayInputStream getPdfStream() {
        return pdfStream;
    }

    public void setPdfStream(ByteArrayInputStream pdfStream) {
        this.pdfStream = pdfStream;
    }

    public ByteArrayInputStream getZipStream() {
        return zipStream;
    }

    public void setZipStream(ByteArrayInputStream zipStream) {
        this.zipStream = zipStream;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getBranchListString() {
        return branchListString;
    }

    public void setBranchListString(String branchListString) {
        this.branchListString = branchListString;
    }
    
    


    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDiscountMethord() {
        return discountMethord;
    }

    public void setDiscountMethord(String discountMethord) {
        this.discountMethord = discountMethord;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
    
    
    

    private List<ItemDataBean> gridModel;

//    private List<ItemDataBean> data1;
//
//    public void setData1(List<ItemDataBean> data1) {
//        this.data1 = data1;
//    }
//
//    public List<ItemDataBean> getData1() {
//        return data1;
//    }
    public List<ItemDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ItemDataBean> gridModel) {
        this.gridModel = gridModel;
    }

    public Integer getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuntity() {
        return quntity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuntity(int quantity) {
        this.quntity = quantity;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCartData() {
        return cartData;
    }

    public void setCartData(String cartData) {
        this.cartData = cartData;
    }

}
