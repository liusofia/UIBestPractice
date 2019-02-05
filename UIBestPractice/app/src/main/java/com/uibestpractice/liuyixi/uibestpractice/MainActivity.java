package com.uibestpractice.liuyixi.uibestpractice;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;

    private Button send;

    private RecyclerView msgRecyclerView;

    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initMsg();

        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);

        msgRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(lm);

        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String content = inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg = new Msg(content,Msg.TYPE_SEND);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1);//当有新消息刷新RecyclerView中的显示
                    //将RecyclerView定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);

                    inputText.setText("");//清空输入框的内容
                }
            }
        });
    }

    private void initMsg(){
        Msg msg1 = new Msg("hello sofia",Msg.TYPE_RECEIVED);

        msgList.add(msg1);

        Msg msg2 = new Msg("hello ChouLiuMang",Msg.TYPE_SEND);

        msgList.add(msg2);
    }
}
