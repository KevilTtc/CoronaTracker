package com.example.mcad_proj;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.mukesh.countrypicker.Country;
import com.mukesh.countrypicker.CountryPicker;
import com.mukesh.countrypicker.OnCountryPickerListener;

public class CountryData {

    public  static final String[] countryNames = {

            "IND", "USA", "MY", "AFG", "MF"
    };

    public  static final int[] countryFlag = {

            R.drawable.india, R.drawable.uk, R.drawable.malesiya, R.drawable.afganistan, R.drawable.maxico
    };


 /*   private TextView countryNametv;
    private ImageView countryFlag;
    private Button pickCountry;
    private CountryPicker countryPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();
        setListener();
    }

    private void setListener() {
        pickCountry
    }*/
}

