package com.healthinsurence.serviceimplemention;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.healthinsurence.model.HealthInsurenceModel;
import com.healthinsurence.model.PaymentModel;
import com.healthinsurence.model.RelationModel;
import com.healthinsurence.repo.HealthInsurenceRepository;
import com.healthinsurence.repo.PaymentRepo;
import com.healthinsurence.repo.RelationRepository;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class InVoiceServiceImpl {

    private final HealthInsurenceRepository signupRepository;
    private final PaymentRepo paymentRepo;
    private final RelationRepository relationRepo;
   
    private Optional<PaymentModel> paymentdetails;
    private Optional<HealthInsurenceModel> signupDetails;
    private List<RelationModel> relationDetails;


    public InVoiceServiceImpl(HealthInsurenceRepository signupRepository, PaymentRepo paymentRepo,RelationRepository relationRepo,
            Optional<PaymentModel> paymentetails, Optional<HealthInsurenceModel>  signupDetails, List<RelationModel> relationDetails) {
        this.signupRepository = signupRepository;
        this.paymentRepo = paymentRepo;
        this.paymentdetails = paymentetails;
        this.signupDetails = signupDetails;
		this.relationRepo = relationRepo;
    }

    public Optional<PaymentModel> getPaymentetails() {
        return paymentdetails;
    }

    public Optional<HealthInsurenceModel>  getSignupDetails() {
        return signupDetails;
    }
    
    public List<RelationModel>  getRelationDetails() {
        return relationDetails;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(new Color(253, 240, 213));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(new Color(31, 53, 65));

        cell.setPhrase(new Phrase("Insurance Type", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sum Assured ", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("No.of years", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Premium Amount", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Start Date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Renewal Date", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
  


        cell.setPhrase(new Phrase(paymentdetails.get().getInsuranceType()));
    	table.addCell(cell);

    		// table.addCell(quoteDetails.get(0).getPremium().toString());

            cell.setPhrase(new Phrase(String.valueOf(paymentdetails.get().getSumAssuredAmount())));
            table.addCell(cell);
      
    	    cell.setPhrase(new Phrase(paymentdetails.get().getYear()));
    		table.addCell(cell);
    	
        	cell.setPhrase(new Phrase(String.valueOf(paymentdetails.get().getPremiumAmount())));
            table.addCell(cell);
    	
            cell.setPhrase(new Phrase(paymentdetails.get().getStartDate()+""));
    		table.addCell(cell);

            cell.setPhrase(new Phrase(paymentdetails.get().getEndDate()+""));
            table.addCell(cell);
    }
   
    public void export(HttpServletResponse response, String paymentId) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

     
      
         this.paymentdetails = paymentRepo.findByPaymentId(paymentId);
         this.signupDetails = signupRepository.findByCustomerId(paymentdetails.get().getCustomerId());
         this.relationDetails = relationRepo.findByPaymentId(paymentId);
        // this.fullDetails = fullDetailRepository.findByPaymentId(paymentId);
        // this.signupDetails=signupRepository.findByCustomerId(paymentetails.getCustomerId()+"");
    


        try {
            // Load image from resources folder
        	
 
            Image image = Image.getInstance("D:/PicturesFolder/img.jpeg");
//            image.scalePercent(8.0f, 8.0f);
            image.scaleToFit(100f, 100f);
            document.add(image);

            // Add header
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            headerFont.setSize(25);
            headerFont.setColor(new Color(120, 0, 0));
            Paragraph pHeader = new Paragraph("RS Insurance Pvt Ltd. \n", headerFont);
            pHeader.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(pHeader);

            // Add bank details
            Font fontP = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontP.setSize(18);
            fontP.setColor(new Color(120, 0, 0));
            Chunk glue = new Chunk(new VerticalPositionMark());
            Paragraph pp = new Paragraph("\nCompany Details", fontP);
            pp.add(new Chunk(glue));
            pp.add("Customer Details");
            document.add(pp);

            Font fontN = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontN.setSize(12);
            fontN.setColor(Color.BLACK);
            Paragraph pN = new Paragraph("Company Name: RS Insurance pvt ltd.");
            pN.add(new Chunk(glue));

            pN.add("CustomerID: "+paymentdetails.get().getCustomerId());
           
            document.add(pN);

            Font fontA = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontA.setSize(12);
            fontA.setColor(Color.BLACK);
            Paragraph pA = new Paragraph("Agency No: 10012");
            pA.add(new Chunk(glue));
            pA.add("Mobile Number: "+signupDetails.get().getMobileNo());
           
            document.add(pA);

            Font fontC = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontC.setSize(12);
            fontC.setColor(Color.BLACK);
            Paragraph pC = new Paragraph("Email : accounts@ramanasoft.com", fontC);
            pC.add(new Chunk(glue));
            pC.add("Email: " + signupDetails.get().getEmail());
            document.add(pC);

          

            // Add transaction section header
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            font.setSize(22);
            font.setColor(new Color(120, 0, 0));
            Paragraph p1 = new Paragraph("\n Invoice\n  ", font);
            p1.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(p1);

            Font fontName = FontFactory.getFont(FontFactory.HELVETICA);
            fontName.setSize(15);
            fontName.setColor(Color.BLACK);
            
    // Retrieve the name
    String name = signupDetails.get().getFullName()+", ";

    // Define fonts
    Font normalFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.NORMAL);
    Font boldFont = FontFactory.getFont(FontFactory.TIMES, 15, Font.BOLD);

    // Create chunks for the parts of the paragraph
    Chunk chunk1 = new Chunk("Dear ", normalFont);
    Chunk chunk2 = new Chunk(name, boldFont);
    // Chunk chunk3 = new Chunk(", invoice Details as follows: ", normalFont);
    // Chunk chunk3 = new Chunk(", invoice Details as follows: ", normalFont);
    Chunk chunk3 = new Chunk("PolicyID: RSHI"+paymentdetails.get().getId(), normalFont);

    // Combine chunks into a paragraph
    Paragraph p = new Paragraph();
    p.add(chunk1);
    p.add(chunk2);
    p.add(new Chunk(glue));
    p.add(chunk3);

    // Add the paragraph to the document
    document.add(p);
    Paragraph ps = new Paragraph("\n");
    document.add(ps);


            // Add statement range
        //    Font fontStatementRange = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        //    fontStatementRange.setSize(14);
        //    fontStatementRange.setColor(new Color(120, 0, 0));
        //    Paragraph pStatementRange = new Paragraph("PolicyID: RSPI"+quoteDetails.get(0).getId(), fontStatementRange);
        //    pStatementRange.setAlignment(Paragraph.ALIGN_LEFT);
        //    document.add(pStatementRange);

            // Add table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setWidths(new float[]{2.0f, 2.0f, 1.0f, 3.6f, 2.2f, 2.2f});
            table.setSpacingBefore(10);

            writeTableHeader(table);
            writeTableData(table);

            document.add(table);

            // Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            // footerFont.setSize(12);
            // footerFont.setColor(new Color(120, 0, 0));
            // Paragraph fHeader = new Paragraph("\n \n \n Thank you for choosing Rs Insurance.If any querys feel free to Contact us at support@ramanasoft.com or 1800-258-2465. \n");
            // fHeader.setAlignment(Paragraph.ALIGN_CENTER);
            // document.add(fHeader);
            
            if (!relationDetails.isEmpty()) {
            	
            	Font fontP1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                fontP1.setSize(18);
                fontP1.setColor(new Color(120, 0, 0));
                Chunk glue1 = new Chunk(new VerticalPositionMark());
                Paragraph pp1 = new Paragraph("\nInsured Members :", fontP1);
                pp1.add(new Chunk(glue1));
//                pp1.add("");
                document.add(pp1);
               
             // Create a PdfPTable with 3 columns: Relation Name, Age, Disease
                PdfPTable relationTable = new PdfPTable(3);
                relationTable.setWidthPercentage(100f);
                relationTable.setSpacingBefore(10);

                // Add table header
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(new Color(253, 240, 213));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setPadding(5);

                Font headerFont1 = FontFactory.getFont(FontFactory.HELVETICA);
                headerFont1.setColor(new Color(31, 53, 65));

                cell.setPhrase(new Phrase("Relation Name", headerFont1));
                relationTable.addCell(cell);

                cell.setPhrase(new Phrase("Age", headerFont1));
                relationTable.addCell(cell);

                cell.setPhrase(new Phrase("Disease", headerFont1));
                relationTable.addCell(cell);

                // Loop through the relation details and add data for each relation in the same row
                for (RelationModel relation : relationDetails) {
                    // Add Relation Name
                    PdfPCell nameCell = new PdfPCell(new Phrase(relation.getRelationPersonName()));
                    nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    relationTable.addCell(nameCell);

                    // Add Age
                    PdfPCell ageCell = new PdfPCell(new Phrase(String.valueOf(relation.getAgeOfTheRelation())));
                    ageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    relationTable.addCell(ageCell);
                    
                    // Add Disease
                    PdfPCell diseaseCell = new PdfPCell(new Phrase(String.valueOf(relation.getDisease())));
                    diseaseCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    relationTable.addCell(diseaseCell);
                }

                // Add the relation table to the document
                document.add(relationTable);

            }
            
            Font fontP11 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontP11.setSize(18);
            fontP11.setColor(new Color(120, 0, 0));
            Chunk glue11 = new Chunk(new VerticalPositionMark());
            Paragraph pp11 = new Paragraph("\n", fontP11);
            pp11.add(new Chunk(glue11));
            pp11.add("Best Regards");
            document.add(pp11);

            Font fontN1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontN1.setSize(12);
            fontN1.setColor(Color.BLACK);
            Paragraph pN1 = new Paragraph("");
            pN1.add(new Chunk(glue11));
            pN1.add("Rs Insurance pvt ltd");
            document.add(pN1);

            Font fontA1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontA1.setSize(12);
            fontA1.setColor(Color.BLACK);
            Paragraph pA1 = new Paragraph("");
            pA1.add(new Chunk(glue11));
            pA1.add("Madhapur, Hyderbad");
            document.add(pA1);

            Font fontC1= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontC1.setSize(12);
            fontC1.setColor(Color.BLACK);
            Paragraph pC1 = new Paragraph("");
            pC1.add(new Chunk(glue11));
            pC1.add("India, 500081\n");
            document.add(pC1);
            
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            footerFont.setSize(12);
            footerFont.setColor(new Color(120, 0, 0));
            Paragraph fHeader = new Paragraph("\n Thank you for choosing Rs Insurance.If any queries feel free to Contact us at support@ramanasoft.com or 1800-258-2465. \n");
            fHeader.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(fHeader);
            }catch (Exception e) {
                System.out.println("Error while generating PDF: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (document != null) {
                    document.close();
                }
                
            }
      }
      
}