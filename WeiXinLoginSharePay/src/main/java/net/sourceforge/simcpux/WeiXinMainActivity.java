package net.sourceforge.simcpux;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sinothk.loginSharePay.weixin.R;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.JumpToOfflinePay;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import net.sourceforge.simcpux.bean.AppPayRequest;


public class WeiXinMainActivity extends Activity {

    private Button gotoBtn, regBtn, launchBtn, scanBtn, subscribeMsgBtn, subscribeMiniProgramMsgBtn;

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        checkPermission();
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);

        regBtn = (Button) findViewById(R.id.reg_btn);
        regBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                api.registerApp(Constants.APP_ID);
            }
        });

        gotoBtn = (Button) findViewById(R.id.goto_send_btn);
        gotoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeiXinMainActivity.this, SendToWXActivity.class));
//		        finish();
            }
        });

        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
        launchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(WeiXinMainActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
            }
        });

        subscribeMsgBtn = (Button) findViewById(R.id.goto_subscribe_message_btn);
        subscribeMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeiXinMainActivity.this, SubscribeMessageActivity.class));
//				finish();
            }
        });

        subscribeMiniProgramMsgBtn = (Button) findViewById(R.id.goto_subscribe_mini_program_msg_btn);
        subscribeMiniProgramMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WeiXinMainActivity.this, SubscribeMiniProgramMsgActivity.class));
            }
        });


        View jumpToOfflinePay = (Button) findViewById(R.id.jump_to_offline_pay);
        jumpToOfflinePay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int wxSdkVersion = api.getWXAppSupportAPI();
                if (wxSdkVersion >= Build.OFFLINE_PAY_SDK_INT) {
                    api.sendReq(new JumpToOfflinePay.Req());
                } else {
                    Toast.makeText(WeiXinMainActivity.this, "not supported", Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.jump_to_online_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    AppPayRequest vo = new AppPayRequest();
//                    vo.setAppid(Constants.APP_ID);
//
//                    PayReq req = new PayReq();
//                    //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
//                    req.appId = vo.getAppid();
//                    req.partnerId = vo.getPartnerid();
//                    req.prepayId = vo.getPrepayid();
//                    req.nonceStr = vo.getNoncestr();
//                    req.timeStamp = vo.getTimestamp();
//                    req.packageValue = vo.getPackageValue();
//                    req.sign = vo.getSign();
//                    req.extData = "app data"; // optional
//                    // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
//                    api.sendReq(req);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
                    @Override
                    public void run() {
                        AppPayRequest vo = new AppPayRequest();
                        vo.setAppid(Constants.APP_ID);

                        PayReq req = new PayReq();
                        //req.appId = "wxf8b4f85f3a794e77";  // 测试用appId
                        req.appId = vo.getAppid();
                        req.partnerId = vo.getPartnerid();
                        req.prepayId = vo.getPrepayid();
                        req.nonceStr = vo.getNoncestr();
                        req.timeStamp = vo.getTimestamp();
                        req.packageValue = vo.getPackageValue();
                        req.sign = vo.getSign();
                        req.extData = "app data"; // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);
                    }
                };
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }

    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    Constants.PERMISSIONS_REQUEST_STORAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSIONS_REQUEST_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(WeiXinMainActivity.this, "Please give me storage permission!", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}