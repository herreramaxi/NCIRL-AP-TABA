/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.mhtabaap.BankLoanCalculatorService;
import com.mycompany.mhtabaap.CalculationCustomException;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
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
    public void TestCorrectMonthlyPaymentCalculation() throws CalculationCustomException {
        double monthlyPayment = _bankLoanCalculatorService.calculateMonthlyPayment(6, 360, 100000);
        assertThat("599.55", is(String.format("%.2f", monthlyPayment)));
    }

    @Test
    public void TestCorrectTotalRepaymentCalculation() throws CalculationCustomException {
        double monthlyPayment = _bankLoanCalculatorService.calculateMonthlyPayment(6, 360, 100000);
        assertThat("215838.19", is(String.format("%.2f", monthlyPayment * 360)));
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Zero_On_AnualIR_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(0, 360, 100000);
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Negative_Value_On_AnualIR_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(-1, 360, 100000);
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Zero_On_Months_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(1, 0, 100000);
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Negative_Value_On_Months_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(1, -1, 100000);
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Zero_On_Amount_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, 0);
    }

    @Test(expected = CalculationCustomException.class)
    public void When_Entering_Negative_Value_On_Amount_An_Exception_Is_Thrown() throws CalculationCustomException {
        _bankLoanCalculatorService.calculateMonthlyPayment(1, 360, -1);
    }
}
