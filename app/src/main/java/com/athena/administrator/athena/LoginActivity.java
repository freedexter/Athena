package com.athena.administrator.athena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends ActionBarActivity {
    private static Handler handler=new Handler();
    EditText editText;
    String sUsername=null,sPassword=null;
    private Button btlogin;
    CheckBox cb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  getuser();
        btlogin = (Button) findViewById(R.id.button1);
        btlogin.setOnClickListener (new View.OnClickListener()  {
            @Override
            public void onClick(View view) {
                editText = (EditText) findViewById(R.id.username);
                sUsername=editText.getText().toString();
                editText = (EditText) findViewById(R.id.password);
                sPassword=editText.getText().toString();

                // TODO Auto-generated method stub
                if( sUsername.isEmpty() ){
                    Toast.makeText( LoginActivity.this, "请输入用户名" , Toast.LENGTH_SHORT).show();
                }else {
                    if( sPassword.isEmpty()){
                        Toast.makeText( LoginActivity.this, "请输入密码" , Toast.LENGTH_SHORT).show();
                    }else {
                        //  Log.v("tag",Environment.getExternalStorageDirectory().getAbsolutePath());
                        //Log.v("tag",getExternalFilesDir("exter_test").getAbsolutePath());

                      //  LoginIn(username, password);
                    }
                }
            }
        });
    }/*
    public void getuser(){
        LoginDataBaseAdapter lg =new LoginDataBaseAdapter( this );
        cb = (CheckBox)this.findViewById(R.id.checkBox);
        username = lg.getRemberUser();
        if(username!=null){
            editText = (EditText) findViewById(R.id.name);
            editText.setText(username.trim());
            cb.setChecked(true);
            editText = (EditText) findViewById(R.id.pass);
            editText.requestFocus();
        }else {
            cb.setChecked(false);
            editText = (EditText) findViewById(R.id.name);
            editText.requestFocus();
        }
    }


    public void remberUser(){
        LoginDataBaseAdapter lg =new LoginDataBaseAdapter( this );
        cb = (CheckBox)this.findViewById(R.id.checkBox);
        if( cb.isChecked() ){
            lg.remberUser(username);
        }else{
            lg.unremberUser(username);
        }

    }

    public void LoginIn(String username,String password){
        String stat;

        LoginDataBaseAdapter lg =new LoginDataBaseAdapter( this );
        //  Log.v("tag",username+password);
        stat = lg.login(username, password);
        if( !stat.equals("False") ){
            Toast.makeText( LoginActivity.this, "Welcome "+username , Toast.LENGTH_SHORT).show();
            remberUser();
            starAct(stat);
        }else{
            Toast.makeText(LoginActivity.this, "��Ǹ�� ����������", Toast.LENGTH_SHORT).show();
        }

    }
*/
    private void starAct(String stat){
        Intent i=new Intent();
        i.setClass(getApplicationContext(), MainActivity.class);
        Bundle b = new Bundle();
        b.putString("username",sUsername);
        b.putString("stat",stat);
        i.putExtras(b);

        startActivity(i);
        finish();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

