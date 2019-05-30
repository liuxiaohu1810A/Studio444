package com.example.day02.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
@Entity
public class DatasBean {
    @Id
    private Long id;
    private String envelopePic;
    private String link;
    private String title;
    @Generated(hash = 411757703)
    public DatasBean(Long id, String envelopePic, String link, String title) {
        this.id = id;
        this.envelopePic = envelopePic;
        this.link = link;
        this.title = title;
    }
    @Generated(hash = 128729784)
    public DatasBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEnvelopePic() {
        return this.envelopePic;
    }
    public void setEnvelopePic(String envelopePic) {
        this.envelopePic = envelopePic;
    }
    public String getLink() {
        return this.link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


}
