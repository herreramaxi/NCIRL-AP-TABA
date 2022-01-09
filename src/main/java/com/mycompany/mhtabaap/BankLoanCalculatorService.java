/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mhtabaap;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maximiliano Herrera
 */
public class BankLoanCalculatorService {

    private static BankLoanCalculatorService _instance;

    public static BankLoanCalculatorService getInstance() {
        if (_instance == null) {
            _instance = new BankLoanCalculatorService();
        }

        return _instance;
    }

    private BankLoanCalculatorService() {
    }

    public CalculatorServiceResponse calculateMonthlyPayment(double anualIR, int months, double amount) {
        CalculatorServiceResponse response = new CalculatorServiceResponse();

        //Arbitrary validations
        if (anualIR <= 0 || anualIR > 100)
            return response.setAsFailed("Invalid argument: anualIR should be greater than 0 and less than 100");
        if (months <= 0 || months > 480)
            return response.setAsFailed("Invalid argument: months should be greater than 0 and less than 480");
        if (amount <= 0 || amount > 2000000)
            return response.setAsFailed("Invalid argument: amount should be greater than 0 and less than 2,000,000");

        try {
            double monthlyIR = anualIR / 100 / 12;
            double discountFactor = (Math.pow(1 + monthlyIR, months) - 1) / (monthlyIR * Math.pow(1 + monthlyIR, months));
            double monthlyPayment = amount / discountFactor;

            return response.setAsSuccessful(monthlyPayment);
        } catch (Exception exception) {
            Logger.getLogger(BankLoanCalculatorService.class.getName()).log(Level.SEVERE, null, exception);
            response.setAsFailed("Unknown error: " + exception.getMessage());
        }

        return response;
    }

    public CalculatorServiceResponse calculateTotalRepayment(double monthlyPayment, int months) {
        CalculatorServiceResponse response = new CalculatorServiceResponse();

        if (monthlyPayment <= 0)
            return response.setAsFailed("Invalid argument: monthlyPayment should be greater than 0");
        if (months <= 0)
            return response.setAsFailed("Invalid argument: months should be greater than 0");

        return response.setAsSuccessful(monthlyPayment * months);
    }
}
