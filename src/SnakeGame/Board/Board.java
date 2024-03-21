package SnakeGame.Board;

import SnakeGame.Board.Cell.Cell;
import SnakeGame.Board.Cell.CellType;
import SnakeGame.Board.Cell.Food.Food;
import SnakeGame.Board.Cell.Snake.Snake;
import SnakeGame.Board.Cell.Snake.SnakeBody;
import SnakeGame.Controller.Controller;
import SnakeGame.IBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class Board extends JPanel implements IBoard {
    private Cell[][] grid;
    private final int width;
    private final int height;
    private Snake snake;
    private final int cellSize = 50;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols){
        this.height = (rows) * cellSize + 30;
        this.width = (cols) * cellSize;

        //how big is one cell??
        //Say 10 by 10?
        this.rows = rows;
        this.cols = cols;
        initBoard();
    }
    public int getHeight(){
        return height;
    }
    public int getWidth(){
        return width;
    }
    //create 2d array
    public void initBoard() {
        addKeyListener(new Controller(this));
        setBackground(Color.BLACK);

        setFocusable(true);

        this.grid = new Cell[rows][cols];
        //init cells
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++) {
                this.grid[row][col] = new Cell(cellSize * col,cellSize * row, this.cellSize, row, col);
                this.grid[row][col].setType(CellType.EMPTY);
            }
        }
        this.initSnake();
        this.placeFood();
    }

    private void placeFood(){
        //any empty space is a valid spot for food
        Random rand = new Random();
        int rowI = rand.nextInt(rows);
        int colI = rand.nextInt(cols);

        Cell targetCell = grid[rowI][colI];
        if (targetCell.getType() == CellType.EMPTY){
            grid[rowI][colI].setType(CellType.FOOD); //= new Food(targetCell.getX(), targetCell.getY(), targetCell.getSize(), rowI, colI);
            rebuildGrid();
        }else {
            this.placeFood();
        }
    }
    private void initSnake(){
        this.snake = new Snake(this.grid);
        rebuildGrid();
    }
    public void printGrid(){
        for (int row = 0; row < grid.length; row++){
            System.out.print("| ");
            for (int col = 0; col < grid[row].length; col++){
                System.out.print(grid[row][col].print() + " | ");
            }
            System.out.print("\n");
        }
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++) {
                this.grid[row][col].drawCell(g);
            }
        }

    }
    public void actionEvent(char event){
        switch (event) {
            case 'S' -> {
                System.out.println("Moving South");
                handleHit(this.snake.move('S'));
            }
            case 'N' -> {
                System.out.println("Moving North");
                handleHit(this.snake.move('N'));
            }
            case 'E' -> {
                System.out.println("Moving East");
                handleHit(this.snake.move('E'));
            }
            case 'W' -> {
                System.out.println("Moving West");
                handleHit(this.snake.move('W'));
            }
        }
        //printGrid();
        rebuildGrid();
        repaint();
        System.out.println(this.snake.printSnake());
    }
    private void handleHit(Cell hitCell){
        System.out.println(hitCell.getType());
        switch (hitCell.getType()){
            case FOOD -> {
                System.out.println("Hit FOOD");
                //remove hit food
                grid[hitCell.getRow()][hitCell.getCol()].setType(CellType.EMPTY);
                //add snake part
                this.snake.addSnakePart(grid[hitCell.getRow()][hitCell.getCol()]);
                //move food
                placeFood();
            }
            case SNAKE -> {
                System.out.println("You hit yourself!");
                System.exit(0);
            }
            case OUT -> {
                System.out.println("You are OUT");
                System.out.println("You are OUT");
            }
            case WIN -> {
                System.out.println("You Win!");
                System.exit(0);
            }
        }
    }
    private void rebuildGrid(){
        int emptyCount = 0;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[row].length; col++){
                if (grid[row][col].getType() == CellType.SNAKE){
                    grid[row][col] = new SnakeBody(grid[row][col].getX(),grid[row][col].getY(),grid[row][col].getSize(),grid[row][col].getRow(),grid[row][col].getCol());
                    if(grid[row][col].getRow() == this.snake.getHead().getRow() && grid[row][col].getCol() == this.snake.getHead().getCol()){
                        this.snake.updateHead(grid[row][col]);
                    }else{
                        this.snake.updateSnakePart(grid[row][col]);
                    }
                }else if (grid[row][col].getType() == CellType.FOOD){
                    grid[row][col] = new Food(grid[row][col].getX(),grid[row][col].getY(),grid[row][col].getSize(),grid[row][col].getRow(),grid[row][col].getCol());
                }else if (grid[row][col].getType() == CellType.EMPTY){
                    emptyCount++;
                    grid[row][col] = new Cell(grid[row][col].getX(),grid[row][col].getY(),grid[row][col].getSize(),grid[row][col].getRow(),grid[row][col].getCol());
                    grid[row][col].setType(CellType.EMPTY);
                }
            }
        }
        if (emptyCount == 0){
            //you win
            Cell winCell = new Cell();
            winCell.setType(CellType.WIN);
            handleHit(winCell);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);

    }

}
