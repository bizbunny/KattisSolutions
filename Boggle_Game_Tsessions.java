import java.util.Scanner;
import java.util.TreeSet;

public class Boggle_Game_Tsessions {
	static int[] wordScoreTracker = {0,0,0,1,1,2,3,5,11};//score points tracker
	//gives all possible directions of travel. Goes clockwise starting from north.
	static int[] boardX = {0,1,1,1,0,-1,-1,-1};//makes space for going along x coordinates
	static int[] boardY = {-1,-1,0,1,1,1,0,-1};//makes space for going along y coordinates

	static TreeSet<String> possibleWordsDict = new TreeSet<String>();//dictionary of possible words. The bucket of words for our dictionary
/*	
	static boolean isWord(String str) {
		for(int p = 0; p<possibleWords.size();p++) {
			if(str.equals(possibleWords.ceiling(str))) {
				return true;
			}
		}
		return false;
	}
*/	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);//for getting input in
		//System.out.println("Please enter the number of possible words (W): ");//--------------------------------------------------------prompt
		int W = sc.nextInt();//number of words possibly found in the boards. Number of words in dctionary
		
		//System.out.println("Please enter the possible words found (possibleWords): ");//--------------------------------------------------------prompt
		for(int i = 0;i<W;i++) {//filling up the dictionary
			String possibleWord_Found = sc.next();//user input dictionary words
			possibleWordsDict.add(possibleWord_Found);//possible words we will find in boggle boards get added to dictionary
		}//for 0 to W
			System.out.println("\n");//blank line------------------------------------------------------------------------------------------------------------

			String[] wordFound = new String[8];//we have a space for when we find a word and we compare it to words in dictionary to see if they match
			/*wordFound is building up the word in each index instead of building up a word to make up entire array*/

			//ArrayList<String> words_Found = new ArrayList<String>();
			//int row_Y = 0;//
			//int col_X = 0;//

			//int[] path = {2,2,2,4,5,5,6,0};
		
			//System.out.println("Please enter the number of boards (B): ");//---prompt

			int B = sc.nextInt();//number of boards
			
			//System.out.println("Enter the rows in each board: ");//---prompt

			//each game!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			for(int i = 0;i<B;i++) {//go through each board. This particular for loop goes through number of boards indicated.
				String[][] boggle_board44 = new String [4][4];//board is 4 rows x 4 columns. Our board!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				boolean[][] visited = new boolean[4][4];//mark which part of the board we have visited
				int[] pathx = new int[8];//what's been visited in x (keep track by coordinate)
				int[] pathy = new int[8];//what's been visited in y (keep track by coordinate)
				int[] direction = new int[8];//stores direction we go in depending on one of the 8 directions!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				int wordLength = 0;//self explanatory
				
				TreeSet<String> scoreWords = new TreeSet<String>();//make sure you dont score particular word twice* * * *
				
				int totalScore = 0;//total overall scoreto output later
				String longestWord="";//to build up and output later
				int numWords_found=0;//to output later. number of words we found in particular board
				
				//fill current boggle_board44 with letters
				for(int row = 0;row<4;row++) {//from user input
					boggle_board44[row] = sc.next().split("");//row of letters to put in boggle board
				}//for 0 to 4//fills boggle boards
				
				pathx[wordLength] = -1;//starting point. We did not start searching yet, so starting point is set to -1.
				pathy[wordLength] = -1;//start y-----------test. We did not start searching yet, so starting point is set to -1.
				//evaluate word so far and backtrack if needed *  *  *  *  *  *  *  *  *
				while(true) {
					//find new letter
					boolean foundLetter = false;// we did not find anything yet
					boolean start_position_found = false;//has not been used -------------------------
					//for starting index only
					if(wordLength == 0) {//we can start from any position of the board. We try to find a start position
						if(pathx[wordLength]>=0) {//release previous dice. If pathx[wordLength] is bigger than 0, we already visited something, so we have to reset visited[] back to false
							visited[pathy[wordLength]][pathx[wordLength]] = false;
							//we are changing the starting point
						}
						//System.out.println(pathx[wordLength]);//debug
						//System.out.println(pathy[wordLength]);//debug
						if(pathx[wordLength]<0) {//we don't have a starting point yet, so we make one, by making pathx and pathy [wordLength] = 0
							//start at 0,0
							pathx[wordLength] = 0;
							pathy[wordLength] = 0;
							//pathx[wordLength]++;
							//pathy[wordLength]++;
						}
						else if(pathx[wordLength]<3) {//this makes sure we can start at any point on the board by expanding the pool of potential starting points
							pathx[wordLength]++;
							//wordLength++;//test
						}
						else if(pathy[wordLength]<3) {//this makes sure we can start at any point on the board by expanding the pool of potential starting points
							pathy[wordLength]++;
							pathx[wordLength] = 0;//since we're dealing with y, set pathx blabla bla to 0.
							//wordLength++;//test
						}
						else { break; }//pathx and pathy have both arrived at 3
						visited[pathy[wordLength]][pathx[wordLength]] = true;//we visited every possible starting char in the board
						wordFound[wordLength] = boggle_board44[pathy[wordLength]][pathx[wordLength]];//it's putting whatever letter we are currently at in the wordFound array. This line basically means we have a start letter to use.
						foundLetter = true;//found new letter since we found a start letter(see previous line)*  * * *  * * * * * **  * * *
						// *****************************we found starting letter*****************************
						
						//start_position_found = true;
						
					}
					//this big else statement is used because now we have a starting letter. The starting letter is what we rotate around.
					else{//we choose a direction from previous letter---what does the rotation------------------------------------need new flag so this can be accessed
						//we choose a direction from the previous letter (in this for loop)
						// -1 with wordLength is to make it an index. +1 moves us to the next direction slot
						for(int d = direction[wordLength-1]+1;d<8;d++) {//starts from direction we've already done

							//System.out.println("d "+d);//----------------------debug
							
							//check if dir possible
							if(pathx[wordLength-1]+ boardX[d]>=0 && pathx[wordLength-1]+ boardX[d]<=3 &&
								pathy[wordLength-1]+ boardY[d] >=0 && pathy[wordLength-1]+ boardY[d]<=3) {
								/*So this goes through the rotation of x coordinates by making sure it's between 0 and 3 inclusive. If it's not between 0 and 3
								* it's off the boundaries of the 4 by 4 board. Same applies to the y coordinates.*/
								if(!visited[pathy[wordLength-1]+ boardY[d]][pathx[wordLength-1]+ boardX[d]]) {//this if statement checks whether or not we used this direction yet?
									//d not used yet
									if(direction[wordLength-1]>=0) {//RESET VISITED
										
										//System.out.println(d);//----------------------debug

										//we change dir here. SO we have to reset visited.
										visited[pathy[wordLength]][pathx[wordLength]]= false;
										//You searched all that you could so you go to a new spot to search because you have not visited yet. Remember, this changes the direction.
									}
									direction[wordLength-1] = d;//record direction we currently use.
									
									pathx[wordLength] = pathx[wordLength-1]+ boardX[d];//begin new dir x coordinate.
									pathy[wordLength] = pathy[wordLength-1]+ boardY[d];//begin new direction y coordinate
									visited[pathy[wordLength]][pathx[wordLength]] = true;//visited new direction. we're marking this new direction as visited to mark the fact that we are going there.
									wordFound[wordLength] = wordFound[wordLength-1]+boggle_board44[pathy[wordLength]][pathx[wordLength]];//this finally adsd new stuff to word found.
									foundLetter = true;//something new is in word found, so foundLetter is marked as true
									break;//first section done//found new letter//we are leaving the else

								}//if (!visited[pathy[wordLength-1]+board_y[d]][pathx[wordLength-1]+board_x[d]])
							}//if(pathx[wordLength-1]+board_x[d]>=0 && pathx[wordLength-1]+board_x[d]<=3 && pathy[wordLength-1]+board_y[d] >=0 && pathy[wordLength-1]+board_y[d]<=3)
						}//for d direction
					}//else choose direction we've already done
					
					/* Note: ceiling gives you closest match to wordFound
					stringcompare of beginning needed
					AC different from ACM
					substring of ACM maybe */

					/*
					System.out.println("wordLength "+wordLength);                                        //----debug
					//System.out.println("wordFound[wordLength] "+wordFound[wordLength]);                //----debug
					System.out.println("wordFound "+Arrays.deepToString(wordFound));                     //----debug
					System.out.println("direction "+Arrays.toString(direction));
					for(int row=0;row<4;row++) { //print out visited cells in board                      //----debug
						for(int col=0;col<4;col++) {                                                     //----debug
							System.out.printf(visited[row][col]?" >%s":"  %s", boggle_board44[row][col]);  //----debug
						}                                                                                //----debug
						System.out.println();                                                            //----debug
					}                                                                                    //----debug
					*/

					//in case we found letter (the thing we put in foundLetter)
					//Backtracking process
					if(foundLetter) { //<--set breakpoint here for debug-----we're still going forward since we found a letter
						// Code to verify whether word is in dictionary, update score, and optionally pruning the tree
						//if good direction go forward(beginning matches possible words since we're using TreeSets)
						String bestMatch = possibleWordsDict.ceiling(wordFound[wordLength]);//bestMatch gives us closest match to word from dictionary
						if(bestMatch != null) {//if there's something actually in there
							/*substring gets whatever is shorter between wordFound and bestMatch. So if both are the same length, the substring is the entire word.
							* If both wordFound and bestMatch are ACM, then bestMatch is the entire word, and that word is in dictionary.
							* So we have AC and ACM, the following line cuts down ACM to AC to make it easier to compare to the other AC. That's why we have the Math. min()*/

							bestMatch = bestMatch.substring(0, Math.min(bestMatch.length(), wordFound[wordLength].length()));//helps get same number of letters between wordFound and bestMatch
							if(possibleWordsDict.contains(wordFound[wordLength])) {//so if the dictionary has teh word we found. That means you found something that matches
								//found a matched word
								if(!scoreWords.contains(wordFound[wordLength])) {//scoreWords doesn't have anything in there yet.
									//found new word
									scoreWords.add(wordFound[wordLength]);//the found word is added to scoreWords
									numWords_found++;//we found a word so this goes up.
									
									totalScore+= wordScoreTracker[wordFound[wordLength].length()];//we index however long that word is, and we apply the appropriate number points to the score depending on the length of the found word
									
									//compare word lengths
									if(wordFound[wordLength].length()>longestWord.length() || 
											wordFound[wordLength].length()==longestWord.length() &&
											wordFound[wordLength].compareTo(longestWord)<0) {//compareTo gives -val if ealier lexico order
										longestWord = wordFound[wordLength];//new long word;
										//this if statement compares what's the longest word and makes that the longest word appropriately and also gets the earliest lexicographical longest word.
									}
								}
							}//if found possible word
							//compare to below, if 0 that means wordFound[wordLength] matches bestMatch
							//* * * * this is where pruning happens. Because if wordFound and bestMatch are AC, then we don't have to continue because we know that not in dictionary * * * *
							/* compareTo returns non 0 number if what is compared is not equal.*/
							//wordFound and bestMatch are being compared. Are they equal? and is wordLength less than 7? Because at 8, you can't go any further.
							//If wordFound does not = bestMatch, we don't need to continue

							//less than 7 because, once we hit 8, we can't go forward
							if(wordFound[wordLength].compareTo(bestMatch) == 0 && wordLength<7) { //<--set shorter limit for debug, should be 7 in final program (8 letters max)
								direction[wordLength]=-1; //initial direction unknown. We are still going foward. We have a semblance of a word, but where are we going? From where do we add new letters? North, South, Etc?
								wordLength++;  //found letter, go forward
							} //able to add additional letters
							//goes back to while true if word length = 7
						}//if bestMatch not null
					} //found letter
					else { //no new letter found, backtrack-----------backward * * * * * * * *
						if(wordLength==0) { break; } //at first letter //cannot backtrack further, exit backtracking algorithm
						else { //can backtrack because you have more than just a starting point
							wordFound[wordLength]="";//we're erasing what char we have at that index to move back.
							visited[pathy[wordLength]][pathx[wordLength]]=false;//whatever square we're at gets set back to false since we backtracking
							wordLength--; //backtrack
						} // can backtrack
					} //exit or backtrack
				}//while true

				//output
				System.out.println(totalScore+" "+longestWord+" "+numWords_found);
			}// for 0 to B
	}//main
}//class Boggle_Game_Tsessions
