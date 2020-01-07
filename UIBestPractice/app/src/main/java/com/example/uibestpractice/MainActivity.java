package com.example.uibestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> mMsgList = new ArrayList<>();

    private EditText mEditText;

    private Button mSendButton;

    private RecyclerView mRecyclerView;

    private MsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsg();
        mEditText = findViewById(R.id.input_text);
        mSendButton = findViewById(R.id.send);
        mRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MsgAdapter(mMsgList);
        mRecyclerView.setAdapter(mAdapter);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    mMsgList.add(msg);
                    mAdapter.notifyItemInserted(mMsgList.size()-1);
                    mRecyclerView.scrollToPosition(mMsgList.size()-1);
                    mEditText.setText("");
                }
            }
        });
    }

    private void initMsg() {
        Msg msg1 = new Msg("hello guy", Msg.TYPE_RECEIVED);
        mMsgList.add(msg1);
        Msg msg2 = new Msg("hello, who is that?", Msg.TYPE_SEND);
        mMsgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED);
        mMsgList.add(msg3);
    }
}
