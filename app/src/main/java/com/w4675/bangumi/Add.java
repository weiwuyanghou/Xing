package com.w4675.bangumi;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        aet1=view.findViewById(R.id.et1);
        aet2=view.findViewById(R.id.et2);
        aet1=view.findViewById(R.id.et3);
        aet1=view.findViewById(R.id.et4);
        aet1=view.findViewById(R.id.et5);
        aet1=view.findViewById(R.id.et6);
        aet1=view.findViewById(R.id.et7);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
       // ContentValues contentValues = new ContentValues();
        switch (v.getId()) {
            case R.id.abt1:
                break;
            case R.id.abt2:


                break;
        }
    }
}