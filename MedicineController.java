package com.emrsys.medmatrix.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.emrsys.medmatrix.object.MedicineOjt;
import com.emrsys.medmatrix.service.MedicineInfoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicineController extends BasePageController {

	private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";

	@Autowired
	private MedicineInfoService medicineInfoService;

	@GetMapping("/newMedicine")
	public ModelAndView showAddMedicineForm(HttpSession session, Model model) {
		model.addAttribute("medicine", new MedicineOjt());
		return createMav("newMedicine", session);
	}

	@PostMapping("/saveMedicineInfo")
	public ModelAndView saveMedicine(@ModelAttribute("medicine") MedicineOjt medicineOjt,
			@RequestParam("picLinkFile") MultipartFile picLinkFile, HttpSession session, Model model) {

		long maxSizeInBytes = 5 * 1024 * 1024; // Limit the size to 5MB

		// Handle file upload
		if (picLinkFile.isEmpty()) {
			model.addAttribute("errorMessage", "薬の写真をアップロードしてください。");
			return createMav("newMedicine", session);
		}

		if (picLinkFile.getSize() > maxSizeInBytes) {
			model.addAttribute("errorMessage", "ファイルサイズが大きすぎます。5MB以下のファイルをアップロードしてください。");
			return createMav("newMedicine", session);
		}

		try {
			// Ensure the upload directory exists
			Path uploadPath = Paths.get(UPLOAD_DIR);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}

			// Save the file to the server
			String fileName = System.currentTimeMillis() + "_" + picLinkFile.getOriginalFilename();
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(picLinkFile.getInputStream(), filePath);

			// Set the file path in the DTO
			medicineOjt.setPicLink("/uploads/" + fileName);

			// Save the medicineOjt using the service
			medicineInfoService.saveMedicine(medicineOjt);

			model.addAttribute("successMessage", "保存されました！");

			return createMav("medicineSaved", session); // show success info after saving

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "アップロードエラーが発生しました。");
			return createMav("newMedicine", session); // Return the form with error message
		}
	}
}
