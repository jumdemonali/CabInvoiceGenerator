package com.bl.cabinvoicegenerator.service;

import com.bl.cabinvoicegenerator.model.InvoiceSummary;
import com.bl.cabinvoicegenerator.model.Ride;

import java.util.HashMap;
import java.util.Map;

public class InvoiceService {
    public static final double COST_PER_KM = 10;
    public static final int COST_PER_MIN = 1;
    public static final double MIN_Fare = 5.0;

    public static double CalculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_Fare);
    }

    public double calculateTotalFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare = CalculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;

    }

    public static InvoiceSummary CalculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += CalculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public static InvoiceSummary getInvoice(int userId) {
        Map<Integer, Ride[]> map = new HashMap<>();
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        Ride[] ride1 = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        map.put(3, rides);
        map.put(1, ride1);
        for (Map.Entry<Integer, Ride[]> entry : map.entrySet()) {
            if (userId == entry.getKey()) {
                Ride[] ridesArray = entry.getValue();
                return CalculateFare(ridesArray);
            }
        }
        return null;
    }
}