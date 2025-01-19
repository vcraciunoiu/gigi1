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

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        // Get template stream (either the default or overridden by the user)
        String fileName = "/Users/Vlad.Craciunoiu/Downloads/tmpl.odt";
        InputStream fileInputStream = new FileInputStream(fileName);

        // Prepare the IXDocReport instance based on the template, using Freemarker template engine
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(fileInputStream, TemplateEngineKind.Freemarker);
        Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);

        // instruct XDocReport to inspect InvoiceRow entity as well, which is given as a list and iterated in a table
        FieldsMetadata metadata = report.createFieldsMetadata();
        metadata.load("r", InvoiceRow.class, true);

        InvoiceProcessor invoiceProcessor = new InvoiceProcessor();

        Invoice invoice = invoiceProcessor.createStupidInvoice();
//        Invoice invoice = new Invoice();
//        invoice.setVariabila1("greatest");

        Double myPrice = invoiceProcessor.calculateTotalPriceForInvoice(invoice);
        System.out.printf("Asta este pretu la factura, boss: %f", myPrice);

        // Add properties to the context
        IContext ctx = report.createContext();
        ctx.put("invoice", invoice);
        ctx.put("to", invoice.getTo());
        ctx.put("sender", invoice.getInvoicer());
        ctx.put("r", invoice.getInvoiceRows());

        String outFile = "/Users/Vlad.Craciunoiu/Downloads/raportul-final.pdf";
        OutputStream outputStream = new FileOutputStream(outFile);
        report.convert(ctx, options, outputStream);
    }

}