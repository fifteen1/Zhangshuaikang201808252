package bwie.com.zhangshuaikang20180825;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> list;
    private FluidLayout fluidLayout;
    private EditText sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        fluidLayout = findViewById(R.id.filu);
        sel = findViewById(R.id.sel);
    }

    public void jump(View view) {
        startActivity(new Intent(this,ShopActivity.class));
    }

    public void select(View view) {
        String s = sel.getText().toString();
        String liu[] ={s};
        for (int i = 0; i < liu.length; i++) {
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12,12,12,12);
            TextView textView = new TextView(this);
            textView.setText(liu[i]);
            fluidLayout.addView(textView,params);
        }


    }
}
