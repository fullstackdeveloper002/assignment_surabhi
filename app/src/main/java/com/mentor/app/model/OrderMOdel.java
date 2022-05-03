package com.mentor.app.model;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderMOdel implements Serializable {

    String id, order_id,quantity,user_type,status,trash,created_date,price,created_at,productname,product_id,
            sumqty,sumprice,image,name;

    ArrayList<String> prductnames;

    public ArrayList<String> getPrductnames() {
        return prductnames;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getTrash() {
        return trash;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getPrice() {
        return price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getProductname() {
        return productname;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getSumqty() {
        return sumqty;
    }

    public String getSumprice() {
        return sumprice;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
