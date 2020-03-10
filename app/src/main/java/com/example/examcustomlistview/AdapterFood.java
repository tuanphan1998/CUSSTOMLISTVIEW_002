package com.example.examcustomlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterFood extends ArrayAdapter<Food> {

    Context context;
    int resource;
    List<Food> listFood;

    public AdapterFood(@NonNull Context context, int resource, @NonNull List<Food> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.listFood = objects;
    }

    public class ViewHolder {
        CheckBox cbFood;
        ImageView imgFood;
        TextView txtName;
        TextView txtPrice;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.cbFood = convertView.findViewById(R.id.cb_food);
            viewHolder.imgFood = convertView.findViewById(R.id.img_food);
            viewHolder.txtName = convertView.findViewById(R.id.txt_name);
            viewHolder.txtPrice = convertView.findViewById(R.id.txt_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Food food = listFood.get(position);
        viewHolder.cbFood.setChecked(food.isCheck());
        viewHolder.imgFood.setImageResource(food.getImage());
        viewHolder.txtName.setText(food.getName());
        viewHolder.txtPrice.setText(String.valueOf(food.getPrice()));

        viewHolder.cbFood.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    food.setCheck(true);
                } else {
                    food.setCheck(false);
                }
            }
        });

        return convertView;
    }
}
