package com.bawei.cinemademo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.base.BaseActivity;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.LoginBean;
import com.bawei.cinemademo.frag.FragHome;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_email)
    EditText loginEmail;
    @BindView(R.id.login_pwd)
    EditText loginPwd;
    @BindView(R.id.login_wangji)
    Button loginWangji;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.login_Wechat)
    ImageButton loginWechat;
    private LoginPresenter loginPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        loginPresenter = new LoginPresenter(new loginData());

        //跳转注册页面
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });


        //登录
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emil = loginEmail.getText().toString().trim();
                String pass = loginPwd.getText().toString().trim();
                String encrypt = EncryptUtil.encrypt(pass);

                loginPresenter.getRequestData(emil,encrypt);
            }
        });


    }

    class loginData implements CallBackT<LoginBean>{

        @Override
        public void onSuccess(LoginBean loginBean) {
            startActivity(new Intent(LoginActivity.this, FragHome.class));
        }

        @Override
        public void onError(Data data) {
            Toast.makeText(LoginActivity.this, ""+data.message, Toast.LENGTH_SHORT).show();
        }
    }

}
