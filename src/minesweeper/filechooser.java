package minesweeper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class filechooser {
    File file1;
    JFrame fileframe;
    File file2;
    public filechooser() {
        fileframe = new JFrame();
        fileframe.setTitle("文件选择器");
        fileframe.setLayout(null);
        fileframe.setSize(300, 300);
        fileframe.setLocationRelativeTo(null);
        fileframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fileframe.setVisible(true);
    }
    public void read(){
        JFileChooser Chooser = new JFileChooser();
        Chooser.setCurrentDirectory(new File("."));
        Chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        Chooser.setFileFilter(new FileNameExtensionFilter("txt(*.txt)", "txt"));
        int result = Chooser.showOpenDialog(fileframe);
        if (result == JFileChooser.APPROVE_OPTION) {
            file1 = Chooser.getSelectedFile();
        }
    }
    public void save(){
        JFileChooser Chooser = new JFileChooser();
        Chooser.setCurrentDirectory(new File("."));
        Chooser.setSelectedFile(new File("inputname.txt"));

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = Chooser.showSaveDialog(fileframe);

        if (result == JFileChooser.APPROVE_OPTION) {
            file2 = Chooser.getSelectedFile();
        }

    }


    public File getFile2() {
        return file2;
    }

    public File getFile1() {
        return file1;
    }
    public void close(){
        fileframe.dispose();
    }
}
