package com.bl.cabinvoicegenerator;

import com.bl.cabinvoicegenerator.model.Ride;
import com.bl.cabinvoicegenerator.service.InvoiceService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceGeneratorTest {
    InvoiceService invoiceService;

    @Before
    public void setUp() {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double totalFare = invoiceService.CalculateFare(distance, time);
        Assert.assertEquals(25.0, totalFare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinFare() {
        invoiceService = new InvoiceService();
        double distance = 0.1;
        int time = 1;
        double totalFare = invoiceService.CalculateFare(distance, time);
        Assert.assertEquals(5.0, totalFare, 0);
    }
    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides={
                new Ride(2.0,5),
                new Ride(0.1,1)
        };
        double totalFare = invoiceService.CalculateFare(rides);
        Assert.assertEquals(30.0, totalFare,0.0);
    }
}