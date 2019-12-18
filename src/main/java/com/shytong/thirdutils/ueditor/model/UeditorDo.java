package com.shytong.thirdutils.ueditor.model;

/**
 * @Description: 百度富文本实体类
 * @Author: CL
 * @Date: 2019/12/18 9:07
 */
public class UeditorDo {
    private String state;
    private String url;
    private String title;
    private String original;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }
}
