package com.szdd.qianxun.start.welcome;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.szdd.qianxun.R;
import com.szdd.qianxun.main_main.MainMain;
import com.szdd.qianxun.message.baichuan.im.LoginSampleHelper;
import com.szdd.qianxun.message.baichuan.mine.BaiChuanUtils;
import com.szdd.qianxun.message.baichuan.util.AppUtil;
import com.szdd.qianxun.message.msg_tool.InfoTool;
import com.szdd.qianxun.message.msg_tool.MsgTool;
import com.szdd.qianxun.message.msg_tool.ParamTool;
import com.szdd.qianxun.message.msg_tool.UserStateTool;
import com.szdd.qianxun.more.AboutMe;
import com.szdd.qianxun.start.login.Login;
import com.szdd.qianxun.start.tool.UserTool;
import com.szdd.qianxun.tools.connect.ConnectDialog;
import com.szdd.qianxun.tools.connect.ConnectEasy;
import com.szdd.qianxun.tools.connect.ConnectList;
import com.szdd.qianxun.tools.connect.ConnectListener;
import com.szdd.qianxun.tools.connect.ServerURL;
import com.szdd.qianxun.tools.top.NetActivity;

public class Welcome extends NetActivity {
    private String name = "", pass = "";
    private String msg = "";
    private boolean need_close = false;

    @Override
    public void onCreate() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.start_welcome);

        if (isSeriousError())
            return;
        getMessage();
        UserTool.setLoginState(this, false);// 先保存为未登录状态，然后在login中修改

        if (!UserStateTool.isLoginEver(this)) {
            LoginSampleHelper.getInstance().initIMKitTemp();
        }

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (UserTool.isFirstRun(Welcome.this)) {
                    startActivity(new Intent(Welcome.this, FirstRun.class));
                    finish();
                } else {
                    autoLogin();
                }
            }
        }, 1000);// 时间可以慢慢调整
    }

    private boolean isSeriousError() {
        String value = ParamTool.getParam("serious_error");
        if (value.equals("1")) {
            Toast.makeText(this, "系统正在维护", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AboutMe.class);
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    private void getMessage() {
        msg = getIntent().getStringExtra("note_extra");
        if (msg == null)
            msg = "";
    }

    private void autoLogin() {
        String[] user = UserTool.getUser(this);
        name = user[0];
        pass = user[1];
        if (name.equals("") || pass.length() < 6) {// 未登录过，或者是测试的
            pass = "";
            toLoginActivity();
            return;
        }
        // 有登录记录，到login中自动登录（减少重复代码）
        startLogin();
    }

    private void toLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }

    private void toMainActivity() {
        //if (!msg.equals("")) {}
        Intent intent = new Intent(this, MainMain.class);
        startActivity(intent);
        finish();
    }

    private void startLogin() {
        ConnectEasy.POSTLOGIN(this, ServerURL.LOGIN, new ConnectListener() {
            public ConnectDialog showDialog(ConnectDialog dialog) {
                return null;
            }

            public ConnectList setParam(ConnectList list) {
                list.put("appSecret", Login.APP_SECRET);
                list.put("username", name);
                list.put("password", pass);
                return list;
            }

            public void onResponse(String response) {
                loginBaiChuan(response); // 失败等都在这里面处理了
            }
        });
    }

    private void loginBaiChuan(String response) {
        String pass = MsgTool.dealResponseGetPass(response);
        String name = null;
        try {
            name = BaiChuanUtils.getUserName(Long.parseLong(InfoTool.getUserID(this)));
        } catch (Exception e) {
            return;
        }
        // 保存登录状态
        UserTool.setLoginState(Welcome.this, true);// 保存为登录状态
        BaiChuanUtils.login(name, pass, null);//在后台慢慢地登录去吧
        //等待3秒钟，减少网络占用
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppUtil.dismissProgressDialog();
                toMainActivity();
            }
        }, 3000);
//        AppUtil.dismissProgressDialog();
//        if (state) {
//            toMainActivity();
//        } else {
//            BaiChuanUtils.showToast("登录失败");
//            toLoginActivity();
//        }
    }

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }


    @Override
    public void receiveMessage(String what) {

    }

    @Override
    public void newThread() {
        while (!need_close) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }

        }
    }

}
