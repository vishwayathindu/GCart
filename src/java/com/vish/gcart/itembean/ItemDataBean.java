/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vish.gcart.itembean;

/**
 *
 * @author vishwa_p
 */
public class ItemDataBean {

    private Integer itemId;
    private String itemName;
    private int quntity;
    private int unitPrice;
    private String discountType;
    private String discountMethord;
    private int discount;

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

    public void setQuntity(int quntity) {
        this.quntity = quntity;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemDataBean{" + "itemId=" + itemId + ", itemName=" + itemName + ", quntity=" + quntity + ", unitPrice=" + unitPrice + '}';
    }

}
