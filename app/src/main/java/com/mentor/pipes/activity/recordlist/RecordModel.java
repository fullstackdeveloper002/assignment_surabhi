package com.mentor.pipes.activity.recordlist;

import com.mentor.pipes.activity.recordlist.model.HistoryModel;

import java.io.Serializable;
import java.util.ArrayList;

public class RecordModel implements Serializable {
    String id, order_id, user_id, to_user, product_id, quantity, user_type, status, created_date,productName,remaining_qty;

    ArrayList<HistoryModel> stock;
    ArrayList<HistoryModel> history;

    public String getId() {
        return id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getTo_user() {
        return to_user;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public ArrayList<HistoryModel> getStock() {
        return stock;
    }

    public ArrayList<HistoryModel> getHistory() {
        return history;
    }

    public String getProductName() {
        return productName;
    }

    public void setStock(ArrayList<HistoryModel> stock) {
        this.stock = stock;
    }

    public void setHistory(ArrayList<HistoryModel> history) {
        this.history = history;
    }

    public String getRemaining_qty() {
        return remaining_qty;
    }
}
