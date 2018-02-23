package com.szdd.qianxun.more;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.szdd.qianxun.R;
import com.szdd.qianxun.message.info.credit.RealNameCheck;
import com.szdd.qianxun.message.info.credit.RealStudentCheck;
import com.szdd.qianxun.message.msg_tool.InfoTool;
import com.szdd.qianxun.tools.top.TActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class UserVerify extends TActivity {
    @Bind(R.id.more_user_verify_id_status)
    TextView moreUserVerifyIdStatus;
    @Bind(R.id.more_user_verify_stu_status)
    TextView moreUserVerifyStuStatus;

    private int code = 0;

    @Override
    public void onCreate() {
        setContentView(R.layout.more_user_verify);
        ButterKnife.bind(this);
        setTitle("个人认证");
        showBackButton();
        initView();
        initActionBar(getResources().getColor(R.color.topbar_bg));
    }

    private void initView() {
        code = InfoTool.getUserInfo(this).getVerifyStatus();
        switch (code) {
            case 0:
                break;
            case 1:
                moreUserVerifyIdStatus.setText("已认证");
                break;
            case 2:
                moreUserVerifyIdStatus.setText("已认证");
                moreUserVerifyStuStatus.setText("已认证");
                break;
            default:
                break;
        }
    }

    @Override
    public void showContextMenu() {

    }

    @OnClick({R.id.more_user_verify_id, R.id.more_user_verify_stu})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.more_user_verify_id:
                if (!moreUserVerifyIdStatus.getText().equals("已认证"))
                    intent = new Intent(this, RealNameCheck.class);
                else showToast("您已通过该认证");
                break;
            case R.id.more_user_verify_stu:
                if (moreUserVerifyIdStatus.getText().equals("已认证")) {
                    if (!moreUserVerifyStuStatus.getText().equals("已认证"))
                        intent = new Intent(this, RealStudentCheck.class);
                    else showToast("您已通过该认证");
                } else showToast("请先通过身份认证");
                break;
        }
        if (intent != null) startActivity(intent);
    }
}
