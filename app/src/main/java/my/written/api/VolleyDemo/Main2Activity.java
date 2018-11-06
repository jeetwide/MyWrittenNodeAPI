package my.written.api.VolleyDemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import my.written.api.AddDataInterface;
import my.written.api.Constants;
import my.written.api.Course;
import my.written.api.CourseAdapter;
import my.written.api.MainActivity;
import my.written.api.R;

import static my.written.api.AppClass.volleyQueue;
import static my.written.api.Constants.*;
import static my.written.api.Constants.base_Url_volley;

public class Main2Activity extends AppCompatActivity {


    private static RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private static List<Contacts> contactsList;
    private static RecyclerView.Adapter adapter;
    public static Context ctx;
    //  private String url = "http://192.168.43.112:4000/course/";
    TextView tv_add;
    RelativeLayout rlBottom;
    AddDataInterface myinterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mList = findViewById(R.id.main_list);
        tv_add=findViewById(R.id.tv_add);
        contactsList = new ArrayList<>();
        adapter = new ContactsAdapter(Main2Activity.this,contactsList);
        rlBottom=findViewById(R.id.rlBottom);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();

    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();




        JsonObjectRequest request = new JsonObjectRequest(Method.GET, Constants.base_Url_volley, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                // TODO Auto-generated method stub

                contactsList.clear();
                JSONObject jsonObject = new JSONObject();
                jsonObject=response;
                JSONArray jsonArray = null;
                try{
                    jsonArray = jsonObject.getJSONArray("data");
                   Log.d("DataArray ", String.valueOf(jsonObject.optJSONArray("data")));
                   Log.d("MessageTag ", String.valueOf(jsonObject.optString("message")));

                    Toast.makeText(Main2Activity.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Main2Activity.this, jsonObject.optString("status"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(Main2Activity.this, jsonObject.optString("status"), Toast.LENGTH_SHORT).show();
                    //Log.d("Message ",jsonObject.getString("message"));
                    //Log.d("Status ",jsonObject.getString("status"));



                    for (int i = 0; i < response.length(); i++) {

                        try{
                            jsonObject=jsonArray.getJSONObject(i);
                            Contacts contacts = new Contacts();

                            contacts.setName(jsonObject.getString("name"));
                            contacts.setGender(jsonObject.getString("gender"));
                            contacts.setEmail(jsonObject.getString("email"));
                            contacts.setPhone(jsonObject.getString("phone"));
                            contactsList.add(contacts);


                        }catch (NumberFormatException e){
                            e.printStackTrace();

                        }


                        Log.d("SucResponse from API",response.toString());
                        Contacts contacts = new Contacts();


                       // contacts.setName(jsonArray.getString(Integer.parseInt("name")));



                        contactsList.add(contacts);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                Log.d("FailResponse from API",arg0.getMessage());

            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    }

