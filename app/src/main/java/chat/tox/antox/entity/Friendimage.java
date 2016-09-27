package chat.tox.antox.entity;

import java.io.Serializable;

/**
 * Created by wqw on 2016/3/10.
 */
public class Friendimage implements Serializable {
    private int id;
    private String time;
    private String image;
    private int relatedid;

    public Friendimage() {
    }

    public Friendimage(int id, String time, String image,int relatedid) {
        this.id = id;
        this.relatedid = relatedid;
        this.image = image;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRelatedid() {
        return relatedid;
    }

    public void setRelatedid(int relatedid) {
        this.relatedid = relatedid;
    }
}
