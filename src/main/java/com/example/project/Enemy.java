package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite  { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x,y);//constructor that initializes the x and y coordinates
    }
    
    //the methods below should override the super class 
    @Override
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();//overrides the super class to return "Enemy: " before printing out the coordinates
    }
    @Override
    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);//overrides the super class to return "Enemy: " before printing out the row and column order//
    }
}