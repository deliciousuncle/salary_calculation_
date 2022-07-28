package com.example.salary_calculation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class monthly_salary extends AppCompatActivity {
    TextView Monthly_Salary, Salary,your_base_salary,your_monthly_bouns;
    EditText basic_salary,monthly_bonus;
    Button clear1,clear2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_salary);
       basic_salary=findViewById(R.id.Basic_salary);
       monthly_bonus=findViewById(R.id.monthly_bonus);
       basic_salary.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
       monthly_bonus.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
       Monthly_Salary=findViewById(R.id.Monthly_Salary);
        your_base_salary=findViewById(R.id.your_base_salary);
        your_monthly_bouns=findViewById(R.id.your_monthly_bouns);
        Salary=findViewById(R.id.Salary);
        clear1=findViewById(R.id.clear1);
        clear2=findViewById(R.id.clear2);
    }

    public void calculate_monthly_salary(View view) {
if(basic_salary.length()==0||monthly_bonus.length()==0){Salary.setText("底薪或獎金未輸入");return;}
        int basesalary=Integer.parseInt(basic_salary.getText().toString());
        your_base_salary.setText(basesalary+"");
        int monthlybonus=Integer.parseInt((monthly_bonus.getText().toString()));
        your_monthly_bouns.setText(monthlybonus+"");
        Salary.setText(basesalary+monthlybonus+"");
    }

    public void clear(View view) {
        if(view.getId()==clear1.getId())
        {
            basic_salary.setText("");
        }
        if(view.getId()==clear2.getId())
        {
            monthly_bonus.setText("");
        }
    }

    public void inputdata(View view) {
        Intent intent=new Intent();
        intent.putExtra("base_Salary",Integer.parseInt(your_base_salary.getText().toString()));
        intent.putExtra("mothly_bouns",Integer.parseInt(your_monthly_bouns.getText().toString()));
       // intent.putExtra("Salary",Integer.parseInt(Salary.getText().toString()));
        setResult(1,intent);
        finish();
    }
}