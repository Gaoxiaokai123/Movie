package com.bawei.cinemademo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.cinemademo.R;
import com.bawei.cinemademo.base.BaseActivity;
import com.bawei.cinemademo.bean.Data;
import com.bawei.cinemademo.bean.LoginBean;
import com.bawei.cinemademo.model.CallBackT;
import com.bawei.cinemademo.presenter.EmilPresenter;
import com.bawei.cinemademo.presenter.RegisterPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {


    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_emil)
    EditText registerEmil;
    @BindView(R.id.register_pwd)
    EditText registerPwd;
    @BindView(R.id.register_yanzhneg)
    EditText registerYanzhneg;
    @BindView(R.id.register_huoqu)
    Button registerHuoqu;
    @BindView(R.id.register_login)
    TextView registerLogin;
    @BindView(R.id.register_btn)
    Button login_btn;
    private RegisterPresenter registerPresenter;
    private EmilPresenter emilPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        //注册P层
        registerPresenter = new RegisterPresenter(new registerData());
        //邮箱验证
        emilPresenter = new EmilPresenter(new emilData());

        //获取验证码
        registerHuoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String youxinag = registerEmil.getText().toString().trim();
                emilPresenter.getRequestData(youxinag);
            }
        });

        //注册
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = registerName.getText().toString().trim();
                String emil = registerEmil.getText().toString().trim();
                String pass = registerPwd.getText().toString().trim();
                //调用AES加密
                String encrypt = EncryptUtil.encrypt(pass);
                String yzm = registerYanzhneg.getText().toString().trim();

                registerPresenter.getRequestData(name,emil,encrypt,yzm);

            }
        });
    }


    //注册
    class registerData implements CallBackT<Data<LoginBean>>{

        @Override
        public void onSuccess(Data<LoginBean> loginBeanData) {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            Toast.makeText(RegisterActivity.this, ""+loginBeanData.message, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Data data) {
            Toast.makeText(RegisterActivity.this, "" + data.message, Toast.LENGTH_SHORT).show();

        }
    }


    class emilData implements CallBackT<Data>{

        @Override
        public void onSuccess(Data data) {

        }

        @Override
        public void onError(Data data) {
            Toast.makeText(RegisterActivity.this, ""+data.message.toString(), Toast.LENGTH_SHORT).show();
        }
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
}
