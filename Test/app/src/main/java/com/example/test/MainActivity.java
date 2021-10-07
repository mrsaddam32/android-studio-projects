package com.example.test;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edtPanjang, edtLebar;
    private Button btnHitung;
    private TextView txtLuas;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // kenalkan komponen-komponen yang ada di layout activity_main
        edtPanjang = (EditText) findViewById(R.id.edt_panjang);
        edtLebar = (EditText) findViewById(R.id.edt_lebar);
        btnHitung = (Button) findViewById(R.id.btn_hitung);
        txtLuas = (TextView) findViewById(R.id.txt_luas);

        getSupportActionBar().setTitle("Hitung Luas Persegi Panjang");

        // berikan action button hitung untuk menghitung hasil
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String panjang, lebar;
                panjang = edtPanjang.getText().toString();
                lebar = edtLebar.getText().toString();
                // TextUtils berfungsi supaya ketika EditText nya tidak terisi (Kosong) Maka
                // nanti akan muncul notifikasi "tidak boleh kosong"
                if (TextUtils.isEmpty(panjang)) {
                    edtPanjang.setError("Tidak Boleh Kosong!!!");
                    edtPanjang.requestFocus();
                } else if (TextUtils.isEmpty(lebar)) {
                    edtLebar.setError("Tidak Boleh Kosong!!!");
                    edtLebar.requestFocus();
                } else {
                    // Masukan Rumus untuk menghitung Panjang dan juga lebar nya
                    double p = Double.parseDouble(panjang);
                    double l = Double.parseDouble(lebar);
                    double hasil = p * l;
                    // Kemudian Hasil di tampilkan di TextView
                    txtLuas.setText("Hasil Luas  : " + hasil);
                }

            }
        });
    }
}