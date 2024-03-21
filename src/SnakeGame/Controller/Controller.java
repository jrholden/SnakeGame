package SnakeGame.Controller;

import SnakeGame.Board.Board;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller extends KeyAdapter {
    Board board;
    public Controller(Board board){
    this.board = board;
    }
    public void keyTyped(KeyEvent e){
        if (e.getKeyChar() == 'w'){
            System.out.println("North");
            board.actionEvent('N');
        }else if (e.getKeyChar() == 's'){
            System.out.println("South");
            board.actionEvent('S');
        }else if (e.getKeyChar() == 'd'){
            System.out.println("East");
            board.actionEvent('E');
        }else if (e.getKeyChar() == 'a'){
            System.out.println("West");
            board.actionEvent('W');
        }
    }
}
