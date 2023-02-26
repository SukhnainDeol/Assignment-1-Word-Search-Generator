// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/14/2023
// Assignment: Lab 5: Tower Of Hanoi

// Purpose: Creates a menu for the user to interact 
// with and create a word search

import java.util.*;

public class WordSearchGeneratorMenu 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        // check if user hasn't quit
        boolean inUse = true;
        // check if user inputed positive int for word count
        boolean correctInt; 
        int wordCount = 0;

        WordSearchGenerator test = new WordSearchGenerator();
        String[] words; // words to add to word search

        // welcomes user
        printIntro();

        while(inUse)
        {
            // prints commands
            printCommands();

            // asks user for commands
            System.out.print("\n\nCommand: ");
            String command = in.next();
            in.nextLine(); // clear inputs
            // translates first letter of string into char
            char input = command.charAt(0);

            // checks if word search generated if 
            // trying to display the word search
            if(test.exists() == false && input != 'g' && input != 'q')
            {
                // if not generated yet ask them to
                System.out.println("Please Generate a Word Search First.");
                System.out.println("\n\nPress Enter To Continue");
                in.nextLine(); // waits for user to press enter
                continue; // restarts loop
            }

            // main menu switch/case
            switch(input)
            {
                case 'g': // generate
                    // loops to ask for # of words
                    correctInt = false;
                    while(!correctInt)
                    {
                        System.out.print("How many words do you ");
                        System.out.print("want in your word search?: ");

                        if(in.hasNextInt()) // if int
                        {
                            // take in
                            wordCount = in.nextInt();
                            if(wordCount > 0) // if over 0
                                {correctInt = true;} // end loop
                            else // else error
                            {
                                System.out.print("ERROR: Please Input a ");
                                System.out.println("Positive Whole Number");
                                in.nextLine(); // clears input for next loop
                            }
                        }
                        else // else error
                        {
                            System.out.print("ERROR: Please Input a ");
                            System.out.println("Positive Whole Number");
                            in.nextLine(); // clears input for next loop
                        }
                    }

                    // loop to ask for each word
                    words = new String[wordCount];
                    String nextWord; 
                    for(int i = 0; i < wordCount; i++)
                    {
                        // asks for word
                        System.out.print("Word " + (i+1) + ": ");
                        nextWord = in.next();

                        // checks if contains only alphabetic letters
                        if(nextWord.matches("[a-zA-Z]+"))
                        {
                            words[i] = nextWord;
                        }
                        // else decrements i to restart loop
                        else    
                        {
                            System.out.print("ERROR: Please only use");
                            System.out.println(" alphabetic letters");
                            i--;
                        }
                        in.nextLine(); // clears input for next loop
                    }
                    
                    // generates word search
                    test.generate(words);
                    // notifies users of success
                    System.out.println("\nWord Search Generated!");
                    break;
                case 'p': // print word search
                    test.print();
                    break;
                case 's': // print solution word search
                    test.showSolution();
                    break;
                case 'q': // end program
                    inUse = false; // stops while condition
                    continue; // continue to instantly end loop
                default: // defaults to error
                    System.out.println("ERROR: Incorrect Input");
                    break;
            } // end of switch/case

            // requires user to press enter to proceed to next loop
            System.out.println("\n\n\nPress Enter To Continue");
            in.nextLine(); // waits for user to press enter

        } // end of program loop
        in.close(); // closes scanner
    } // end of main method



    // parameters : None
    // pre : None
    // post : prints introduction to user
    public static void printIntro()
    {
        System.out.println("Welcome to my word search generator!");
        System.out.print("This program will allow you to");
        System.out.println(" generate you own word search puzzle");
    } // end of printIntro method



    // parameters : None
    // pre : None
    // post : prints commands that user has access to 
    public static void printCommands()
    {
        System.out.println("Please Select an Option: ");
        System.out.println("g - Generate a New Word Search");
        System.out.println("p - Print out your Word Search");
        System.out.println("s - Show the Solution to your Word Search");
        System.out.println("q - Quit the Program");
    } // end of printCommands method
} // end of WordSearchGeneratorMenu class
