package com.example.litepaltest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknown");
                book.save();
            }
        });

        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("Unknown");
                book.save();
                book.setPrice(10.99);
                book.save();*/
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ?",
                        "The Lost Symbol", "Dan Brown");

                //设置默认值
                /*Book book1 = new Book();
                book1.setToDefault("pages");
                book1.updateAll();*/

            }
        });

        Button deleteData = findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class, "price < ?", "15");
            }
        });


        Button queryData = findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book :
                        books) {
                    Log.d(TAG, "book name is " + book.getName());
                    Log.d(TAG, "book author is " + book.getAuthor());
                    Log.d(TAG, "book pages is " + book.getPages());
                    Log.d(TAG, "book price is " + book.getPrice());
                    Log.d(TAG, "book press is " + book.getPress());
                }

                //查询第一个
                Book firstBook = LitePal.findFirst(Book.class);
                //查询最后一个
                Book lastBook = LitePal.findLast(Book.class);
                //select()方法用于指定查询那几列
                List<Book> books1 = LitePal.select("name", "author").find(Book.class);
                //where()方法用于指定查询的约束条件
                List<Book> books2 = LitePal.where("pages > ?", "400").find(Book.class);
                //order()方法用于指定查询结果的排序方式
                List<Book> books3 = LitePal.order("price desc").find(Book.class);
                //limit()方法用于指定查询结果的数量
                List<Book> books4 = LitePal.limit(3).find(Book.class);
                //offset()方法用于指定查询结果的偏移量
                List<Book> books5 = LitePal.limit(3).offset(1).find(Book.class);
                //还可以进行结合查询
                List<Book> books6 = LitePal
                        .select("name", "author", "pages")
                        .where("pages > ?", "400")
                        .order("pages")
                        .limit(10)
                        .offset(10)
                        .find(Book.class);
            }
        });
    }
}
