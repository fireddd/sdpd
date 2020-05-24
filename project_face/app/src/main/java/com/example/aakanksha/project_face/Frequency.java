package com.example.aakanksha.project_face;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.google.android.gms.common.util.NumberUtils.isNumeric;

public class Frequency extends AppCompatActivity implements Messageadapter.Itemclicked{

    RecyclerView recyclerView;
    RecyclerView.Adapter myadapter;
    RecyclerView.LayoutManager layoutManager;
    EditText frequencynumber;
    String Clickedtext1,Clickedtext2;
    DatabaseReference infodatabase;
    DatabaseReference demoRef;
    ArrayList<message> messagestobe;
    Button submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenc;
        setContentView(R.layout.activity_frequency);
        infodatabase = FirebaseDatabase.getInstance().getReference();
        demoRef = infodatabase.child("names");
        recyclerView=findViewById(R.id.recyclelist);
        submitbutton=findViewById(R.id.buttonfrequency);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = frequencynumber.getText().toString();
                if(isNumeric(value)) {
                    Toast.makeText(getApplicationContext(), "valid input!", Toast.LENGTH_SHORT).show();
                    //push creates a unique id in database
                    demoRef.child("name").setValue(value);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! Enter again" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        frequencynumber = (EditText) findViewById(R.id.editText);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        messagestobe = new ArrayList<message>();
        messagestobe.add(new message("Give 10 % discount"));
        messagestobe.add(new message("Baby's Day out "));
        messagestobe.add(new message("Free delivery today"));
        messagestobe.add(new message("10% off on creditcards"));
        messagestobe.add(new message("Give 10 % discount"));
        messagestobe.add(new message("Baby's Day out "));
        messagestobe.add(new message("10% off on creditcards"));
        messagestobe.add(new message("Give 10 % discount"));
        messagestobe.add(new message("Baby's Day out "));
        messagestobe.add(new message("Free delivery today"));
        messagestobe.add(new message("10% off on creditcards"));
        messagestobe.add(new message("Give 10 % discount"));
        messagestobe.add(new message("Baby's Day out "));
        messagestobe.add(new message("10% off on creditcards"));
        myadapter = new Messageadapter(this,messagestobe);
        recyclerView.setAdapter(myadapter);

    }

    @Override
    public void onItemClicked(int index) {
        Clickedtext1=messagestobe.get(index).getMsg();
        Clickedtext2=Clickedtext1+" is send to the customer";
        Toast.makeText(getApplicationContext(), Clickedtext2, Toast.LENGTH_SHORT).show();
    }
}