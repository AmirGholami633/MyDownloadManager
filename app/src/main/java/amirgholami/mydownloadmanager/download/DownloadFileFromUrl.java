package amirgholami.mydownloadmanager.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import amirgholami.mydownloadmanager.G;
import amirgholami.mydownloadmanager.listeners.onDownloadListener;

public class DownloadFileFromUrl {

  public static void download(final String downloadPath, final String filepath, final onDownloadListener listener) {
    Thread thread = new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          URL url = new URL(downloadPath);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.setDoOutput(true);
          connection.connect();

          final int fileSize = connection.getContentLength();

          File file = new File(filepath);

          if (file.exists()) {
            file.delete();
          }

          FileOutputStream outputStream = new FileOutputStream(filepath);

          InputStream inputStream = connection.getInputStream();
          byte[] buffer = new byte[G.DOWNLOAD_BUFFER_SIZE];
          int len = 0;
          int downloadedSize = 0;
          while ((len = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, len);
            downloadedSize += len;

            final float downloadPercent = 100.0f * (float) downloadedSize / fileSize;
            if (listener != null) {

              final int finalDownloadedSize = downloadedSize;
              G.HANDLER.post(new Runnable() {

                @Override
                public void run() {
                  listener.onProgressDownload((int) downloadPercent, finalDownloadedSize, fileSize);
                }
              });
            }
          }

          outputStream.close();
        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    });

    thread.start();
  }
}
