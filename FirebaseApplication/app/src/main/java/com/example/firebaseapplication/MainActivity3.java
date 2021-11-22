package com.example.firebaseapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {
    private TextView tvKategori, tvTanggal, tvJudul, tvIsi;
    private String Kategori, Tanggal, Judul, Isi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvKategori=findViewById(R.id.tvKategori);
        tvTanggal=findViewById(R.id.tvTanggal);
        tvJudul=findViewById(R.id.tvJudul);
        tvIsi=findViewById(R.id.tvIsi);
        Kategori=getIntent().getStringExtra("Kategori");
        Tanggal=getIntent().getStringExtra("Tanggal");
        Judul=getIntent().getStringExtra("Judul");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef =database.getReference("berita");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Isi =
                        dataSnapshot.child(Kategori).child(Tanggal).child(Judul).getValue(String
                                .class);
                tvKategori.setText(Kategori);
                tvTanggal.setText(Tanggal);
                tvJudul.setText(Judul);
                tvIsi.setText(Isi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}