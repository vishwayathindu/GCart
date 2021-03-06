package com.vish.gcart.entity;
// Generated Oct 23, 2020 10:52:39 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Itemtable generated by hbm2java
 */
@Entity
@Table(name="itemtable"
    ,catalog="grocerycart"
)
public class Itemtable  implements java.io.Serializable {


     private Integer itemId;
     private String itemName;
     private int quantity;
     private int unitPrice;
     private String discountType;
     private String discountMethord;
     private int discount;

    public Itemtable() {
    }

    public Itemtable(String itemName, int quantity, int unitPrice, String discountType, String discountMethord, int discount) {
       this.itemName = itemName;
       this.quantity = quantity;
       this.unitPrice = unitPrice;
       this.discountType = discountType;
       this.discountMethord = discountMethord;
       this.discount = discount;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="itemId", unique=true, nullable=false)
    public Integer getItemId() {
        return this.itemId;
    }
    
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    
    @Column(name="itemName", nullable=false)
    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="unitPrice", nullable=false)
    public int getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    
    @Column(name="discountType", nullable=false)
    public String getDiscountType() {
        return this.discountType;
    }
    
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    
    @Column(name="discountMethord", nullable=false)
    public String getDiscountMethord() {
        return this.discountMethord;
    }
    
    public void setDiscountMethord(String discountMethord) {
        this.discountMethord = discountMethord;
    }

    
    @Column(name="discount", nullable=false)
    public int getDiscount() {
        return this.discount;
    }
    
    public void setDiscount(int discount) {
        this.discount = discount;
    }




}


