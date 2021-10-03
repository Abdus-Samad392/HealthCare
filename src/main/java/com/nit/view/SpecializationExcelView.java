package com.nit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nit.entity.Specialization;

public class SpecializationExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=SPECS.xlsx");
		List<Specialization> list=(List<Specialization>) model.get("specs");
		Sheet sheet=workbook.createSheet("Specialization");
		
		setHead(sheet);
		setData(sheet,list);
	}

	private void setData(Sheet sheet, List<Specialization> list) {
		int rowNum=1;
		for(Specialization spec:list) {
			Row row=sheet.createRow(rowNum);
			row.createCell(0).setCellValue(spec.getSpecCode());
			row.createCell(1).setCellValue(spec.getSpecName());
			row.createCell(2).setCellValue(spec.getSpecNote());
			rowNum++;
		}
		
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Specialization Code");
		row.createCell(1).setCellValue("Specialization Name");
		row.createCell(2).setCellValue("Specialization Note");
	}
}
