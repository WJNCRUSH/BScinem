package com.example.bscinem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.bscinem.wechat.WeChatPayActivity;
import com.example.bscinem.wechat.WeChatPayWindow;
import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements OnPasswordFinishedListener{
    Button btn_login;
    EditText et_username;
    EditText et_password;
    private WeChatPayWindow weChatPayWindow;

    @BindView(R.id.main_parent)
    ConstraintLayout mainparent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Button btn_login = findViewById(R.id.button2);
        et_username = findViewById(R.id.ED1);
        et_password = findViewById(R.id.ED2);
        ButterKnife.bind(this);


       /* btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weChatPayWindow = new WeChatPayWindow(this);
                weChatPayWindow.setOnPasswordFinishedListener(this);
                weChatPayWindow.show(llParent);
                /*final Gson gs = new Gson();
                FinalHttp fh = new FinalHttp();
                String url = "http://192.168.1.115:8080/home/login";
                AjaxParams ap = new AjaxParams();
                ap.put("name", et_username.getText().toString());
                ap.put("password", et_password.getText().toString());

                fh.post(url, ap, new AjaxCallBack<Object>() {
                    @Override
                    public void onSuccess(Object o){
                        Log.d("LoginActivity", o.toString());
                        System.out.println(o.toString());
                    }
                    @Override
                    public void onFailure(Throwable t, int errorNo, String strMsg) {
                        Toast.makeText(MainActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });*/
    }

    public void onFinish(String password) {
        Intent intent =  new Intent(MainActivity.this, WeChatPayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("username", et_username.getText().toString());
        bundle.putString("password", et_password.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @OnClick(R.id.button2)
    public void onViewClicked() {
        weChatPayWindow = new WeChatPayWindow(this);
        weChatPayWindow.setOnPasswordFinishedListener(this);
        weChatPayWindow.show(mainparent);
    }
}
