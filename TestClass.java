// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/14/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: 

import java.util.*;

public class TestClass 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean inUse = true;

        printIntro();

        while(inUse)
        {
            System.out.println("\n\nCommand: ");
            String command = in.nextChar();
            in.nextLine(); // clear inputs

            switch(command)
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
                case 'q':
                    inUse = false;
                    continue;
                default:
                    System.out.println("ERROR: Incorrect Input");
                    break;
            } // end of switch/case
        } // end of program loop
    } // end of main method



    public static void printIntro()
    {
        System.out.println("Welcome to my word search generator!");
        System.out.println("This program will allow you to generate you own word search puzzle");
        System.out.println("Please Select an Option: ");
        System.out.println("g - Generate a New Word Search");
        System.out.println("p - Print out your Word Search");
        System.out.println("s - Show the Solution to your Word Search");
        System.out.println("q - Quit the Program");
    } // end of printIntro method
} // end of TestClass class
