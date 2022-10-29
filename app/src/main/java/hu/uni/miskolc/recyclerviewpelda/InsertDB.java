package hu.uni.miskolc.recyclerviewpelda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class InsertDB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_db);
        UserDatabase db = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "sajatusers").build();
        UserDAO dao = db.getUserDAO();
        Button hozzaadasgomb = findViewById(R.id.button);
        Button osszesgomb = findViewById(R.id.getAll);

        osszesgomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsertDB.this, ListDB.class);
                startActivity(intent);
            }
        });

        hozzaadasgomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask.execute(new Runnable() {
                    @Override

                    public void run() {
                        EditText username = findViewById(R.id.username);
                        EditText csaladnev = findViewById(R.id.csaladnev);
                        EditText keresztnev = findViewById(R.id.keresztnev);
                        User user = new User();

                        user.setUsername(username.getText().toString());
                        user.setFirstName(keresztnev.getText().toString());
                        user.setLastName(csaladnev.getText().toString());
                        try{dao.insert(user);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(InsertDB.this, getString(R.string.uzenet), Toast.LENGTH_SHORT).show();
                                    username.setText("");
                                    csaladnev.setText("");
                                    keresztnev.setText("");
                                }
                            });


                        }
                        catch (SQLiteConstraintException e){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    username.setError(getString(R.string.marfoglalt));
                                }
                            });
                        }



                    }
                });


            }
        });
    }
}