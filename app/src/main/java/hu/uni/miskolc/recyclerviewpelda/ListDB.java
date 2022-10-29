package hu.uni.miskolc.recyclerviewpelda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import hu.uni.miskolc.recyclerviewpelda.ui.db.DbAdapter;
import hu.uni.miskolc.recyclerviewpelda.ui.dolgozo.DolgozoAdapter;
import hu.uni.miskolc.recyclerviewpelda.ui.dolgozo.DolgozoFragment;

public class ListDB extends AppCompatActivity {
    private RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_db);
        rv=(RecyclerView)findViewById(R.id.rec);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        class GetData extends AsyncTask<Void,Void, List<User>> {

            @Override
            protected List<User> doInBackground(Void... voids) {
                UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                        UserDatabase.class, "sajatusers").build();
                UserDAO dao = db.getUserDAO();
                List<User>myDataList=dao.getAll();
                return myDataList;

            }

            @Override
            protected void onPostExecute(List<User> myDataList) {
                DbAdapter adapter=new DbAdapter(myDataList);
                rv.setAdapter(adapter);
                adapter.setOnDbClickListener(new DbAdapter.DbClickListener() {
                    @Override
                    public void onDbClick(int position, View v) {
                        System.out.println("A következőre kattintottam: ");
                        System.out.println("A következőre kattintottam: "+myDataList.get(position));
                        Intent intent = new Intent(ListDB.this, ListDetailsDb.class);
                        intent.putExtra("id", myDataList.get(position).getId());
                        intent.putExtra("knev", myDataList.get(position).getFirstName());
                        intent.putExtra("vnev", myDataList.get(position).getLastName());
                        intent.putExtra("unev", myDataList.get(position).getUsername());

                        startActivity(intent);
                    }
                });
                super.onPostExecute(myDataList);
            }
        }
        GetData gd=new GetData();
        gd.execute();
    }
}