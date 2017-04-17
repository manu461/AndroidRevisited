package com.example.stalker.databaseconnectivitydemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText toDoeditText;
    private Button addToDoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addToDoButton = (Button)findViewById(R.id.id_button_addTodo_activityOne);
        addToDoButton.setOnClickListener(this);
        toDoeditText = (EditText)findViewById(R.id.id_editText_activityOne);

        DataBaseHelper dbHelper = new DataBaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columns = {"title", "completed"};
        Cursor cursor = db.query("todo",columns,null,null,null,null,null);

        while (cursor.moveToNext()){
            Log.d("Codekamp","title : "+cursor.getString(0));
            Log.d("CodeKamp","Completed : "+cursor.getInt(1));
        }

    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.id_button_addTodo_activityOne){
            String toDo = toDoeditText.getText().toString();
            DataBaseHelper dbHelper = new DataBaseHelper(MainActivity.this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues data = new ContentValues();
            data.put("title",toDo);
            data.put("completed", 0);
            db.insert("todo",null,data);
        }

    }
}
