package com.szdd.qianxun.main_main;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.szdd.qianxun.message.baichuan.im.LoginSampleHelper;

public class MainTab_01 {
    private TextView mUnread;
    private YWIMKit mIMKit;
    private IYWConversationService mConversationService;
    private IYWConversationUnreadChangeListener mConversationUnreadChangeListener;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    public MainTab_01() {
        init();
    }

    public Fragment getImFragment() {
        return mIMKit.getConversationFragment();
    }

    public void setUnreadView(TextView view) {
        mUnread = view;
    }

    public void init() {
        mIMKit = LoginSampleHelper.getInstance().getIMKit();
        if (mIMKit == null)
            return;
        mConversationService = mIMKit.getConversationService();
        initListeners();
    }

    /**
     * 初始化相关监听
     */
    private void initListeners() {
        mConversationUnreadChangeListener = new IYWConversationUnreadChangeListener() {
            //当未读数发生变化时会回调该方法，开发者可以在该方法中更新未读数
            @Override
            public void onUnreadChange() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mUnread == null)
                            return;
                        //获取当前登录用户的所有未读数
                        int unReadCount = mConversationService.getAllUnreadCount();
                        if (unReadCount > 0) {
                            mUnread.setVisibility(View.VISIBLE);
                            if (unReadCount < 100) {
                                mUnread.setText(unReadCount + "");
                            } else {
                                mUnread.setText("99+");
                            }
                        } else {
                            mUnread.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        };
    }


    public void onPause() {
        //删除会话未读消息变化的全局监听器
        mConversationService.removeTotalUnreadChangeListener(mConversationUnreadChangeListener);
    }

    public void onResume() {
        //resume时需要检查全局未读消息数并做处理，因为离开此界面时删除了全局消息监听器
        mConversationUnreadChangeListener.onUnreadChange();
        //在Tab栏增加会话未读消息变化的全局监听器
        mConversationService.addTotalUnreadChangeListener(mConversationUnreadChangeListener);
    }

}