import java.util.Scanner;

public class BinaryToHexDecBCD {

    public static void main(String[] args) {

        Scanner input = new Scanner ( System.in );

        System.out.println ( "\nIngrese el número binario" );

        String binary = input.next ( );
        int decimal = toDecimalUsingParseInt ( binary );
        decimal = recursiveBin ( binary );


        String hexadecimal = DectoHexString ( decimal );
        hexadecimal = recursiveDecHex ( decimal );


        System.out.printf ( "\nBinario a Hexadecimal (%s) = %s ", binary, hexadecimal );
        System.out.printf ( "\nBinario a Decimal (%s) = %d ", binary, decimal );

        String BCD = toBCD (decimal);
        System.out.printf ( "\nBCD (%s)", BCD );

    }

    private static int toDecimalUsingParseInt (String binaryNumber) {
        return Integer.parseInt(binaryNumber, 2);
    }

    private static String DectoHexString(int number) {
        return Integer.toHexString(number);
    }

    private static int recursiveBin(String binaryNumber) {
        int decimal = 0;
        int length = binaryNumber.length();
        if (length > 0) {
            String substring = binaryNumber.substring(1);
            int digit = Character.getNumericValue(binaryNumber.charAt(0));
            decimal = digit * (int) Math.pow(2, length - 1) + recursiveBin(substring);
        }
        return decimal;
    }

    private static String recursiveDecHex(int number) {
        StringBuilder builder = new StringBuilder();
        if (number > 0) {
            String hexNumber = recursiveDecHex(number / 16);
            String hexCode = "0123456789ABCDEF";
            int hexDigit = number % 16;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
        }
        return builder.toString();
    }

    private static String toBCD (int number) {
        String BCD="";
        while(number!=0)
        {
            int t=number%10;
            String tBCD=Integer.toBinaryString(t);
            while(tBCD.length()<4)
            {
                tBCD="0"+tBCD;
            }
            BCD=tBCD+BCD;
            number/=10;
        }
        return BCD;
    }

}