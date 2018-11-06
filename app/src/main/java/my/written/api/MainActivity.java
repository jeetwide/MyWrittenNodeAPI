package my.written.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.written.api.AppClass.applicationContext;
import static my.written.api.AppClass.getAppContext;
import static my.written.api.AppClass.volleyQueue;

public class MainActivity extends AppCompatActivity implements AddDataInterface {


    private static RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private static List<Course> courseList;
    private static RecyclerView.Adapter adapter;
    public static Context ctx;
  //  private String url = "http://192.168.43.112:4000/course/";
    TextView tv_add;
    RelativeLayout rlBottom;
    AddDataInterface myinterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mList = findViewById(R.id.main_list);
        tv_add=findViewById(R.id.tv_add);
        courseList = new ArrayList<>();
        adapter = new CourseAdapter(MainActivity.this,courseList,this);
        rlBottom=findViewById(R.id.rlBottom);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        getData();


        //Adding item.
        rlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogClass cdd=new CustomDialogClass(MainActivity.this,MainActivity.this);
                cdd.show();
            }
        });



    }

    public  void CallAddRecordAPI(String course_name, int course_price ){

        JSONObject json = new JSONObject();
        try {
            json.put("course_name",course_name);
            json.put("course_price",course_price);
            Log.d("Json body",json.toString());
            //Toast.makeText(applicationContext, json.toString(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Constants.add_Url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(applicationContext, "Course Added Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Add API Response ",response.toString());
                        getData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Add API Response ",error.toString());
            }
        }) { // Notice no semi-colon here
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };


        volleyQueue.add(jsonObjectRequest);




    }

  public  void CallUpdateRecordAPI(String course_name, int course_price, String url){

        JSONObject json = new JSONObject();
        try {
            json.put("course_name",course_name);
            json.put("course_price",course_price);
            Log.d("Json body",json.toString());
            //Toast.makeText(applicationContext, json.toString(), Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(applicationContext, "Course Updated Successfully", Toast.LENGTH_SHORT).show();
                        Log.d("Update API Response ",response.toString());
                        getData();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("Update API Response ",error.toString());
                Toast.makeText(applicationContext, "Course Updatation failed", Toast.LENGTH_SHORT).show();
            }
        }) { // Notice no semi-colon here
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }

        };


       volleyQueue.add(jsonObjectRequest);

    }

    public static void CallDeleteAPI(String url) {


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("Response from DeleteAPI",response);
                        Toast.makeText(getAppContext(), response, Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Delete Api error", error.toString());
            }
        });

          volleyQueue.add(stringRequest);



    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constants.base_Url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                courseList.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Log.d("Response from API",jsonObject.getString("course_name")+jsonObject.getInt("course_price"));
                        Course course = new Course();
                        course.setCourse_name(jsonObject.getString("course_name"));
                        course.setCourse_price(jsonObject.getInt("course_price"));
                        course.setCourse_id(jsonObject.getString("_id"));


                        courseList.add(course);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void Adddata(String name, int Price) {
        CallAddRecordAPI(name,Price);

    }

    @Override
    public void Updatedata(String name, int Price,String url) {
        CallUpdateRecordAPI(name,Price,url);
    }
}