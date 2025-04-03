package com.example.project;
//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;//creates 2 variables one is the grid and the other is the size
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
    this.size = size;//initializes the size of the grid
    grid = new Sprite[size][size];
    for(int i = 0; i < grid.length; i++){
        for(int j = 0; j < grid[0].length; j++){//nested for loop to iterate through the grid and set all the values to a new Dot object
            grid[i][j] = new Dot(j, size-1-i);
        }
    }
    }
    
    public Sprite[][] getGrid(){return grid;}//returns the grid
    
    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size-1-s.getY()][s.getX()] = s;//converts the x and y coordinates to the proper row and column values of the grid and places the sprite at that position
    }

     public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int oldx = -1;//initializes both to -1 just to prevent errors//
        int oldy = -1; 
            if(direction.equals("d")){//if direction is right, then the old x coordinate is 1 to the left
                oldx = s.getX()-1;
                oldy = s.getY();
            }else if(direction.equals("w")){//if direction is up, then old y coordinate is 1 down//
                oldx = s.getX();
                oldy = s.getY()-1;
            }else if(direction.equals("a")){//if direction is left, then old x coordinate is 1 right//
                oldy = s.getY();
                oldx = s.getX()+1;

            }else if(direction.equals("s")){//if direction is down, then old y coordinate is 1 up//
                oldx = s.getX();
                oldy = s.getY()+1;
            }
            grid[size-1-oldy][oldx] = new Dot(oldx,oldy);//replaces the old posititon back to a Dot object by converting x and y coordinates to the proper row and column values
            placeSprite(s);//call this method to place the sprite in the new spot
    }
    public void display() { //print out the current grid to the screen 
        System.out.println();//prints a blank line first just for formatting
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){//iterates through the entire grid using a nested for loop
                if(grid[i][j] instanceof Enemy){
                    System.out.print("🦖");//emoji for type enemy
                }else if(grid[i][j] instanceof Player){
                    System.out.print("🦍");//emoji for type player
                }else if(grid[i][j] instanceof Dot){
                    System.out.print("⏹️ ");//emoji for type dot
                }else if(grid[i][j] instanceof Treasure &&  !(grid[i][j] instanceof Trophy)){
                    System.out.print("🪙 ");//emoji for type treasure that is not a trophy
                }else if(grid[i][j] instanceof Trophy){
                    System.out.print("🏆");//emoji for type treasure that is a trophy
                }
            }
            System.out.println();//prints a new line between every row
        }
        System.out.println();//prints a blank line after just for formatting purposes
    }
    
    public void gameover(){ //use this method to display a loss
    System.out.println("You lose");
    }

    public void win(){ //use this method to display a win 
    System.out.println("You win");
    }


public static void main(String[] args) {//I had a main method when testing and debugging to find out where my code was incorrect
    Player player = new Player(0,0);
    Grid grid = new Grid(10);
    Enemy enemy = new Enemy(4, 5);
    
    
    
    for(int i=0;i<5;i++){
        player.move("w"); //[9-5][0]
        grid.placeSprite(player, "w");
    }

    for(int i=0;i<4;i++){
        player.move("d");//[5][4]
        grid.placeSprite(player, "d");
    }

     // Interact with the enemy
    player.interact(10, "d", 1, enemy);
    System.out.println(player.getRowCol(10));
    player.move("d");//[4][5]
    System.out.println(player.getRowCol(10));
    grid.placeSprite(player, "d");
    System.out.println(player.getLives());
    System.out.println(player.getRowCol(10));
    grid.display();

}
}
