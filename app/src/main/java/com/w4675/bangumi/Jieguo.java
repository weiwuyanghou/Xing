package com.w4675.bangumi;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class Jieguo extends Fragment {
    private SQLiteDatabase db = null;
    private MyOpenHelper helper = null;
    List<Result> resultList;
    JieguoAdapter jieguoAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview1, container, false);
        ListView listView=view.findViewById(R.id.lv);
        helper = new MyOpenHelper(getContext());
        db = helper.getReadableDatabase();
        resultList = new ArrayList<Result>();
        switch (getArguments().getString("shuju"))
            {
                case "id":
//                    Toast.makeText(getContext(),getArguments().getString("id"),Toast.LENGTH_SHORT).show();
                     Cursor cursor = db.query("information",new String[]{"name","years","fangjian","id"},"id = ?",new String[]{getArguments().getString("id")},null,null,null);
                     while (cursor.moveToNext()){
                         String fangjian = cursor.getString(cursor.getColumnIndex("fangjian"));
                         String name = cursor.getString(cursor.getColumnIndex("name"));
                         String nianling = cursor.getString(cursor.getColumnIndex("years"));
                         String id = cursor.getString(cursor.getColumnIndex("id"));
                         Result result = new Result(name,nianling,fangjian,id);
                         resultList.add(result);}
                 break;
            case "xingming":
//                Toast.makeText(getContext(),getArguments().getString("name"),Toast.LENGTH_SHORT).show();
                Cursor cursor1 = db.query("information",new String[]{"name","years","fangjian","id"},"name = ?",new String[]{getArguments().getString("name")},null,null,null);
                while (cursor1.moveToNext()){
                    String fangjian = cursor1.getString(cursor1.getColumnIndex("fangjian"));
                    String name = cursor1.getString(cursor1.getColumnIndex("name"));
                    String nianling = cursor1.getString(cursor1.getColumnIndex("years"));
                    String id = cursor1.getString(cursor1.getColumnIndex("id"));
                    Result result = new Result(name,nianling,fangjian,id);
                    resultList.add(result);}
                break;
            case "fangjian":
//                Toast.makeText(getContext(),getArguments().getString("fangjian"),Toast.LENGTH_SHORT).show();
                Cursor cursor2 = db.query("information",new String[]{"name","years","fangjian","id"},"fangjian = ?",new String[]{getArguments().getString("fangjian")},null,null,null);
                while (cursor2.moveToNext()){
                    String fangjian = cursor2.getString(cursor2.getColumnIndex("fangjian"));
                    String name = cursor2.getString(cursor2.getColumnIndex("name"));
                    String nianling = cursor2.getString(cursor2.getColumnIndex("years"));
                    String id = cursor2.getString(cursor2.getColumnIndex("id"));
                    Result result = new Result(name,nianling,fangjian,id);
                    resultList.add(result);}
                break;
        }
        JieguoAdapter jieguoAdapter =new JieguoAdapter();
        listView.setAdapter(jieguoAdapter);
        return view;
    }

    private class JieguoAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return resultList.size();
        }

        @Override
        public Result getItem(int position) {
            return resultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Result u = getItem(position);
            View v;
            if(convertView == null){
                v = LayoutInflater.from(getContext()).inflate(R.layout.listview2,null);
            }
            else {
                v = convertView;
            }
            //        ImageView img1 = v.findViewById(R.id.image1);
            final TextView textname = v.findViewById(R.id.textname1);
            final TextView textnianling = v.findViewById(R.id.textnianling1);
            final TextView Textid =v.findViewById(R.id.textbianhao1);
            TextView textdata = v.findViewById(R.id.textdata1);
            EditText ett=v.findViewById(R.id.sousuo);
//            String Url = "https://yt3.ggpht.com/a-/AAuE7mApYLZlPopDPw_N6QT0NL8Wo7q2EmI8QhPNXA=s300-mo-c-c0xffffffff-rj-k-no";
            //textView.setText("14:00");
            //textname.setText("2123");
//            if(ett.getText()==null)
            //           {
            textname.setText(u.getName());
            textnianling.setText("年龄："+u.getNianling());
            textdata.setText("房间号："+u.getFangjian());
            Textid.setText(u.getid());
            final NiftyDialogBuilder dialogBuilder1 = NiftyDialogBuilder.getInstance(getContext());
            textdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogBuilder1
                            .withTitle(textname.getText())
                            .withTitleColor("#ffffff")
                            .withDialogColor("#23ade5")
                            .isCancelableOnTouchOutside(true)
                            .withDuration(500)
                            .withEffect(Effectstype.Fadein)
                            .withButton1Text("删除")
                            .withButton2Text("查看/修改")
                            .setButton1Click(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getContext(),"已删除！",Toast.LENGTH_SHORT).show();
                                    db.delete("information","id = ?" ,new String[]{ Textid.getText().toString()});
                                }
                            })
                            .setButton2Click(new View.OnClickListener() {
                                Change change = null;
                                @Override
                                public void onClick(View v) {
                                    change = new Change();
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.cont_frame, change);
                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                                    ft.addToBackStack(null);
                                    ft.commit();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("chuanshu",Textid.getText().toString());
                                    change.setArguments(bundle);
                                    dialogBuilder1.cancel();
                                }

                            }).show();

                }
            });
            return v;
        }
    }
}
