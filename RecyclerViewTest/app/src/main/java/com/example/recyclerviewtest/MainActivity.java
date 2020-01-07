package com.example.recyclerviewtest;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> mFruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFruits();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        FruitAdapter adapter = new FruitAdapter(mFruitList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));

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

}
