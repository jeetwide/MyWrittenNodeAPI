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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static my.written.api.AppClass.applicationContext;
import static my.written.api.AppClass.volleyQueue;

public class ActRetrofit extends AppCompatActivity {

    private static RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private static List<Course> courseList;
    private static List<CourseRetro> courseListretro;
    private static RecyclerView.Adapter adapter;
    public static Context ctx;
    Call<CourseRetro> cc;

    TextView tv_add;
    RelativeLayout rlBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_retrofit);
        rlBottom=findViewById(R.id.rlBottom);




        rlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogClassRetro cdd=new CustomDialogClassRetro(ActRetrofit.this);
                cdd.show();
            }
        });
        getData();

    }


    public  void CallUpdateRecordAPI(String course_name, int course_price, String id){

        JSONObject json = new JSONObject();
        try {
            json.put("course_name",course_name);
            json.put("course_price",course_price);
            Log.d("Json body",json.toString());
            //Toast.makeText(applicationContext, json.toString(), Toast.LENGTH_SHORT).show();


            GetDataServiceRetro serviceRetro= RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetro.class);
            Call<CourseRetro> call = serviceRetro.updateRecord(json.toString(),id);
            call.enqueue(new Callback<CourseRetro>() {
                @Override
                public void onResponse(Call<CourseRetro> call, retrofit2.Response<CourseRetro> response) {
                    Toast.makeText(ActRetrofit.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(ActRetrofit.this, response.toString(), Toast.LENGTH_SHORT).show();
                    Log.d("Update Api ResponseSuce",response.toString());
                    getData();


                }

                @Override
                public void onFailure(Call<CourseRetro> call, Throwable t) {
                    Toast.makeText(ActRetrofit.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Update Api ResponseFail",t.getMessage());

                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }





    }

    public  void CallAddRecordAPI(String course_name, int course_price ){

        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("course_name", course_name);
            paramObject.put("course_price", course_price);

           // Call<CourseRetro> userCall = apiInterface.getUser(paramObject.toString());
           // userCall.enqueue(this);

            GetDataServiceRetro serviceRetro= RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetro.class);
            Call<CourseRetro> call = serviceRetro.addRecord(paramObject.toString());
            call.enqueue(new Callback<CourseRetro>() {
                @Override
                public void onResponse(Call<CourseRetro> call, retrofit2.Response<CourseRetro> response) {
                    Toast.makeText(ActRetrofit.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    Log.d("Add Api Response",response.toString());
                    getData();


                }

                @Override
                public void onFailure(Call<CourseRetro> call, Throwable t) {
                    Toast.makeText(ActRetrofit.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Add Api Response",t.getMessage());

                }
            });


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        GetDataServiceRetro serviceRetro= RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetro.class);
        Call<List<CourseRetro>> call = serviceRetro.getAllCourses();
        call.enqueue(new Callback<List<CourseRetro>>() {
            @Override
            public void onResponse(Call<List<CourseRetro>> call, retrofit2.Response<List<CourseRetro>> response) {
                progressDialog.dismiss();
                Log.d("get api responseSuc",response.toString());
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<CourseRetro>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("get api responseFail",t.getMessage());
            }
        });


    }
    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<CourseRetro> courseList) {
        mList = findViewById(R.id.main_list);
        adapter = new CourseAdapterRetro(this,courseList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ActRetrofit.this);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(adapter);
    }


    public  void CallDeleteRetro(String id){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        GetDataServiceRetro serviceRetro= RetrofitClientInstance.getRetrofitInstance().create(GetDataServiceRetro.class);
        Call<CourseRetro> call = serviceRetro.deleteRecords(id);
        call.enqueue(new Callback<CourseRetro>() {
            @Override
            public void onResponse(Call<CourseRetro> call, retrofit2.Response<CourseRetro> response) {
                progressDialog.dismiss();

                CourseRetro obj = response.body();
                Log.d("Success Delete : ",obj.getCourse_name()+obj.getCourse_price());
                //generateDataListObj(response.body());
                Toast.makeText(ActRetrofit.this, "Successfully Removed", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CourseRetro> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ActRetrofit.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error Delete : ",t.getMessage()+" : "+t.getLocalizedMessage());

            }
        });

    }
}
