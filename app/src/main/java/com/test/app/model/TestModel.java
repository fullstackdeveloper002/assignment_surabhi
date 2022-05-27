package com.test.app.model;

import java.io.Serializable;

public class TestModel implements Serializable {

    String id, section_name, image_ur1, image_ur2, image_ur3;
    boolean isVisible = false;

    public TestModel(String id, String section_name, String image_ur1, String image_ur2, String image_ur3) {
        this.id = id;
        this.section_name = section_name;
        this.image_ur1 = image_ur1;
        this.image_ur2 = image_ur2;
        this.image_ur3 = image_ur3;
    }

    public String getId() {
        return id;
    }

    public String getSection_name() {
        return section_name;
    }

    public String getImage_ur1() {
        return image_ur1;
    }

    public String getImage_ur2() {
        return image_ur2;
    }

    public String getImage_ur3() {
        return image_ur3;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
