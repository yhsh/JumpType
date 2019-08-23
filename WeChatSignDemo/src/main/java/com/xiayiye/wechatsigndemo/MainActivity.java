package com.xiayiye.wechatsigndemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.pm.PackageManager.GET_RESOLVED_FILTER;


public class MainActivity extends Activity {
    private TextView errorTv;
    private Button getBtn;
    private EditText pkgNameEt;
    private TextView resultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pkgNameEt = ((EditText) findViewById(R.id.et_package_name));
        resultTv = ((TextView) findViewById(R.id.tv_result));
        errorTv = ((TextView) findViewById(R.id.tv_error));
        getBtn = ((Button) findViewById(R.id.bt_get_result));
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTv.setText("");
                errorTv.setText("");
                String str = pkgNameEt.getText().toString();
                if ((str != null) && (str.length() > 0)) {
                    getSign(str);
                }
            }
        });
        showDialog();
    }

    private void errout(String paramString) {
        errorTv.append(paramString + "\n");
    }

    @SuppressLint("WrongConstant")
    private Signature[] getRawSignature(Context paramContext, String paramString) {
        if ((paramString == null) || (paramString.length() == 0)) {
            errout("getSignature, packageName is null");
            return null;
        }
        PackageManager localPackageManager = paramContext.getPackageManager();
        PackageInfo localPackageInfo;
        try {
            localPackageInfo = localPackageManager.getPackageInfo(paramString, GET_RESOLVED_FILTER);
            if (localPackageInfo == null) {
                errout("info is null, packageName = " + paramString);
                return null;
            }
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            errout("NameNotFoundException");
            return null;
        }
        return localPackageInfo.signatures;
    }

    private void getSign(String paramString) {
        Signature[] arrayOfSignature = getRawSignature(this, paramString);
        if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)) {
            errout("signs is null");
        }
        if (arrayOfSignature != null) {
            for (Signature signature : arrayOfSignature) {
                stdout(MD5.getMessageDigest(signature.toByteArray()));
            }
        } else {
            if (errorTv.getText().toString().trim().contains("NameNotFoundException")) {
                Toast.makeText(this, "请先安装输入包名的apk的正式版再试！", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void showDialog() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setCancelable(false);
        localBuilder.setTitle("声明").setMessage("此工具仅用于获取安装到手机的第三方应用签名信息,点击 \"确定\"  继续操作,点击 \"取消\" 退出程序。");
        localBuilder.setPositiveButton("确定", null);
        localBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                finish();
            }
        });
        localBuilder.show();
    }

    private void stdout(String paramString) {
        resultTv.append(paramString + "\n");
    }

}
