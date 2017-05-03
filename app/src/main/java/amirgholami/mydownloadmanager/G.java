package amirgholami.mydownloadmanager;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import java.io.File;


public class G extends Application {
  public static Context context;
  public static final int DOWNLOAD_BUFFER_SIZE = 8 * 1024;
  public static final String APP_DIR = Environment.getExternalStorageDirectory().toString() + "/myFileDownloader";
  public static final Handler HANDLER = new Handler();

  @Override
  public void onCreate() {
    super.onCreate();
    context = getApplicationContext();
    new File(APP_DIR).mkdirs();
  }

  public static String convertByteToMB(int byteLength) {
    float sizeInKB = (float) byteLength / 1024;
    float sizeInMB = (float) sizeInKB / 1024;

    return String.format("%.1fMB", sizeInMB);
  }
}
