package amirgholami.mydownloadmanager.model;

public class FileModel {
  private int id;
  private String fileName;
  private String fileUrl;
  private String fileSize;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getFileSize() {
    return fileSize + " MB";
  }

  public void setFileSize(String fileSize) {
    this.fileSize = fileSize;
  }
}
