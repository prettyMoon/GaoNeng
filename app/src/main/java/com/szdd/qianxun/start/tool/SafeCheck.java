package com.szdd.qianxun.start.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.widget.Toast;

///////////////////////////////////////////////////以后修改（phone登录）
public class SafeCheck {

	public static boolean isSafe(String input) {
		Pattern pat = Pattern.compile("\\W");
		Matcher mat = pat.matcher(input);
		if (mat.find())
			return false;
		return true;
	}

	// 判断是否为数字的方法
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isPhone(String input) {
		if (input.length() != 11)
			return false;
		return isNumber(input);
	}

	public static boolean checkLoginUser(Context context, String username) {
		if (username == null || username.equals("")) {
			showToast(context, "用户名不能为空");
			return false;
		}
		if (!isPhone(username)) {
			showToast(context, "登录失败。用户名或密码错误");
			return false;
		}
		if (!isSafe(username)) {// 字符检测
			showToast(context, "登录失败。用户名或密码错误");
			return false;
		}
		return true;
	}

	public static boolean checkLoginPass(Context context, String password) {
		if (password == null || password.equals("")) {
			showToast(context, "密码不能为空");
			return false;
		}
		if (password.length() < 6 || password.length() > 24) {
			showToast(context, "登录失败。用户名或密码错误");
			return false;
		}
		if (!isSafe(password)) {// 字符检测
			showToast(context, "登录失败。用户名或密码错误");
			return false;
		}
		return true;
	}

	public static boolean checkUser(Context context, String username) {
		if (username == null || username.equals("")) {
			showToast(context, "用户名不能为空");
			return false;
		}
		if (!isPhone(username)) {
			showToast(context, "请输入正确的手机号码");
			return false;
		}
		if (!isSafe(username)) {// 字符检测
			showToast(context, "用户名含有非法字符");
			return false;
		}
		return true;
	}

	public static boolean checkPass(Context context, String password,
			String passwordConfirm) {
		if (password == null || password.equals("")) {
			showToast(context, "密码不能为空");
			return false;
		}
		if (password.length() < 6) {
			showToast(context, "密码过短，请至少输入6位密码");
			return false;
		}
		if (password.length() > 24) {
			showToast(context, "密码过长，密码不能超过24位");
			return false;
		}
		if (!isSafe(password)) {// 字符检测
			showToast(context, "密码含有非法字符");
			return false;
		}
		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			showToast(context, "两次密码输入不一致");
			return false;
		}
	}

	public static boolean checkNickName(Context context, String name) {
		if (name == null || name.equals("")) {
			showToast(context, "昵称不能为空");
			return false;
		}
//		if (name.length() < 4) {
//			showToast(context, "昵称过短，请至少输入4位");
//			return false;
//		}
		if (name.length() > 20) {
			showToast(context, "昵称不能超过20位");
			return false;
		}
		return true;
	}

	/**
	 * 验证验证码是否为空已经是否正确
	 * 
	 * @param verifyCode
	 * @return
	 */
	public static boolean checkCode(Context context, String verifyCode) {
		if (verifyCode == null || verifyCode.equals("")) {
			showToast(context, "验证码不能为空");
			return false;
		}
		return true;
	}

	private static void showToast(Context context, String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
