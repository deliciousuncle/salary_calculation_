package com.example.salary_calculation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

public class Money_From_The_Company_Activity extends AppCompatActivity {
  private  double mothly_bouns,basesalary,base_year_end_bounes,Max_year_end_bounes;
  private EditText salary_raise,seniority; int salary_raise_data,seniority_data;
  private TextView least_money,make_the_most_money;
  private double earn_the_least,earn_the_most;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_from_the_company);
        initview();
        initvalue();
        salary_raise.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        seniority.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
    }
    public void getviewdata(){//取得年資資料,及加薪資料
        if(salary_raise.getTextSize()==0&&seniority.getTextSize()==0){salary_raise_data=0;seniority_data=0;}
        if(seniority.getTextSize()==0&&salary_raise.getTextSize()>0){seniority_data=0;salary_raise_data=Integer.parseInt(salary_raise.getText().toString());}
        if(salary_raise.getTextSize()==0&&seniority.getTextSize()>0){salary_raise_data=0;seniority_data=Integer.parseInt(seniority.getText().toString());}
        else {salary_raise_data=Integer.parseInt(salary_raise.getText().toString());
        seniority_data=Integer.parseInt(seniority.getText().toString());}
    }
    public void initvalue(){//取得每月獎金,底薪,最低年終月份,最高年終月份
        mothly_bouns=getIntent().getDoubleExtra("mothly_bouns",-1);
        basesalary=getIntent().getDoubleExtra("basesalary",-1);
        base_year_end_bounes=getIntent().getDoubleExtra("base_year_end_bounes",-1);
        Max_year_end_bounes=getIntent().getDoubleExtra("Max_year_end_bounes",-1);
        ////第一年年薪
        earn_the_least=(mothly_bouns+basesalary)*12+base_year_end_bounes*basesalary;
        earn_the_most=(mothly_bouns+basesalary)*12+Max_year_end_bounes*basesalary;
    }
    public void initview(){
        salary_raise=findViewById(R.id.salary_raise);
        seniority=findViewById(R.id.seniority);
        least_money=findViewById(R.id.least_money);
        make_the_most_money=findViewById(R.id.make_the_most_money);
    }

    public void calculate_money_from_the_company_btn(View view) { //計算一輩子獲取的薪水
        initvalue();
        getviewdata();
        for(int i=0;i<=seniority_data-1;i++){
            basesalary=basesalary+salary_raise_data;
            earn_the_least=earn_the_least+(mothly_bouns+basesalary)*12+base_year_end_bounes*basesalary;
            earn_the_most= earn_the_most+(mothly_bouns+basesalary)*12+Max_year_end_bounes*basesalary;
        }
        least_money.setText(earn_the_least+"");
        make_the_most_money.setText(earn_the_most+"");
    }

    public void back(View view) {
        finish();
    }
}