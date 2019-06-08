package com.sinothk.loginSharePay.demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sinothk.qq.QQDemoMainActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.sourceforge.simcpux.WeiXinMainActivity;

public class LoginSharePayDemoMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void gotoWX(View view) {
        startActivity(new Intent(this, WeiXinMainActivity.class));
    }

    public void gotoQQ(View view) {
        startActivity(new Intent(this, QQDemoMainActivity.class));
    }

    public void gotoZFB(View view) {

    }
}
