package com.example.firebaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {

    private EditText etKategori, etTanggal, etJudul, etIsi;
    private Button btSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etKategori = findViewById(R.id.etKategori);
        etTanggal = findViewById(R.id.etTanggal);
        etJudul = findViewById(R.id.etJudul);
        etIsi = findViewById(R.id.etIsi);
        btSimpan = findViewById(R.id.btSimpan);

        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });
    }

    private void simpan() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("berita");

        myRef.child(etKategori.getText().toString()).child(etTanggal.getText().toString()).child(etJudul.getText().toString()).setValue(etIsi.getText().toString());

        Intent form3 = new Intent(MainActivity2.this, MainActivity3.class);
        form3.putExtra("Kategori", etKategori.getText().toString());
        form3.putExtra("Tanggal", etTanggal.getText().toString());
        form3.putExtra("Judul", etJudul.getText().toString());
        startActivity(form3);

    }
}
