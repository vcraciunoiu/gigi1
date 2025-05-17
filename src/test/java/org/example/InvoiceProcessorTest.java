package org.example;

import org.example.model.Invoice;
import org.example.model.InvoiceRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class InvoiceProcessorTest {

    /**
     * This class tests the calculateTotalPriceForInvoice method in the InvoiceProcessor class.
     * <p>
     * The calculateTotalPriceForInvoice method computes the total price of an invoice
     * by summing up the product of price and quantity for every invoice row.
     */

    @Test
    public void calculateTotalPriceForInvoice_WithMultipleItems() {
        // Arrange
        InvoiceProcessor processor = new InvoiceProcessor();
        Invoice invoice = new Invoice();
        List<InvoiceRow> rows = new ArrayList<>();

        InvoiceRow row1 = new InvoiceRow();
        row1.setDescription("Item 1");
        row1.setPrice(10.0);
        row1.setQuantity(2.0);
        rows.add(row1);

        InvoiceRow row2 = new InvoiceRow();
        row2.setDescription("Item 2");
        row2.setPrice(15.5);
        row2.setQuantity(3.0);
        rows.add(row2);

        invoice.setInvoiceRows(rows);

        // Act
        Double totalPrice = processor.calculateTotalPriceForInvoice(invoice);

        // Assert
        assertEquals(56.5, totalPrice);
    }

    @Test
    public void testCalculateTotalPriceForInvoice_WithSingleItem() {
        // Arrange
        InvoiceProcessor processor = new InvoiceProcessor();
        Invoice invoice = new Invoice();
        List<InvoiceRow> rows = new ArrayList<>();

        InvoiceRow row1 = new InvoiceRow();
        row1.setDescription("Item 1");
        row1.setPrice(20.0);
        row1.setQuantity(5.0);
        rows.add(row1);

        invoice.setInvoiceRows(rows);

        // Act
        Double totalPrice = processor.calculateTotalPriceForInvoice(invoice);

        // Assert
        assertEquals(100.0, totalPrice);
    }

    @Test
    public void testCalculateTotalPriceForInvoice_WithEmptyInvoice() {
        // Arrange
        InvoiceProcessor processor = new InvoiceProcessor();
        Invoice invoice = new Invoice();
        invoice.setInvoiceRows(new ArrayList<>());

        // Act
        Double totalPrice = processor.calculateTotalPriceForInvoice(invoice);

        // Assert
        assertEquals(0.0, totalPrice);
    }

    @Test
    public void testCalculateTotalPriceForInvoice_WithZeroQuantity() {
        // Arrange
        InvoiceProcessor processor = new InvoiceProcessor();
        Invoice invoice = new Invoice();
        List<InvoiceRow> rows = new ArrayList<>();

        InvoiceRow row1 = new InvoiceRow();
        row1.setDescription("Item 1");
        row1.setPrice(50.0);
        row1.setQuantity(0.0);
        rows.add(row1);

        invoice.setInvoiceRows(rows);

        // Act
        Double totalPrice = processor.calculateTotalPriceForInvoice(invoice);

        // Assert
        assertEquals(0.0, totalPrice);
    }

    @Test
    public void testCalculateTotalPriceForInvoice_WithNegativePrice() {
        // Arrange
        InvoiceProcessor processor = new InvoiceProcessor();
        Invoice invoice = new Invoice();
        List<InvoiceRow> rows = new ArrayList<>();

        InvoiceRow row1 = new InvoiceRow();
        row1.setDescription("Faulty Item");
        row1.setPrice(-15.0);
        row1.setQuantity(2.0);
        rows.add(row1);

        invoice.setInvoiceRows(rows);

        // Act
        Double totalPrice = processor.calculateTotalPriceForInvoice(invoice);

        // Assert
        assertEquals(-30.0, totalPrice);
    }

}