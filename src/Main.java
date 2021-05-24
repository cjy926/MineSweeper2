import Music.Music;
import minesweeper.MainFrame;
import minesweeper.Read;
import minesweeper.SetFrame;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SetFrame setFrame = new SetFrame();
            setFrame.start();
        });
        Music music=new Music();
        music.run();
    }

}
