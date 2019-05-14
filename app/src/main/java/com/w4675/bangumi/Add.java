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

public class Add extends Fragment  implements View.OnClickListener{
    private Button bt1,bt2;
    private EditText aet1,aet2,aet3,aet4,aet5,aet6,aet7;
    private SQLiteDatabase db = null;
    private MyOpenHelper helper = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add, container, false);
        bt1=view.findViewById(R.id.abt1);
        bt2=view.findViewById(R.id.abt2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        aet1=view.findViewById(R.id.et1);
        aet2=view.findViewById(R.id.et2);
        aet3=view.findViewById(R.id.et3);
        aet4=view.findViewById(R.id.et4);
        aet5=view.findViewById(R.id.et5);
        aet6=view.findViewById(R.id.et6);
        aet7=view.findViewById(R.id.et7);
        return view;
    }


    @Override
    public void onClick(View v) {
        Shouye fragment1 = null;
        String title = getString(R.string.app_name);
         ContentValues contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.abt1:
                helper = new MyOpenHelper(getContext());
                db = helper.getReadableDatabase();
//                try {
                String id = aet1.getText().toString();
                String name = aet2.getText().toString();
                String sex = aet3.getText().toString();
                String year = aet4.getText().toString();
                String address = aet5.getText().toString();
                String telephone = aet6.getText().toString();
                String feiyong = aet7.getText().toString();
                contentValues.put("id", id);
                contentValues.put("name", name);
                contentValues.put("sex", sex);
                contentValues.put("years", year);
                contentValues.put("address", address);
                contentValues.put("telephone", telephone);
                contentValues.put("feiyong", feiyong);
                contentValues.put("biaoshi", 1);
      //          Cursor cursor = db.query("information",new String[]{"id"},"id = ?" ,new String[]{aet1.getText().toString()},null,null,null);
        //      while (cursor.moveToNext()){
          //      String check = cursor.getString(cursor.getColumnIndex("id"));
 //               cursor.close();
            //      if (id.equals(check)==false)
                {
        //           while (cursor.moveToNext()) {
                       db.insert("information", null, contentValues);
                       Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show();
              //     }
               //    }
             //  else
               // {
                //    Toast.makeText(getContext(),"添加失败！id重复",Toast.LENGTH_SHORT).show();
               // }

                }
                break;
            case R.id.abt2:
                fragment1 = new Shouye();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.cont_frame, fragment1);
                ft.commit();
                break;
        }
    }
}