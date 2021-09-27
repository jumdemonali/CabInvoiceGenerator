package com.bl.cabinvoicegenerator.service;

public class InvoiceService {
    public static final double COST_PER_KM = 10;
    public static final int COST_PER_MIN = 1;
    public static final double MIN_Fare = 5.0;

    public double CalculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_Fare);
    }
}
