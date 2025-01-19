package org.example;

import java.time.LocalDate;

public class InvoiceProcessor {

    public Invoice createStupidInvoice() {
        Invoice invoice1 = new Invoice();

        invoice1.setInvoiceNumber(123);
        invoice1.setInvoiceDate(LocalDate.now());
        invoice1.setDueDate(LocalDate.now());

        Contact contactTo = new Contact();
        contactTo.setName("contactname");
        contactTo.setAddress1("addressa1");
        contactTo.setAddress2("addr2");
        contactTo.setEmail("con1@gmail.com");
        contactTo.setPhone("0777087695");
        invoice1.setTo(contactTo);

        Invoicer invoiceru = new Invoicer();
        invoiceru.setName("invoicername");
        invoiceru.setEmail("invoiceru@gmail.com");
        invoiceru.setPhone("07708976540");
        invoiceru.setBankAccount("RO1234567890");
        invoiceru.setAddress("Fabbricii 47");
        invoice1.setInvoicer(invoiceru);

        InvoiceRow irow1 = new InvoiceRow();
        irow1.setDescription("bujie");
        irow1.setPrice(23.50);
        irow1.setQuantity(4.0);
        irow1.setUnit("buc");
        invoice1.getInvoiceRows().add(irow1);

        InvoiceRow irow2 = new InvoiceRow();
        irow2.setDescription("ulei de motor");
        irow2.setPrice(21.67);
        irow2.setQuantity(4.5);
        irow2.setUnit("litru");
        invoice1.getInvoiceRows().add(irow2);

        return invoice1;
    }

    public Double calculateTotalPriceForInvoice(Invoice invoice) {
        return invoice.getInvoiceRows()
                .stream()
                .mapToDouble(row -> row.getPrice() * row.getQuantity())
                .sum();
    }

}
