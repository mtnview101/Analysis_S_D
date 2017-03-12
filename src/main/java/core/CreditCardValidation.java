package core;

import java.io.*;
import java.sql.Timestamp;

public class CreditCardValidation {
// 4532547106994816   or  4532-5471-0699-4816   or  4532 5471 0699 4816
	
	
       public static boolean card_number_length(String cc_type, String cc_number) {
              String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));

if (cc_type.equals("VISA") && (number.length() == 13 || number.length() == 16)) {return true;}
else if (cc_type.equals("MasterCard") && number.length() == 16) {return true;}
else if (cc_type.equals("Discover") && number.length() == 16) {return true;}
else if (cc_type.equals("American Express") && number.length() == 15) {return true;}
else {return false;}}
       

       public static boolean card_type(String cc_type, String cc_number) {
              String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));

     if (number.startsWith("4") && (number.length() == 13 || number.length() == 16) && 
cc_type.equals("VISA")) {return true;} 
else if ((number.startsWith("51") || number.startsWith("52") ||
number.startsWith("53") || number.startsWith("54") || number.startsWith("55")) &&
number.length() == 16 && cc_type.equals("MasterCard")) {return true;}
else if ((number.startsWith("6011") || number.startsWith("62") ||
number.startsWith("64") || number.startsWith("65")) && number.length() == 16 &&
cc_type.equals("Discover")) {return true;}
else if ((number.startsWith("34") || number.startsWith("37")) &&
number.length() == 15 && cc_type.equals("American Express")) {return true;} 
else {return false;}}
       // 02/18  
       
       
public static boolean card_exp_format(String cc_exp)  {
              return (cc_exp.matches("^(\\d{2}/\\d{2})$"));}


       public static boolean card_exp(String cc_exp) {
Timestamp ts = new Timestamp(System.currentTimeMillis()); // 2017-03-05 21:33:11.045
int exp = Integer.parseInt(cc_exp.substring(3, 5) + cc_exp.substring(0, 2));
int today = Integer.parseInt((String.valueOf(ts.toString()
.split("-")[0].substring(2, 4)) +   // 1703    1802   
ts.toString().split("-")[1])); return (today <= exp);}
       
              
       public static boolean card_cvv(String cc_type, String cc_cvv) {
              if (cc_type.equals("American Express")) {return cc_cvv.matches("^(\\d{4})$");} 
              else {return cc_cvv.matches("^(\\d{3})$");}}


       public static boolean luhn(String cc_number) {
              String number = new String(cc_number.replaceAll("\\s", "").replaceAll("-", ""));

              int sum = 0; boolean swap = false;
              for (int i = number.length() - 1; i >= 0; i--) {
                     int digit = Integer.parseInt(number.substring(i, i + 1));
                     if (swap) {digit = digit*2; if (digit > 9) {digit = digit-9;}}
                     sum = sum+digit; swap = !swap;} return (sum % 10 == 0);}

public static void main(String[] args) throws IOException  {

// if (args.length == 0) {System.err.println("Please enter a path as an argument"); System.exit(1);}
// if (!args[0].contains(".csv")) {System.err.println("Argument must be an csv file"); System.exit(1);}
// String csvFile = args[0];
              String csvFile = "./src/resources/test_data/csv/cc.csv";
              String line = "";
              String delimiter = ",";
              BufferedReader br = new BufferedReader(new FileReader(csvFile));
              while ((line = br.readLine()) != null) {
                     String[] csv = line.split(delimiter);
System.out.println(
"CC Type: "+ card_type(csv[0], csv[1]) + ", "+ 
"CC Length - "+ (card_number_length(csv[0],csv[1]) ? "valid" : "Invalid length")+"; "+
"Exp Format - "+ (card_exp_format(csv[2]) ? "valid" : "invalid") + "; "+
"Expiration - "+ (card_exp(csv[2]) ? "not expired" : "expired") + "; "+
"CVV Length - "+ (card_cvv(csv[0], csv[3]) ? "valid" : "Invalid length") + "; "+
"Luhn Test: "+ ((luhn(csv[1])) ? "valid" : "invalid"));}
              br.close();
// CreditCardValidation a = new CreditCardValidation();
       }
}

