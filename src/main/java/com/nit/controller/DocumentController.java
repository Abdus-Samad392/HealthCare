package com.nit.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nit.entity.Document;
import com.nit.service.IDocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private IDocumentService service;
	
	@GetMapping("/show")
	public String launchDocumentUploadPage(Model model) {
		model.addAttribute("documents", service.getDocumentIdAndName());
		return "DocumentUpload";
	}
	
	@PostMapping("/upload")
	public String saveDocument(@RequestParam MultipartFile docName) {
		try {
		Document doc=new Document();
		doc.setDocumentName(docName.getOriginalFilename());
		doc.setDocumentData(docName.getBytes());
		service.saveDocumentData(doc);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:show";
	}
	
	@GetMapping("/download")
	public String downloadDocument(@RequestParam Long id,HttpServletResponse res) {
		Document doc=service.getDocumentById(id);
		res.addHeader("Content-Disposition", "attachment;filename="+doc.getDocumentName());
		try {
		FileCopyUtils.copy(doc.getDocumentData(), res.getOutputStream());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	@GetMapping("/delete")
	public String deleteDocument(@RequestParam Long id) {
		service.deleteDocumentById(id);
		return "redirect:show";
	}
}
