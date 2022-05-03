package com.mentor.app.activity.recordlist.model;

import java.io.Serializable;

public class StockModel implements Serializable {

    String id,type,product_id,bach_no,quantity,status,created_at;

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getBach_no() {
        return bach_no;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }
}
