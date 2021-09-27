package com.bl.cabinvoicegenerator.service;

import com.bl.cabinvoicegenerator.model.InvoiceSummary;
import com.bl.cabinvoicegenerator.model.Ride;

public class InvoiceService {
    public static final double COST_PER_KM = 10;
    public static final int COST_PER_MIN = 1;
    public static final double MIN_Fare = 5.0;

    public double CalculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_Fare);
    }
    public InvoiceSummary CalculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += CalculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);

    }
}