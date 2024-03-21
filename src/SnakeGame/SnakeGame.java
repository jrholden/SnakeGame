package SnakeGame;

import SnakeGame.Board.Board;

import javax.swing.*;
import java.awt.*;

public class SnakeGame {
    private Board boardPanel;
    private final JFrame snakeFrame;

    public SnakeGame(){
        snakeFrame = new JFrame("Snake Game");
        snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snakeFrame.setBackground(Color.BLACK);
    }
    public void startGameLoop(){
        int rows = 3;
        int cols = 3;
        this.boardPanel = new Board(rows, cols);
        snakeFrame.setSize(boardPanel.getWidth(), boardPanel.getHeight());
        snakeFrame.add(this.boardPanel);
        snakeFrame.setVisible(true);
    }
    public void play(){
        this.print();
    }
    protected void print(){
        this.boardPanel.printGrid();
    }
}
