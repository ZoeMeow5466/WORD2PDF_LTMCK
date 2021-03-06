package model.bo;

import java.io.*;
import java.sql.Timestamp;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class WORD2PDF {
    static model.dao.Data data = new model.dao.Data();

    public static void ConvertToPDF(model.bean.WORD2PDF word) {
        try {
            // Set to coverting...
            // https://www.tabnine.com/code/java/methods/java.sql.PreparedStatement/setDate
            data.setStatusResult(word.getSourcePath(), new Timestamp(System.currentTimeMillis()), 1);

            // TimeUnit.SECONDS.sleep(10);

            // Working...
            // https://rdtschools.com/how-to-covert-docx-file-to-pdf-using-apache-poi-library-in-java/
            String inputFile = word.getSourcePath();
            String outputFile = word.getTargetPath();
            
            InputStream docFile = new FileInputStream(new File(inputFile));
    		XWPFDocument doc = new XWPFDocument(docFile);
    		PdfOptions pdfOptions = PdfOptions.create();
    		OutputStream out = new FileOutputStream(new File(outputFile));
    		PdfConverter.getInstance().convert(doc, out, pdfOptions);
    		doc.close();
    		out.close();

            // Set to successful
            System.out.println("Successful--------------------------------");
            data.setStatusResult(word.getSourcePath(), new Timestamp(System.currentTimeMillis()), 2);
        }
        catch (Exception ex) {
            // Failed
            System.out.println(ex);
            System.out.println("Failed--------------------------------");
            data.setStatusResult(word.getSourcePath(), new Timestamp(System.currentTimeMillis()), -1);
        }
    }
}
