package com.mentor.app.model;

import java.io.Serializable;

public class ProductModel implements Serializable {

    String id, title;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
