package com.example.duynguyen.list_adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String str1 = "khong nhap du";
    String str2 = "nhap sai";

    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> strings;

    EditText editText1,editText2;
    TextView text;
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.text1);
        editText2 = (EditText) findViewById(R.id.text2);
        text = (TextView) findViewById(R.id.text);
        listView = findViewById(R.id.list_item);
        button = (Button)findViewById(R.id.button);

        strings = new ArrayList<String>();

        arrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,strings);
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                strings.remove(position);
                arrayAdapter.notifyDataSetChanged();
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    boolean kiem_tra_1 = kiem_tra_chuoi(editText1.getText().toString());
                    boolean kiem_tra_2 = kiem_tra_chuoi(editText2.getText().toString());
                    if(editText1.getText().toString().equals("") || editText2.getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),str1,Toast.LENGTH_LONG).show();
                    }else if(kiem_tra_1 == false || kiem_tra_2 == false){
                        Toast.makeText(getApplicationContext(),str2,Toast.LENGTH_LONG).show();
                    }else{
                        float a = Float.parseFloat(editText1.getText().toString());
                        float b = Float.parseFloat(editText2.getText().toString());
                        float s = a/b;
                        String kk = a+" / "+b+" = "+s;
                        strings.add(kk);
                        text.setText(a+" / "+b+" = "+s);
                        arrayAdapter.notifyDataSetChanged();
                    }
            }
        });

    }

    public boolean kiem_tra_chuoi(String str){
        for(int i = 0 ;i < str.length();i++){
            int st = str.codePointAt(i);
            if(st <= 48 || st >= 57){
                return false;
            }
        }
        return true;
    }

}
