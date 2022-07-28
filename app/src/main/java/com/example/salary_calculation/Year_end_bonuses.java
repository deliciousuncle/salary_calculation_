package com.example.salary_calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedList;

public class Year_end_bonuses extends AppCompatActivity {
private int base_Salary;
private TextView year_end_bonuse_view;
private EditText base_year_end_bounes,Max_year_end_bounes;
private double base_year_end_bounes_db,Max_year_end_bounes_db;
//////////////////
    private ListView listview;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,String>> list;
    private String[] from={"year_bounes"};
    private int[] to={R.id.list_bounes};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_end_bonuses);
        base_Salary= getIntent().getIntExtra("baseSalary",0);//獲取底薪
        year_end_bonuse_view=findViewById(R.id.year_end_bonuse_view);
        base_year_end_bounes=findViewById(R.id.base_year_end_bounes);
        Max_year_end_bounes=findViewById(R.id.Max_year_end_bounes);
        base_year_end_bounes.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        Max_year_end_bounes.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        itivlistview();
    }
    public void itivlistview(){
        listview=findViewById(R.id.listview);
        list=new LinkedList<>();
        adapter=new SimpleAdapter(this,list,R.layout.listview,from,to);
        listview.setAdapter(adapter);
    }

    public void calculate_year_end_bounes_btn(View view) {
        base_year_end_bounes_db=Double.parseDouble(base_year_end_bounes.getText().toString());
        Max_year_end_bounes_db=Double.parseDouble((Max_year_end_bounes.getText().toString()));
        if(base_year_end_bounes_db>Max_year_end_bounes_db){
            return;
        }
        for(double i=base_year_end_bounes_db;i<=Max_year_end_bounes_db;i++){
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put(from[0],(double)base_Salary*i+"");
            list.add(hashMap);
            adapter.notifyDataSetChanged();
            listview.smoothScrollToPosition(list.size()-1);
        }
;    }
    public void clear_btn(View view) {
        base_year_end_bounes.setText("");
        Max_year_end_bounes.setText("");
        list.clear();
        adapter.notifyDataSetChanged();
    }
    public void getout_btn(View view) {
        Intent intent=new Intent();
        intent.putExtra("base_year_end_bounes",base_year_end_bounes_db);
        intent.putExtra("Max_year_end_bounes",Max_year_end_bounes_db);
        setResult(1,intent);
        finish();
    }
}