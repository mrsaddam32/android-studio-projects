package com.example.basiccredential;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.KeyguardManager;
import android.os.Build;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;

public class MainActivity extends AppCompatActivity {
    public static final int LOCK_REQUEST_CODE = 221;
    public static final int SECURITY_SETTING_REQUEST_CODE = 223;

    TextView textView;

    // Authentication Method
    private void authenticateApp() {
        // Inisiasi Object KeyguardManager
        // KeyguardManager digunakan untuk mengontrol buka dan tutup
        KeyguardManager keyguardManager = (KeyguardManager)getSystemService(KEYGUARD_SERVICE);

        //Mengecek apakah versi device Android lebih atau sama dengan lollipop
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // Membuat intent untuk membuka tampilan autentifikasi
            Intent i = keyguardManager.createConfirmDeviceCredentialIntent(getResources().getString(R.string.unlock),getResources().getString(R.string.confirm_pattern));
            try {
                // Jalankan intent jika autentifikasi telah dilakukan dan kirim request code
                startActivityForResult(i,LOCK_REQUEST_CODE);
            } catch (Exception e) {
                // Exception dijalankan jika device belum mengimplementasikan PIN/Password/Pattern
                Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
                try {
                    // Jalankan intent ke setting
                    startActivityForResult(intent,SECURITY_SETTING_REQUEST_CODE);
                } catch (Exception ex) {
                    // Jika tidak ditemukan autentifikasi maka user harus membuat terlebih dahulu manual
                    textView.setText(getResources().getString(R.string.setting_label));
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode) {
            case LOCK_REQUEST_CODE:
                if(resultCode != RESULT_OK) {
                    // Jika autentifikasi tidak sukses, update textView pada main_activity.xml
                    textView.setText(getResources().getString(R.string.unlock_failed));
                }
                break;
            case SECURITY_SETTING_REQUEST_CODE:
                // Cek apakah user sudah menyalakan security
                if(isDeviceSecure()) {
                    // Jika ditemukan pengaturan security
                    Toast.makeText(this,getResources().getString(R.string.device_is_secure),Toast.LENGTH_SHORT).show();
                    // Lakukan autentifikasi ulang
                    authenticateApp();
                } else {
                    // Jika tidak ditemukan
                    textView.setText(getResources().getString(R.string.security_device_cancelled));
                }
                break;
        }
    }

    private boolean isDeviceSecure() {
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

        // Cek pengaturan security pada SDK versi 16 keatas
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN && keyguardManager.isKeyguardSecure();

        // Kita bisa menggunakan keyguardManager.isDeviceSecure(); apabila SDK yang digunakan versi 23 atau diatasnya
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv_label);
        authenticateApp();
    }
}