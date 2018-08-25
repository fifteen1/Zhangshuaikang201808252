package bwie.com.zhangshuaikang20180825.myView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import bwie.com.zhangshuaikang20180825.R;

public class My_jiajian extends LinearLayout {
    private TextView jia, jian;
    private EditText numEt;
    private int num = 1;

    public My_jiajian(Context context) {
        this(context, null);
    }

    public My_jiajian(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public My_jiajian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jiajian_layout, this, true);
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        numEt = view.findViewById(R.id.num_et);

        numEt.setText(numEt + "");

        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num++;
                numEt.setText(num + "");
                if (jiajianLintener != null) {
                    jiajianLintener.getNum(num);
                }
            }
        });

        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                num--;
                if (num <= 0) {
                    Toast.makeText(getContext(), "数量不能小于1", Toast.LENGTH_SHORT).show();
                    num = 1;
                }
                numEt.setText(num + "");
                if (jiajianLintener != null) {
                    jiajianLintener.getNum(num);
                }
            }
        });
    }

    public void setNumEt(int n){
        numEt.setText(n+"");
        num = Integer.parseInt(numEt.getText().toString());
    }

    private JiajianLintener jiajianLintener ;

    public void setJiajianLintener(JiajianLintener jiajianLintener) {
        this.jiajianLintener = jiajianLintener;
    }

    public interface JiajianLintener{
        void getNum(int num);
    }
}
