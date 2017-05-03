package amirgholami.mydownloadmanager.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import amirgholami.mydownloadmanager.R;
import amirgholami.mydownloadmanager.adapter.DownloadedListAdapter;
import amirgholami.mydownloadmanager.db.DBHelper;
import amirgholami.mydownloadmanager.model.FileModel;

public class DownloadedFiles extends AppCompatActivity {
  private RecyclerView recyclerview;
  private DBHelper dbHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_downloaded_files);

    initView();
  }

  private void initView() {
    recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    dbHelper = new DBHelper(this);
    setupRecyclerview(dbHelper.getFileList());
  }

  private void setupRecyclerview(List<FileModel> fileModelList) {
    DownloadedListAdapter downloadedListAdapter = new DownloadedListAdapter(this, fileModelList);
    recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerview.setAdapter(downloadedListAdapter);
  }
}
