package components;

import Music.Music;
import controller.GameController;
import entity.GridStatus;
import minesweeper.GamePanel;
import minesweeper.MainFrame;
import minesweeper.SetFrame;
import minesweeper.SetFrame;

import java.awt.*;
import java.util.Set;

public class GridComponent extends BasicComponent {
    public static int gridSize = 30;


    private int xCount;
    private int yCount;//每个点需要知道当前棋盘的大小
    private GamePanel gamePanel;//当前的棋盘
    private MainFrame mainFrame;


    private int row;
    private int col;
    private GridStatus status = GridStatus.Covered;
    private int content = 0;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image1 = toolkit.getImage("lei.jpg");
    public void setMainFrame(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
    public void setXY(int xCount,int yCount){
        this.xCount = xCount;
        this.yCount = yCount;
    }

    public void setGamePanel(GamePanel gamePanel)
    {
        this.gamePanel = gamePanel;
    }
    public void open()//翻开当前雷
    {
        if(this.status == GridStatus.Covered && this.content == -1)
            this.status = GridStatus.Clickedbomb;
        repaint();
    }
    public void openall()
    {
        if(this.status == GridStatus.Covered)
        {
            if(this.content == -1)
                this.status = GridStatus.Clickedbomb;
            else
                this.status = GridStatus.Clicknull;
        }
        repaint();
    }
    public GridComponent(int x, int y) {//定义这个雷的位置
        this.setSize(gridSize, gridSize);
        this.row = x;
        this.col = y;
    }

    //鼠标点击这里应该需要增加一个判断条件，点击不是cover的格子的时候不能够算次数！
    @Override
    public void onMouseLeftClicked() {//鼠标左击
//        System.out.printf("Gird (%d,%d) is right-clicked.\n", row + 1, col + 1);
        //注意这里需要加1，因为正常数的话是没有第0行的
        if(this.gamePanel.getZdjs() == 0)//判断第一次是否触雷
        {
            if(this.content == -1)
            {
                this.status = GridStatus.Clickedbomb;
                repaint();
                System.out.println("第一次点击碰到雷，请重新开始");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
                this.mainFrame.get_new();
                return;
            }
        }
        if (this.status == GridStatus.Covered) {//没有被打开的状态
            click_left();
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP2())
            {
                if(MainFrame.getController().getRobot() != 0)
                {
                    if(MainFrame.getController().getRobot() == 2) {
                        for (int i = 0; i < xCount; i++)
                            for (int j = 0; j < yCount; j++)
                                if (gamePanel.getGrid(i, j).status == GridStatus.Covered) {
                                    if (check(i, j) == -1) {
                                        gamePanel.getGrid(i, j).click_right();
                                        return;
                                    }
                                }
                        for (int i = 0; i < xCount; i++)
                            for (int j = 0; j < yCount; j++)
                                if (gamePanel.getGrid(i, j).status == GridStatus.Covered) {
                                    if (check(i, j) == 1) {
                                        gamePanel.getGrid(i, j).click_left();
                                        return;
                                    }
                                }
                    }
                    for(int i = 0; i < xCount; i++)
                        for(int j = 0; j < yCount; j++)
                            if(gamePanel.getGrid(i,j).status == GridStatus.Covered)
                            {
                                gamePanel.getGrid(i,j).click_left();
                                return;
                            }
                }
            }
        }
        else//点击显示的点就不算了
            System.out.printf("Gird (%d,%d) has been clicked, please try again.\n", row + 1, col + 1);

        //TODO: 在左键点击一个格子的时候，还需要做什么？
        //这里需要增加第一次点击不能为雷
        //需要连续一片出来的效果

        //一下代码用于点击之后出现一片出来的效果
        //如果点击出来的是数字并且当前位置为0或者周围存在0，就把所有连续的0全部都翻出来

    }
    public void click_left(){
        //这里调整了一下位置
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row + 1, col + 1);
        if (content==-1) {//当前是雷
            Music.bomb();
            this.status = GridStatus.Clickedbomb;//把当前状态变为雷
            //下面是判断是哪一位玩家
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP1()){
                MainFrame.controller.getP1().costScore();
            }
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP2()){
                MainFrame.controller.getP2().costScore();
            }
        }
        if (content!=-1) {
            Music.shot();
            this.status = GridStatus.Clicknull;
            if(content == 0)
            {
                this.gamePanel.setUse(row,col);
                this.dfs(row,col);
                repaint();
            }
        }
        this.gamePanel.setClickPosition(this.row,this.col,0);
        repaint();
        MainFrame.controller.nextTurn();
    }
    //这个是搜索0的method
    //具体的含义是当前位置是now_x,now_y，并且保证当前的点为0
    public void dfs(int now_x,int now_y)
    {
        int x[] = {-1,-1,-1,0,0,1,1,1};
        int y[] = {1,0,-1,1,-1,1,0,-1};
        for(int i = 0; i < x.length; i++)
        {
            int fx = now_x +x[i], fy = now_y + y[i];//fx和fy表示下一个点的位置
            if(0 <= fx && fx < xCount && 0 <= fy && fy < yCount)//判断位置是否合法
            {
                GridComponent nextGridComponent = this.gamePanel.getGrid(fx, fy);
                if (nextGridComponent.content == 0 && gamePanel.getUse()[fx][fy] == 0) {
                        gamePanel.setUse(fx,fy);
                        if(nextGridComponent.status == GridStatus.Covered)
                             nextGridComponent.status = GridStatus.Clicknull;
                        nextGridComponent.dfs(fx, fy);
                }
                else
                {
                    if(nextGridComponent.status == GridStatus.Covered)
                        nextGridComponent.status = GridStatus.Clicknull;
                }
                nextGridComponent.repaint();
            }
        }
    }

    @Override
    public void onMouseRightClicked() {//鼠标右键
//        System.out.printf("Gird (%d,%d) is right-clicked.\n", row + 1, col + 1);
        //这里同理也需要加上1
        if (this.status == GridStatus.Covered) {//没有点过
            click_right();
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP2())
            {
                if(MainFrame.getController().getRobot() != 0)
                {
                    if(MainFrame.getController().getRobot() == 2) {
                        for (int i = 0; i < xCount; i++)
                            for (int j = 0; j < yCount; j++)
                                if (gamePanel.getGrid(i, j).status == GridStatus.Covered) {
                                    if (check(i, j) == -1) {
                                        gamePanel.getGrid(i, j).click_right();
                                        return;
                                    }
                                }
                        for (int i = 0; i < xCount; i++)
                            for (int j = 0; j < yCount; j++)
                                if (gamePanel.getGrid(i, j).status == GridStatus.Covered) {
                                    if (check(i, j) == 1) {
                                        gamePanel.getGrid(i, j).click_left();
                                        return;
                                    }
                                }
                    }
                    for(int i = 0; i < xCount; i++)
                        for(int j = 0; j < yCount; j++)
                            if(gamePanel.getGrid(i,j).status == GridStatus.Covered)
                            {
                                gamePanel.getGrid(i,j).click_left();
                                return;
                            }
                }
            }
        }
        else//点击显示的点就不算了
            System.out.printf("Gird (%d,%d) has been clicked, please try again.\n", row + 1, col + 1);

        //TODO: 在右键点击一个格子的时候，还需要做什么？
    }

    public int check(int x,int y)
    {
        int[] a = {-1,-1,-1,0,0,1,1,1};
        int[] b = {1,0,-1,1,-1,1,0,-1};
        for(int i = 0; i < a.length; i++)
        {
            int fx = x + a[i],fy = y + b[i];
            if(0 <= fx && fx < xCount && 0 <= fy && fy < yCount)
            {
                if(gamePanel.getGrid(fx,fy).status != GridStatus.Covered && gamePanel.getGrid(fx,fy).content != -1)
                {
                    int lei = 0,kong = 0,tot = 0;
                    for(int j = 0; j < a.length; j++)
                    {
                        int ffx = fx + a[j],ffy = fy + b[j];
                        if(0 <= ffx && ffx < xCount && 0 <= ffy && ffy < yCount)
                        {
                            if(gamePanel.getGrid(ffx,ffy).status == GridStatus.Covered)
                                kong++;
                            if(gamePanel.getGrid(ffx,ffy).status == GridStatus.Clickedbomb ||
                                    gamePanel.getGrid(ffx,ffy).status == GridStatus.Flagright)
                                lei++;
                            tot++;
                        }
                    }
                    int res = gamePanel.getGrid(fx,fy).content - lei;
                    if(res == 0)
                        return 1;
                    if(res == kong)
                        return -1;
                }
            }
        }
        return 0;
    }

    public void click_right(){
        //这里调整了一下位置
        System.out.printf("Gird (%d,%d) is left-clicked.\n", row + 1, col + 1);
        if (content==-1) {
            Music.shot();
            this.status = GridStatus.Flagright;
            if (MainFrame.controller.getOnTurnPlayer()==MainFrame.getController().getP1()){
                MainFrame.controller.getP1().addScore();
            }
            if (MainFrame.controller.getOnTurnPlayer()==MainFrame.getController().getP2()){
                MainFrame.controller.getP2().addScore();
            }
        }
        if (content!=-1) {
            Music.shot();
            this.status = GridStatus.Flagwrong;
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP1()){
                MainFrame.controller.getP1().addMistake();
            }
            if (MainFrame.getController().getOnTurnPlayer()==MainFrame.getController().getP2()){
                MainFrame.controller.getP2().addMistake();
            }
        }
        this.gamePanel.setClickPosition(this.row,this.col,1);
        repaint();
        MainFrame.controller.nextTurn();
    }
    @Override
    public void touch() {
        repaint();
    }

    public void draw(Graphics g) {

        if (this.status == GridStatus.Covered&&touch==true) {
            g.setColor(Color.blue);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
        if (this.status == GridStatus.Covered&&touch==false) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
        }

        if (this.status == GridStatus.Clickedbomb) {

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.BLACK);
            g.drawImage(image1,getWidth()/2-13,getHeight() / 2-13,22,22,this);

        }
        if (this.status == GridStatus.Clicknull) {
            if(content!=0) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);
            }
            else{
                g.setColor(Color.YELLOW);
                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
        if (this.status == GridStatus.Flagright) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            g.drawString("\uF04F", getWidth() / 2 - 5, getHeight() / 2 + 5);
        }
        if (this.status == GridStatus.Flagwrong) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            g.setColor(Color.RED);
            if(content!=0) {
                g.drawString(Integer.toString(content), getWidth() / 2 - 5, getHeight() / 2 + 5);
            }
        }


    }

    public void setContent(int content) {
        this.content = content;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        draw(g);
    }
}
