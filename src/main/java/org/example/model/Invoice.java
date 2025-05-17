package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    public static enum Status {
        InProgress, Sent, Received
    }


    private String variabila1;
    public void setVariabila1(String variabila1) {
        this.variabila1 = variabila1;
    }

    private Integer invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private Contact to;
    private Invoicer invoicer;
//    private User lastEditor;
//    private LocalDate lastEdited;

    private List<InvoiceRow> invoiceRows = new ArrayList<>();


    public Integer getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(Integer invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public List<InvoiceRow> getInvoiceRows() {
        return invoiceRows;
    }

    public void setInvoiceRows(List<InvoiceRow> invoiceRows) {
        this.invoiceRows = invoiceRows;
    }

    public Double getTotal() {
        double sum = 0;
        List<InvoiceRow> invoiceRows1 = getInvoiceRows();
        for (InvoiceRow r : invoiceRows1) {
            sum += r.getQuantity() * r.getPrice();
        }
        return sum;
    }

    public String getReference() {
        int invoicenr = getInvoiceNumber();
        int checksum = 0;
        int counter = 0;
        int[] multipliers = {7, 3, 1};
        while (invoicenr > 0) {
            int d = invoicenr % 10;
            invoicenr = invoicenr / 10;
            checksum += d * multipliers[counter % multipliers.length];
            counter++;
        }
        checksum = checksum % 10;
        checksum = 10 - checksum;
        checksum = checksum % 10;
        String ref = "" + getInvoiceNumber() + checksum;
        return ref;
    }

    public Invoicer getInvoicer() {
        return invoicer;
    }

    public void setInvoicer(Invoicer invoicer) {
        this.invoicer = invoicer;
    }

    public Contact getTo() {
        return to;
    }

    public void setTo(Contact to) {
        this.to = to;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
