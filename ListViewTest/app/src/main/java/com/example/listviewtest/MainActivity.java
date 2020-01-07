package com.example.listviewtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple",
            "Strawberry", "Cherry", "Mango", "Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"};*/

    private List<Fruit> mFruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this,
                R.layout.fruit_item, mFruitList);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = mFruitList.get(position);
                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
            mFruitList.add(apple);
            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
            mFruitList.add(banana);
            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
            mFruitList.add(orange);
            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
            mFruitList.add(watermelon);
            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
            mFruitList.add(pear);
            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
            mFruitList.add(grape);
            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
            mFruitList.add(pineapple);
            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
            mFruitList.add(strawberry);
            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
            mFruitList.add(cherry);
            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
            mFruitList.add(mango);
        }
    }

    class FruitAdapter extends ArrayAdapter<Fruit> {

        private int resourceId;

        public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Fruit fruit = getItem(position);
            /*View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);*/
            //优化listview
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.fruit_item, parent,
                        false);
                viewHolder = new ViewHolder();
                viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
                viewHolder.fruitName = view.findViewById(R.id.fruit_name);
                view.setTag(viewHolder); //将ViewHolder存储在View中
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.fruitImage.setImageResource(fruit.getImageId());
            viewHolder.fruitName.setText(fruit.getName());
            return view;
        }

        class ViewHolder {
            ImageView fruitImage;
            TextView fruitName;
        }
    }
}
