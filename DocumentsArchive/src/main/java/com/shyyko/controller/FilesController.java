package com.shyyko.controller;

import com.shyyko.entity.FileEntity;
import com.shyyko.model.form.FileUploadForm;
import com.shyyko.service.FileService;
import com.shyyko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller()
public class FilesController {
	@Autowired
	private UserService userService;
	@Autowired
	private FileService fileService;

	@RequestMapping(value = "files", method = RequestMethod.GET)
	public String files(ModelMap map, @RequestParam(value = "notes", required = false) String notes,
						@RequestParam(value = "filename", required = false) String filename) {
		if (notes != null && !notes.isEmpty()) {
			List<FileEntity> list = fileService.getFilesByNotesWithoutData(notes);
			map.addAttribute("files", list);
		} else if (filename != null && !filename.isEmpty()) {
			List<FileEntity> list = fileService.getFilesByFilenameWithoutData(filename);
			map.addAttribute("files", list);
		} else {
			map.addAttribute("files", fileService.getAllFindEntitiesWithoutData());
		}
		return "files";
	}

	/**
	 * upload
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUploadForm fileUploadForm) throws IOException {
		MultipartFile file = fileUploadForm.getFile();
		FileEntity fileEntity = new FileEntity();
		fileEntity.setFilename(file.getOriginalFilename());
		fileEntity.setNotes(fileUploadForm.getNotes());
		fileEntity.setType(file.getContentType());
		fileEntity.setFile(file.getBytes());

		fileService.save(fileEntity);

		return "redirect:/files";
	}


	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/download")
	public String download(@RequestParam("id")
						   Integer documentId, HttpServletResponse response) throws IOException {

		FileEntity fileEntity = fileService.getFileById(documentId);
		response.setHeader("Content-Disposition", "inline;filename=\"" + fileEntity.getFilename() + "\"");
		OutputStream out = response.getOutputStream();
		response.setContentType(fileEntity.getType());
		FileCopyUtils.copy(fileEntity.getFile(), out);
		out.flush();
		out.close();

		return null;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/delete")
	public String delete(@RequestParam("id")
						 Integer documentId, HttpServletResponse response) throws IOException {
		fileService.delete(documentId);
		return "redirect:/files";
	}
}