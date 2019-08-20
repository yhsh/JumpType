package com.xiayiye.moneydemo;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @  DELL
 */
public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etInput = (EditText) findViewById(R.id.et_input);
        etInput.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().indexOf(".") == 1) {
                    isFirst = true;
                    //证明小数点前面只有一位不让删除
                } else {
                    isFirst = false;
                }
            }
        });
        etInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    String content = etInput.getText().toString();
                    int length = content.length();
                    if (isFirst) {
                        if (etInput.getSelectionStart() != 1 && etInput.getSelectionStart() != 2) {
                            return false;
                        } else if (length == 2) {
                            return false;
                        } else {
                            etInput.setSelection(length);
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return false;
            }
        });
    }
}
