package core;

public class CardNumberGenerator {
	public static void main(String[] args) {
String  cc_number="4532-4567-1234-9870";
        String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));

        int sum = 0; boolean swap = false;
        for (int i = number.length() - 1; i >= 0; i--) {
               int digit = Integer.parseInt(number.substring(i, i + 1));
               if (swap) {digit = digit*2; if (digit > 9) {digit = digit-9;}}
               sum = sum+digit; swap = !swap;} 
        if (sum % 10 == 0) System.out.println(cc_number); else System.out.println("change card last digit");
}
}
