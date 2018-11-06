package my.written.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.List;

/**
 * Created by NIK on 29-10-2018.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context context;
    private List<Course> list;

    public String id;
    public String Delete_url, edit_url;
    public AddDataInterface myinterface;

    public CourseAdapter(Context context, List<Course> list, AddDataInterface addDataInterface) {
        this.context = context;
        this.list = list;
        this.myinterface = addDataInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Course course = list.get(position);

        holder.course_name.setText(course.getCourse_name());
        holder.course_price.setText(String.valueOf(course.getCourse_price()));

        id = course.getCourse_id();
        holder.ib_row_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
                Delete_url = Constants.base_Url + "delete/" + id;
                // CallDelete(Delete_url);
                MainActivity.CallDeleteAPI(Delete_url);

            }
        });

        holder.ib_row_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = course.getCourse_id();

                edit_url = Constants.update_base_Url + id;
                Log.d("EDIT URL TAG", edit_url);

                CustomDialogEdit cdd = new CustomDialogEdit(context,myinterface, edit_url);
                cdd.show();
                // CallDelete(Delete_url);


            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView course_name, course_price;
        public ImageButton ib_row_delete, ib_row_edit;

        public ViewHolder(View itemView) {
            super(itemView);

            course_name = itemView.findViewById(R.id.tv_course_name);
            course_price = itemView.findViewById(R.id.tv_course_price);
            ib_row_delete = itemView.findViewById(R.id.ib_row_delete);
            ib_row_edit = itemView.findViewById(R.id.ib_row_edit);

        }
    }
}
