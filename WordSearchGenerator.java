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
   // organize method
   // more methods


import java.util.*;


class WordSearchGenerator
{
    char[][] wordSearch;
    char[][] solvedWordSearch;


    // This method does the bulk of the work. It will prompt the user for how many words and what
    // the words are and generate a word search based on those words
    public void generate(String[] searchWords)
    {
        int longest = findLongest(searchWords) + 10;

        // make it at least enough space for all words?
        solvedWordSearch = new char[longest][longest];
        fillChar(solvedWordSearch, 'X');
        

        // adds each word to the word search
        for(int i = 0; i < searchWords.length; i++)
        {
            int tries = 0; // tracks how many tries to place a word
            boolean wordPlaced = false; // check if word is placed
            String word = searchWords[i].toLowerCase(); // takes next word
            // loops until word is placed
            while(!wordPlaced)
            {
                // gets random values for dir x and y
                // either -1, 0, 1
                int[] coord = getRandDirection();

                // assign direction
                int dirX=coord[0]; 
                int dirY=coord[1];

                // random var to create a random coordinate
                // for next word to place
                Random rand = new Random();
                int randX = rand.nextInt(longest);
                int randY = rand.nextInt(longest);


                // places word if there are enough open spaces in the direction
                if(isOpen(word.length(), randX, randY, solvedWordSearch, 'X', dirX, dirY))
                {
                    placeWord(word, randX, randY, dirX, dirY, solvedWordSearch);
                    wordPlaced = true; // end loop for this word
                }

                // ends loop after 100 tries
                if(tries > 100)
                    {continue;}
                tries++;
            }
        }

        wordSearch = new char[solvedWordSearch.length][solvedWordSearch[0].length];
        copyArray(solvedWordSearch, wordSearch);
        randomReplace(solvedWordSearch, 'X');
    } // end of generate method



    private static void copyArray(char[][] arr, char[][] arr2)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                arr2[i][j] = arr[i][j];
            }
        }
    }



    private int[] getRandDirection()
    {
       // gets random number 0-7 to choose which direction a word is placed
       Random rand = new Random();
       int direction = rand.nextInt(8);

       // dir values are -1, 0, 1 depending on the direction
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
       int[] coord = {dirX, dirY};
       return coord;
    }

    private void placeWord(String word, int x, int y, int dirX, int dirY, char[][] arr)
    {
        System.out.println(word);
        System.out.println(x);
        System.out.println(y);
        System.out.println(dirX);
        System.out.println(dirY + "a");

        // loops for each char in word
        for(int i = 0; i < word.length(); i++)
        {
            // gets each char of the word
            char letter = word.charAt(i);
            
            System.out.println(i);
            
            // places letter at appropiate index
            // i * dir increments it in a direction
            arr[x+i*(dirX)][y+i*(dirY)] = letter;
        }
            
    }



    // find longest word in string arraylist
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
    }


    
    // fill 2d char arraylist with a given value
    private void fillChar(char[][] arr, char fillValue)
    {
        // iterate over each array
        for(int i = 0; i < arr.length; i++)
        {
            // each element in each array is 'X'
            for(int j = 0; j < arr[i].length; j++)
                {arr[i][j] = fillValue;}
        }
    }


    
    // replace each instance of a given value in a 2d char array with a random alphabetical letter
    public void randomReplace(char[][] arr, char replaceChar)
    {
        Random rand = new Random();

        // create a method to ieterateo over 2d array
        for(int i = 0; i < wordSearch.length; i++)
        {
            // each element in each array is 'X'
            for(int j = 0; j < wordSearch[i].length; j++)
            {
                // replace with random letter a-z
                if(wordSearch[i][j] == replaceChar)
                {
                    wordSearch[i][j] = (char)('a'+rand.nextInt(26));
                }
            }
        }
    }



    // check if a given number of chars in a direction of a 2d arr are a certain value
    private boolean isOpen(int charNum, int x, int y, char[][] arr, char value, int dirX, int dirY)
    {
        try
        {
            // checks number of chars
            for(int i = 0; i < charNum; i++)
            {
                // if not equal to value, return false
                if(arr[x+(i * dirX)][y+(i * dirY)] != value)
                    {return false;}
            }
        }
        catch(IndexOutOfBoundsException e)
            {return false;}
        
        return true;
    }



    // prints all the characters of a 2d char array
    // each array of spaced chars on its own line
    private void print2DCharArray(char[][] arr)
    {
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = 0; j < arr[i].length; j++)
            {
                System.out.print(arr[i][j] + " ");
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