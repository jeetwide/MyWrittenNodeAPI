package my.written.api;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by NIK on 01-11-2018.
 */

public interface GetDataServiceRetro {



    @GET("course/")
    Call<List<CourseRetro>> getAllCourses();



    @GET("course/delete/{id}")
    Call<CourseRetro> deleteRecords(@Path("id") String id);

    @Headers("Content-Type: application/json")
    @POST("course/add")
    Call<CourseRetro> addRecord(@Body String body);


    @Headers("Content-Type: application/json")
    @POST("course/update/{id}")
    Call<CourseRetro> updateRecord(@Body String body,@Path("id") String id);


}
