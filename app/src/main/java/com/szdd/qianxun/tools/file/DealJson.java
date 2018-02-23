package com.szdd.qianxun.tools.file;

import android.content.Context;

import com.alibaba.fastjson.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DealJson {

    public static String getProvinceText(Context context) {
        try {
            InputStream in = null;
            in = context.getResources().getAssets().open("province.json");
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (br != null) {
                br.close();
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }

    public static String getSchoolText(Context context) {
        try {
            InputStream in = null;
            in = context.getResources().getAssets().open("school.json");
            BufferedReader br = null;
            StringBuilder sb = new StringBuilder();
            String line;
            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (br != null) {
                br.close();
            }
            return sb.toString();
        } catch (Exception e) {
        }
        return "";
    }

    public static JSONArray getPrivinceArray(Context context) {
        try {
            return JSONArray.parseArray(getProvinceText(context));
        } catch (Exception e) {
            return null;
        }
    }

    public static JSONArray getSchoolArray(Context context) {
        try {
            return JSONArray.parseArray(getSchoolText(context));
        } catch (Exception e) {
            return null;
        }
    }

}
