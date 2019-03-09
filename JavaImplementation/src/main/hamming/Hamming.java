package main.hamming;

import java.util.Arrays;
import java.util.BitSet;

public class Hamming {
    private static String parityType = "even";

    private static BitSet parityCheck(BitSet input){
        BitSet output= setParityBits(input);
        return output;
    }

    private static BitSet setParityBits (BitSet bitSet){
        BitSet output = new BitSet(4);
        if (!parityType.equals("even")){
            output.flip(0,4);
        }
        for (int i=0;i<=7;i+=1){
            if (bitSet.get(i)){
                if(Arrays.asList(0,1,3,4,6).contains(i)) output.flip(0);
                if(Arrays.asList(0,2,3,5,6).contains(i)) output.flip(1);
                if(Arrays.asList(1,2,3,7).contains(i)) output.flip(2);
                if(Arrays.asList(4,5,6,7).contains(i)) output.flip(3);
            }
        }
        return output;
    }


    public static void setParityType(String newParityType){
        parityType = newParityType;
    }

    public static String[][] Table(BitSet result){

        String[][] inputTable = new String[6][11];
        for (int i=0; i<6;i++){
            for (int j=0;j<11;j++) {

                if(Arrays.asList(2,4,5,6,8,9,10).contains(j) && i==0)
                    inputTable[i][j]= (result.get(j)) ? "1" : "0";
                if(Arrays.asList(0,2,4,6,8,10).contains(j) && i==1)
                    inputTable[i][j]= (result.get(j)) ? "1" : "0";
                if(Arrays.asList(1,2,5,6,9,10).contains(j) && i==2)
                    inputTable[i][j]= (result.get(j)) ? "1" : "0";
                if(Arrays.asList(3,4,5,6).contains(j) && i==3)
                    inputTable[i][j]= (result.get(j)) ? "1" : "0";
                if(Arrays.asList(7,8,9,10).contains(j) && i==4)
                    inputTable[i][j]= (result.get(j)) ? "1" : "0";
                if (i==5)
                inputTable[i][j]= (result.get(j)) ? "1" : "0";
            }
        }
        return inputTable;
    }
    private static BitSet join(BitSet bitSet1, BitSet bitSet2){
        BitSet output = new BitSet(11);
        int count1 = 0;
        int count2 = 0;
        for (int i=0; i <12;i++){
            if (Arrays.asList(0,1,3,7).contains(i)){
                output.set(i,bitSet1.get(count1));
                count1++;
                continue;
            }
            output.set(i,bitSet2.get(count2));
            count2++;
        }
        return output;
    }

    private static BitSet stringToBitSet(String input){
        int bits = input.length();
        BitSet bitSet = new BitSet(bits);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '1'){
                bitSet.set(i);
            }else if (ch != '0'){
                System.out.println("invalid input"); //TODO handle input error
            }
        }
        return bitSet;
    }
    public static void main(String[] args) {

        BitSet correctInput = stringToBitSet("0110101");
        BitSet parity = parityCheck(correctInput);
        BitSet result = join(parity,correctInput);
        BitSet out = stringToBitSet("10001100100");
        String[][] table = Table(out);
        for (int i=0; i<6;i++){
            for (int j=0;j<11;j++) {
                if (table[i][j] == null) System.out.print("*" + "\t");
                else System.out.print(table[i][j] + "\t");
            }
            System.out.println();
        }

    }
}
