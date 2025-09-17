package vn.khoa.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UploadUntils {
	public static String processUpload(String fieldName, HttpServletRequest req, String storeFolder,
			String storeFilename) throws Exception {
		Part filePart = req.getPart(fieldName);
		if (filePart == null || filePart.getSize() == 0) {
			return "";
		}

		if (storeFolder == null) {
			storeFolder = "E:\\uploads1";
		}

		if (storeFilename == null) {
			storeFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		} else {
			storeFilename += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).toString());
		}

		Path uploadPath = Paths.get(storeFolder);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		filePart.write(Paths.get(uploadPath.toString(), storeFilename).toString());
		return storeFilename;
	}

	public static String processUploadFolderWeb(String fieldName, HttpServletRequest req, String storeFolder,
			String storeFilename) throws Exception {
		Part filePart = req.getPart(fieldName);
		if (filePart == null || filePart.getSize() == 0) {
			return "";
		}

		if (storeFolder == null) {
			storeFolder = "/uploads";
		}

		if (storeFilename == null) {
			storeFilename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
		} else {
			storeFilename += "." + FilenameUtils.getExtension(Paths.get(filePart.getSubmittedFileName()).toString());
		}

		// lấy đường dẫn trong web
		String uploadFolder = req.getServletContext().getRealPath(storeFolder);
		Path uploadPath = Paths.get(uploadFolder);
		// hết lấy đường dẫn trong web

		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}

		filePart.write(Paths.get(uploadPath.toString(), storeFilename).toString());
		return storeFilename;
	}
}
