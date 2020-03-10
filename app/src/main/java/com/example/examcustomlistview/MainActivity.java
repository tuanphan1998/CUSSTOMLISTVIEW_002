package com.example.examcustomlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CheckBox cbFood;
    ImageView imgFood;
    TextView txtName, txtPrice;
    EditText edtName, edtPrice;
    Button btnEdit, btnPay;
    ListView lvFood;

    List<Food> listFood = new ArrayList<>();
    AdapterFood adapterFood;
    int posItem = 0;

    String TAG = "DEBUG: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbFood = findViewById(R.id.cb_food);
        imgFood = findViewById(R.id.img_food);
        txtName = findViewById(R.id.txt_name);
        txtPrice = findViewById(R.id.txt_price);
        edtName = findViewById(R.id.edt_name);
        edtPrice = findViewById(R.id.edt_price);
        btnEdit = findViewById(R.id.btn_edit);
        btnPay = findViewById(R.id.btn_pay);
        lvFood = findViewById(R.id.lv_food);
        edtName.setEnabled(false);edtPrice.setEnabled(false);
        btnEdit.setEnabled(false); btnPay.setEnabled(false);
        edtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                {
                    edtName.setEnabled(false);
                }
                else
                {
                    edtName.setEnabled(true);
                    btnEdit.setEnabled(true);
                    btnPay.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0)
                {
                    edtPrice.setEnabled(false);
                }
                else
                {
                    edtPrice.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listFood.add(new Food(false, R.drawable.birthday, "Ga quay", 5000));
        listFood.add(new Food(false, R.drawable.gmn, "Ga quay 2", 6000));
        listFood.add(new Food(false, R.drawable.gn, "Ga quay 3", 7000));
        listFood.add(new Food(false, R.drawable.newyear, "Ga quay 4", 7000));
        listFood.add(new Food(false, R.drawable.ngvn, "Ga quay 5", 7000));

        adapterFood = new AdapterFood(this, R.layout.row_item, listFood);
        lvFood.setAdapter(adapterFood);

        btnEdit.setOnClickListener(this);
        btnPay.setOnClickListener(this);

        lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Bạn có muốn xoá: " + listFood.get(position).getName());
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listFood.remove(position);
                        lvFood.setAdapter(adapterFood);

                        Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                return false;
            }
        });

        lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Food food = (Food) parent.getItemAtPosition(position);
                edtName.setText(food.getName());
                edtPrice.setText(String.valueOf(food.getPrice()));

                posItem = position;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_edit:
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                listFood.get(posItem).setName(name);
                listFood.get(posItem).setPrice(Integer.parseInt(price));

                lvFood.setAdapter(adapterFood);

                edtName.setText("");
                edtPrice.setText("");

                Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_pay:
                List<Food> listPay = new ArrayList<>();
                for (int i = 0; i < listFood.size(); i++) {
                    if (listFood.get(i).isCheck()) {
                        listPay.add(listFood.get(i));
                    }
                }

                Intent intent = new Intent(MainActivity.this, ActivityPay.class);
                intent.putExtra("data", (Serializable) listPay);
                startActivity(intent);

                break;
        }
    }
}
