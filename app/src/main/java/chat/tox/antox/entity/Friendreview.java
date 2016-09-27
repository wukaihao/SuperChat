package chat.tox.antox.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wqw on 2016/3/7.
 */
public class Friendreview implements Serializable {
    private String content;
    private int id;
    private int relatedid;
    private int praise;
    private String time;
    private String toxid;

    public Friendreview() {
    }

    public Friendreview(String content, int id, int relatedid, int praise, String time, String toxid) {
        this.content = content;
        this.id = id;
        this.relatedid = relatedid;
        this.praise = praise;
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


    public int getParentid() {
        return relatedid;
    }

    public void setParentid(int relatedid) {
        this.relatedid = relatedid;
    }

    public int getPraise() {
        return praise;
    }

    public void setPraise(int praise) {
        this.praise = praise;
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
