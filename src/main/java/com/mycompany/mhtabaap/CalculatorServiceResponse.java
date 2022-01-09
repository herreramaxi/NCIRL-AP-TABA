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
public class CalculatorServiceResponse {

    private double _value;
    private String _errorMessage;
    private boolean _successful;

    public double getValue() {
        return _value;
    }

    public String getErrorMessage() {
        return _errorMessage;
    }

    public boolean isSuccessful() {
        return _successful;
    }
    
    public CalculatorServiceResponse setAsSuccessful(double value) {
        _value = value;
        _successful = true;
        
        return this;
    }

    public CalculatorServiceResponse setAsFailed(String errorMessage) {
        _errorMessage = errorMessage;
        _successful = false;
        
        return this;
    }
}
