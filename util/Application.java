package util;

import model.User;
import java.io.IOException;


/**
 * The {@code Application} class is the entry point for the system.
 * It handles user authentication, displays a welcome message, and allows logged-in users to navigate through their menus.
 *
 * <p>This application provides a text-based user interface with the following features:
 * <ul>
 *   <li>Authentication of users through the {@link Authentication} class</li>
 *   <li>Dynamic screen clearing for better user experience</li>
 *   <li>Menu-based interaction for users to access various features</li>
 * </ul>
 *
 * <p>The program runs continuously in a loop, allowing multiple users to authenticate and use the system sequentially.
 *
 * <h2>Usage</h2>
 * Compile and run the application with the following commands:
 * <pre>
 * chcp 65001
 * javac -d out -cp ".;mysql-connector-j-9.1.0.jar" util/Application.java
 * java -cp ".;out;mysql-connector-j-9.1.0.jar" util.Application
 * </pre>
 *
 * @author imrandurmus
 */
public class Application {

    /**
     * The main method, which serves as the entry point for the application.
     * It initializes the {@link Authentication} class and facilitates
     * user interaction through a menu-driven interface. This method loops
     * indefinitely until manually stopped.
     *
     * @param args command-line arguments (not used).
     */
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

    /**
     * Clears the terminal screen for a better user interface experience.
     * This method is compatible with both Windows and Unix-based systems.
     *
     * <p>For Windows, it uses the {@code cmd /c cls} command.
     * For Unix-based systems, it uses the {@code clear} command.
     *
     * <p>In case of an exception, an error message is printed to the console.
     */
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