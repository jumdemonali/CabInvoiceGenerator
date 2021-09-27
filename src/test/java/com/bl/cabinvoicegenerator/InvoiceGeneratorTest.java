package com.bl.cabinvoicegenerator;

import com.bl.cabinvoicegenerator.model.InvoiceSummary;
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
    public void givenMultipleRides_ShouldReturnTotalOfTotalFare() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(5.0, 10),
                new Ride(0.1, 1),
                new Ride(20, 60)
        };
        InvoiceService invoiceService = new InvoiceService();
        double totalFare = invoiceService.calculateTotalFare(rides);
        Assert.assertEquals(260, totalFare, 0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        InvoiceSummary invoiceSummary = invoiceService.CalculateFare(rides);
        InvoiceSummary expectedInvoice = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(invoiceSummary, expectedInvoice);
    }

    @Test
    public void givenUserIdShouldReturnInvoiceSummary() {
        InvoiceSummary invoiceSummary = InvoiceService.getInvoice(3);
        InvoiceSummary expectedInvoiceSummery = new InvoiceSummary(2, 30);
        Assert.assertEquals(expectedInvoiceSummery.getInvoiceSummary(), invoiceSummary.getInvoiceSummary());
   }

@Test
public void givenDistanceAndTimeWhenPremiumShouldReturnTotalFare() {
    double distance = 5.0;
    int time = 12;
    String type = "premium";
    double fare = invoiceService.calculateFare(distance, time, type);
    Assert.assertEquals(99, fare, 0.0);

}

    @Test
    public void givenLessDistanceAndTimeWhenGivenPremiumShouldReturnMinFare() {

        double distance = 1.1;
        int time = 2;
        String type = "premium";
        double fare = invoiceService.calculateFare(distance, time, type);
        Assert.assertEquals(20.5, fare, 0.0);
    }

    @Test
    public void givenMultipleRidesWhenGivenPremiumShouldReturnTotalFare() {

        Ride[] rides = {new Ride(2.0, 5),
                new Ride(0.1, 1)};
        String type = "premium";
        double totalFare = invoiceService.calculateFare(rides, type);
        Assert.assertEquals(60, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRidesWhenGivenPremiumNotInProperFormatShouldReturnFalseInvoiceSummary() {
        String type = "Premium";
        Ride[] rides = {new Ride(3.0, 4),
                new Ride(1.1, 1),
        };
        InvoiceSummary invoiceSummary = invoiceService.calculateTotalFare(rides, type);
        InvoiceSummary expectedInvoices = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoices.getInvoiceSummary(), invoiceSummary.getInvoiceSummary());
    }
}

