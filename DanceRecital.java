import java.util.Scanner;
public class DanceRecital {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int R = scanner.nextInt();// input number of dance routines in performance
        String[] inputTable = new String[R];
        for(int i = 0;i<R;i++) {
            inputTable[i] = scanner.next();//filling the table with all the different dance routines
        }

        int[] calculationTable = new int[R];//for testing out different permutations of dances and indexing the first table (inputTable[])
        for(int j = 0;j<R;j++) {
            calculationTable[j] = -1;//fills the empty table with negative 1 to have default empty value. We did not put dance routines in yet
        }

        boolean[] sequenced_dance = new boolean[R];//keeps track of what dance is present in calc table
        int[] quick_changes = new int[R];// we have to keep this as low as possible
        int bestScore = 1000;

        int danceNumber = 0;//Keep track of which routine in input table we're working with// How to go forward or backtrack

        while(true) {
            //choose next dance number for this danceNum
            boolean foundDance=false;//dance is valid and needs no reordering// without this the backtrack doesn' t know if we found a dance or not// keep track if you took it out of the hat or not
            int cumulscore=0;//compares this to bestScore//cumulative score for how many quick changes there are
            for(int d =calculationTable[danceNumber]+1;d<R;d++) {//checks each next dance routine
                if(!sequenced_dance[d]) {//if this dance d not sequenced yet
                    if(calculationTable[danceNumber]>-1) {//if danceNum sequenced, un-sequence it(get it out of calc and dance sequence table)
                        sequenced_dance[calculationTable[danceNumber]]=false;//put back in hat
                    }
                    calculationTable[danceNumber]=d;//when not= -1; calcTable contains index of dance number in inputTable . Probably tells us what dance we're on
                    sequenced_dance[d]=true;//It's true because it be out of the hat
                    foundDance=true;//You found a dance, so this keeps track of the fact that it's out of the hat
                    if(danceNumber>0) {//if 0, cant compare to previous dance routine
                        quick_changes[danceNumber-1] = 0;//you're initiallizing default value to quick change table because you'll be adding to it later (tentatively)
                        for(int c = 0;c< inputTable[calculationTable[danceNumber]].length();c++) {
                            //loop through each char of new dance number
                            if(inputTable[calculationTable[danceNumber-1]].indexOf(inputTable[calculationTable[danceNumber]].charAt(c)) != -1) {//look to see if quick change is needed
                                quick_changes[danceNumber-1]++;//ties with line 32 ********************************8
                                //look through char in string of inputTable with given index from calcTable to find same char
                                //add to quick change table
                            }//found quick change
                        }//loop through all char

                        //there is a quick change now
                        //cumulative score now?
                        for(int i = 0;i<danceNumber;i++) {
                            cumulscore +=quick_changes[i];
                        }

                        //debug only
                        if(danceNumber==R-1) {
                            for(int k=0;k<R;k++) {                                                //debug only
                                System.out.printf("%s ", inputTable[calculationTable[k]]);        //debug only
                            }                                                                     //debug only
                            System.out.printf("%d%n", cumulscore);                                //debug only
                        }//debug only
                    }
                    break; //foundDance// to get out of big for loop so we can inspect it below to see if we need to backtrack or not
                }//found un-sequenced number
            }//scan through all remaining possibilities

            //Evaluate solution found
            if(foundDance) {
                //pruning happens in this area******************************************
                if(cumulscore < bestScore) {//pruning not applicable
                    // if so, we're not in pruning condition, we can continue
                    if(danceNumber == R-1) {//we're at end of array.
                        bestScore = cumulscore;//where we know we're finished with one sequence
                    }
                    else{
                        danceNumber++;//danceNum hasn't reached last sequence yet.
                    }
                }
            }
            else {//not foundDance, backtrack**********************************************
                if(calculationTable[danceNumber]>-1) {//this means it not be empty so we have danceNum in there
                    //we've chosen a dance so have to clear
                    sequenced_dance[calculationTable[danceNumber]] = false;
                    calculationTable[danceNumber] = -1;
                }
                danceNumber--;//backtrack
                if(danceNumber<0) {
                    break; //exit if no backtrack possible
                }
            }//foundDance
        }//while
        scanner.close();//if it not be here, there be resource leak
        System.out.println(bestScore);

    }//main
}//class
