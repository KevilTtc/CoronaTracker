package com.example.mcad_proj.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcad_proj.R;
import com.example.mcad_proj.Statistics;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActivityReflect extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ImageView btn_callPhone, imgBackHome;
    private CheckBox checkbox_nghingo,btn_diTuVungDich, btn_tiepXuc;
    private EditText edt_phanAnh, edtNoiSayRa;
    private LinearLayout setDate;
    private Button btn_send;
    private TextView txtvalueSetDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        init();
        Log.d("19009095", "onClick: " + getData());
        initDataEvent();
    }

    private void initDataEvent() {
        imgBackHome.setOnClickListener(view -> {

            Intent i = new Intent(ActivityReflect.this, Home.class);
            startActivity(i);
            finish();

        });

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),ActivityReflect.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                SmsManager sms=SmsManager.getDefault();
              //  Log.d("19009095", "onClick: " + getData());
                Toast.makeText(getApplication(), "Send sucess!", Toast.LENGTH_SHORT).show();
                sms.sendTextMessage("19009095", null, getData(), pi,null);
            }
        });
    }
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        txtvalueSetDate.setText("Time: " + currentDateString);

    }
    private String  getData(){
        String contentReflect = "Phan anh " + "\n";
        if (checkbox_nghingo.isChecked()){
            contentReflect += "1. Có trường hơp nghỉ ngờ mắc bệnh" + "\n";
        }
        if (btn_diTuVungDich.isChecked()){
            contentReflect += "2. Có trường hơp đi về từ vùng dịch" + "\n";
        }
        if (btn_tiepXuc.isChecked()){
            contentReflect += "3.  " + getString(R.string.conten1) + "\n";
        }
        if (edt_phanAnh.getText().toString().trim() != null){
            contentReflect += "Phản ảnh khác \n " + edt_phanAnh.getText().toString().trim() + "\n";
        }
        if (txtvalueSetDate.getText().toString().trim() != null){
            contentReflect += "Date " + txtvalueSetDate.getText().toString().trim() + "\n";
        }
        if (edtNoiSayRa.getText().toString().trim() != null){
            contentReflect += "Nơi sảy ra: " + edtNoiSayRa.getText().toString().trim() + "\n";
        }
        return contentReflect;
    }

    private void init() {
        btn_callPhone = findViewById(R.id.btn_callPhone);
        imgBackHome = findViewById(R.id.imageView);
        checkbox_nghingo = findViewById(R.id.checkbox_nghingo);
        btn_diTuVungDich = findViewById(R.id.btn_diTuVungDich);
        btn_tiepXuc = findViewById(R.id.btn_tiepXuc);
        edt_phanAnh = findViewById(R.id.edt_phanAnh);
        edtNoiSayRa = findViewById(R.id.edtNoiSayRa);
        setDate = findViewById(R.id.setDate);
        txtvalueSetDate = findViewById(R.id.txtvalueSetDate);
        btn_send = findViewById(R.id.btn_send);
    }
}