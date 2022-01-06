/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mhtabaap;

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

    public double calculateMonthlyPayment(double anualIR, int months, double amount) throws CalculationCustomException {
        if (anualIR <= 0)
            throw new CalculationCustomException("Invalid argument: anualIR should be greater than 0");
        if (months <= 0)
            throw new CalculationCustomException("Invalid argument: months should be greater than 0");
        if (amount <= 0)
            throw new CalculationCustomException("Invalid argument: amount should be greater than 0");

        double monthlyIR = anualIR / 100 / 12;

        double discountFactor = (Math.pow(1 + monthlyIR, months) - 1) / (monthlyIR * Math.pow(1 + monthlyIR, months));
        double monthlyPayment = amount / discountFactor;

        return monthlyPayment;
    }

    public double calculateTotalRepayment(double monthlyPayment, int months) {
        return monthlyPayment * months;
    }
}
