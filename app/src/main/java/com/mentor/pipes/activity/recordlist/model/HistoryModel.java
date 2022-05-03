package com.mentor.pipes.activity.recordlist.model;

import java.io.Serializable;

public class HistoryModel implements Serializable {

    String id,bach_no,quantity,unit,price,qtymain,product_id,remaining_qty,position;

    public String getId() {
        return id;
    }

    public String getBach_no() {
        return bach_no;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public String getPrice() {
        return price;
    }

    public void setQtymain(String qtymain) {
        this.qtymain = qtymain;
    }

    public String getQtymain() {
        return qtymain;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getRemaining_qty() {
        return remaining_qty;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
