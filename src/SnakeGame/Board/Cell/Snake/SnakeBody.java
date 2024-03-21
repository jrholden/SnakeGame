package SnakeGame.Board.Cell.Snake;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;

import java.awt.*;

public class SnakeBody extends Cell {
    private final int offset = 1;
    public SnakeBody(int x, int y, int size, int row, int col) {
        super(x, y, size, row, col);
        super.setType(CellType.SNAKE);
    }
    public void drawCell(Graphics g){
        super.drawCell(g);
        g.setColor(Color.GREEN);
        g.fillRect(this.x+offset , this.y , this.size-offset*2 , this.size);
    }
}
