package com.szdd.qianxun.start.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.szdd.qianxun.R;
import com.szdd.qianxun.main_main.MainMain;
import com.szdd.qianxun.start.login.Login;
import com.szdd.qianxun.start.register.Register1;

public class FirstRunEnd extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start_first_run_end);

		initView();
	}

	private void initView() {
		Button btn_login = (Button) findViewById(R.id.first_run_btn_login);
		Button btn_register = (Button) findViewById(R.id.first_run_btn_register);
		Button btn_jump = (Button) findViewById(R.id.first_run_btn_jump);
		btn_login.setOnClickListener(this);
		btn_register.setOnClickListener(this);
		btn_jump.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.first_run_btn_login:
			startActivity(new Intent(this, Login.class));
			finish();
			break;
		case R.id.first_run_btn_register:
			startActivity(new Intent(this, Register1.class));
			finish();
			break;
		case R.id.first_run_btn_jump:
			startActivity(new Intent(this, MainMain.class));
			finish();
			break;
		}
	}

}
