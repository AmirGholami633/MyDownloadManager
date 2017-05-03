package amirgholami.mydownloadmanager.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import amirgholami.mydownloadmanager.R;
import amirgholami.mydownloadmanager.model.FileModel;

public class DownloadedListAdapter extends RecyclerView.Adapter<DownloadedListAdapter.DownloadedListViewHolder> {

  private final Context context;
  private final List<FileModel> fileModelList;

  public DownloadedListAdapter(Context context, List<FileModel> fileModelList) {
    this.context = context;
    this.fileModelList = fileModelList;
  }

  @Override
  public DownloadedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(context).inflate(R.layout.layout_item_file, parent, false);
    return new DownloadedListViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(DownloadedListViewHolder holder, int position) {
    FileModel fileModel = fileModelList.get(position);
    holder.txt_file_title.setText(fileModel.getFileName());
    holder.txt_file_size.setText(fileModel.getFileSize());
  }

  @Override
  public int getItemCount() {
    return fileModelList.size();
  }

  public class DownloadedListViewHolder extends RecyclerView.ViewHolder {
    private TextView txt_file_title;
    private TextView txt_file_size;
    private ImageView img_file_type;

    public DownloadedListViewHolder(View itemView) {
      super(itemView);
      txt_file_title = (TextView) itemView.findViewById(R.id.txt_file_title);
      txt_file_size = (TextView) itemView.findViewById(R.id.txt_file_size);
      img_file_type = (ImageView) itemView.findViewById(R.id.img_file_type);
    }
  }
}
