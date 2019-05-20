package com.w4675.bangumi;

public class Cunchu {
    private String dizhi;
    private String biaoti;
    private String shijian;


   
    public String getDizhi() {
        return dizhi;
    }
    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }
    public String getBiaoti() {
        return biaoti;
    }
    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }
    public String getShijian() {
        return shijian;
    }
    public void setShiajin(String shijian) {
        this.shijian = shijian;
    }

    public Cunchu( String dizhi, String biaoti,String shijian ){
      super();

        this.dizhi =dizhi;
        this.biaoti = biaoti;
        this.shijian=shijian;
}
}
