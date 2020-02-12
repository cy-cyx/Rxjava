package android.com.rxjava;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    private Type[] types = {Type.Base, Type.SwitchThread};

    private RecyclerView recyclerView;

    private RxjavaPack rxjavaPack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        rxjavaPack = new RxjavaPack();

        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        ViewGroup.LayoutParams vl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setContentView(frameLayout, vl);

        recyclerView = new RecyclerView(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.setLayoutManager(linearLayoutManager);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        frameLayout.addView(recyclerView, fl);
    }

    private void click(Type type) {
        switch (type) {
            case Base:
                rxjavaPack.simpleUse();
                break;
            case SwitchThread:
                rxjavaPack.switchThread();
                break;
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ViewGroup.LayoutParams vl = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Button button = new Button(parent.getContext());
            button.setLayoutParams(vl);
            return new RecyclerView.ViewHolder(button) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            ((Button) holder.itemView).setText(types[position].getDesc());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click(types[position]);
                }
            });
        }

        @Override
        public int getItemCount() {
            return types.length;
        }
    }
}
