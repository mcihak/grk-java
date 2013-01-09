import java.awt.FileDialog;
import java.awt.Frame;

public class FileDialogGrafEd {
	
  public FileDialogGrafEd() {}

  public String loadFile (Frame f, String title, String defDir, String fileType) {
    FileDialog fd = new FileDialog(f, title, FileDialog.LOAD);
    fd.setFile(fileType);
    fd.setDirectory(defDir);
    fd.setLocation(50, 50);
    fd.setVisible(true);
    return fd.getDirectory() + fd.getFile();
  }

  public String saveFile (Frame f, String title, String defDir, String fileType) {
    FileDialog fd = new FileDialog(f, title, FileDialog.SAVE);
    fd.setFile(fileType);
    fd.setDirectory(defDir);
    fd.setLocation(50, 50);
    fd.setVisible(true);
    return fd.getDirectory() + fd.getFile();
  }
}