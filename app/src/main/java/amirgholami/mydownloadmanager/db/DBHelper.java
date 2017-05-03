package amirgholami.mydownloadmanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import amirgholami.mydownloadmanager.model.FileModel;

public class DBHelper extends SQLiteOpenHelper {
  private static final String DBNAME = "download-list.sqlite";
  private static final String DBVERSION = "1";
  private static final String TBLDOWNLOADLIST = "tbl_download_list";
  private static final String COLID = "id";
  private static final String COLNAME = "file_name";
  private static final String COLURL = "file_url";

  private static final String SQLCOMMAND = "CREATE TABLE IF NOT EXISTS " + TBLDOWNLOADLIST +
          " ( " + COLID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "
          + COLNAME + " TEXT,"
          + COLURL + " TEXT";

  public DBHelper(Context context) {
    super(context, DBNAME, null, Integer.valueOf(DBVERSION));
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(SQLCOMMAND);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }

  public void saveFileDownload(FileModel fileModel) {
    SQLiteDatabase database = getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(COLID, fileModel.getId());
    contentValues.put(COLNAME, fileModel.getFileName());
    contentValues.put(COLURL, fileModel.getFileUrl());

    database.insert(TBLDOWNLOADLIST, null, contentValues);
  }

  public List<FileModel> getFileList() {
    SQLiteDatabase database = getReadableDatabase();
    Cursor cursor = database.rawQuery("SELECT * FROM " + TBLDOWNLOADLIST, null);
    List<FileModel> fileModelList = new ArrayList<>();

    try {
      if (cursor.moveToFirst()) {
        do {
          FileModel fileModel = new FileModel();

          fileModelList.add(fileModel);
        } while (cursor.moveToNext());
      }
    } catch (Exception e) {


    } finally {
      if (cursor != null && !cursor.isClosed()) {
        cursor.close();
      }
    }
    return fileModelList;
  }
}
