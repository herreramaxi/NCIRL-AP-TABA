/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.mhtabaap.BankLoanCalculatorService;
import com.mycompany.mhtabaap.CalculatorServiceResponse;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Maximiliano Herrera
 */
public class BankLoanCalculatorServiceTests {

    private final BankLoanCalculatorService _bankLoanCalculatorService = BankLoanCalculatorService.getInstance();

    public BankLoanCalculatorServiceTests() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TestCorrectMonthlyPaymentCalculation() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(6, 360, 100000);
        assertThat("599.55", is(String.format("%.2f", response.getValue())));
    }

    @Test
    public void TestCorrectTotalRepaymentCalculation() {
        double monthlyPayment = _bankLoanCalculatorService.calculateMonthlyPayment(6, 360, 100000).getValue();
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateTotalRepayment(monthlyPayment, 360);

        assertThat("215838.19", is(String.format("%.2f", response.getValue())));
        assertThat(monthlyPayment * 360, is(response.getValue()));
        assertThat(true, is(response.isSuccessful()));
    }

    @Test
    public void When_Entering_OutOfRange_AnualIR_A_Failed_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(0, 360, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: anualIR"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(-1, 360, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: anualIR"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(101, 360, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: anualIR"));
    }

    @Test
    public void When_Entering_InRange_AnualIR_A_Successful_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(0.5, 360, 100000);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(50, 360, 100000);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(100, 360, 100000);
        assertThat(true, is(response.isSuccessful()));
    }

    @Test
    public void When_Entering_OutOfRange_Months_A_Failed_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(1, -1, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: months"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 0, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: months"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 481, 100000);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: months"));
    }

    @Test
    public void When_Entering_InRange_Months_A_Successful_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 1, 100000);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 100000);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 480, 100000);
        assertThat(true, is(response.isSuccessful()));
    }

    @Test
    public void When_Entering_OutOfRange_Amount_A_Failed_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, -1);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: amount"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 0);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: amount"));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 2000001);

        assertThat(false, is(response.isSuccessful()));
        Assert.assertTrue(response.getErrorMessage().contains("Invalid argument: amount"));
    }

    @Test
    public void When_Entering_InRange_Amount_A_Successful_Response_Is_Generated() {
        CalculatorServiceResponse response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 1);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 400000);
        assertThat(true, is(response.isSuccessful()));

        response = _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 2000000);
        assertThat(true, is(response.isSuccessful()));
    }
}
