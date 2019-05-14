package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {

//    private SQLiteDatabase db = null;
//    private MyOpenHelper helper = null;
//    List<Xianshi> xianshiList;

 //   @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chazhao, container, false);
//        ListView listView = view.findViewById(R.id.lv);
//        xianshiList = new ArrayList<Xianshi>();
//        ContentValues contentValues = new ContentValues();
//        Cursor cursor = db.query("information",new String[]{"name","years","fangjian"},"biaoshi = ?",new String[]{"1"},null,null,null);
        final RadioButton checkbox1=view.findViewById(R.id.checkBox);
        final RadioButton checkbox2=view.findViewById(R.id.checkBox1);
        final RadioButton checkbox3=view.findViewById(R.id.checkBox2);
        Button b1=view.findViewById(R.id.b1);
        Button b2=view.findViewById(R.id.b2);
        final EditText edit=view.findViewById(R.id.sousuo);
//        helper = new MyOpenHelper(getContext());
//        db = helper.getReadableDatabase();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String xuanzhong="";
                Jieguo jieguo=null;
                if(checkbox1.isChecked())
                {
                    jieguo = new Jieguo();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.cont_frame, jieguo);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("shuju","id");
                    bundle.putString("id",edit.getText().toString());
                    jieguo.setArguments(bundle);
                }
                if(checkbox2.isChecked())
                {
                    jieguo = new Jieguo();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.cont_frame, jieguo);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("shuju","xingming");
                    bundle.putString("name",edit.getText().toString());
                    jieguo.setArguments(bundle);
                }
                if(checkbox3.isChecked())
                {
                    jieguo = new Jieguo();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.cont_frame, jieguo);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.addToBackStack(null);
                    ft.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("shuju","fangjian");
                    bundle.putString("fangjian",edit.getText().toString());
                    jieguo.setArguments(bundle);
                }
                }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit.setText("");
                checkbox1.setChecked(true);
                checkbox2.setChecked(false);
                checkbox3.setChecked(false);
            }
        });

/*        while (cursor.moveToNext()){
            String fangjian = cursor.getString(cursor.getColumnIndex("fangjian"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String nianling = cursor.getString(cursor.getColumnIndex("years"));
            String id=cursor.getString(cursor.getColumnIndex("id"));
            Xianshi xianshi = new Xianshi(name,nianling,fangjian,id);
            xianshiList.add(xianshi);
      SearchAdapter searchAdapter = new SearchAdapter();
        listView.setAdapter(searchAdapter);
        }*/

        return view;

    }
}
