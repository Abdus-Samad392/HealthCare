package com.nit.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nit.entity.Specialization;

public class SpecializationPDFView extends AbstractPdfView {
	
	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		HeaderFooter header=new HeaderFooter(new Phrase("SPECIALIZATION DATA"), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		HeaderFooter footer=new HeaderFooter(new Phrase("@CopyRight "+new Date()), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=SPECS.pdf");
		List<Specialization> list=(List<Specialization>) model.get("specs");
		
		Font font1=new Font(Font.TIMES_ROMAN,12,Font.BOLD,Color.BLUE);
		PdfPTable table=new PdfPTable(3);
		table.addCell(new Phrase("Name",font1));
		table.addCell(new Phrase("Code",font1));
		table.addCell(new Phrase("Note",font1));
		
		for(Specialization spec:list) {
			table.addCell(spec.getSpecName());
			table.addCell(spec.getSpecCode());
			table.addCell(spec.getSpecNote());
		}
		
		document.add(table);
	}

}
