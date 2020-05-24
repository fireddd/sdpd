package com.example.aakanksha.project_face;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class cashier_customer_info extends AppCompatActivity {

    String theage="gggg",thegender="sdfg";
    TextView custname,custphone,custfrequency,custsentiment,custlastvisited;
    ImageView custimage;
    DatabaseReference infodatabase,vardatabase1,vardatabase2,vardatabase3;
    DatabaseReference demoRef,demoRef1,demoRef2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashier_customer_info);
        custname = findViewById(R.id.c_user);
        custsentiment = findViewById(R.id.c_user_sent);
        custphone = findViewById(R.id.c_user_phone);
        custfrequency = findViewById(R.id.c_user_freq);
        custlastvisited = findViewById(R.id.c_user_date);
        custimage = (ImageView) findViewById(R.id.c_user_image);
        infodatabase = FirebaseDatabase.getInstance().getReference();
        demoRef = infodatabase.child("Url");
        DatabaseReference vardatabase = demoRef.child("userid");
        demoRef1=infodatabase.child("Data");
        demoRef2=infodatabase.child("Urlinfo");
        //demoRef = infodatabase.child("Url");
        vardatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Uidclass thedata = dataSnapshot.getValue(Uidclass.class);
                custname.setText(thedata.getID().toString());
                String personid=custname.getText().toString();
                vardatabase2 = demoRef1.child(personid);
                vardatabase3 = demoRef2.child(personid);
                //Toast.makeText(getApplicationContext(),personid, Toast.LENGTH_SHORT).show();
                //thegender = dataSnapshot.child("/Gender").getValue(String.class);
                vardatabase2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Datafromfirebase thedata1 = dataSnapshot.getValue(Datafromfirebase.class);
                       // Toast.makeText(getApplicationContext(), thedata.getAge().toString()+"  "+thedata.getGender().toString(), Toast.LENGTH_SHORT).show();
                        custphone.setText(thedata1.getAge().toString());
                        custlastvisited.setText(thedata1.getGender().toString());
                        custfrequency.setText(thedata1.getFreq().toString());
                        custsentiment.setText(thedata1.getEmotion().toString());
                        vardatabase3.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Urldata thedata2 = dataSnapshot.getValue(Urldata.class);
                                // Toast.makeText(getApplicationContext(), thedata.getAge().toString()+"  "+thedata.getGender().toString(), Toast.LENGTH_SHORT).show();
                                String imgpath=thedata2.getUrl().toString();
                                Picasso.get().load(imgpath).into(custimage);
                                //thegender = dataSnapshot.child("/Gender").getValue(String.class);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //sendData("abc");
        //Toast.makeText(getApplicationContext(), personid, Toast.LENGTH_SHORT).show();




        //custname.setText(theage);
        //custname.setText(theage);
        //custsentiment.setText(thegender);
        //new DownLoadImageTask(custimage).execute("http://i.imgur.com/DvpvklR.png");
        //https://firebasestorage.googleapis.com/v0/b/pythoncalling.appspot.com/o/images%2Fnewimage0.png?alt=media



    }
    /*
    public void sendData(String name)
    {
        Datafromfirebase senduser = new Datafr  omfirebase("123","malefsa");

        demoRef.child("userid123").setValue(senduser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }

    */
    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;

        public DownLoadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String... urls) {
            String urlOfImage = urls[0];
            Bitmap logo = null;
            try {
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            } catch (Exception e) { // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}
