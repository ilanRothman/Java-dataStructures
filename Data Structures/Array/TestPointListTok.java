package com.company;
import java.io.*;
import java.awt.Point;

public class TestPointListTok {
    private InputStreamReader reader;
    private StreamTokenizer tokens;
    private ArrayPointList points;
    
    TestPointListTok(){
        reader = new InputStreamReader(System.in);
        tokens = new StreamTokenizer(reader);
        points = new ArrayPointList();

    }
    public boolean nextToken() throws IOException {

        if(this.tokens.nextToken() != StreamTokenizer.TT_WORD){
            System.out.println("wrong input");
            return true;
        }
        String command = tokens.sval;
        if(command.equals("add")){
          if(!add()){
              System.out.println("wrong input");
          }
        }
        else if(command.equals("curr")){
            System.out.println("("+(int)points.getCursor().getX()+","+(int)points.getCursor().getY()+")");
        }
        else if(command.equals("next")){
            System.out.println(points.goToNext());
        }
        else if(command.equals("prev")){
            points.goToPrior();
        }
        else if(command.equals("start")){
            System.out.println((points.goToBeginning()));
        }
        else if(command.equals("end")){
            System.out.println((points.goToEnd()));

        }
        else if(command.equals("empty")){
            System.out.println((points.isEmpty()));
        }
        else if(command.equals("full")){
            System.out.println(points.isFull());
        }
        else if(command.equals("clear")){
            points.clear();
        }
        else if(command.equals("quit")){
            return false;
        }
        else{
            System.out.println("wrong input try again");
        }
        return true;
    }
    private boolean add() throws IOException {
        int x,y;
        if(tokens.nextToken() != StreamTokenizer.TT_NUMBER){
            return false;
        }
        x = (int)tokens.nval;
        if(tokens.nextToken() != StreamTokenizer.TT_NUMBER){
            return false;
        }
        y = (int)tokens.nval;
        points.append(new Point(x,y));
        return true;
    }

}
