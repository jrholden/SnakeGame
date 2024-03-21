package SnakeGame.Board.Cell.Snake;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;

import java.awt.*;

public class SnakeHead extends SnakeBody {
    public SnakeHead(int x, int y, int size, int row, int col) {
        super(x, y, size, row, col);
        super.setType(CellType.SNAKE);
    }
    public void drawCell(Graphics g){
        super.drawCell(g);
    }
}
