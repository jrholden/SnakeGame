package SnakeGame.Board.Cell.Snake;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;

import java.awt.*;

public class SnakeTail extends Cell {
    private final int offset = 5;
    public SnakeTail(int x, int y, int size, int row, int col) {
        super(x, y, size, row, col);
        super.setType(CellType.SNAKE);
    }
    public void drawCell(Graphics g){
        super.drawCell(g);
        g.setColor(Color.GREEN);
        g.fillRect(this.x+offset , this.y+offset , this.size-offset*2 , this.size);
    }
}
