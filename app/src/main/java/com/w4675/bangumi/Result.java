package com.w4675.bangumi;

public class Result {
    private String name;
    private String nianling;
    private String fangjian;
    private String id;


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

    public String getFangjian() { return fangjian; }
    public void setFangjian(String chuangwei ){ this.fangjian = chuangwei; }
    public String getid() { return id; }
    public void setid(String id ){ this.id = id; }
    public Result(String name, String nianling, String fangjian,String id) {
        super();
        this.name = name;
        this.nianling =nianling;
        this.fangjian = fangjian;
        this.id=id;

    }
}
