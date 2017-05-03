package amirgholami.mydownloadmanager.listeners;

import amirgholami.mydownloadmanager.model.FileModel;

public interface SelectorFileListener {
  FileModel onSelectItem(FileModel fileModel);
}