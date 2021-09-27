package com.bl.cabinvoicegenerator.model;

import com.bl.cabinvoicegenerator.service.InvoiceService;

import java.util.Objects;

public class InvoiceSummary {
    int numOfRides;
    double totalFare;
    double avgFare;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numOfRides = numberOfRides;
        this.totalFare = totalFare;
    }
    public InvoiceSummary(int numberOfRides, double totalFare, String type) {
        this.numOfRides = numberOfRides;
        this.totalFare = totalFare;
    }

    public InvoiceSummary getInvoiceSummary() {
        this.avgFare = this.totalFare / this.numOfRides;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numOfRides == that.numOfRides && Double.compare(that.totalFare, totalFare) == 0 && Double.compare(that.avgFare, avgFare) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numOfRides, totalFare, avgFare);
    }

}