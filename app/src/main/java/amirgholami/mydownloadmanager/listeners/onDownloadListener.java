package amirgholami.mydownloadmanager.listeners;

public interface onDownloadListener {

  void onProgressDownload(int percent, int downloadedSize, int fileSize);
}