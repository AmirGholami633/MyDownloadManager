package amirgholami.mydownloadmanager.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import amirgholami.mydownloadmanager.DownloadList;
import amirgholami.mydownloadmanager.G;
import amirgholami.mydownloadmanager.R;
import amirgholami.mydownloadmanager.adapter.DownloadManagerAdapter;
import amirgholami.mydownloadmanager.listeners.SelectorFileListener;
import amirgholami.mydownloadmanager.listeners.onDownloadListener;
import amirgholami.mydownloadmanager.model.FileModel;

public class MainActivity extends AppCompatActivity {
  private TextView txt_file_title;
  private TextView txt_file_size;
  private TextView txt_file_percent;
  private RecyclerView recyclerview;
  private FloatingActionButton floatingActionButton;
  private ProgressBar prgDownload;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initView();
    setupRecyclerView(DownloadList.getFileList());
  }

  private void initView() {
    recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
    txt_file_title = (TextView) findViewById(R.id.txt_file_title);
    txt_file_size = (TextView) findViewById(R.id.txt_file_size);
    txt_file_percent = (TextView) findViewById(R.id.txt_file_percent);
    prgDownload = (ProgressBar) findViewById(R.id.prgDownload);
    floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

    floatingActionButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(MainActivity.this, DownloadedFiles.class));
      }
    });
  }

  private void setupRecyclerView(List<FileModel> fileModelList) {
    DownloadManagerAdapter adapter = new DownloadManagerAdapter(this, fileModelList, new SelectorFileListener() {
      @Override
      public FileModel onSelectItem(FileModel fileModel) {
        if (fileModel != null) {
          txt_file_title.setText(fileModel.getFileName());
          txt_file_size.setText(String.valueOf(fileModel.getFileSize()));
        }
        return null;
      }
    }, new onDownloadListener() { /*Download Progress Listener*/
      @Override
      public void onProgressDownload(int percent, int downloadedSize, int fileSize) {
        prgDownload.setProgress(percent);
        txt_file_percent.setText(String.valueOf(percent));
        txt_file_size.setText(G.convertByteToMB(fileSize) + "/" + G.convertByteToMB(downloadedSize));
      }
    });

    recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerview.setAdapter(adapter);
  }
}