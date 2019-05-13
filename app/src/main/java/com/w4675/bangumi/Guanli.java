package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class Guanli extends Fragment {
    SettingAdapter settingAdapter;
    List<Xianshi> xianshiList;
    private SQLiteDatabase db = null;
    private MyOpenHelper helper = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview1, container, false);
        ListView listView = view.findViewById(R.id.lv);
//        ftv.setText("Setting");

        helper = new MyOpenHelper(getContext());
        db = helper.getReadableDatabase();

        xianshiList = new ArrayList<Xianshi>();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.query("information",new String[]{"name","years","fangjian"},"biaoshi = ?",new String[]{"1"},null,null,null);
        while (cursor.moveToNext()){
            String fangjian = cursor.getString(cursor.getColumnIndex("fangjian"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String nianling = cursor.getString(cursor.getColumnIndex("years"));
            Xianshi xianshi = new Xianshi(name,nianling,fangjian);
            xianshiList.add(xianshi);

        }

        SettingAdapter settingAdapter = new SettingAdapter();
        listView.setAdapter(settingAdapter);
        return view;
    }
    class SettingAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return xianshiList.size();
        }

        @Override
        public Xianshi getItem(int position) {
            return xianshiList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Xianshi u = getItem(position);
            View v;
            if(convertView == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.listview2,null);
            }
            else {
                v = convertView;
            }
    //        ImageView img1 = v.findViewById(R.id.image1);
            TextView textname = v.findViewById(R.id.textname1);
            final TextView textnianling = v.findViewById(R.id.textnianling1);
            TextView textdata = v.findViewById(R.id.textdata1);
            EditText ett=v.findViewById(R.id.sousuo);
//            String Url = "https://yt3.ggpht.com/a-/AAuE7mApYLZlPopDPw_N6QT0NL8Wo7q2EmI8QhPNXA=s300-mo-c-c0xffffffff-rj-k-no";
            //textView.setText("14:00");
            //textname.setText("2123");
//            if(ett.getText()==null)
 //           {
            textname.setText(u.getName());
            textnianling.setText(u.getNianling());
            textdata.setText("房间号："+u.getFangjian());
   /*         }
            else
                {
                    String ss=ett.getText().toString();
                    Cursor cursor = db.query("information",new String[]{"name","years","fangjian"},"id = ?",new String[]{ss},null,null,null);
                    while (cursor.moveToNext()){
                        String fangjian = cursor.getString(cursor.getColumnIndex("fangjian"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String nianling = cursor.getString(cursor.getColumnIndex("years"));
                        Xianshi xianshi = new Xianshi(name,nianling,fangjian);
                        xianshiList.add(xianshi);

                    }
                }
/*            textdata.setTextSize(TypedValue.COMPLEX_UNIT_DIP,30);
            textdata.setGravity(Gravity.CENTER);
            textdata.setText("已添加黑名单！");
//            Glide.with(getContext()).load(u.getImg()).diskCacheStrategy(DiskCacheStrategy.SOURCE).fitCenter().into(img1);
            final NiftyDialogBuilder dialogBuilder1 = NiftyDialogBuilder.getInstance(getContext());
            textdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBuilder1
                            .withMessage("确定要移出黑名单吗？")
                            .withMessageColor("#FFFFFF")
                            .withDialogColor("#0078D7")
                            .isCancelableOnTouchOutside(true)
                            .withDuration(500)
                            .withEffect(Effectstype.Fadein)
                            .withButton1Text("移出黑名单")
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ContentValues contentValues = new ContentValues();
                                    Cursor cursor = db.query("information",new String[]{"unfavo"},"name = ?" ,new String[]{(String) textname.getText()},null,null,null);
                                    try{
                                        contentValues.put("unfavo",0);
                                        db.update("information",contentValues,"name = ?",new String[]{(String) textname.getText()});
                                        Toast.makeText(getContext(),"已移出黑名单！",Toast.LENGTH_SHORT).show();
                                    }
                                    catch (Exception e){
                                        Toast.makeText(getContext(),"已经移出黑名单！",Toast.LENGTH_SHORT).show();
                                        e.printStackTrace();

                                    }
                                    dialogBuilder1.cancel();
                                }
                            })
                            .show();
                }
            });*/
            return v;
        }
    }

}

