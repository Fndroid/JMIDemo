package com.fndroid.jmidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.Conversation;

public class MainActivity extends AppCompatActivity {


    private Button send;
    private EditText inputView;
    private TextView outputView;
    private Conversation conversation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        JMessageClient.init(this);
        JPushInterface.setDebugMode(true);
//        JMessageClient.registerEventReceiver(this);

        initViews();
    }

    private void initViews() {
        send = (Button) findViewById(R.id.main_btn_send);
        inputView = (EditText) findViewById(R.id.main_et_input);
        outputView = (TextView) findViewById(R.id.main_tv_output);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = inputView.getText().toString().trim();
                if (conversation == null) {
                    conversation = JMessageClient.getGroupConversation(1011);
                }
                JMessageClient.sendMessage(JMessageClient.createSingleTextMessage("1011", msg));
            }
        });
    }

}
