package ham;

import java.util.BitSet;

public class Hamming {
    private static String parityType = "even";

    public static void setParityType(String newParityType){
        parityType = newParityType;
    }

    public static void getHammingTable(String input){
        String[][] Table = new String[7][17];

        BitSet bitSet= stringToBitSet(input, Table);

        for (int i = 0; i <=4; i++){
            parityBit((int)Math.pow(2,i)-1,bitSet,Table,i+1);
        }

        for (int i =0; i<bitSet.length();i++){Table[6][i]=booleanToString(bitSet.get(i));}

        for (String[] row:Table) {
            for (String s :row ) {
                if (s==null){
                    s = "*";
                }
                System.out.print(s + "\t");
            }
            System.out.println();

        }
    }


    private static void parityBit(int bitPosition, BitSet bitSet, String[][] Table, int bitRow){
        boolean parity = true;
        if (parityType.equals("even")) parity = false;

        for (int i=1; i<=bitSet.length();i++){
            if(((bitPosition+1) & i)!=0){
                Table[bitRow][i-1] = booleanToString(bitSet.get(i-1));
                parity = parity^bitSet.get(i-1);
            }
        }
        Table[bitRow][bitPosition] = booleanToString(parity);
        bitSet.set(bitPosition,parity);
    }

    private static String booleanToString(boolean bool){
        return bool ? "1": "0";
    }

    private static boolean charToBoolean(char ch){
        return ch=='1';
    }

    private static BitSet stringToBitSet(String input, String[][] Table){
        BitSet bitSet = new BitSet(17);
        int count = 1;
        for (char ch: input.toCharArray()){
            while((count & (count-1))==0){count++;}
            Table[0][count-1] = String.valueOf(ch);
            bitSet.set(count-1,charToBoolean(ch));
            count++;
        }
        return bitSet;
    }

    public static void main(String[] args) {

        getHammingTable("100000000001");

    }
}
