package com.nit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.nit.entity.Appointment;

public class AppointmentExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.addHeader("Content-Disposition", "attachment;filename=Appointments.xlsx");
		List<Appointment> list=(List<Appointment>) model.get("appointments");
		Sheet sheet=workbook.createSheet("AppointmentSheet");
		setHead(sheet);
		setData(sheet,list);
	}

	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Doctor");
		row.createCell(1).setCellValue("Date");
		row.createCell(2).setCellValue("No Of Slots");
		row.createCell(3).setCellValue("Appointment Details");
		row.createCell(4).setCellValue("Consultation Fee");
		
		
	}

	private void setData(Sheet sheet, List<Appointment> list) {
		var rowNum=1;
		for(Appointment app:list) {
			Row row=sheet.createRow(rowNum);
			row.createCell(0).setCellValue(app.getAppointmentWithdoctor().getDocName());
			row.createCell(1).setCellValue(app.getAppointmentDate());
			row.createCell(2).setCellValue(app.getNoOfSlots());
			row.createCell(3).setCellValue(app.getAppointmentDetails());
			row.createCell(4).setCellValue(app.getConsultationFee());
			rowNum++;
		}
		
	}

}
