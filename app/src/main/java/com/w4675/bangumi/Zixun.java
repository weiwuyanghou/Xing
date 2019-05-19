package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Zixun extends Fragment {
  //  private List<String> url;
    private SQLiteDatabase db=null;
    private MyOpenHelper helper=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
       // ListView listView=view.findViewById(R.id.lv);
     //   URL url = null;
    //    WebView webView=view.findViewById(R.id.ww);
   //    webView .getSettings().setJavaScriptEnabled(true);
   //     webView.loadUrl("https://www.baidu.com/");
  //      JsoupImage();
        return view;
    }

/* public void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            Document doc = Jsoup.connect("http://v-sta.net/").get();
            Elements elements1 = doc.select("section");
            Elements elements2 = elements1.select("tbody");
            Elements elements3 = elements2.select("td.schedule,td.schedule now");
            for(Element text : elements3){
                Elements text1 = text.select("div.item");
                for (Element src : text1){
                    Elements time = src.select("div.date");
                    Elements title = src.select("div.title");
                    Elements name = src.select("div.name");
                    Elements img = src.select("img");
                    String imglink = img.attr("src");
                    String ttime = time.text();
                    String ttitle = title.text();
                    String tname = name.text();
//                        textView.setText(ttime);
//                        textname.setText(tname);
//                        textdata.setText(ttitle);
//                        Glide.with(getContext()).load(imglink).diskCacheStrategy(DiskCacheStrategy.SOURCE).fitCenter().into(imageView);

                    Log.e("tag",tname);
                }
            }

        }catch (Exception e){
            Log.e("tag","get error");
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
    }
*/
    public void JsoupImage() {
        try {
            //           url = new ArrayList<>();
//            ContentValues contentValues = new ContentValues();
            Document doc = Jsoup.connect("http://www.ttys5.com/shanshi/shipu/")
                   // .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0)Gecko/20100101 Firefox/5.0")
                    .get();
            //           Elements aaa=doc.select("body");
            Elements imglinkclass = doc.select("h4");
            //          Elements imglink=imglinkclass.select("li.clearfix mt15");
            //          Element b=imglink.select("a").first();
            for (Element sss : imglinkclass) {
                String c = sss.text();
                Log.e("tag", c);
            }
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
