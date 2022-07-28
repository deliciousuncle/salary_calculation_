package com.example.salary_calculation;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ActivityResultContracts.StartActivityForResult contract=new ActivityResultContracts.StartActivityForResult();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //////////////////////////// 月薪的處理
    private int basesalary,mothly_bouns;//基本薪資及獎金+津貼
   //月薪的ActivityForResult
    ActivityResultCallback<ActivityResult> monthlysalary_callback=new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
           basesalary=result.getData().getIntExtra("base_Salary",-1);
          //  monthlySalary=result.getData().getIntExtra("Salary",-1);
            mothly_bouns=result.getData().getIntExtra("mothly_bouns",-1);
        }
    };
    ActivityResultLauncher<Intent> monthlysalary_Lancer=registerForActivityResult(contract,monthlysalary_callback);
    public void inputmonthiy_salary(View view) {//進入月薪的介面
        Intent intent=new Intent(this,monthly_salary.class);
        monthlysalary_Lancer.launch(intent);
    }
    //////////////////////////////////////// 年終獎的處理
    private double base_year_end_bounes,Max_year_end_bounes;
    ActivityResultCallback<ActivityResult> year_end_bonuses_callback=new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            base_year_end_bounes=result.getData().getDoubleExtra("base_year_end_bounes",0);
            Max_year_end_bounes=result.getData().getDoubleExtra("Max_year_end_bounes",0);
        }
    };
    ActivityResultLauncher<Intent> year_end_bonuses_launcher=registerForActivityResult(contract,year_end_bonuses_callback);
    public void year_end_bonuses_btn(View view) {
        Intent intent=new Intent(this,Year_end_bonuses.class);
        intent.putExtra("baseSalary",basesalary);
        year_end_bonuses_launcher.launch(intent);
    }
    //////////////////////////////////////////////////////////
    ActivityResultCallback<ActivityResult> money_from_the_company_callback=new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

        }
    };
    ActivityResultLauncher<Intent> money_from_the_company_launcher=registerForActivityResult(contract,money_from_the_company_callback);
    public void money_from_the_company_btn(View view) {
        Intent intent=new Intent(this,Money_From_The_Company_Activity.class);
        intent.putExtra("mothly_bouns",(double) mothly_bouns);//每月獎金
        intent.putExtra("basesalary",(double)basesalary);//底薪
        intent.putExtra("base_year_end_bounes",base_year_end_bounes);//年終最少
        intent.putExtra("Max_year_end_bounes",Max_year_end_bounes);//年終最多
        money_from_the_company_launcher.launch(intent);
    }
}