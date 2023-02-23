// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/14/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: Creates a menu for the user to interact 
// with and createa word search

// to do:
    // comments 
    // cleanup
    // <84 char

import java.util.*;

public class TestClass 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean inUse = true;

        WordSearchGenerator test = new WordSearchGenerator();
        String[] arr = {"Appl"};

        test.generate(arr);

        test.print();
        System.out.println();
        test.showSolution();
        

        // welcome to user
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate you own word search puzzle");

        while(inUse)
        {
            printIntro();

            System.out.print("\n\nCommand: ");
            String command = in.next();
            in.nextLine(); // clear inputs

            // translates first letter of string into char
            char input = command.charAt(0);
            switch(input)
            {
                case 'g':
                    // ask user for how many words
                    // ask for each word
                    // make word search
                    break;
                case 'p':
                    // if word search is generated
                        // print word search array
                    break;
                case 's':
                    // if word search is generated
                        // print solution word search array
                    break;
                case 'q': // end program
                    inUse = false;
                    continue;
                default:
                    System.out.println("ERROR: Incorrect Input");
                    break;
            } // end of switch/case

            System.out.println("\n\nPress Enter To Continue");
            in.nextLine(); // waits for user to press enter
        } // end of program loop
        in.close();
    } // end of main method



    // parameters : None
    // pre : None
    // post : prints commands that user has access to 
    public static void printIntro()
    {
        System.out.println("Please Select an Option: ");
        System.out.println("g - Generate a New Word Search");
        System.out.println("p - Print out your Word Search");
        System.out.println("s - Show the Solution to your Word Search");
        System.out.println("q - Quit the Program");
    } // end of printIntro method
} // end of TestClass class
