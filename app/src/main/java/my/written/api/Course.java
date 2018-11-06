package my.written.api;

/**
 * Created by NIK on 29-10-2018.
 */

public class Course {
    public String course_name,course_id;
    public int course_price;

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public Course() {
    }

    public Course(String course_name, int course_price,String course_id) {
        this.course_name = course_name;
        this.course_price = course_price;
        this.course_id= course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_price() {
        return course_price;
    }

    public void setCourse_price(int course_price) {
        this.course_price = course_price;
    }
}
