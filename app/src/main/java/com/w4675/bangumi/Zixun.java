package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Person;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Zixun extends Fragment {
  //  private List<String> url;
   MyAdapter myAdapter;
    List<Cunchu> cunchuList;
    private SQLiteDatabase db=null;
    private MyOpenHelper helper=null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        ImageView imageView=view.findViewById(R.id.iii);
        ListView listView = view.findViewById(R.id.lv1);
       // ListView listView=view.findViewById(R.id.lv);
     //   URL url = null;
    //    WebView webView=view.findViewById(R.id.ww);
   //    webView .getSettings().setJavaScriptEnabled(true);
   //     webView.loadUrl("https://www.baidu.com/");
     //   Glide.with(getContext()).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558338549458&di=9f3840a818f7b96b00ad669021255ae1&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fae301becc97016fde8f4178239c258263b44f5e08d1e-L7CDKh_fw658").fitCenter().into(imageView);
 //       JsoupImage();
        cunchuList = new ArrayList<Cunchu>();
        try {
            Document doc = Jsoup.connect("https://m.cndzys.com/jijie/jieqi/")
                    //.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:5.0)Gecko/20100101 Firefox/5.0")
                    .get();
            Elements imglink=doc.select("div.tag_list");
            Elements imglink1=imglink.select("ul");
            for (Element text : imglink1){
                Elements xunhuan1=text.select("li");
                for (Element xunhuan2 : xunhuan1){
                    Elements dizhi1 =xunhuan2.select("a");
                    String dizhi=dizhi1.attr("href");
                    Element biaoti1=xunhuan2.select("p").first();
                    String biaoti=biaoti1.text();
                    Elements shijian1=xunhuan2.select("p.p1");
                    String shijian=shijian1.text();
                    Cunchu cunchu = new Cunchu(dizhi,biaoti,shijian);
                    cunchuList.add(cunchu);
                }
            }
            Elements element=imglink1.select("li");
            Elements elements = element.select("a");
            String a=elements.attr("href");
           // Log.e("tag",a);

        } catch (Exception e) {
            Log.e("tag", "catch");
            e.printStackTrace();
        }
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        return view;
    }




     class MyAdapter  extends BaseAdapter {
         @Override
         public int getCount() {
             return cunchuList.size();
         }

         @Override
         public Cunchu getItem(int position) {
             return cunchuList.get(position);
         }

         @Override
         public long getItemId(int position) {
             return position;
         }

         @Override
         public View getView(int position, View convertView, ViewGroup parent) {
             final Cunchu c=getItem(position);
             View v;
             if(convertView==null){
                 v=LayoutInflater.from(getContext()).inflate(R.layout.xinlistview,null);
             }
                else{
                    v=convertView;
             }
             final TextView textView=v.findViewById(R.id.txv1);
             TextView textdata=v.findViewById(R.id.txv2);
             textView.setText(c.getBiaoti());
             textdata.setText(c.getShijian());
             final NiftyDialogBuilder dialogBuilder1 = NiftyDialogBuilder.getInstance(getContext());
             textdata.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 dialogBuilder1.withTitle(textView.getText());
                                                 dialogBuilder1.withTitleColor("#ffffff");
                                                 dialogBuilder1.withDialogColor("#23ade5");
                                                 dialogBuilder1.isCancelableOnTouchOutside(true);
                                                 dialogBuilder1.withDuration(500);
                                                 dialogBuilder1.withEffect(Effectstype.Fadein);
                                                 dialogBuilder1.withButton1Text("查看");
                                                 dialogBuilder1.setButton1Click(new View.OnClickListener() {
                                                     Wangye wangye = null;
                                                     @Override
                                                     public void onClick(View v) {
                                                         wangye=new Wangye();
                                                         FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                         ft.replace(R.id.cont_frame, wangye);
                                                         ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                                         ft.addToBackStack(null);
                                                         ft.commit();
                                                         Bundle bundle = new Bundle();
                                                         bundle.putString("dizhi", c.getDizhi());
                                                         wangye.setArguments(bundle);
                                                    //     Toast.makeText(getContext(), c.getDizhi(), Toast.LENGTH_SHORT).show();
                                                         dialogBuilder1.cancel();
                                                     }
                                                 }).show();

                                             }
                                         });
            // Log.e("tag",c.getDizhi());
             return v;
         }
     }
}

