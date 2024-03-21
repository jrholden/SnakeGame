package SnakeGame.Board.Cell.Food;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;

import java.awt.*;

public class Food extends Cell {

    public Food(int x, int y, int size, int row, int col) {
        super(x, y, size, row, col);
        super.setType(CellType.FOOD);
    }
    public void drawCell(Graphics g){
        super.drawCell(g);
        g.setColor(Color.RED);
        g.fillOval(this.x + (this.size / 4), this.y + (this.size / 4), this.size / 2, this.size / 2);
    }
}
