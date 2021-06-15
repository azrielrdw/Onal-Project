package onal.v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "content.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + Content.ContentTable.TABLE_NAME + " (" +
                Content.ContentTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Content.ContentTable.DIRECTORY + " TEXT NOT NULL, " +
                Content.ContentTable.NAME + " TEXT NOT NULL" +
                ");";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addContent(Content content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Content.ContentTable.DIRECTORY, content.getDirectory());
        cv.put(Content.ContentTable.NAME, content.getName());

        long insert = db.insert(Content.ContentTable.TABLE_NAME, null, cv);
        if (insert == -1){
            return false;
        } else{
            return true;
        }
    }

    public List<Content> getWallpaperContent(){
        List<Content> contentWallpaperList = new ArrayList<>();
        String selectRows = "SELECT * FROM " + Content.ContentTable.TABLE_NAME + " WHERE directory LIKE '%.jpg' or directory LIKE '%.png';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectRows, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String directory = cursor.getString(1);
                String name = cursor.getString(2);

                Content newContent = new Content(id, directory, name);
                contentWallpaperList.add(newContent);
            }while (cursor.moveToNext());
        } else{

        }

        cursor.close();
        db.close();

        return contentWallpaperList;
    }

    public List<Content> getRingtoneContent(){
        List<Content> contentRingtoneList = new ArrayList<>();
        String selectRows = "SELECT * FROM " + Content.ContentTable.TABLE_NAME + " WHERE directory LIKE '%.mp3';";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectRows, null);
        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String directory = cursor.getString(1);
                String name = cursor.getString(2);

                Content newContent = new Content(id, directory, name);
                contentRingtoneList.add(newContent);
            }while (cursor.moveToNext());
        } else{

        }

        cursor.close();
        db.close();

        return contentRingtoneList;
    }
}
