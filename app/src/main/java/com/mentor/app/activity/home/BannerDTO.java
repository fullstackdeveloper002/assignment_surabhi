package com.mentor.app.activity.home;

import java.io.Serializable;

public class BannerDTO implements Serializable {
    String id,redirect_url,image,title,rotation_time;

    public String getId() {
        return id;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public String getImg() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getRotation_time() {
        return rotation_time;
    }
}
