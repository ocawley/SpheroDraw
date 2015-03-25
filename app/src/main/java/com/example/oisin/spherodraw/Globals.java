package com.example.oisin.spherodraw;

/**
 * Created by Oisin on 06/03/2015.
 */
public class Globals{
    private static Globals instance;

    // Global variable
//    private int data[][];

    //Capture the coordinates so we can send them to Sphero.
    private int[][] track = new int[2][1000];
    public int trackLength = 0;

    // Restrict the constructor from being instantiated
    private Globals(){}

    public void setData(int d[][]){
        this.track=d;
    }
    public int[][] getData(){
        return this.track;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}