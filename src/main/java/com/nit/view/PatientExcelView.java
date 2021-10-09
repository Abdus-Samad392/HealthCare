package com.nit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nit.entity.Patient;

public class PatientExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment;filename=PATIENTS.xlsx");
		List<Patient> list=(List<Patient>) model.get("patients");
		Sheet sheet=workbook.createSheet("PATIENTSHEET");
		setHead(sheet);
		setData(list,sheet);
		
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		sheet.addMergedRegion(CellRangeAddress.valueOf("I1:J1"));
		row.createCell(0).setCellValue("Patient Name");
		row.createCell(1).setCellValue("Email ID");
		row.createCell(2).setCellValue("Gender");
		row.createCell(3).setCellValue("Date Of Birth");
		row.createCell(4).setCellValue("Marital Status");
		row.createCell(5).setCellValue("Mobile No");
		row.createCell(6).setCellValue("Present Address");
		row.createCell(7).setCellValue("Permanent Address");
		row.createCell(8).setCellValue("Past Medical History");
		
	}

	private void setData(List<Patient> list, Sheet sheet) {
		var rowNum=1;
		var colNum=8;
		sheet.addMergedRegion(CellRangeAddress.valueOf("I2:J2"));
		for(Patient patient:list) {
			Row row=sheet.createRow(rowNum);
			row.createCell(0).setCellValue(patient.getPatientName());
			row.createCell(1).setCellValue(patient.getPatientEmailId());
			row.createCell(2).setCellValue(patient.getPatientGender());
			row.createCell(3).setCellValue(patient.getPatientDOB());
			row.createCell(4).setCellValue(patient.getPatientMaritalStatus());
			row.createCell(5).setCellValue(patient.getPatientMobileNo());
			row.createCell(6).setCellValue(patient.getPatientPresentAddress());
			row.createCell(7).setCellValue(patient.getPatientPermanentAddress());
			
			for(String value:patient.getPatientPastMedicalHistory()) {
				row.createCell(colNum).setCellValue(value);
				colNum++;
			}
			
		}
		
	}

}
