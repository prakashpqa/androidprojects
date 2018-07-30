package com.prakashpweb.otpsync;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText ipAddress, portNo, userName, passWord;
    private Button button;
    public static final String OTP_REGEX = "[0-9]{1,6}";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ipAddress = findViewById(R.id.ipaddress);
        portNo = findViewById(R.id.portnum);
        userName = findViewById(R.id.username);
        passWord = findViewById(R.id.password);
        button = findViewById(R.id.saveBtn);

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                Log.e("Message",messageText);
                Toast.makeText(MainActivity.this,"Message: "+messageText,Toast.LENGTH_LONG).show();

                Pattern pattern = Pattern.compile(OTP_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                String otp = null;
                while (matcher.find())
                {
                    otp = matcher.group();
                }
                Toast.makeText(MainActivity.this,"OTP: "+ otp ,Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveToDB(View view) {
        try {
            SQLiteDatabase database = new SampleSQLiteDBHelper(this).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(SampleSQLiteDBHelper.OTPSYNC_COLUMN_IPADDRESS, ipAddress.getText().toString());
            values.put(SampleSQLiteDBHelper.OTPSYNC_COLUMN_PORTNO, portNo.getText().toString());
            values.put(SampleSQLiteDBHelper.OTPSYNC_COLUMN_DBUSERNAME, userName.getText().toString());
            values.put(SampleSQLiteDBHelper.OTPSYNC_COLUMN_DBPASSWORD, passWord.getText().toString());
            long newRowId = database.insert(SampleSQLiteDBHelper.OTPSYNC_TABLE_NAME, null, values);
            Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
