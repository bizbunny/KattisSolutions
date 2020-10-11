import java.util.Scanner;
public class geppetto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter N and M");                                                              // <<<<<< Debug only
        int N = scanner.nextInt();//number of ingredients
        int M = scanner.nextInt();//number of NoMixPairs
        int[][] cantMix = new int[N][N]; // cantMix[i-1][j-1]=1 if ingredients i and j (0<=i,j<N) cannot be mixed; initially all zeroes
        System.out.printf("Please enter %d pairs of ingredients that cannot be mixed%n",M);                     // <<<<<< Debug only
        for(int pair=0;pair<M;pair++) {//no pairs yet, so pair = 0. M is the number of no mix pairs we have at most.
            int i=scanner.nextInt();//first digit in NoMixPairs
            int j=scanner.nextInt();//second digit in NoMixPairs
            //System.out.println("cantMix[i-1][j-1]=1; : "+cantMix[i-1][j-1]);                                  // <<<<<< Debug only
            cantMix[i-1][j-1]=1;//first thing in the table/list
            cantMix[j-1][i-1]=1;//first thing in the table/list
        }
        int[] pizza = new int[N];//for pizza Testing
        int possiblePizzas = 1; //represents first valid pizza, the completely plain one
        int ingrNo = 0;//for when we iterate through the ingredients to test shit out
        boolean ingrOk;//check to see if ingredient combo is valid or not
        while(true) {//infinite loop to help with testing
            if(pizza[ingrNo]<N) { //one more ingredient to try for ingrNo. Expand teh partial solution if you will
                if(pizza[ingrNo]==0 && ingrNo>0) { //choose next ingredient but not less than previous ingredient since sorted. Slot is also empty so we put something there
                    pizza[ingrNo]=pizza[ingrNo-1]+1;}  //if no ingredient there, choose next ingredient from previous ingredient
                else {//iterate to next ingredient since current slot already full
                    pizza[ingrNo]++;  //try next ingredient in sequence; including special case ingrNo==0, choose ingredient 1
                }
                ingrOk=true;//ingredients are valid
                for(int i=0;i<ingrNo;i++) {//we at current pizza I think and see if ingredient combo is valid with this specific ingredient
                    if(cantMix[pizza[i]-1][pizza[ingrNo]-1]>0) {//try every combo iwth pizza[i] to see if valid
                        ingrOk=false;//combo is not valid
                        break;
                    } //check that pizza[ingrNo] is compatible with all other ingredients before
                }
                if(ingrOk) {                                                   // <<<<<< Debug only
                    possiblePizzas++;                                          // <<<<<< Debug only
                    System.out.printf("%d:", possiblePizzas);                 // <<<<<< Debug only
                    for(int j=0;j<N;j++) {                                    // <<<<<< Debug only
                        if(pizza[j]>0) {System.out.printf(" %d",pizza[j]);}  // <<<<<< Debug only
                        else break;                                           // <<<<<< Debug only
                    }                                                         // <<<<<< Debug only
                    System.out.println();                                     // <<<<<< Debug only
                    if(pizza[ingrNo]<N) {
                        ingrNo++;//We continue to advance since there's more ingredients
                    }
                }
            }
            else {//this is where the backtracking happens when we need it
                pizza[ingrNo]=0;
                if(ingrNo>0) {
                    ingrNo--;//go back if needed
                }
                else {
                    break;
                }
            }
        }
        System.out.print(possiblePizzas);
    }
}
