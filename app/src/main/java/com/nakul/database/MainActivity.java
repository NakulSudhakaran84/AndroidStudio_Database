package com.nakul.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText textName,textUserName,textPassword;
    Button insertBtn,deleteBtn,updateBtn,viewBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textName=findViewById(R.id.textName);
        textUserName=findViewById(R.id.textUserName);
        textPassword=findViewById(R.id.textPassword);
        insertBtn=findViewById(R.id.insertBtn);
        deleteBtn=findViewById(R.id.deleteBtn);
        updateBtn=findViewById(R.id.updateBtn);
        viewBtn=findViewById(R.id.viewBtn);


         Database g=new Database(this);
         //SQLiteDatabase db=g.getReadableDatabase();
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=textName.getText().toString();
                String username=textUserName.getText().toString();
                String password=textPassword.getText().toString();

                if(name.equals("")||username.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this,"Enter all the fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean i=g.insert_data(name,username,password);
                    if(i==true){
                        Toast.makeText(MainActivity.this,"Successful",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Not Successful",Toast.LENGTH_SHORT).show();
                    }

                }
                textName.setText("");
                textUserName.setText("");
                textPassword.setText("");
            }
        });
        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor=g.getInfo();
                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("Name     : "+cursor.getString(1)+"\n");
                    buffer.append("Username : "+cursor.getString(2)+"\n");
                    buffer.append("Password : "+cursor.getString(3)+"\n\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Sign Up Usrers Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=textUserName.getText().toString();
                Boolean i=g.delete_data(username);
                if(i==true){
                    Toast.makeText(MainActivity.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Not Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=textName.getText().toString();
                String username=textUserName.getText().toString();
                String password=textPassword.getText().toString();
                Boolean i=g.update_data(name,username,password);
                if(i==true){
                    Toast.makeText(MainActivity.this,"Successfully Updated",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Not Updated",Toast.LENGTH_SHORT).show();
                }
                textName.setText("");
                textUserName.setText("");
                textPassword.setText("");
            }
        });
    }
}