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
  
  private static final Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK);
  private static final Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD, BaseColor.BLACK);
  private static final Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 13);
  
  private static String file, title;
  
  public GenerarPdf(String name, String title)
  {
      file= "c:/temp/"+name+".pdf";
      this.title=title;
  }   
   
  public void createTable(Object [] [] data, String [] titleTable)
  {
      try 
      {
          Document document = new Document(PageSize.LETTER.rotate());
          PdfWriter.getInstance(document, new FileOutputStream(file));
          document.open();
          addMetaData(document);
          addTittle(document);
          createTable(document, data, titleTable);
          document.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
  }
  
  public void betterOption(String [] rooms, String [] price, String [] revenue)
  {
      try 
      {
          Document document = new Document(PageSize.LETTER);
          PdfWriter.getInstance(document, new FileOutputStream(file));
          document.open();
          addMetaData(document);
          addTittle(document);
          
          Paragraph content= new Paragraph();
          
          //content.add(new Paragraph("La mejor opcion es: "+ option, font3));          
          //addEmptyLine(content, 1);
          
          content.add(new Paragraph(" Mejor cantidad de habitaciones por tipo: ",font2));
          addEmptyLine(content, 1);
          content.add(new Paragraph("   Simple: " + rooms[0], font3));
          content.add(new Paragraph("   Doble: " + rooms[1], font3));
          content.add(new Paragraph("   Suite Jr.: "+ rooms[2], font3));
          addEmptyLine(content, 1);
          
          content.add(new Paragraph(" Mejor precio de las habitaciones por tipo: ",font2));
          addEmptyLine(content, 1);
          content.add(new Paragraph("   Simple: " + price[0], font3));
          content.add(new Paragraph("   Doble: " + price[1], font3));
          content.add(new Paragraph("   Suite Jr.: "+ price[2], font3));
          addEmptyLine(content, 1);
          
          content.add(new Paragraph(" Ingresos por tipo de habitaciones: ",font2));
          addEmptyLine(content, 1);
          content.add(new Paragraph("   Simple: " + revenue[0], font3));
          content.add(new Paragraph("   Doble: " + revenue[1], font3));
          content.add(new Paragraph("   Suite Jr.: "+ revenue[2], font3));
          
          document.add(content);
          document.close();
         
      } catch (Exception e) {
         e.printStackTrace();
      }
  }
  

  private static void addMetaData(Document document) {
    document.addTitle("PDF");
    document.addAuthor(System.getProperty("user.name"));
    document.addCreator(System.getProperty("user.name"));
  }

  private static void addTittle(Document document) throws DocumentException
  {
     Paragraph preface = new Paragraph();
     preface.add(new Paragraph(title, font1));
     addEmptyLine(preface, 1);
     document.add(preface);
  }
  
  private static  void createTable(Document document, Object [] [] data, String [] titleTable) throws BadElementException, DocumentException
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
