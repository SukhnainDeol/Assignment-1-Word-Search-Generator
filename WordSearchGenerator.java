// Programmer: Sukhnain Deol
// Class: CS 145
// Date: 02/14/2023
// Assignment: Lab 5: Tower Of Hanoi

// Purpose: Create a word search class that can generate
// print, and display a solved version of a wordsearch

import java.util.*;

class WordSearchGenerator
{
    // holds playable word search
    char[][] wordSearch;
    // holds solution word search
    char[][] solvedWordSearch;

    
    /*
    param: searchWords - String array of words to search
    pre  : searchWords - must contain only alphabetical letters
    post : generate 2d char array word search and solved word 
           search with words from searchWords
    */
    public void generate(String[] searchWords)
    {
        // keeps track of if any words were removed after 100 tries
        ArrayList<String> removedWords = new ArrayList<>();
        
        int longest = 1;
        // longest is used to determine the size of the word search
        // it uses the longer of the longest word or length of list
        if(findLongest(searchWords) < searchWords.length)
            {longest = (int)(searchWords.length* 1.5)+10;}
        else
            {longest = (int)(findLongest(searchWords) * 1.5)+10;}

        // initlize value of solved version of search
        solvedWordSearch = new char[longest][longest];
        // fills 2d array with 'X's
        fillChar(solvedWordSearch, 'X');
        

        // adds each word to the word search
        for(int i = 0; i < searchWords.length; i++)
        {
            int tries = 0; // tracks how many tries to place a word
            boolean wordPlaced = false; // check if word is placed
            String word = searchWords[i].toLowerCase(); // holds word(s)

            // loops until word is placed
            while(!wordPlaced)
            {
                // gets one random direction
                // dirX & dirY can be either -1, 0, 1
                int[] coord = getRandDirection();   

                // assigns random direction
                int dirX=coord[0];
                int dirY=coord[1];

                // random var to create a random 
                // coordinate for next word to be placed
                Random rand = new Random();            
                int randX = rand.nextInt(longest);
                int randY = rand.nextInt(longest);

                // check if there are enough open spaces in 
                // the direction or an overlapping letter
                if(isOpen(word, randX, randY, 
                solvedWordSearch, 'X', dirX, dirY))
                {
                    // places word
                    placeWord(word, randX, randY, 
                    dirX, dirY, solvedWordSearch);
                    wordPlaced = true; // end loop for this word
                }

                // ends loop if more than 100 tries
                if(tries > 100)
                {
                    // adds removed word to a list
                    removedWords.add(word);
                    continue; // goes to next word
                }
                tries++; // increments tries each loop
            }
        }

        // tells user if any words removed
        if(removedWords.size() > 0)
            {System.out.println("Words Removed: " + removedWords);}
        
        // creates actual word search
        wordSearch = new char[longest][longest];
        // copies over solved word search
        copyArray(solvedWordSearch, wordSearch);
        // replaces 'X' with random lowercase letter
        randomReplace(solvedWordSearch, 'X');
    } // end of generate method



    /*
    param: arr - String array with words to search
    pre  : None
    post: return length of longest word in arr
    */
    private int findLongest(String[] arr)
    {
        int longest = arr[0].length();
        // loops each element after 0
        for (int i = 1; i < arr.length; i++)
        {   // if element has longer length than current, reassign
            if(arr[i].length() > longest)
                {longest = arr[i].length();}
        }
        return longest;
    } // end of findLongest method



    /*
    param: arr - array 2d char array to fill
           fillValue - value to fill arr with
    pre  : None
    post: make arr's elements equal to fillValue
    */
    private void fillChar(char[][] arr, char fillValue)
    {
        // iterate over each array
        for(int i = 0; i < arr.length; i++)
        {
            // each element in each array is 'X'
            for(int j = 0; j < arr[i].length; j++)
                {arr[i][j] = fillValue;}
        }
    } // end of fillChar method



    /*
    param: None
    pre  : None
    post: Returns int arr with x/y values for 
          a direction in a 2d array 
    */
    private int[] getRandDirection()
    {
       // gets random number 0-7 to choose 
       // which direction a word is placed
       Random rand = new Random();
       int direction = rand.nextInt(8);

       // dir values are -1, 0, 1 depending 
       // on the direction, never 0,0 
       int dirX=0; 
       int dirY=0;

       /*
         visual representation of each direction
         7 0 1
         6 * 2
         5 4 3
       */

       switch(direction)
       {
           case 0:
               dirX = 0; 
               dirY = -1;
               break;
           case 1:
               dirX = 1; 
               dirY = -1;
               break;
           case 2:
               dirX = 1; 
               dirY = 0;
               break;
           case 3:
               dirX = 1; 
               dirY = 1;
               break;
           case 4: 
               dirX = 0; 
               dirY = 1;
               break;
           case 5:
               dirX = -1; 
               dirY = 1;
               break;
           case 6:
               dirX = -1; 
               dirY = 0;
               break;
           case 7:
               dirX = -1; 
               dirY = -1;
               break;
       } 
       // store and return direction
       int[] coord = {dirX, dirY};
       return coord;
    } // end of getRandDirection method



    /*
    param: 
          x,y coordinates in arr
          dirX/Y - direction to search
          arr - array to check
          value - char that counts as 'open'
          word - string to check if can fit
    pre  : dirX & Y be -1,0,1 but not both 0
    post: Returns whether enough space to fit a String
          in a 2d char array in a given direction
    */
    private boolean isOpen(String word, int x, int y,
     char[][] arr, char value, int dirX, int dirY)
    {
        // catches index error
        try 
        {
            // checks number of chars in given direction
            for(int i = 0; i < word.length(); i++)
            {
                // checks indexs in a direction
                int indexX = x+(i * dirX);
                int indexY= y+(i * dirY);

                // if not equal to value or same character 
                // as one that would be placed, return false
                if(arr[indexX][indexY] != value && 
                arr[indexX][indexY] != word.charAt(i))
                    {return false;}
            }
        }
        // if out of index, return false
        catch(IndexOutOfBoundsException e)
            {return false;}
        
        // returns true if open
        return true; 
    } // end of isOpen method



    /*
    param: 
          x,y coordinates in arr
          dirX/Y - direction to place
          arr - array to place in
          word - string to place
    pre  : dirX & Y be -1,0,1 but not both 0
    post: places String in a direction in a
          2d char array
    */
    private void placeWord(String word, int x,
     int y, int dirX, int dirY, char[][] arr)
    {
        // loops for each char in word
        for(int i = 0; i < word.length(); i++)
        {
            // gets each char of the word
            char letter = word.charAt(i);

            // each index for word's length in a direction
            int indexX = x+(i * dirX);
            int indexY= y+(i * dirY);

            // places letter at appropiate index
            arr[indexX][indexY] = letter;
        }     
    } // end of placeWord method


    
    /*
    param: 
          arr1 - array to copy
          arr2 - array to copy to
    pre  : arr1 and arr2 are same size
           arr2 has no null values
    post: copies elements from arr1 to arr2
    */
    private static void copyArray(char[][] arr1, char[][] arr2)
    {
        // for each array in arr1
        for(int i = 0; i < arr1.length; i++)
        {
            // for each element in each arr1
            for(int j = 0; j < arr1[i].length; j++)
            {
                // add element to arr2
                arr2[i][j] = arr1[i][j];
            }
        }
    } // end of copyArray method
    

    
    /*
    param: 
          arr - array to replace in
          replaceChar - char in arr to replace
    pre  : None
    post: replaces elements equal to replaceChar with 
          a random lowercase letter from the alphabet
    */
    private void randomReplace(char[][] arr, char replaceChar)
    {
        Random rand = new Random(); 

        // each array in 2d array
        for(int i = 0; i < wordSearch.length; i++)
        {
            // each element in each array
            for(int j = 0; j < wordSearch[i].length; j++)
            {
                // replace replaceChar with random letter from a-z
                if(wordSearch[i][j] == replaceChar)
                    {wordSearch[i][j] = (char)('a'+rand.nextInt(26));}
            }
        }
    } // end of randomReplace method



    // end of methods used in generate method



    /*
    param: arr - array to print
    pre  : No null values
    post: prints all elements in a 2d char array
    */
    private void print2DCharArray(char[][] arr)
    {
        // for each array in 2d array
        for(int i = 0; i < arr.length; i++)
        {
            // for each element in array of 2d array
            for(int j = 0; j < arr[i].length; j++)
            {
                // print each element and space by one
                System.out.print(arr[i][j] + " ");
            }
            System.out.println(); // next line after each array
        }
    } // end of print2DCharArray method



    /*
    param: None
    pre  : wordSearch has been generated
    post: prints all elements in wordSearch
    */
    public void print()
    {
        print2DCharArray(wordSearch);
    } // end of print method



    
    /*
    param: None
    pre  : wordSearch has been generated
    post: prints all elements in solvedWordSearch
    */
    public void showSolution()
    {
        print2DCharArray(solvedWordSearch);
    } // end of showSolution method



    
    /*
    param: None
    pre  : None
    post: returns wheter wordSearch has been generated
    */
    public boolean exists()
    {
        return wordSearch != null;
    } // end of exists method
} // end of WordSearchGenerator class