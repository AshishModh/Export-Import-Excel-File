package Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import Helper.ExcelHelper;
import Model.ExcelTemplateVO;
import Service.FileUploadService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {

    FileUploadService uploadService;

    public FileUploadController(FileUploadService uploadService) {
        this.uploadService = uploadService;
    }


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("upload");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView singleFileUpload(@RequestParam("file") MultipartFile file,
                                         RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return new ModelAndView("upload", "message", "Please select a file to upload");
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get("c://Excel//" + file.getOriginalFilename());
            Files.write(path, bytes);

            uploadService.uploadFileData(String.valueOf("c://Excel//" + path.getFileName()));

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message",
                    "Failure occured during upload '" + file.getOriginalFilename() + "'");
            e.printStackTrace();
        }


        return new ModelAndView("upload", "message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
    }


    @RequestMapping("/download")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile() {
        String filename = "Employee.xlsx";
        System.out.println(uploadService.getAllData().size());
        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(uploadService.getAllData());
        InputStreamResource file = new InputStreamResource(in);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);

    }
}