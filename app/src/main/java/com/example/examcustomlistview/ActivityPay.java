package com.example.examcustomlistview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ActivityPay extends AppCompatActivity {

    ListView lvPay;
    TextView txtTotalPrice;

    int totalPrice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pay);

        lvPay = findViewById(R.id.lv_pay);
        txtTotalPrice = findViewById(R.id.txt_total_price);

        List<Food> listPay = (List<Food>) getIntent().getSerializableExtra("data");
        for (int i = 0; i < listPay.size(); i++) {
            totalPrice += listPay.get(i).getPrice();
        }

        AdapterFood adapterFood = new AdapterFood(this, R.layout.row_item, listPay);
        lvPay.setAdapter(adapterFood);
        txtTotalPrice.setText("Tổng của bạn hết: " + totalPrice);
    }
}
