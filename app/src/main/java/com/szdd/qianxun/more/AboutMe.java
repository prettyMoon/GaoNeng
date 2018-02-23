package com.szdd.qianxun.more;

import com.szdd.qianxun.R;
import com.szdd.qianxun.tools.top.TActivity;

public class AboutMe extends TActivity {

	@Override
	public void onCreate() {
		setContentView(R.layout.more_about_me);
		setTitle("关于软件");
		showBackButton();
		initView();
		initActionBar(getResources().getColor(R.color.topbar_bg));

	}

	private void initView() {
	}

	@Override
	public void showContextMenu() {
	}

}
