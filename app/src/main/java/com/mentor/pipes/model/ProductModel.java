package com.mentor.pipes.model;

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
