package controller;

import components.GridComponent;
import minesweeper.GamePanel;
import entity.Player;
import minesweeper.MainFrame;
import minesweeper.MineLabel;
import minesweeper.ScoreBoard;

import javax.swing.*;


public class GameController {

    private Player p1;
    private Player p2;

    private Player onTurn;
    private int times;
    private int clicktimes=0;
    private GamePanel gamePanel;
    private ScoreBoard scoreBoard;
    private MineLabel mineLabel;
    boolean victory1 = false;
    boolean victory2 = false;
    boolean end = false;
    private int robot = 0;

    public void setRobot(int robot) {
        this.robot = robot;
    }

    public int getRobot() {
        return robot;
    }

    public void setTimes(String times) {
        this.times = Integer.parseInt(times);
    }

    public boolean isEnd() {
        return end;
    }

    public boolean isVictory1() {
        return victory1;
    }

    public boolean isVictory2() {
        return victory2;
    }

    public GameController(Player p1, Player p2) {
        this.init(p1, p2);
        this.onTurn = p1;
    }

    public GameController(Player p1, Player p2, Player p3) {
        this.init(p1, p2);
        this.onTurn = p1;
    }

    public GameController(Player p1, Player p2, Player p3, Player p4) {
        this.init(p1, p2);
        this.onTurn = p1;
    }

    /**
     * 初始化游戏。在开始游戏前，应先调用此方法，给予游戏必要的参数。
     *
     * @param p1 玩家1
     * @param p2 玩家2
     */
    public void init(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.onTurn = p1;

        //TODO: 在初始化游戏的时候，还需要做什么？
    }

    /**
     * 进行下一个回合时应调用本方法。
     * 在这里执行每个回合结束时需要进行的操作。
     * <p>
     * (目前这里没有每个玩家进行n回合的计数机制的，请自行修改完成哦~）
     */
    public void End() {
        if (mineLabel.getLeftmine() == 0) {
            if (p1.getScore() >= p2.getScore()) {
                if (p1.getScore() > p2.getScore()) {
                    victory1 = true;
                } else {
                    if (p1.getMistake() < p2.getMistake()) {
                        victory1 = true;
                    }
                    if (p1.getMistake() > p2.getMistake()) {
                        victory2 = true;
                    }
                }
            } else {
                victory2 = true;
            }

            if (victory1 == true) {
                JOptionPane.showMessageDialog(gamePanel, String.format("%s 赢得了胜利！", p1.getUserName()),
                        "游戏结果", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < gamePanel.getxCount(); i++) {
                    for (int j = 0; j < gamePanel.getyCount(); j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.openall();
                    }
                }
            }
            if (victory2 == true) {
                JOptionPane.showMessageDialog(gamePanel, String.format("%s 赢得了胜利！", p2.getUserName()),
                        "游戏结果", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < gamePanel.getxCount(); i++) {
                    for (int j = 0; j < gamePanel.getyCount(); j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.openall();
                    }
                }
            }
            if (victory1 == false && victory2 == false) {
                JOptionPane.showMessageDialog(gamePanel, "平局！",
                        "游戏结果", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < gamePanel.getxCount(); i++) {
                    for (int j = 0; j < gamePanel.getyCount(); j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.openall();
                    }
                }
            }

        }
        else{
            if (p1.getScore()- p2.getScore()>mineLabel.getLeftmine()){
                JOptionPane.showMessageDialog(gamePanel, String.format("%s 赢得了胜利！", p1.getUserName()),
                        "游戏结果", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < gamePanel.getxCount(); i++) {
                    for (int j = 0; j < gamePanel.getyCount(); j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.openall();
                    }
                }
            }
            if (p2.getScore()- p1.getScore()>mineLabel.getLeftmine()){
                JOptionPane.showMessageDialog(gamePanel, String.format("%s 赢得了胜利！", p2.getUserName()),
                        "游戏结果", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < gamePanel.getxCount(); i++) {
                    for (int j = 0; j < gamePanel.getyCount(); j++) {
                        GridComponent gridComponent = gamePanel.getGrid(i, j);
                        gridComponent.openall();
                    }
                }
            }

        }
    }

    public void nextTurn() {
        gamePanel.setZdjs(gamePanel.getZdjs() + 1);
        clicktimes++;
        if (onTurn == p1&&clicktimes==times) {
            onTurn = p2;
            clicktimes=0;
        }
        if (onTurn == p1&&clicktimes<times) {
            onTurn = p1;
        }
        if (onTurn == p2&&clicktimes==times) {
            onTurn = p1;
            clicktimes=0;
        }
        if (onTurn == p2&&clicktimes<times) {
            onTurn = p2;
        }
        System.out.println("Now it is " + onTurn.getUserName() + "'s turn.");
        scoreBoard.update();
        mineLabel.update();
        //TODO: 在每个回合结束的时候，还需要做什么 (例如...检查游戏是否结束？)
        End();

    }


    /**
     * 获取正在进行当前回合的玩家。
     *
     * @return 正在进行当前回合的玩家
     */
    public Player getOnTurnPlayer() {
        return onTurn;
    }


    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void setMineLabel(MineLabel mineLabel) {
        this.mineLabel = mineLabel;
    }

    public void readFileData(String fileName) {
        //todo: read date from file

    }

    public void writeDataToFile(String fileName) {
        //todo: write data into file
    }
}
