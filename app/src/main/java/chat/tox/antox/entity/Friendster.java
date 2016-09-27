package chat.tox.antox.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wqw on 2016/3/2.
 */
public class Friendster implements Serializable{
    private int id;
    private String content;
    private List<Friendimage> images;
    private List<Friendreview> reviews;
    private String time;
    private String toxid;

    public Friendster() {
    }

    public Friendster(String content, int id, List images, List reviews, String time, String toxid) {
        this.content = content;
        this.images=images;
        this.id = id;
        this.reviews = reviews;
        this.time = time;
        this.toxid = toxid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Friendimage> getImages() {
        return images;
    }

    public void setImage(List<Friendimage> images) {
        this.images = images;
    }

    public List<Friendreview> getReviews() {
        return reviews;
    }

    public void setReview(List<Friendreview> reviews) {
        this.reviews = reviews;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getToxid() {
        return toxid;
    }

    public void setToxid(String toxid) {
        this.toxid = toxid;
    }
}
