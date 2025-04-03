 package com.example.project;
 import java.util.Scanner;//alows for user input//

public class Game{//initialize the grid, size, and all types of sprites
     private Grid grid;
     private Player player;
     private Enemy[] enemies;
     private Treasure[] treasures;
     private Trophy trophy;
     private int size; 

     public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;//initializes the size of the grid and calls the initialize and play method
        initialize();
        play();
     }

     public static void clearScreen() { //do not modify
         try {
             final String os = System.getProperty("os.name").toLowerCase();
             if (os.contains("win")) {
                 // Windows
                 new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
             } else {
                 // Unix-based (Linux, macOS)
                 System.out.print("\033[H\033[2J");
                 System.out.flush();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
     }
     
     public void play() {
        Scanner in = new Scanner(System.in);//allows for user input
        boolean play = true;
        while(play && player.getLives() > 0) {//plays if the player has enough lives and if the player hasn't lost/won
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen();
            grid.display();
            System.out.println("Number of Lives: " + player.getLives());//prints the important information such as lives, coordinates, treasure information, and instructions
            System.out.println("Player coordinate: " + player.getCoords());
            System.out.println("Number of treasures needed: " + treasures.length);
            System.out.println("Number of treasures acquired: " + player.getTreasureCount());
            System.out.println("Enter direction (w/a/s/d): ");
            String direction = in.nextLine().toLowerCase();
            if(direction.equals("w") || direction.equals("a") || direction.equals("s") || direction.equals("d")) {
                if(player.isValid(size, direction)) {//first check if the movement is valid afetr the direction is inputted by user
                    int newX = player.getX();
                    int newY = player.getY();
                    if(direction.equals("w")) {newY++;}//increases the coordiantes accordingly to the direction the user wants//
                    if(direction.equals("a")) {newX--;}
                    if(direction.equals("s")) {newY--;}
                    if(direction.equals("d")) {newX++;}
                    Sprite sprite = grid.getGrid()[size-1-newY][newX];//creates the object sprite at that specific row and column in the grid after converting the coordiantes to 2-d array form
                    player.interact(size, direction, treasures.length, sprite);//call the interact method to "interact" the objects
                    if(sprite instanceof Treasure && !(sprite instanceof Trophy)) {
                        player.move(direction);//move and place sprite accordingly
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Dot){
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
                    if(sprite instanceof Enemy){
                        player.move(direction);
                        grid.placeSprite(player, direction);
                    }
                    
                    if(sprite instanceof Trophy) {//for trophy, it is a little different. First check if player.getWin() is true to see if enough treasures were collected. If so, call the win method and stop user input//
                        
                        if(player.getWin()){
                            grid.win();
                            play = false;
                        }else{//if not enough treasures collected, simply print out you need more treasures and do not move the player to that spot
                            System.out.println("Collect more treasures!");
                        }
                    }
                }
            }
            
        }
        if(player.getLives() <= 0) {//finally, check if the number of lives the player has is sufficient, otherwise the player loses and userinput closes//
            grid.gameover();
        }
        in.close();
    }

     public void initialize(){
        Scanner in = new Scanner(System.in);//initialize by allowing for user input//
        System.out.println("Select difficulty mode: 1 for easy, 2 for medium, and 3 for hard");//user can select the difficulty mode of the game//
        int difficulty = in.nextInt();
        in.nextLine();

         
        if(difficulty==1){
            size = 5;//size if 5, 2 enemies, and 2 treasures in easy mode//
            enemies = new Enemy[2];
            treasures = new Treasure[2];
         }else if(difficulty==2){//size is 10, 4 enemies, and 2 treasures in medium mode//
            size = 10;
            enemies = new Enemy[4];
            treasures = new Treasure[2];
         }else if(difficulty ==3){//size is 15, 6 enemies, and 3 treasures in hard mode//
            size = 15;
            enemies = new Enemy[6];
            treasures =  new Treasure[3];
         } 
         
         grid = new Grid(size);//initialize grid
         trophy = new Trophy(size - 1, size - 1);//trophy always goes in the top right corner of the grid
         player = new Player(0,0);//player always goes in the bottom left corner of the grid
         grid.placeSprite(player);
         grid.placeSprite(trophy);

         for(Enemy enemy : enemies){
            int randX = 0; int randY = 0;
            while(!(grid.getGrid()[size - 1 - randY][randX] instanceof Dot)){//checks if the space is a sprite other than a dot to avoid repetition
                randX = (int) (Math.random() * size);//randomly assigns x and y coordinates within the grid boundaries
                randY = (int) (Math.random() * size);
            }

            Enemy newEnemy = new Enemy(randX, randY);//creates a new enemy object
            grid.placeSprite(newEnemy);//places the enemy there
         }
         for(Treasure treasure : treasures){
            int randX = 0; int randY = 0;
            while(!(grid.getGrid()[size - 1 - randY][randX] instanceof Dot)){//checks if the space is a sprite other than a dot to avoid repetition
                randX = (int) (Math.random() * size);//randomly assigns x and y coordinates within the grid boundaries
                randY = (int) (Math.random() * size);
            }

            Treasure newTreasure = new Treasure(randX, randY);//creates a new treasure object
            grid.placeSprite(newTreasure);//places the treasure there
            //player.setTreasureCount(treasures.length);
         }
     }
     public static void main(String[] args) {
        
        Game a = new Game(15);
        
     }
 }
