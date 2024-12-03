package util;

import model.User;

import java.util.Scanner;
import java.io.IOException;
import java.sql.*;

public class Application {

    public static void main(String[] args) {

        Authentication authentication = new Authentication();
        while (true) {
            clearScreen();
            final String BLUE = "\u001B[34m";
            final String RESET = "\u001B[0m";  
            System.out.println(BLUE + """                                                                                                       
                                                                                
                               ,(#########((####/.                              
                        *##(###((###########(#########(#                        
                    (#(((###/                      .##(#((((/                   
                ,((#(##,                                 (####(#                
              #(#(##                                         ###((#             
           *###((                                              .#(###           
         ,###(/                                                   ##(#(         
        (##((                                                       ##(#/       
      .#(#(                                                          *(((#      
     *##(#                                                             ##(#        
    .####                            (##,                               ####    
    ##(#                          #(##,#(#(#.                            (##(   
   (###                        ((#(,  /(#######(                         ####   
   #((#           #(       .###(.    ,##########(###                      ###/  
   ###(       #(###((## /####.    ##((###########((##(#(  ,##/*#((        #(##  
   #,     /#((,  #(#((#(##          #(#(((##,###((#/  .(#(     #*/###     ####  
      .###(     ###(##(            #/    /#####,   #(#(      ##((####(##*       
   (###,        ,#((                  ###(##(   ((((###(#  .  (#(######(#((#    
    ((#                           /##(######    ,#(###(####(##(.#(###########   
    *###                            .#(#(##(#/       ###(#######(#######(##(    
     ((##                       .#((##########((       #########(##########     
      ((#(                  .################(#*       (########(########(      
       ,((##                  /#(#####(####(#         ##########(########       
         ##(#,               ##(####(#(##           *#######(###(######         
           #(##/          (#####(####*             ((###########(####.          
             (((#(     #(######(##               ###############(##             
               *#############(.                ((##(###########(*               
                  *##(#(##(                  *(#(############                   
                      *                    ,#####(#####(#                       
                                          ########/  

            
            """+ RESET);
            System.out.println("Welcome to the system!");
            User loggedInUser = authentication.authenticate();
            clearScreen();
            System.out.println("Welcome " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName() + "!");
            boolean flag = true;
            while(flag){
                flag = loggedInUser.menu();
            }
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error while clearing screen");
        }
    }
}

//chcp 65001
//javac util/Application.java
//java -cp ".;mysql-connector-j-9.1.0.jar" util.Application

//javac -d out -cp ".;mysql-connector-j-9.1.0.jar" util/Application.java
//java -cp ".;out;mysql-connector-j-9.1.0.jar" util.Application