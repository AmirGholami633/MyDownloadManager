package amirgholami.mydownloadmanager.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import amirgholami.mydownloadmanager.G;
import amirgholami.mydownloadmanager.R;
import amirgholami.mydownloadmanager.db.DBHelper;
import amirgholami.mydownloadmanager.listeners.SelectorFileListener;
import amirgholami.mydownloadmanager.download.DownloadFileFromUrl;
import amirgholami.mydownloadmanager.model.FileModel;
import amirgholami.mydownloadmanager.listeners.onDownloadListener;

public class DownloadManagerAdapter extends RecyclerView.Adapter<DownloadManagerAdapter.DMViewHolder> {
  private Context context;
  private List<FileModel> fileModelList;
  private SelectorFileListener selectorFileListener;
  private onDownloadListener downloadListener;
  private DownloadFileFromUrl downloadFileFromUrl = new DownloadFileFromUrl();
  private DBHelper dbHelper = new DBHelper(G.context);

  public DownloadManagerAdapter(Context context, List<FileModel> fileModelList, SelectorFileListener selectorFileListener, onDownloadListener downloadListener) {
    this.context = context;
    this.fileModelList = fileModelList;
    this.selectorFileListener = selectorFileListener;
    this.downloadListener = downloadListener;
  }

  @Override
  public DMViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item_file, parent, false);
    return new DMViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(final DMViewHolder holder, int position) {
    final FileModel fileModel = fileModelList.get(position);
    holder.txt_file_title.setText(fileModel.getFileName());

    holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        selectorFileListener.onSelectItem(selectorFileListener.onSelectItem(fileModel));
        downloadFileFromUrl.download(fileModel.getFileUrl(), G.APP_DIR + "/" + fileModel.getFileName(), new onDownloadListener() {
          @Override
          public void onProgressDownload(int percent, int downloadedSize, int fileSize) {
            holder.txt_file_size.setText(G.convertByteToMB(fileSize));
            downloadListener.onProgressDownload(percent, downloadedSize, fileSize);
            if (percent >= 100) {
              dbHelper.saveFileDownload(fileModel);
              holder.img_file_type.setImageResource(R.drawable.tick);
            }
          }
        });
      }
    });
  }

  @Override
  public int getItemCount() {
    return fileModelList.size();
  }

  public class DMViewHolder extends RecyclerView.ViewHolder {
    private TextView txt_file_title;
    private TextView txt_file_size;
    private ImageView img_file_type;

    public DMViewHolder(View itemView) {
      super(itemView);
      txt_file_title = (TextView) itemView.findViewById(R.id.txt_file_title);
      txt_file_size = (TextView) itemView.findViewById(R.id.txt_file_size);
      img_file_type = (ImageView) itemView.findViewById(R.id.img_file_type);
    }
  }
}


