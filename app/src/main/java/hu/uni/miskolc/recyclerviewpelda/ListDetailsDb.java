package hu.uni.miskolc.recyclerviewpelda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ListDetailsDb extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details_db);

        Bundle extras = getIntent().getExtras();
        TextView knevM = findViewById(R.id.kNev);
        TextView vnevM = findViewById(R.id.vNev);
        TextView didM = findViewById(R.id.did);
        TextView unevM = findViewById(R.id.uNev);


        Button editBtn = findViewById(R.id.edit);




        String knev = extras.getString("knev");
        String vnev = extras.getString("vnev");
        String unev = extras.getString("unev");
        Integer did = extras.getInt("id");

        knevM.setText(knev);
        vnevM.setText(vnev);
        unevM.setText(unev);
        didM.setText(did.toString());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListDetailsDb.this, EditActivity.class);
                intent.putExtra("did", did);
                intent.putExtra("vnev", vnev);
                intent.putExtra("knev", knev);
                intent.putExtra("unev", unev);

                startActivity(intent);
            }
        });

    }
}
