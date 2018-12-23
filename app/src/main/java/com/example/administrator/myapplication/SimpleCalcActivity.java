package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SimpleCalcActivity extends AppCompatActivity {

    EditText num_1, num_2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calc);

        num_1 = findViewById(R.id.num_1);
        num_2 = findViewById(R.id.num_2);
        result = findViewById(R.id.result);
    }

    protected void clkCalc(View v) {
        Button btn = (Button) v;

        String strNum1 = num_1.getText().toString();
        String strNum2 = num_2.getText().toString();
        String calcSymbol = btn.getText().toString();

        if( strNum1.equals(" ") || strNum2.equals(" ") ) {
            Toast.makeText(SimpleCalcActivity.this, "공백이 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (calcSymbol.equals("/") && strNum2.equals("0")) {
            Toast.makeText(SimpleCalcActivity.this, "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        int intNum1 = Integer.parseInt(strNum1);
        int intNum2 = Integer.parseInt(strNum2);
        int resultNum = 0;

//        String calcSymbol = btn.getText().toString();

        switch (calcSymbol) {
            case "+" :
                resultNum = intNum1 + intNum2;
                break;

            case "-" :
                resultNum = intNum1 - intNum2;
                break;

            case "*" :
                resultNum = intNum1 * intNum2;
                break;

            case "/" :
                resultNum = intNum1 / intNum2;
                break;

            default :
                break;
        }

        String strResult = Integer.toString(resultNum);

        result.setText(strResult);

//        Toast.makeText(SimpleCalcActivity.this, strResult, Toast.LENGTH_SHORT).show();

//        Toast.makeText(SimpleCalcActivity.this, "클릭", Toast.LENGTH_SHORT).show();
    }
}
