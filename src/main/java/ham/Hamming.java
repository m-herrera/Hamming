package ham;

import java.util.BitSet;

public class Hamming {
    private static String parityType = "even";

    public static void setParityType(String newParityType){
        parityType = newParityType;
    }

    public static String[][] getHammingTable(String input){
        String[][] table = new String[7][17];
        getHammingTable(input,table);
        return table;
    }

    private static BitSet getHammingTable(String input, String[][] table){
        BitSet bitSet= stringToBitSet(input, table);

        for (int i = 0; i <=4; i++){
            parityBit((int)Math.pow(2,i)-1,bitSet,table,i+1);
        }

        for (int i =0; i<17;i++){table[6][i]=booleanToString(bitSet.get(i));}

        return bitSet;
    }

    private static void parityBit(int bitPosition, BitSet bitSet, String[][] table, int bitRow){
        boolean parity = true;
        if (parityType.equals("even")) parity = false;

        for (int i=1; i<=17;i++){
            if(((bitPosition+1) & i)!=0 &&(bitPosition+1!=i)){
                table[bitRow][i-1] = booleanToString(bitSet.get(i-1));
                parity = parity^bitSet.get(i-1);
            }
        }
        table[bitRow][bitPosition] = booleanToString(parity)+"^";
        bitSet.set(bitPosition,parity);
    }

    private static String booleanToString(boolean bool){
        return bool ? "1": "0";
    }

    private static boolean charToBoolean(char ch){
        return ch=='1';
    }

    private static BitSet stringToBitSet(String input, String[][] table){
        BitSet bitSet = new BitSet(17);
        int count = 1;
        for (char ch: input.toCharArray()){
            if (input.length() == 12) {while((count & (count-1))==0){count++;}}
            table[0][count-1] = String.valueOf(ch);
            bitSet.set(count-1,charToBoolean(ch));
            count++;
        }
        return bitSet;
    }
    private static BitSet stringToBitSet(String input){
        String[][] tempTable= new String[7][17];
        return stringToBitSet(input,tempTable);
    }

    public static String[][] getHammingError(String error){
        String[][] table = new String[7][19];

        BitSet bitSet = stringToBitSet(error);
        error = removeParity(error);
        BitSet bitSetError = getHammingTable(error,table);
        compareParityBits(bitSet,bitSetError,table);

        return table;
    }

    private static String removeParity(String input){
        String result = "";
        int count =1;
        for (char ch: input.toCharArray()){
            if ((count & (count-1)) == 0){
                count++;
                continue;
            }
            result+=ch;
            count++;
        }
        return result;
    }

    private static void compareParityBits(BitSet original, BitSet error, String[][] table){
        int row = 1;
        int errorPosition = 0;
        for(int i=1; i<=17; i++){
            if( (i&(i-1))==0){
                if(original.get(i-1)==error.get(i-1) ){
                    table[row][17] = "Correcto";
                }else{
                    table[row][17] = "Error";
                    errorPosition+=i;
                }
                table[row][18]=booleanToString(original.get(i-1));
                row++;
            }
        }
        if (errorPosition == 0)return;
        int count =0;
        for(int i=0; i<=6;i++){
            for (int j = 1; j<=16;j++){
                if(table[i][j-1]!=null && i != 6) {
                    if ((j&(j-1))==0){
                        String temp = table[i][j-1];

                        table[i][j-1] = table[i][18] +="^";
                        table[i][18] = String.valueOf(temp.charAt(0));

                        table[6][j-1] = String.valueOf(table[i][j-1].charAt(0));
                    }
                }
            }
            if (table[i][errorPosition-1] != null) table[i][errorPosition-1]+="$";
        }
        for (int i =0; i<17;i++){
            table[0][i]= table[6][i];
            table[6][i] = null;
        }
    }

    public static void printTable(String[][] table){
        for (String[] row:table) {
            for (String s :row ) {
                if (s==null){
                    s = "*";
                }
                System.out.print(s + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static String getHamming17Bit(String[][] table){
        String output = "";
        for (String s: table[6]){
            output+= s;
        }
        return output;
    }

    public static void main(String[] args) {
        printTable(getHammingTable("100110100110"));
        System.out.println(getHamming17Bit(getHammingTable("100110100110")));
        printTable(getHammingError("11010010101001100"));
        setParityType("odd");
    }
}
