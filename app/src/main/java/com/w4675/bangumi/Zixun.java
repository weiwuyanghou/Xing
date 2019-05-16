package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Zixun extends Fragment {
    private List<String> url;
    private SQLiteDatabase db=null;
    private MyOpenHelper helper=null;
    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {
        JsoupImage();
        super.onCreate(savedInstanceState);
    }

    public void JsoupImage() {
        try {
            url = new ArrayList<>();
            ContentValues contentValues = new ContentValues();
            Document doc = Jsoup.connect("http://www.yswol.com/jijie/jieqi/").get();
 //           Elements aaa=doc.select("body");
            Elements imglinkclass = doc.select("div.main_content");
            Elements imglink = imglinkclass.select("div.list02");
            String a=imglinkclass.attr("style");
            Log.e("tag", a);
/*            for (Element imglinks : imglink) {
            Elements diyi=imglinks.select("a");
            Elements dier=imglinks.select("ol");
            Elements disan=imglinks.select("ul");
            Elements tupian=diyi.select("img");
                    String picture = tupian.attr("src");
                    String biaoti = disan.text();
                    String jianjie = dier.text();
             /*       contentValues.put("name", jianjie);
                    contentValues.put("image", picture);
                    contentValues.put("ytb", biaoti);*/
             //       db.insert("information", null, contentValues);
             //       url.add(picture);
                    //       if (!picture.isEmpty()) {
                    //    Glide.with(this).load(picture).diskCacheStrategy(DiskCacheStrategy.SOURCE).preload();
                    // Log.e("tag", "" + ytblink);
                    //} else {
  //                      Log.e("tag",picture );*/
                    //}
  //          }
        } catch (Exception e) {
            Log.e("tag", "catch");
            e.printStackTrace();
        }
    }
}
