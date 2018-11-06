package my.written.api;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NIK on 29-10-2018.
 */

public class CourseAdapterRetro extends RecyclerView.Adapter<CourseAdapterRetro.ViewHolder> {

    private Context context;
    private List<CourseRetro> list;

    public String id;
    public String Delete_url, edit_url;
    public AddDataInterface myinterface;

    public CourseAdapterRetro(Context context, List<CourseRetro> list) {
        this.context = context;
        this.list = list;
       // this.myinterface = addDataInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CourseRetro course = list.get(position);

        holder.course_name.setText(course.getCourse_name());
        holder.course_price.setText(String.valueOf(course.getCourse_price()));

        id = course.get_id();
        holder.ib_row_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
                Log.d("id is : ",id);
                ((ActRetrofit)context).CallDeleteRetro(id);

               // Delete_url = Constants.base_Url + "delete/" + id;
                // CallDelete(Delete_url);
              //  MainActivity.CallDeleteAPI(Delete_url);   through volley


            }
        });

        holder.ib_row_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = course.get_id();

              // edit_url = Constants.update_base_Url + id;
               // Log.d("EDIT URL TAG", edit_url);

                CustomDialogEditRetro cdd = new CustomDialogEditRetro(context,id);
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
