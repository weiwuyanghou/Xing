package com.w4675.bangumi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class First extends Fragment implements View.OnClickListener{
    private Button bt1,bt2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        bt1=view.findViewById(R.id.button);
        bt2=view.findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Add fragment1 = null;
                fragment1 = new Add();
                if (fragment1 != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.cont_frame, fragment1);
                    ft.commit();
                }
                break;
            case R.id.button2:
                Guanli guanli = null;
                guanli = new Guanli();
                if (guanli != null) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.cont_frame, guanli);
                    ft.commit();
                }
                break;
        }

    }
}