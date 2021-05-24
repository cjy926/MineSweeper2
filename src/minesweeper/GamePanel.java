package minesweeper;

import components.GridComponent;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel {
    private GridComponent[][] mineField;
    private int[][] chessboard;
//    private final Random random = new Random();
    private int xCount,yCount;//这里新加入的x,y
    private int mineCount;
    private int[][] use;
    private int zdjs;//总点击数
    private int[] clickX;//这个是依次点击的顺序
    private int[] clickY;
    private int[] clickSide;

    public int[] getClickX(){
        return clickX;
    }
    public int[] getClickY(){
        return clickY;
    }
    public int[] getClickSide(){
        return clickSide;
    }

    public int[][] getChessboard() {
        return chessboard;
    }

    public void setClickPosition(int x,int y,int side)
    {
        clickX[zdjs] = x;
        clickY[zdjs] = y;
        clickSide[zdjs] = side;
    }

    public int getMineCount() { return mineCount; }
    public void setMineCount(int mineCount) { this.mineCount = mineCount; }

    public int getxCount() { return xCount; }//同时加入了get函数，set在初始化的时候就有了
    public int getyCount() { return yCount; }


    public int[][] getUse() { return use; }
    public void setUse(int x,int y) { use[x][y] = 1; }


    public int getZdjs() {
        return zdjs;
    }

    public void setZdjs(int zdjs) {
        this.zdjs = zdjs;
    }
    /**
     * 初始化一个具有指定行列数格子、并埋放了指定雷数的雷区。
     *
     * @param xCount    count of grid in column
     * @param yCount    count of grid in row
     * @param mineCount mine count
     */
    public GamePanel(int xCount, int yCount, int mineCount,MainFrame mainFrame) {//初始化，定义
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;
        this.zdjs = 0;
        this.clickSide = new int[xCount * yCount + 10];
        this.clickX = new int[xCount * yCount + 10];
        this.clickY = new int[xCount * yCount + 10];
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        this.use = new int[xCount][yCount];
        initialGame(xCount, yCount, mineCount,mainFrame);//生成雷区
        repaint();
    }
    //这里重载了一个函数，是带有具体的chessboard的构造函数！
    public GamePanel(int xCount, int yCount, int mineCount,MainFrame mainFrame, int[][] chessboard) {//初始化，定义
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.xCount = xCount;
        this.yCount = yCount;
        this.mineCount = mineCount;
        this.zdjs = 0;
        this.clickSide = new int[xCount * yCount + 10];
        this.clickX = new int[xCount * yCount + 10];
        this.clickY = new int[xCount * yCount + 10];
        this.setSize(GridComponent.gridSize * yCount, GridComponent.gridSize * xCount);
        this.use = new int[xCount][yCount];
        initialGame(xCount, yCount, mineCount,mainFrame,chessboard);//生成雷区
        repaint();
    }
    //这个是带有chessboard的构造棋盘
    public void initialGame(int xCount, int yCount, int mineCount, MainFrame mainFrame,int[][] doc_chessboard) {//初始化游戏
        mineField = new GridComponent[xCount][yCount];
        chessboard = doc_chessboard;
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);//每一个位置导入
                gridComponent.setContent(doc_chessboard[i][j]);
                gridComponent.setXY(xCount,yCount);//这里让每个点都知道整个棋盘的大小
                gridComponent.setGamePanel(this);//让每个点都知道当前的棋盘
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                gridComponent.setMainFrame(mainFrame);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
                gridComponent.repaint();
            }
        }
    }
    public boolean checkChessBoard()
    {
        int x[] = {-1,-1,-1,0,0,1,1,1},y[] = {1,0,-1,1,-1,1,0,-1};
        for(int i = 1; i + 1 < xCount; i++)//判断九个格子里面是否全是雷
            for(int j = 1; j + 1 < yCount; j++)
            {
                if(chessboard[i][j] != -1)
                    continue;
                int tot = 1;
                for(int k = 0; k < x.length; k++)
                {
                    int fx = i + x[k],fy = j + y[k];
                    if(chessboard[fx][fy] == -1)
                        ++tot;
                }
                if(tot == 9)
                    return false;
            }
        return true;
    }

    public void initialGame(int xCount, int yCount, int mineCount, MainFrame mainFrame) {//初始化游戏
        mineField = new GridComponent[xCount][yCount];

        generateChessBoard(xCount, yCount, mineCount);//生成雷区
        while(checkChessBoard() == false)//判断是否九个格子都是雷
            generateChessBoard(xCount, yCount, mineCount);

        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j < yCount; j++) {
                GridComponent gridComponent = new GridComponent(i, j);//每一个位置导入
                gridComponent.setContent(chessboard[i][j]);
                gridComponent.setXY(xCount,yCount);//这里让每个点都知道整个棋盘的大小
                gridComponent.setGamePanel(this);//让每个点都知道当前的棋盘
                gridComponent.setLocation(j * GridComponent.gridSize, i * GridComponent.gridSize);
                gridComponent.setMainFrame(mainFrame);
                mineField[i][j] = gridComponent;
                this.add(mineField[i][j]);
                gridComponent.repaint();
            }
        }
    }


    public void generateChessBoard(int xCount, int yCount, int mineCount) {//生成雷区
        //todo: generate chessboard by your own algorithm
        chessboard = new int[xCount][yCount];//-1代表是雷
        Random rand = new Random();
        for(int i=0;i<mineCount;){
            int a=rand.nextInt(xCount);
            int b=rand.nextInt(yCount);
            if (chessboard[a][b]!=-1){
                chessboard[a][b]=-1;
                i++;
            }
        }

        int x[] = {-1,-1,-1,0,0,1,1,1};//这里更改了一下写法，这样写简单一点
        int y[] = {1,0,-1,1,-1,1,0,-1};
        for (int i = 0; i < xCount; i++) {
            for (int j = 0; j <yCount; j++) {
                if(chessboard[i][j] == -1) continue;
                int tempCount = 0;
                for(int k = 0; k < x.length; k++)
                {
                    int fx = i + x[k],fy = j + y[k];//fx和fy表示下一个点的位置
                    if(0 <= fx && fx < xCount && 0 <= fy && fy < yCount && chessboard[fx][fy] == -1)
                        ++tempCount;
                }
                chessboard[i][j] = tempCount;
            }
        }
    }





    /**
     * 获取一个指定坐标的格子。
     * 注意请不要给一个棋盘之外的坐标哦~
     *
     * @param x 第x列
     * @param y 第y行
     * @return 该坐标的格子
     */
    public GridComponent getGrid(int x, int y) {
        try {
            return mineField[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
