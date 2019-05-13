package com.w4675.bangumi;

public class Xianshi {
    private String name;
    private String nianling;
    private String fangjian;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNianling() {
        return nianling;
    }
    public void setNianling(String nianling) {
        this.nianling = nianling;
    }

public String getFangjian() {
        return fangjian;
        }
public void setFangjian(String chuangwei ){
        this.fangjian = chuangwei;
        }
    public Xianshi(String name, String nianling, String fangjian) {
        super();
        this.name = name;
        this.nianling =nianling;
        this.fangjian = fangjian;

    }
        }