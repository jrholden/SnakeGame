package SnakeGame.Board.Cell.Snake;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;

import java.util.ArrayList;

//snake is array of cells
//heads is always 0, tail always last
public class Snake {
    private final ArrayList<Cell> snakeParts;
    private Cell head;
    private final Cell[][] grid;

    public Snake(Cell[][] grid) {
        this.grid = grid;
        snakeParts = new ArrayList<>();
        initSnake();
    }
    private void initSnake(){
        grid[getCenterY()][getCenterX()].setType(CellType.SNAKE);
        this.head = grid[getCenterY()][getCenterX()];

        grid[getCenterY()+1][getCenterX()].setType(CellType.SNAKE);
        snakeParts.add(grid[getCenterY()+1][getCenterX()]);
    }
    public void addSnakePart(Cell target){
        target.setType(CellType.SNAKE);
        snakeParts.add(target);
    }
    public Cell move(char direction){
        Cell hitCell = new Cell();
        hitCell.setType(CellType.OUT);
        Cell moveTo = hitCell;

        if (direction == 'S'){
            if(this.head.getRow()+1 < grid.length) {
                moveTo = grid[this.head.getRow() + 1][this.head.getCol()];
            }else {
                System.out.println("Snake reached end");
            }
        }else if (direction == 'N'){
            if(this.head.getRow()-1 >= 0) {
                moveTo = grid[this.head.getRow() - 1][this.head.getCol()];
            }else {
                System.out.println("Snake reached end");
            }
        }else if (direction == 'E'){
            if(this.head.getCol()+1 < grid[this.head.getRow()].length) {
                moveTo = grid[this.head.getRow()][this.head.getCol() + 1];
            }else {
                System.out.println("Snake reached end");
            }
        }else if (direction == 'W'){
            if(this.head.getCol()-1 >= 0) {
                moveTo = grid[this.head.getRow()][this.head.getCol() - 1];
            }else {
                System.out.println("Snake reached end");
            }
        }

        this.head.swapCells(moveTo);
        //hit cell will end up being the last one
        hitCell = this.head;
        this.head = moveTo;
        return snakeTraverse(hitCell);
    }
    public void updateSnakePart(Cell cell){
        for (int i = 0; i < snakeParts.size(); i++){
            if (cell.getRow() == snakeParts.get(i).getRow() && cell.getCol() == snakeParts.get(i).getCol()){
                System.out.println("Snake part match!");
                snakeParts.set(i, cell);
            }
        }
    }
    public Cell snakeTraverse(Cell hitCell){
        if( snakeParts.isEmpty()) {
            System.out.println(printSnake());
        }
        else{
            for (int i = 0; i < snakeParts.size(); i++){
                snakeParts.get(i).swapCells(hitCell);
                Cell temp = snakeParts.get(i);
                snakeParts.set(i, hitCell);
                hitCell = temp;
            }
        }
        return hitCell;
    }
    public void updateHead(Cell head){
        this.head = head;
    }
    public String printSnake(){
        StringBuilder returnString = new StringBuilder("\n");
        returnString.append(head.print());
        for (int i = 0; i < snakeParts.size(); i++){
            returnString.append("\n").append(snakeParts.get(i).print());
        }
        return returnString.toString();
    }
    public Cell getHead(){
        return this.head;
    }
    private int getCenterX(){
        return this.grid[0].length / 2;
    }
    private int getCenterY(){
        return this.grid.length / 2;
    }
}
