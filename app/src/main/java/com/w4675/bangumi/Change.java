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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Change extends Fragment {
    private SQLiteDatabase db = null;
    private MyOpenHelper helper = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.change, container, false);
        Button n1 = view.findViewById(R.id.n1);
        Button n2 = view.findViewById(R.id.n2);
        TextView chid=view.findViewById(R.id.y1);
        final EditText chname=view.findViewById(R.id.y2);
        final EditText chsex=view.findViewById(R.id.y3);
        final EditText chage=view.findViewById(R.id.y4);
        final EditText chaddress=view.findViewById(R.id.y5);
        final EditText chtelephone=view.findViewById(R.id.y6);
        final EditText chfeiyong=view.findViewById(R.id.y7);
        final EditText chfangjian=view.findViewById(R.id.y8);
        final String getname = getArguments().getString("chuanshu");
        chid.setText(getname);
        helper = new MyOpenHelper(getContext());
        db = helper.getReadableDatabase();
         Cursor cursor=db.query("information",new String[]{"name","sex","years","address","telephone","feiyong","fangjian"},"id=?",new String[]{getname},null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String years = cursor.getString(cursor.getColumnIndex("years"));
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String telephone = cursor.getString(cursor.getColumnIndex("telephone"));
            String feiyong = cursor.getString(cursor.getColumnIndex("feiyong"));
            String fangjian = cursor.getString(cursor.getColumnIndex("fangjian"));
            chname.setText(name);
            chsex.setText(sex);
            chage.setText(years);
            chaddress.setText(address);
            chtelephone.setText(telephone);
            chfeiyong.setText(feiyong);
            chfangjian.setText(fangjian);
        }
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",chname.getText().toString());
                contentValues.put("sex",chsex.getText().toString());
                contentValues.put("years",chage.getText().toString());
                contentValues.put("address",chaddress.getText().toString());
                contentValues.put("telephone",chtelephone.getText().toString());
                contentValues.put("feiyong",chfeiyong.getText().toString());
                contentValues.put("fangjian",chfangjian.getText().toString());
                db.update("information",contentValues,"id = ?",new String[]{ getname});
                Toast.makeText(getContext(),"修改成功！",Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
            }
        });
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        return view;
    }



}

