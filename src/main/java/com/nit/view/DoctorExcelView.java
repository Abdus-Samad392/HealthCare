package com.nit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nit.entity.Doctor;

public class DoctorExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Doctor> list=(List<Doctor>) model.get("doctors");
		response.setHeader("Content-Disposition", "attachment;filename=DOCTORS.xlsx");
		Sheet sheet=workbook.createSheet("Doctor");
		
		setHead(sheet);
		setData(sheet,list);
	}

	private void setData(Sheet sheet, List<Doctor> list) {
		var rowNum=1;
		for(Doctor doctor:list) {
			Row row=sheet.createRow(rowNum);
			row.createCell(0).setCellValue(doctor.getDocName());
			row.createCell(1).setCellValue(doctor.getDocEmailId());
			row.createCell(2).setCellValue(doctor.getDocSpecialization().getSpecName());
			row.createCell(3).setCellValue(doctor.getDocAddress());
			row.createCell(4).setCellValue(doctor.getDocMobileNo());
			row.createCell(5).setCellValue(doctor.getDocGender());
			row.createCell(6).setCellValue(doctor.getDocNote());
			row.createCell(7).setCellValue(doctor.getDocPhotoLoc());
			rowNum++;
		}
		
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Doctor Name");
		row.createCell(1).setCellValue("Email ID");
		row.createCell(2).setCellValue("Specialization");
		row.createCell(3).setCellValue("Address");
		row.createCell(4).setCellValue("Mobile No");
		row.createCell(5).setCellValue("Gender");
		row.createCell(6).setCellValue("Note");
		row.createCell(7).setCellValue("Photo");
	}
	
	
}
