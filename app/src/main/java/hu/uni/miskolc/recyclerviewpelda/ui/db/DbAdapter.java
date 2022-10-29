package hu.uni.miskolc.recyclerviewpelda.ui.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.uni.miskolc.recyclerviewpelda.R;
import hu.uni.miskolc.recyclerviewpelda.User;



public class DbAdapter extends RecyclerView.Adapter<DbAdapter.ViewHolder>  {
    List<User> dbDataLists;
    private static DbClickListener listener;

    public DbAdapter(List<User> dbDataLists) {
        this.dbDataLists = dbDataLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_data,viewGroup,false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User md=dbDataLists.get(i);

        //  viewHolder.txtid.setText(md.getId());
        viewHolder.txtid.setText(md.getUsername());
        viewHolder.txtknev.setText(md.getFirstName());
        viewHolder.txtvnev.setText(md.getLastName());
    }

    @Override
    public int getItemCount() {
        return dbDataLists.size();
    }

    public void setOnDbClickListener(DbClickListener clickListener){
        listener = clickListener;
    }
    public interface DbClickListener{
        void onDbClick(int position, View v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtid,txtknev,txtvnev;

        public ViewHolder(@NonNull View itemView, DbAdapter.DbClickListener clickListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtid=(TextView)itemView.findViewById(R.id.db_id);
            txtknev=(TextView)itemView.findViewById(R.id.db_knev);
            txtvnev=(TextView)itemView.findViewById(R.id.db_vnev);
            listener = clickListener;
        }

        @Override
        public void onClick(View view) {
            listener.onDbClick(getAdapterPosition(), view);
        }
    }



}