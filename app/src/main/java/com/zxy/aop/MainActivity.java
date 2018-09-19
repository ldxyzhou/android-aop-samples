package com.zxy.aop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.annotation.apt.BindView;
import com.app.annotation.aspect.CheckLogin;
import com.app.aptlibrary.BindViewTools;

/**
 * @author：xinyu.zhou
 * @version: 2018/9/13
 * @ClassName:
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.tv)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BindViewTools.bind(this);
        textView.setText("bind Button success");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doMarkDown();
            }
        });
    }

    @CheckLogin
    public void doMarkDown() {
        Toast.makeText(AopApplication.getAppContext(), "点 点 在点就把你吃掉!", Toast.LENGTH_LONG).show();
    }


}
