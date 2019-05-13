package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Search extends Fragment {

    private SQLiteDatabase db = null;
    private MyOpenHelper helper = null;
    List<Xianshi> xianshiList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview1, container, false);
        ListView listView = view.findViewById(R.id.lv);
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
      SearchAdapter searchAdapter = new SearchAdapter();
        listView.setAdapter(searchAdapter);
        return view;

    }

    private class SearchAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
