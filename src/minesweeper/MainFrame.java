package minesweeper;


import components.GridComponent;
import controller.GameController;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainFrame extends JFrame {
    public static GameController controller;
    private int xCount;
    private int yCount;
    private int mineCount;
    private String times;
    JMenuBar menuBar;
    File file1;
    File file2;
    private difficulty dif = difficulty.diy;
    private String[] players = new String[4];
    int regrestep;

    Player p1;
    Player p2;
    Player p3;
    Player p4;
    JLabel imglabel1;
    JLabel imglabel2;
    JButton change;
    GamePanel gamePanel;
    JButton showmine;
    JButton output;
    ScoreBoard scoreBoard;
    JButton regret;
    MineLabel mineLabel;
    JButton smilebutton;
    int intbackground = 1;

    public void setIntbackground(int intbackground) {
        this.intbackground = intbackground;
    }

    public void setTimes(String a) {
        this.times = a;
    }

    public static GameController getController() {
        return controller;
    }

    public void setMineCount(String mineCount) {
        this.mineCount = Integer.parseInt(mineCount);
        ;
    }

    public void setDif(difficulty dif) {
        this.dif = dif;
    }

    public void setyCount(int yCount) {
        this.yCount = yCount;
    }

    public void setxCount(int xCount) {
        this.xCount = xCount;
    }

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public void setxCount(String xCount) {
        this.xCount = Integer.parseInt(xCount);
    }

    public void setyCount(String yCount) {
        this.yCount = Integer.parseInt(yCount);
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public MainFrame() {
        //todo: change the count of xCount, yCount and mineCount by passing parameters from constructor
        this.Menu();
        this.setJMenuBar(menuBar);
    }


    public void easy() {
        dif = difficulty.easy;
        this.xCount = 9;//grid of row
        this.yCount = 9;// grid of column
        this.mineCount = 10;// mine count
        diy();
    }

    public void middle() {
        dif = difficulty.middle;
        this.xCount = 16;//grid of row
        this.yCount = 16;// grid of column
        this.mineCount = 40;// mine count
        diy();

    }

    public void difficult() {
        dif = difficulty.hard;
        this.xCount = 16;//grid of row
        this.yCount = 30;// grid of column
        this.mineCount = 99;// mine count
        diy();
    }

    public void get_new() {
        removeAll();
        dispose();
        MainFrame mainFrame = new MainFrame();
        mainFrame.setTimes(times);
        mainFrame.setMineCount(mineCount);
        mainFrame.setyCount(yCount);
        mainFrame.setxCount(xCount);
        mainFrame.setPlayers(players);
        mainFrame.setDif(dif);
        if(controller.getRobot() != 0)
        {
            mainFrame.diy_Robot(controller.getRobot());
            return;
        }
        if (dif == difficulty.easy) {
            mainFrame.easy();
        }
        if (dif == difficulty.middle) {
            mainFrame.middle();
        }
        if (dif == difficulty.hard) {
            mainFrame.difficult();
        }
        if (dif == difficulty.diy) {
            mainFrame.diy();
        }
    }

    public void diy() {
        this.setTitle("MineSweeper");//标题
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        this.setLocationRelativeTo(null);

        p1 = new Player(players[0]);
        p2 = new Player(players[1]);


        smilebutton = new JButton(new ImageIcon("smile.jpg"));//笑脸
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 + 40, 20, 30, 30);

        controller = new GameController(p1, p2);//控制人员

        gamePanel = new GamePanel(xCount, yCount, mineCount, this);//棋盘
        controller.setGamePanel(gamePanel);
        gamePanel.setLocation(50, 60);

        scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);//得分板
        controller.setScoreBoard(scoreBoard);
        scoreBoard.setLocation(50, xCount * GridComponent.gridSize + 60);

        mineLabel = new MineLabel(p1, p2, mineCount);//剩余的雷数
        controller.setMineLabel(mineLabel);

        times.replaceAll(" +", "");
        controller.setTimes(times);

        mineLabel.setBounds(70, 20, 100, 30);
        mineLabel.setOpaque(true);
        mineLabel.setBackground(Color.white);

        smilebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//笑脸重置
                super.mouseClicked(e);
                if (e.getButton() == 1) {//左击是重置本局，重新生成雷区
                    removeAll();
                    dispose();
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setTimes(times);
                    mainFrame.setMineCount(mineCount);
                    mainFrame.setyCount(yCount);
                    mainFrame.setxCount(xCount);
                    mainFrame.setPlayers(players);
                    mainFrame.setDif(dif);
                    mainFrame.setIntbackground(intbackground);
                    if (dif == difficulty.easy) {
                        mainFrame.easy();
                    }
                    if (dif == difficulty.middle) {
                        mainFrame.middle();
                    }
                    if (dif == difficulty.hard) {
                        mainFrame.difficult();
                    }
                    if (dif == difficulty.diy) {
                        mainFrame.diy();
                    }
                }
                if (e.getButton() == 3) {
                    setVisible(false);
                    SetFrame setFrame = new SetFrame();
                    setFrame.start();
                }
            }
        });
        this.add(smilebutton);
        this.add(mineLabel);
        this.add(gamePanel);
        this.add(scoreBoard);
        if (intbackground == 1) {
            imglabel1 = new JLabel(new ImageIcon("background.jpg"));
            this.getLayeredPane().add(imglabel1, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel1);
            imglabel1.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        } else {
            imglabel2 = new JLabel(new ImageIcon("background2.jpeg"));
            this.getLayeredPane().add(imglabel2, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel2);
            imglabel2.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void diy_Robot(int difficult) {
        this.setTitle("MineSweeper");//标题
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        this.setLocationRelativeTo(null);

        p1 = new Player(players[0]);
        p2 = new Player(players[1]);


        smilebutton = new JButton(new ImageIcon("smile.jpg"));//笑脸
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 + 40, 20, 30, 30);

        controller = new GameController(p1, p2);//控制人员
        controller.setRobot(difficult);

        gamePanel = new GamePanel(xCount, yCount, mineCount, this);//棋盘
        controller.setGamePanel(gamePanel);
        gamePanel.setLocation(50, 60);

        scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);//得分板
        controller.setScoreBoard(scoreBoard);
        scoreBoard.setLocation(50, xCount * GridComponent.gridSize + 60);

        mineLabel = new MineLabel(p1, p2, mineCount);//剩余的雷数
        controller.setMineLabel(mineLabel);

        times.replaceAll(" +", "");
        controller.setTimes(times);

        mineLabel.setBounds(70, 20, 100, 30);
        mineLabel.setOpaque(true);
        mineLabel.setBackground(Color.white);

        smilebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//笑脸重置
                super.mouseClicked(e);
                if (e.getButton() == 1) {//左击是重置本局，重新生成雷区
                    removeAll();
                    dispose();
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setTimes(times);
                    mainFrame.setMineCount(mineCount);
                    mainFrame.setyCount(yCount);
                    mainFrame.setxCount(xCount);
                    mainFrame.setPlayers(players);
                    mainFrame.setDif(dif);
                    mainFrame.setIntbackground(intbackground);
                    if(controller.getRobot() != 0)
                    {
                        mainFrame.diy_Robot(controller.getRobot());
                        return;
                    }
                }
                if (e.getButton() == 3) {
                    setVisible(false);
                    SetFrame setFrame = new SetFrame();
                    setFrame.start();
                }
            }
        });
        this.add(smilebutton);
        this.add(mineLabel);
        this.add(gamePanel);
        this.add(scoreBoard);
        if (intbackground == 1) {
            imglabel1 = new JLabel(new ImageIcon("background.jpg"));
            this.getLayeredPane().add(imglabel1, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel1);
            imglabel1.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        } else {
            imglabel2 = new JLabel(new ImageIcon("background2.jpeg"));
            this.getLayeredPane().add(imglabel2, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel2);
            imglabel2.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }


        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void setbackground1() {
        this.getLayeredPane().removeAll();
        this.getContentPane().removeAll();
        Menu();
        setJMenuBar(menuBar);
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 + 40, 40, 30, 30);
        gamePanel.setLocation(50, 80);
        scoreBoard.setLocation(50, xCount * GridComponent.gridSize + 80);
        mineLabel.setBounds(70, 40, 100, 30);
        this.getLayeredPane().add(smilebutton);
        this.getLayeredPane().add(mineLabel);
        this.getLayeredPane().add(gamePanel);
        this.getLayeredPane().add(scoreBoard);

        intbackground = 1;
        JLabel imglabel1 = new JLabel(new ImageIcon("background.jpg"));
        this.getLayeredPane().add(imglabel1, new Integer(Integer.MIN_VALUE));
        imglabel1.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        Container cp = this.getContentPane();
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);
        this.getLayeredPane().revalidate();
        this.getLayeredPane().repaint();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void setbackground2() {
        this.getLayeredPane().removeAll();
        this.getContentPane().removeAll();
        Menu();
        setJMenuBar(menuBar);
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 + 40, 40, 30, 30);
        gamePanel.setLocation(50, 80);
        scoreBoard.setLocation(50, xCount * GridComponent.gridSize + 80);
        mineLabel.setBounds(70, 40, 100, 30);
        this.getLayeredPane().add(smilebutton);
        this.getLayeredPane().add(mineLabel);
        this.getLayeredPane().add(gamePanel);
        this.getLayeredPane().add(scoreBoard);
        intbackground = 2;
        JLabel imglabel2 = new JLabel(new ImageIcon("background2.jpeg"));
        this.getLayeredPane().add(imglabel2, new Integer(Integer.MIN_VALUE));
        imglabel2.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        Container cp = this.getContentPane();
        cp.setLayout(null);
        ((JPanel) cp).setOpaque(false);
        this.getLayeredPane().revalidate();
        this.getLayeredPane().repaint();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void read_doc(int step) {
        removeAll();
        dispose();
        MainFrame mainFrame = new MainFrame();
        mainFrame.diy_document(step);
    }

    public void diy_document(int step) {//创建文件读取的雷区

        //注意在这个位置需要传入这几个参数
        //xCount,yCount,chessboard,mineCount,time
        filechooser filechooser1=new filechooser();
        filechooser1.read();
        this.file1=filechooser1.getFile1();
        filechooser1.close();
        String input = Read.read(file1.getName());
        String[] read = input.split("~");
        int doc_xCount = Integer.valueOf(read[0]),doc_yCount = Integer.valueOf(read[1]),
                doc_mineCount = Integer.valueOf(read[2]);
        String doc_time = read[3];
        xCount = doc_xCount;
        yCount = doc_yCount;
        mineCount = doc_mineCount;
        times = doc_time;
        int[][] doc_chessboard = new int[doc_xCount][doc_yCount];
        for(int i = 0; i < doc_xCount; i++)
            for(int j = 0; j < doc_yCount; j++)
                doc_chessboard[i][j] = Integer.valueOf(read[3 + i * doc_yCount + j + 1]);
        int nex = 3 + doc_xCount * doc_yCount + 1;
        this.setTitle("MineSweeper");//标题
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        this.setLocationRelativeTo(null);

        //这里需要传入两个人的名字
        String name1 = read[nex];
        String name2 = read[nex + 1];
        //这里导入已有的名字
        p1 = new Player(name1);
        p2 = new Player(name2);
        nex += 2;

        //这里导入走过的位置与左击还是右击与总的步数
        int doc_zdjs = Integer.valueOf(read[nex]);
        nex++;
        int[] doc_stepX = new int[doc_zdjs];
        int[] doc_stepY = new int[doc_zdjs];
        int[] clickSide = new int[doc_zdjs];
        for(int i = nex,ce = 0; ce < doc_zdjs; i += 3,++ce)
        {
            doc_stepX[ce] = Integer.valueOf(read[i]);
            doc_stepY[ce] = Integer.valueOf(read[i + 1]);
            clickSide[ce] = Integer.valueOf(read[i + 2]);
        }

        smilebutton = new JButton(new ImageIcon("smile.jpg"));//笑脸
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 +40,20,30,30);

        controller = new GameController(p1, p2);//控制人员

        gamePanel = new GamePanel(xCount, yCount, mineCount,this,doc_chessboard);//棋盘
        controller.setGamePanel(gamePanel);
        gamePanel.setLocation(50,60);

        scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);//得分板
        controller.setScoreBoard(scoreBoard);
        scoreBoard.setLocation(50,xCount * GridComponent.gridSize + 60);


        mineLabel=new MineLabel(p1,p2,mineCount);//剩余的雷数
        controller.setMineLabel(mineLabel);

        times.replaceAll(" +","");
        controller.setTimes(times);

        mineLabel.setBounds(70,20,100,30);
        mineLabel.setOpaque(true);
        mineLabel.setBackground(Color.white);

        smilebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//笑脸重置
                super.mouseClicked(e);
                if(e.getButton()==1){//左击是重置本局，重新生成雷区
                    removeAll();
                    dispose();
                    MainFrame mainFrame=new MainFrame();
                    mainFrame.setTimes(times);
                    mainFrame.setMineCount(mineCount);
                    mainFrame.setyCount(yCount);
                    mainFrame.setxCount(xCount);
                    mainFrame.setPlayers(players);
                    mainFrame.setDif(dif);
                    mainFrame.setIntbackground(intbackground);
                    if (dif==difficulty.easy){
                        mainFrame.easy();
                    }
                    if(dif==difficulty.middle){
                        mainFrame.middle();
                    }
                    if(dif==difficulty.hard){
                        mainFrame.difficult();
                    }
                    if (dif==difficulty.diy){
                        mainFrame.diy();
                    }
                }
                if(e.getButton()==3){
                    setVisible(false);
                    SetFrame setFrame=new SetFrame();
                    setFrame.start();
                }
            }
        });
        this.add(smilebutton);
        this.add(mineLabel);
        this.add(gamePanel);
        this.add(scoreBoard);
        if (intbackground==1) {
            imglabel1 = new JLabel(new ImageIcon("background.jpg"));
            this.getLayeredPane().add(imglabel1, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel1);
            imglabel1.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }
        else {
            imglabel2 = new JLabel(new ImageIcon("background2.jpeg"));
            this.getLayeredPane().add(imglabel2, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel2);
            imglabel2.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }

        if(step == 0)
            step = doc_zdjs;
        for(int i = 1; i <= step; i++)
        {
            i--;
            GridComponent clickGridComponent = gamePanel.getGrid(doc_stepX[i],doc_stepY[i]);
            if(clickSide[i] == 0)
                clickGridComponent.click_left();
            else
                clickGridComponent.click_right();
            i++;
        }
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void diy_regret(int step) {//创建文件读取的雷区

        //注意在这个位置需要传入这几个参数
        //xCount,yCount,chessboard,mineCount,time
        String input = Read.read("regret.txt");
        String[] read = input.split("~");
        int doc_xCount = Integer.valueOf(read[0]),doc_yCount = Integer.valueOf(read[1]),
                doc_mineCount = Integer.valueOf(read[2]);
        String doc_time = read[3];
        xCount = doc_xCount;
        yCount = doc_yCount;
        mineCount = doc_mineCount;
        times = doc_time;
        int[][] doc_chessboard = new int[doc_xCount][doc_yCount];
        for(int i = 0; i < doc_xCount; i++)
            for(int j = 0; j < doc_yCount; j++)
                doc_chessboard[i][j] = Integer.valueOf(read[3 + i * doc_yCount + j + 1]);
        int nex = 3 + doc_xCount * doc_yCount + 1;
        this.setTitle("MineSweeper");//标题
        this.setLayout(null);
        this.setSize(yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
        this.setLocationRelativeTo(null);

        //这里需要传入两个人的名字
        String name1 = read[nex];
        String name2 = read[nex + 1];
        //这里导入已有的名字
        p1 = new Player(name1);
        p2 = new Player(name2);
        nex += 2;

        //这里导入走过的位置与左击还是右击与总的步数
        int doc_zdjs = Integer.valueOf(read[nex]);
        nex++;
        int[] doc_stepX = new int[doc_zdjs];
        int[] doc_stepY = new int[doc_zdjs];
        int[] clickSide = new int[doc_zdjs];
        for(int i = nex,ce = 0; ce < doc_zdjs; i += 3,++ce)
        {
            doc_stepX[ce] = Integer.valueOf(read[i]);
            doc_stepY[ce] = Integer.valueOf(read[i + 1]);
            clickSide[ce] = Integer.valueOf(read[i + 2]);
        }

        smilebutton = new JButton(new ImageIcon("smile.jpg"));//笑脸
        smilebutton.setBounds(yCount * GridComponent.gridSize / 2 +40,20,30,30);

        controller = new GameController(p1, p2);//控制人员

        gamePanel = new GamePanel(xCount, yCount, mineCount,this,doc_chessboard);//棋盘
        controller.setGamePanel(gamePanel);
        gamePanel.setLocation(50,60);

        scoreBoard = new ScoreBoard(p1, p2, xCount, yCount);//得分板
        controller.setScoreBoard(scoreBoard);
        scoreBoard.setLocation(50,xCount * GridComponent.gridSize + 60);

        mineLabel=new MineLabel(p1,p2,mineCount);//剩余的雷数
        controller.setMineLabel(mineLabel);

        times.replaceAll(" +","");
        controller.setTimes(times);

        mineLabel.setBounds(70,20,100,30);
        mineLabel.setOpaque(true);
        mineLabel.setBackground(Color.white);

        smilebutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//笑脸重置
                super.mouseClicked(e);
                if(e.getButton()==1){//左击是重置本局，重新生成雷区
                    removeAll();
                    dispose();
                    MainFrame mainFrame=new MainFrame();
                    mainFrame.setTimes(times);
                    mainFrame.setMineCount(mineCount);
                    mainFrame.setyCount(yCount);
                    mainFrame.setxCount(xCount);
                    mainFrame.setPlayers(players);
                    mainFrame.setDif(dif);
                    mainFrame.setIntbackground(intbackground);
                    if (dif==difficulty.easy){
                        mainFrame.easy();
                    }
                    if(dif==difficulty.middle){
                        mainFrame.middle();
                    }
                    if(dif==difficulty.hard){
                        mainFrame.difficult();
                    }
                    if (dif==difficulty.diy){
                        mainFrame.diy();
                    }
                }
                if(e.getButton()==3){
                    setVisible(false);
                    SetFrame setFrame=new SetFrame();
                    setFrame.start();
                }
            }
        });
        this.add(smilebutton);
        this.add(mineLabel);
        this.add(gamePanel);
        this.add(scoreBoard);
        if (intbackground==1) {
            imglabel1 = new JLabel(new ImageIcon("background.jpg"));
            this.getLayeredPane().add(imglabel1, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel1);
            imglabel1.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }
        else {
            imglabel2 = new JLabel(new ImageIcon("background2.jpeg"));
            this.getLayeredPane().add(imglabel2, new Integer(Integer.MIN_VALUE));
            this.getLayeredPane().moveToFront(imglabel2);
            imglabel2.setBounds(0, 0, yCount * GridComponent.gridSize + 100, xCount * GridComponent.gridSize + 230);
            Container cp = this.getContentPane();
            cp.setLayout(null);
            ((JPanel) cp).setOpaque(false);
        }

        if(step == 0)
            step = doc_zdjs;
        for(int i = 1; i <= step; i++)
        {
            i--;
            GridComponent clickGridComponent = gamePanel.getGrid(doc_stepX[i],doc_stepY[i]);
            if(clickSide[i] == 0)
                clickGridComponent.click_left();
            else
                clickGridComponent.click_right();
            i++;
        }
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void Menu(){
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenu cheatMenu = new JMenu("道具");
        JMenu viewMenu = new JMenu("视图");
        JMenu helpMenu = new JMenu("帮助");

        menuBar.add(fileMenu);
        menuBar.add(cheatMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        JMenuItem saveMenuItem = new JMenuItem("保存");
        JMenuItem readMenuItem = new JMenuItem("读取");

        fileMenu.add(saveMenuItem);
        fileMenu.add(readMenuItem);

        JMenuItem cheatMenuItem = new JMenuItem("透视雷");
        JMenuItem regretMenuItem = new JMenuItem("悔棋");

        cheatMenu.add(cheatMenuItem);
        cheatMenu.add(regretMenuItem);

        JMenuItem changeMenuItem = new JMenuItem("换装");

        viewMenu.add(changeMenuItem);

        //保存监听器的设置
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                filechooser filechooser=new filechooser();
                filechooser.save();
                file2=filechooser.getFile2();
                filechooser.close();
                String s = new String() ;
                s = String.valueOf(xCount) + "~" + String.valueOf(yCount) + "~" + String.valueOf(mineCount) + "~" + times + "~";
                for(int i = 0; i < xCount; i++)
                    for(int j = 0; j < yCount; j++)
                        s = s + String.valueOf(gamePanel.getChessboard()[i][j]) + "~";
                s = s + p1.getUserName() + "~" + p2.getUserName() + "~";
                s = s + String.valueOf(gamePanel.getZdjs());
                for(int i = 0; i < gamePanel.getZdjs(); i++)
                    s = s + "~" + gamePanel.getClickX()[i] + "~" + gamePanel.getClickY()[i] + "~" +gamePanel.getClickSide()[i];
                Read.saveAsFileWriter(s,file2.getName());
            }
        });

        //读取监听器设置
        readMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                MainFrame mainFrame=new MainFrame();
                mainFrame.diy_document(0);
            }
        });

        //透视雷监听器的设置
        cheatMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (int i = 0; i < xCount; i++) {
                    for (int j = 0; j < yCount; j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.open();
                    }
                }
            }
        });

        //切换主题监听器
        changeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (intbackground==1){
                    setbackground2();
                }
                else {
                    setbackground1();
                }
            }
        });

        //帮助监听器设置

        //悔棋监听器设置
        regretMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              String str=JOptionPane.showInputDialog("输 入 悔 棋 步 数","");
              regrestep=Integer.parseInt(str);
              String s = new String() ;
              s = String.valueOf(xCount) + "~" + String.valueOf(yCount) + "~" + String.valueOf(mineCount) + "~" + times + "~";
              for(int i = 0; i < xCount; i++)
                   for(int j = 0; j < yCount; j++)
                        s = s + String.valueOf(gamePanel.getChessboard()[i][j]) + "~";
                s = s + p1.getUserName() + "~" + p2.getUserName() + "~";
                s = s + String.valueOf(gamePanel.getZdjs());
                for(int i = 0; i < gamePanel.getZdjs(); i++)
                    s = s + "~" + gamePanel.getClickX()[i] + "~" + gamePanel.getClickY()[i] + "~" +gamePanel.getClickSide()[i];
              Read.cun(s);
              removeAll();
              dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.diy_regret(gamePanel.getZdjs() - regrestep);
            }
        });
    }

}
