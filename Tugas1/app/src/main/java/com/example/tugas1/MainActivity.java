package com.example.tugas1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Declaring components
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    Button btnSubmit, btnDatePicker;
    EditText mEditText, mNim;
    TextView tvTextPreview,tvDateResult;

    String[] className = {"10", "11", "12"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get all components
        btnSubmit = findViewById(R.id.submit_button);
        mEditText = findViewById(R.id.edit_text);
        mNim = findViewById(R.id.edit_nim);
        tvTextPreview = findViewById(R.id.text_preview);
        tvDateResult = findViewById(R.id.tv_dateresult);
        AutoCompleteTextView spin = findViewById(R.id.simpleSpinner);
        spin.setOnItemSelectedListener(this);
        radioGroup = findViewById(R.id.radioGroup);
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btnDatePicker = findViewById(R.id.btn_datepicker);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, className);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        btnDatePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        btnSubmit.setOnClickListener((OnClickListener) v -> {
            // Get the checked radio value
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);

            // set the entered data to text preview
            tvTextPreview.setText("Nama Saya Adalah : " +
                    mEditText.getText().toString() + "\n" +
                    "NIM Saya : " + mNim.getText().toString() + "\n" +
                    "Kelas Saya : " + spin.getText().toString() + "\n" +
                    "Jenis Kelamin : " + radioButton.getText().toString() + "\n");
        });
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(), className[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
    }

    private void showDateDialog(){
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();
        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int
                            dayOfMonth) {
                        /**
                         * Method ini dipanggil saat kita selesai memilih tanggal di
                         DatePicker
                         */
                        /**
                         * Set Calendar untuk menampung tanggal yang dipilih
                         */
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        /**
                         * Update TextView dengan tanggal yang kita pilih
                         */
                        tvDateResult.setText("Tanggal Dipilih : " + dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH));
        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }
}