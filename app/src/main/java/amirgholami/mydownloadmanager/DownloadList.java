package amirgholami.mydownloadmanager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import amirgholami.mydownloadmanager.db.DBHelper;
import amirgholami.mydownloadmanager.model.FileModel;

public class DownloadList {
  public static List<FileModel> getFileList() {

    List<FileModel> fileModelList = new ArrayList<>();
    FileModel fileModel = new FileModel();
    fileModel.setId(1);
    fileModel.setFileName("Labkhand Arosaki.mp3");
    fileModel.setFileUrl("http://192.168.1.112/arosaki.mp3");
    //fileModel.setFileSize("150");

    FileModel fileModel2 = new FileModel();
    fileModel2.setId(2);
    fileModel2.setFileName("CodeIgniter.zip");
    fileModel2.setFileUrl("http://192.168.1.112/codeigniter.zip");
    //fileModel2.setFileSize("1.5");

    FileModel fileModel3 = new FileModel();
    fileModel3.setId(3);
    fileModel3.setFileName("File Number #2");
    fileModel3.setFileUrl("http://localhost/file2.mp3");
    //fileModel3.setFileSize("5");

    FileModel fileModel4 = new FileModel();
    fileModel4.setId(4);
    fileModel4.setFileName("File Number #3");
    fileModel4.setFileUrl("http://localhost/file3.pdf");
    //fileModel4.setFileSize("2");

    FileModel fileModel5 = new FileModel();
    fileModel5.setId(5);
    fileModel5.setFileName("File Number #4");
    fileModel5.setFileUrl("http://localhost/file4.apk");
    //fileModel5.setFileSize("1");

    fileModelList.add(fileModel);
    fileModelList.add(fileModel2);
    fileModelList.add(fileModel3);
    fileModelList.add(fileModel4);
    fileModelList.add(fileModel5);

    return fileModelList;
  }
}
