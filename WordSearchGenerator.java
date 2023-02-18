// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/14/2023
// Assignmnet: Lab 5: Tower Of Hanoi

// Purpose: Create a word search class that can generate
// print, and display a solved version of a wordsearch

// To DO:
    // make word search only taken in alphabet letters
        // no special chars
        // use special char as placeholder
        // Selecting “s” to show the solution should display the word search, but with all the random letters as “X”.
    // Note that you many need to adjust the size of your word search to accommodate more words or larger words.
    // parameters : 
    // pre :
    // posts : 
    // comments 
    // cleanup
    // <84 char
    // procedure
        // choose random coord
        // choose random direction
        // check if enough space & no other letters there
        // recursive back tracking 100 times
    // account for dynamic size
        // width = longest word or length of # of words
    // use a stack

import java.util.*;

class WordSearchGenerator 
{
    char[][] wordSearch = new char[10][10];
    char[][] solvedWordSearch = new char[10][10];

    // prob not needed
    public WordSearchGenerator()
    {

    } // end of WordSearchGenerator constructor method



    // This method does the bulk of the work. It will prompt the user for how many words and what
    // the words are and generate a word search based on those words
    public void generate(ArrayList<String> searchWords)
    {
        
    } // end of generate method


    // prints all the characters of a 2d char array
    // each array of spaced chars on its own line
    private void print2DCharArray(char[][] arr)
    {
        // iterate over each array
        for(char[] i : arr)
        {
            // iterate over each element in each array
            for(int j: i)
            {   
                //  each element in each array
                System.out.print(i[j] + " ");
            }
            System.out.println(); // next line after each array 
        }
    } // end of print2DCharArray method



    // This method prints the current word search that has been generated.
    public void print()
    {
        print2DCharArray(wordSearch);
    } // end of print method


    // use 2, 2 dimensional arrays
    // This method prints the solution to the word search that has been generated.
    public void showSolution()
    {
        print2DCharArray(solvedWordSearch);
    } // end of showSolution method
} // end of WordSearchGenerator class
