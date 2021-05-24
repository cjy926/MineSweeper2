package minesweeper;

import entity.Player;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class SetFrame extends JFrame {
    public difficulty dif;

    public MainFrame mainframe = new MainFrame();

    public SetFrame() {
        this.setTitle("MineSweeper");
        this.setLayout(null);
        this.setLocation(750, 200);//改了位置
        this.setSize(500, 500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void start() {
        JPanel set = new JPanel(null);
        JButton mode1 = new JButton("对      战      模      式");
        JButton mode2 = new JButton("人      机      模      式");
        JButton mode3 = new JButton("读      取      文      件");
        mode1.setLocation(150, 80);
        mode2.setLocation(150, 215);
        mode3.setLocation(150, 350);
        mode1.setSize(200, 50);
        mode2.setSize(200, 50);
        mode3.setSize(200, 50);
        //这里是读取文件的按钮，点了之后可以选择文件来读取
        //然后选择了文件之后是直接创建一个mainframe,然后进入mainframe,注意：在mainframe里面导入文件的数值！
        //具体函数调用mainframe里面的read_doc(step);

        mode1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        remove(set);
                                        difficult();
                                    }
                                }
        );
        mode2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        remove(set);
                                        dif = difficulty.middle;//合理随便给个值
                                        robotdif();

                                    }
                                }
        );
        mode3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        remove(set);
                                        MainFrame mainFrame = new MainFrame();
                                        mainFrame.read_doc(0);
                                        dispose();
                                    }
                                }
        );
        set.add(mode1);
        set.add(mode2);
        set.add(mode3);
        this.setContentPane(set);
        ImageIcon background = new ImageIcon("background.jpg");
        JLabel imglabel = new JLabel(background);
        this.getLayeredPane().add(imglabel, new Integer(Integer.MIN_VALUE));
        imglabel.setBounds(0, 0, 500, 500);
        Container cp = this.getContentPane();//设置背景
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);
        this.setVisible(true);
    }

    public void robotdif() {
        JPanel robot = new JPanel(null);
        JButton mode1 = new JButton("低  级");
        JButton mode2 = new JButton("高  级");
        mode1.setLocation(150, 120);
        mode2.setLocation(150, 280);
        mode1.setSize(200, 50);
        mode2.setSize(200, 50);
        mode1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        remove(robot);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 对 手 游 戏 昵 称", " ");
                                        mainframe.setPlayers(players);
                                        String x, y, z, w;
                                        x = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        while (Integer.parseInt(x) > 24) {
                                            JOptionPane.showMessageDialog(mainframe, "行数不能超过24，请重新输入");
                                            x = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        }
                                        y = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        while (Integer.parseInt(y) > 30) {
                                            JOptionPane.showMessageDialog(mainframe, "列数不能超过30，请重新输入");
                                            y = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        }
                                        z = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        while (Integer.parseInt(z) > Integer.parseInt(x) * Integer.parseInt(y) * 0.5) {
                                            JOptionPane.showMessageDialog(mainframe, "雷数不能超过总格子的一半，请重新输入");
                                            z = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        }
                                        w = "1";
                                        mainframe.setxCount(Integer.valueOf(x));
                                        mainframe.setyCount(Integer.valueOf(y));
                                        mainframe.setMineCount(z);
                                        mainframe.setTimes("1");
                                        mainframe.diy_Robot(Integer.valueOf(w));

                                    }
                                }
        );

        mode2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        remove(robot);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 对 手 游 戏 昵 称", " ");
                                        mainframe.setPlayers(players);
                                        String x, y, z, w;
                                        x = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        while (Integer.parseInt(x) > 24) {
                                            JOptionPane.showMessageDialog(mainframe, "行数不能超过24，请重新输入");
                                            x = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        }
                                        y = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        while (Integer.parseInt(y) > 30) {
                                            JOptionPane.showMessageDialog(mainframe, "列数不能超过30，请重新输入");
                                            y = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        }
                                        z = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        while (Integer.parseInt(z) > Integer.parseInt(x) * Integer.parseInt(y) * 0.5) {
                                            JOptionPane.showMessageDialog(mainframe, "雷数不能超过总格子的一半，请重新输入");
                                            z = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        }
                                        w = "2";
                                        mainframe.setxCount(Integer.valueOf(x));
                                        mainframe.setyCount(Integer.valueOf(y));
                                        mainframe.setMineCount(z);
                                        mainframe.setTimes("1");
                                        mainframe.diy_Robot(Integer.valueOf(w));

                                    }
                                }
        );
        robot.add(mode1);
        robot.add(mode2);
        this.setContentPane(robot);
        ImageIcon background = new ImageIcon("background.jpg");
        JLabel imglabel = new JLabel(background);
        this.getLayeredPane().add(imglabel, new Integer(Integer.MIN_VALUE));
        imglabel.setBounds(0, 0, 500, 500);
        Container cp = this.getContentPane();//设置背景
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);
        this.setVisible(true);

    }

    public void difficult() {
        JPanel difficult = new JPanel(null);
        JButton mode1 = new JButton("简      单");
        JButton mode2 = new JButton("一      般");
        JButton mode3 = new JButton("困      难");
        JButton mode4 = new JButton("自    定    义");
        mode1.setLocation(150, 40);
        mode2.setLocation(150, 150);
        mode3.setLocation(150, 260);
        mode4.setLocation(150, 370);
        mode1.setSize(200, 50);
        mode2.setSize(200, 50);
        mode3.setSize(200, 50);
        mode4.setSize(200, 50);
        mode1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        dif = difficulty.easy;
                                        remove(difficult);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 2 号 选 手 游 戏 昵 称", " ");
                                        String times = JOptionPane.showInputDialog
                                                (mainframe, "设 定 选 手 每 回 合 点 击 次 数", "");
                                        mainframe.setTimes(times);
                                        mainframe.setPlayers(players);
                                        if (dif == difficulty.easy) {
                                            mainframe.easy();
                                        }
                                        if (dif == difficulty.middle) {
                                            mainframe.middle();
                                        }
                                        if (dif == difficulty.hard) {
                                            mainframe.difficult();
                                        }
                                        if (dif == difficulty.diy) {
                                            mainframe.diy();
                                        }
                                    }
                                }
        );
        mode2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        dif = difficulty.middle;
                                        remove(difficult);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 2 号 选 手 游 戏 昵 称", " ");
                                        String times = JOptionPane.showInputDialog
                                                (mainframe, "设 定 选 手 每 回 合 点 击 次 数", "");
                                        mainframe.setTimes(times);
                                        mainframe.setPlayers(players);
                                        if (dif == difficulty.easy) {
                                            mainframe.easy();
                                        }
                                        if (dif == difficulty.middle) {
                                            mainframe.middle();
                                        }
                                        if (dif == difficulty.hard) {
                                            mainframe.difficult();
                                        }
                                        if (dif == difficulty.diy) {
                                            mainframe.diy();
                                        }
                                    }
                                }
        );
        mode3.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        dif = difficulty.hard;
                                        remove(difficult);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 2 号 选 手 游 戏 昵 称", " ");
                                        String times = JOptionPane.showInputDialog
                                                (mainframe, "设 定 选 手 每 回 合 点 击 次 数", "");
                                        mainframe.setTimes(times);
                                        mainframe.setPlayers(players);
                                        if (dif == difficulty.easy) {
                                            mainframe.easy();
                                        }
                                        if (dif == difficulty.middle) {
                                            mainframe.middle();
                                        }
                                        if (dif == difficulty.hard) {
                                            mainframe.difficult();
                                        }
                                        if (dif == difficulty.diy) {
                                            mainframe.diy();
                                        }
                                    }
                                }
        );
        mode4.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent actionEvent) {
                                        dif = difficulty.diy;
                                        remove(difficult);
                                        String xcount = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        while (Integer.parseInt(xcount) > 24) {
                                            JOptionPane.showMessageDialog(mainframe, "行数不能超过24，请重新输入");
                                            xcount = JOptionPane.showInputDialog("输  入  设  定  行  数");
                                        }
                                        String ycount = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        while (Integer.parseInt(ycount) > 30) {
                                            JOptionPane.showMessageDialog(mainframe, "列数不能超过30，请重新输入");
                                            ycount = JOptionPane.showInputDialog("输  入  设  定  列  数");
                                        }
                                        String minecount = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        while (Integer.parseInt(minecount) > Integer.parseInt(xcount) * Integer.parseInt(ycount) * 0.5) {
                                            JOptionPane.showMessageDialog(mainframe, "雷数不能超过总格子的一半，请重新输入");
                                            minecount = JOptionPane.showInputDialog("输  入  设  定  雷  数");
                                        }
                                        mainframe.setxCount(xcount);
                                        mainframe.setyCount(ycount);
                                        mainframe.setMineCount(minecount);
                                        setVisible(false);
                                        String[] players = new String[4];
                                        players[0] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 1 号 选 手 游 戏 昵 称", " ");
                                        players[1] = JOptionPane.showInputDialog
                                                (mainframe, "输 入 2 号 选 手 游 戏 昵 称", " ");
                                        String times = JOptionPane.showInputDialog
                                                (mainframe, "设 定 选 手 每 回 合 点 击 次 数", "");
                                        mainframe.setTimes(times);
                                        mainframe.setPlayers(players);
                                        if (dif == difficulty.easy) {
                                            mainframe.easy();
                                        }
                                        if (dif == difficulty.middle) {
                                            mainframe.middle();
                                        }
                                        if (dif == difficulty.hard) {
                                            mainframe.difficult();
                                        }
                                        if (dif == difficulty.diy) {
                                            mainframe.diy();
                                        }
                                    }
                                }
        );
        difficult.add(mode1);
        difficult.add(mode2);
        difficult.add(mode3);
        difficult.add(mode4);
        this.setContentPane(difficult);
        Container cp = this.getContentPane();//设置背景
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);
        this.setVisible(true);
    }
}