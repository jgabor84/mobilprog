package hu.uni.miskolc.recyclerviewpelda;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import hu.uni.miskolc.recyclerviewpelda.MainActivity;
import hu.uni.miskolc.recyclerviewpelda.MainActivity2;
import hu.uni.miskolc.recyclerviewpelda.R;

public class DolgozoLap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolgozo_lap);

        Bundle extras = getIntent().getExtras();
        TextView knevM = findViewById(R.id.kNev);
        TextView vnevM = findViewById(R.id.vNev);
        TextView didM = findViewById(R.id.did);
        TextView fizM = findViewById(R.id.fiz);

        String knev = extras.getString("knev");
        String vnev = extras.getString("vnev");
        Integer fiz = extras.getInt("fiz");
        Integer did = extras.getInt("did");

        knevM.setText(knev);
        vnevM.setText(vnev);
        didM.setText(did.toString());
        fizM.setText(fiz.toString());



    }
}