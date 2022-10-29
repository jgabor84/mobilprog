package hu.uni.miskolc.recyclerviewpelda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "sajatusers").build();
        UserDAO dao = db.getUserDAO();

        Bundle extras = getIntent().getExtras();
        EditText knevM = findViewById(R.id.keresztnev);
        EditText vnevM = findViewById(R.id.csaladnev);
        EditText unevM = findViewById(R.id.username);

        Button editBtn = findViewById(R.id.edit);

        String knev = extras.getString("knev");
        String vnev = extras.getString("vnev");
        String unev = extras.getString("unev");
        Integer did = extras.getInt("did");

        knevM.setText(knev);
        vnevM.setText(vnev);
        unevM.setText(unev);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override

                    public void run() {

                        User user = new User();

                        user.setUsername(unevM.getText().toString());
                        user.setFirstName(vnevM.getText().toString());
                        user.setLastName(knevM.getText().toString());
                        user.id = did;
                        try{dao.update(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Intent intent = new Intent(EditActivity.this, ListDB.class);
                                    startActivity(intent);
                                }
                            });
                        }
                        catch (SQLiteConstraintException e){

                        }
                    }
                });


            }
        });
    }
}
