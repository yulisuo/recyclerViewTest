package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.list);
        adapter = new Adapter(this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


    static class Adapter extends RecyclerView.Adapter<Adapter.VH> {

        int init_size = 30;
        Activity context;
        ArrayList<String> data = new ArrayList<>();

        public Adapter(Activity context) {
            this.context = context;
            for (int i = 0; i < init_size; i++) {
                data.add("" + i);
            }
        }

        public void update(int p, String text) {
            data.set(p, text);
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
            VH vh = new VH(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.setPosition(position);
        }

        @Override
        public int getItemCount() {
            int size = data.size();
            Log.i("123", "size:" + size);
            return size;
        }

        class VH extends RecyclerView.ViewHolder {

            Button b;
            TextView text;
            int p;

            public VH(@NonNull View itemView) {
                super(itemView);
                b = itemView.findViewById(R.id.button);
                text = itemView.findViewById(R.id.text);

                b.setOnClickListener(v -> {
                    Intent intent = new Intent(context, SaveActivity.class);
                    intent.putExtra("position", p);
                    context.startActivityForResult(intent, 1);
                });
            }

            public void setPosition(int position) {
                p = position;
                if (data != null && !data.isEmpty()) {
                    text.setText(data.get(p));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String text = data.getStringExtra("result");
        int p = data.getIntExtra("position", -1);
        if (p != -1 && !TextUtils.isEmpty(text))
        adapter.update(p, text);
        adapter.notifyDataSetChanged();
    }
}