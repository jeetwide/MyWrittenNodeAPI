package my.written.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by NIK on 29-10-2018.
 */

public class CourseRetro implements Serializable{




    public CourseRetro(){


    }
    @SerializedName("course")
    @Expose
    private String course;

    @SerializedName("_id")
    private String _id;

    @SerializedName("course_name")
    private String course_name;

    @SerializedName("course_price")
    private String course_price;

    public CourseRetro(String _id, String course_name, String course_price) {
        this._id = _id;
        this.course_name = course_name;
        this.course_price = course_price;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_price() {
        return course_price;
    }

    public void setCourse_price(String course_price) {
        this.course_price = course_price;
    }
}
