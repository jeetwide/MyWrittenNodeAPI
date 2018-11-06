package my.written.api.VolleyDemo;

/**
 * Created by NIK on 29-10-2018.
 */

public class Contacts {
 public String name;
 public String gender;
 public String email;
 public String phone;

    public String getName() {
        return name;
    }

    public Contacts() {
    }

    public Contacts(String name, String gender, String email, String phone) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
