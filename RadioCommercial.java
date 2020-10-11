import java.util.*;
public class RadioCommercial {
    public static int profit(int[] array){
        //int midMax=0;
        //int sumL = -100000, sumR = -100000;
        //int middle = (array.length/2)-1;

        int maxRightNow = array[0];
        int maxEnd = array[0];

        if(array.length<100000){
            if(array.length==1){
                return 1;
            }
            else if(array.length<0){
                return 0;
            }
            else{
                /*
                for(int i = middle; i>0;i--){
                    int sum = 0;
                    sum = sum+array[i];
                    if(sum> sumL){
                        sumL=sum;
                    }
                }
                for(int j = middle+1; j < array.length; j++){
                    int sum = 0;
                    sum = sum+array[j];
                    if(sum>sumR){
                        sumR=sum;
                    }
                }
                System.out.println("middle: "+array[middle]);//debug
                System.out.println("sumL: "+sumL);//debug
                System.out.println("sumR: "+sumR);//debug
                midMax = Math.max(Math.max(sumL,sumR),sumL+sumR);
                * */
                for(int i = 1; i<array.length;i++){
                    maxEnd = Math.max(array[i],maxEnd+array[i]);
                    maxRightNow = Math.max(maxRightNow,maxEnd);
                }
            }
        }
        return maxRightNow;
    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//number of commercial breaks
        int P = sc.nextInt();//price per commercial break
        int[] commercialB = new int[N];
        for(int i = 0; i<N;i++){
            int iCB = sc.nextInt()-P;
            commercialB[i]=iCB;
            //System.out.println("\narray of listeners: "+commercialB[i]);//debug
        }
        int maxProfit = profit(commercialB);
        System.out.println(maxProfit);
    }
}
