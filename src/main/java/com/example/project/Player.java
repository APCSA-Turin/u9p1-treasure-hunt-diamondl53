package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite  {//initializes 3 variables
    private int treasureCount;
    private int numLives;
    private boolean win;
    
    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
        win = false;
    }
    
    public int getTreasureCount(){return treasureCount;}//helper methods to return and set count for testing//
    public void setTreasureCount(int count){
        treasureCount = count;
    }
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}
    
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.toLowerCase().equals("w")){
            setY(getY() + 1);//if w, then the y coordinate increases by 1//
        }
        if(direction.toLowerCase().equals("a")){
            setX(getX() - 1);//if a, then the x coordinate decreases by 1//
        }
        if(direction.toLowerCase().equals("s")){
            setY(getY() - 1);//if s, then the y coordinate decreases by 1
        }
        if(direction.toLowerCase().equals("d")){
            setX(getX() + 1);//if d, then the x coordinate increases by 1
        }
    }

    @Override
    public String getRowCol(int size){
        return "Player:" + super.getRowCol(size);//overrides the getRowCol in the super class to print the player row and column values
    }

    @Override
    public String getCoords(){
        return "Player:" + super.getCoords();//overrides the getCoords in the super class to print the player coorindates
    }
    
    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if(isValid(size,direction)){//first check if the interaction is a valid movement
            if(obj instanceof Enemy){
                numLives--;//if it is an enemy, decrease the numLives by 1
                
            }else if(obj instanceof Treasure){//check if the object is a treasure
                if(obj instanceof Trophy){//if it is a trophy, the player needs to have already collected all the treasures to win//
                    if(treasureCount==numTreasures){
                        
                        win = true;//if all treasures collected, set win to true//
                    }
                }else{
                    treasureCount++;//if it is not a trophy but is a treasure, simply increase the treasureCount by 1
                    
                }
            }           
        }else{
            System.out.println("cannot move this way");//if the movement is not valid then print that this interaction is not valid
        }
    }
    
    public boolean isValid(int size, String direction){ //check grid boundaries
        if(direction.toLowerCase().equals("w")){//if w, then check if the y coordinate is within the size of the grid and return as necessary
            if(size-1>getY()){
                return true;
            }else{
                return false;
            }
        }else if(direction.toLowerCase().equals("a")){//if a, then check if the x value is greater than 0 for it to be within the grid and return as necessary
            if(getX()>0){
                return true;
            }else{
                return false;
            }
        }else if(direction.toLowerCase().equals("s")){//if s, check if the y coordinate is greater than 0 otherwise return false as it is outside the grid//
            if(getY()>0){
                return true;
            }else{
                return false;
            }
        }else if(direction.toLowerCase().equals("d")){//if d, then check if the x coordinate is within the size of the grid and return as necessary
            if(getX() < size-1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}



