/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;
import java.io.FileOutputStream;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author A
 */


public class GenerarPdf {
  
  private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.UNDERLINE, BaseColor.BLUE);
  
  private static String file, title;
  private static Object [] [] data;
  private static String [] titleTable;
  
  public GenerarPdf(String name, String title, Object [][] data, String [] titleTabla)
  {
      file= "c:/temp/"+name+".pdf";
      this.title=title;
      this.data=data;
      this.titleTable=titleTabla;
      
      try {
        Document document = new Document(PageSize.LETTER.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        addMetaData(document);
        addContent(document);
        document.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
  }

  private static void addMetaData(Document document) {
    document.addTitle("Graficos PDF");
    document.addAuthor(System.getProperty("user.name"));
    document.addCreator(System.getProperty("user.name"));
  }

  private static void addContent(Document document) throws DocumentException
  {
     Paragraph preface = new Paragraph();
     preface.add(new Paragraph(title, catFont));
     addEmptyLine(preface, 1);
     document.add(preface);
      createTable(document);
  }


  private static  void createTable(Document document) throws BadElementException, DocumentException
  { 
    
    int k=0;
    while(k<titleTable.length)
    {
        int length=10;
        if(titleTable.length-k<length)
            length=titleTable.length-k;
        
        PdfPTable table = new PdfPTable(length);
        for(int i=k; i<k+length; i++)
        {
            PdfPCell c1 = new PdfPCell(new Phrase(titleTable[i]));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
            
        }
    
        table.setHeaderRows(1);
        
        for(int i=0; i<12; i++)
            for(int j=k; j<k+length; j++)
              table.addCell(""+data[i][j]);
        
        
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 4);
        document.add(preface);
        document.add(table);
        document.newPage();
        k+=10;
    }
  }

  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
} 
