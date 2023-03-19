package org.example;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

import java.io.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        // Get template stream (either the default or overridden by the user)
        String fileName = "/home/vlad/Downloads/tmpl.odt";
        InputStream fileInputStream = new FileInputStream(fileName);

        // Prepare the IXDocReport instance based on the template, using Freemarker template engine
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(fileInputStream, TemplateEngineKind.Freemarker);
        Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);

        // instruct XDocReport to inspect InvoiceRow entity as well, which is given as a list and iterated in a table
        FieldsMetadata metadata = report.createFieldsMetadata();
        metadata.load("r", InvoiceRow.class, true);

        Invoice invoice = createStupidInvoice();

        // Add properties to the context
        IContext ctx = report.createContext();
        ctx.put("invoice", invoice);
        ctx.put("to", invoice.getTo());
        ctx.put("sender", invoice.getInvoicer());
        ctx.put("r", invoice.getInvoiceRows());

        String outFile = "/home/vlad/Downloads/raportul-final.pdf";
        OutputStream outputStream = new FileOutputStream(outFile);
        report.convert(ctx, options, outputStream);
    }

    private static Invoice createStupidInvoice() {
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
}