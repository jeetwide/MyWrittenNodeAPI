package my.written.api.VolleyDemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import my.written.api.AddDataInterface;
import my.written.api.Constants;
import my.written.api.Course;
import my.written.api.CustomDialogEdit;
import my.written.api.MainActivity;
import my.written.api.R;

/**
 * Created by NIK on 29-10-2018.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Context context;
    private List<Contacts> list;

    public String id;
    public String Delete_url, edit_url;
    public AddDataInterface myinterface;

    public ContactsAdapter(Context context, List<Contacts> list/*, AddDataInterface addDataInterface*/) {
        this.context = context;
        this.list = list;
      //  this.myinterface = addDataInterface;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_row_volley, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Contacts contacts = list.get(position);

        holder.tv_contacts_name.setText(contacts.getName());
        holder.tv_contacts_email.setText(contacts.getEmail());
        holder.tv_contacts_gender.setText(contacts.getGender());
        holder.tv_contacts_phone.setText(contacts.getPhone());

       // id = course.getCourse_id();
       /* holder.ib_row_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyDataSetChanged();
                Delete_url = Constants.base_Url + "delete/" + id;
                // CallDelete(Delete_url);
                MainActivity.CallDeleteAPI(Delete_url);

            }
        });*/

       /* holder.ib_row_edit.setOnClickListener(new View.OnClickListener() {
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
*/

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_contacts_name, tv_contacts_gender,tv_contacts_email,tv_contacts_phone;
        public ImageButton ib_row_delete, ib_row_edit;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_contacts_name = itemView.findViewById(R.id.tv_contacts_name);
            tv_contacts_gender = itemView.findViewById(R.id.tv_contacts_gender);
            tv_contacts_email = itemView.findViewById(R.id.tv_contacts_email);
            tv_contacts_phone = itemView.findViewById(R.id.tv_contacts_phone);
            ib_row_delete = itemView.findViewById(R.id.ib_row_delete);
            ib_row_edit = itemView.findViewById(R.id.ib_row_edit);

        }
    }
}
