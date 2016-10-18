package cn.kami7.witness.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.kami7.witness.Witness;
import cn.kami7.witness.sourceitem.TextSourceItem;
import cn.kami7.witness.subject.ButtonSubject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText et = (EditText) findViewById(R.id.et);
        final TextView tv = (TextView) findViewById(R.id.tv);
        Button btn = (Button) findViewById(R.id.btn);

        Witness witness = new Witness(1);
        witness.setSubject(new ButtonSubject(btn));
        witness.addItem(new TextSourceItem(et));
        witness.addItem(new TextSourceItem(tv));
        witness.allAdded();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tv.getText().length() == 0){
                    tv.setText("yeah");
                }else{
                    tv.setText("");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et.setText("");
            }
        });
    }
}
