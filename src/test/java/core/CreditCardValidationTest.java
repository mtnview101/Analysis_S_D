package core;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;
import org.testng.ITest;
import org.testng.annotations.*;


public class CreditCardValidationTest implements ITest {
       String csvFile = "./src/resources/test_data/csv/cc.csv";
// String csvFile = System.getProperty("testcases"); // mvn site -Dtestcases="./src/resources/test_data/csv/cc.csv"
       private String test_name = ""; public String getTestName() {return test_name;}
       private void setTestName(String a) {test_name = a;}

/*       @BeforeMethod(alwaysRun = true)
       public void bm(Method method, Object[] parameters) {
              setTestName(method.getName());
              Override a = method.getAnnotation(Override.class);
              String testCaseId = (String) parameters[a.id()];setTestName(testCaseId);}*/
       
       @BeforeMethod(alwaysRun = true)
       public void bm(Method method, Object[] parameters) {
              setTestName(method.getName());
              Override a = method.getAnnotation(Override.class);
              String testCaseId = (String) parameters[a.id()];
              setTestName(testCaseId);}

       @DataProvider(name = "dp")
       public Iterator<String[]> a2d() throws InterruptedException, IOException {
              String cvsLine = ""; String[] a = null;
              ArrayList<String[]> al = new ArrayList<>();
              BufferedReader br = new BufferedReader(new FileReader(csvFile));
              while ((cvsLine = br.readLine()) != null) {
              a = cvsLine.split(","); al.add(a);}br.close(); return al.iterator();}

       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "card_type")
       public void card_type_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) {
              assertThat(CreditCardValidation.card_type(cc_type, cc_number), is(true));}
       
       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "card_length")
public void card_number_length_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) {
              assertThat(CreditCardValidation.card_number_length(cc_type, cc_number), is(true));}
       
       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "exp_format")
public void card_exp_format_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) {
              assertThat(CreditCardValidation.card_exp_format(cc_exp), is(true));}
       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "exp")
public void card_exp_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) {
              assertThat(CreditCardValidation.card_exp(cc_exp), is(true));}
       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "cvv")
public void card_cvv_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) {
              assertThat(CreditCardValidation.card_cvv(cc_type, cc_cvv), is(true));}
       @Override(id = 1)
       @Test(dataProvider = "dp", enabled = true, groups = "luhn")
public void luhn_test(String cc_type, String cc_number, String cc_exp, String cc_cvv) throws IOException {
// CreditCardValidation a = new CreditCardValidation ();a.getClass();
// CreditCardValidation.main(null);
              assertThat(CreditCardValidation.luhn(cc_number), is(true));}
}
