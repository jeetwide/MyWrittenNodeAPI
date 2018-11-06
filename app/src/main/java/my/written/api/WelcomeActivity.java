package my.written.api;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import my.written.api.VolleyDemo.Main2Activity;

public class WelcomeActivity extends AppCompatActivity {

    Button btn, retro_btn, volley_arr_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        btn = findViewById(R.id.btn);
        retro_btn = findViewById(R.id.retro_btn);
        volley_arr_btn = findViewById(R.id.volley_arr_btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });
        retro_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, ActRetrofit.class));
            }
        });

        volley_arr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeActivity.this, Main2Activity.class));
            }
        });


    }
}
