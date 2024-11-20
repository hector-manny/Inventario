package com.example.ApiQuotations.util;


import java.math.BigDecimal;

public class CalculationHelper {
    public static BigDecimal calculatePercentage(BigDecimal base, BigDecimal percentage) {
        return base.multiply(percentage).divide(new BigDecimal(100));
    }
}

