package my.written.api;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by NIK on 30-10-2018.
 */

public class CustomDialogEdit extends Dialog implements View.OnClickListener {

    public Activity c;
    Context context;
    public Dialog d;
    public Button yes, no;
    public EditText et_coursename, et_courseprice;
   public AddDataInterface myinterface;
   public String EditAPiURL;

    public CustomDialogEdit(@NonNull Context context,AddDataInterface addDataInterface, String url) {
        super(context);
        this.context = context;
        this.myinterface = addDataInterface;
        this.EditAPiURL=url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);

        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        et_coursename = (EditText) findViewById(R.id.et_coursename);
        et_courseprice = (EditText) findViewById(R.id.et_courseprice);
/*
        yes.setOnClickListener((View.OnClickListener) applicationContext);
        no.setOnClickListener((View.OnClickListener) applicationContext);*/

        yes.setOnClickListener(CustomDialogEdit.this);
        no.setOnClickListener(CustomDialogEdit.this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.btn_yes:
                String course_name = et_coursename.getText().toString();
                int course_price = Integer.parseInt(et_courseprice.getText().toString());
                myinterface.Updatedata(course_name, course_price,EditAPiURL);
              //  ((MainActivity)context).CallUpdateRecordAPI(course_name,course_price,EditAPiURL);
                //((MainActivity)context).CallAddRecordAPI(course_name,course_price);
                //   CallAddRecordAPI(course_name, course_price);

                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}

