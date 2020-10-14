import java.util.*;
public class RadioCommercial_updated {
    public static int profit(int[] array, int i, int j){

        if(i == j) { //single item: base case
            if(array[i] > 0) {
                //System.out.printf("Returning %d==MaxExtraProfit(moneylist,%d,%d)%n",array[i],i,j);            //--------Debug only
                return array[i];
            }
            else {
                //System.out.printf("Returning %d==MaxExtraProfit(moneylist,%d,%d)%n",0,i,j);                       //--------Debug only
                return 0;
            }
        }

        else { //multiple items: calculate cross-over sub-array then make recursive call for left and right sub-arrays
            int lMax=0; //to calculate cross-over sub-array
            int rMax=0;
            int lSum=0;
            int rSum=0;
            int middle=(i+j)/2; //split sub-array for recursion
            for(int m=middle;m>=i;m--) { //scan leftwards for the maximum sum
                lSum+=array[m];
                if(lMax < lSum) {
                    lMax=lSum;
                }
            }
            for(int m=middle+1;m<=j;m++) { //scan rightwards for the maximum sum
                rSum+=array[m];
                if(rMax < rSum) {
                    rMax=rSum;
                }
            }
            //recursive call using crossover max sum (calculated above), left and right max sum (recursive call)
            //System.out.printf("Calling MaxExtraProfit(moneylist,%d,%d) and MaxExtraProfit(moneylist,%d,%d)%n", //--------Debug only
            //        i,middle,middle+1,j);                                                                                //--------Debug only
            int lMaxExtraProfit=profit(array,i,middle);
            int rMaxExtraProfit=profit(array,middle+1,j);
            //System.out.printf("Returning %d==MaxExtraProfit(moneylist,%d,%d)==max(%d,%d(%d..%d),%d(%d..%d)%n", //--------Debug only
            //        Math.max(lMax+rMax, Math.max(lMaxExtraProfit, rMaxExtraProfit)),i,j,                       //--------Debug only
            //        lMax+rMax, lMaxExtraProfit, i, middle, rMaxExtraProfit, middle+1,j);                                 //--------Debug only
            return Math.max(lMax+rMax,Math.max(lMaxExtraProfit,rMaxExtraProfit));//************************************************************************
        }

    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//number of commercial breaks
        int P = sc.nextInt();//price per commercial break
        int[] commercialB = new int[N];//lists profit for listeners per ad break - price per commercial break
        for(int i = 0; i<N;i++){
            int iCB = sc.nextInt()-P;
            commercialB[i]=iCB;
            //System.out.println("\narray of listeners: "+commercialB[i]);//debug
        }
        int maxProfit = profit(commercialB, 0, N-1);
        System.out.println(maxProfit);
    }
}
