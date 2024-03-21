package SnakeGame.Board.Cell;

import java.awt.*;

public class Cell {
    private CellType type;
    protected final int x;
    protected final int y;
    protected final int row;
    protected final int col;
    protected final int size;

    public Cell(){
        this.x = 0;
        this.y = 0;
        this.row = 0;
        this.col = 0;
        this.size = 0;
    }
    public Cell(int x, int y, int size, int row, int col){
        this.x = x;
        this.y = y;
        this.row = row;
        this.col = col;
        this.size = size;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    public void setType(CellType type){
        this.type = type;
    }
    public CellType getType(){
        return this.type;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getSize(){
        return this.size;
    }
    public void swapCells(Cell swapWith){
       CellType thisType = this.getType();
       this.setType(swapWith.getType());
       swapWith.setType(thisType);
    }
    public void drawCell(Graphics g){
        g.setColor(Color.WHITE);
        g.drawRect(this.x, this.y,this.size,this.size);

    }
    public String print(){
        return "Row: " + this.row + " "+ "Col: " + this.col + " " +type+"";
    }
}
