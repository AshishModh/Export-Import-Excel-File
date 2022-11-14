package Helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import Model.ExcelTemplateVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"employeeId", "employeeName", "address", "country"};
    static String SHEET = "Employee";

    public static ByteArrayInputStream tutorialsToExcel(List<ExcelTemplateVO> excelTemplateVOS) {

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream();)
        {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++)
            {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ExcelTemplateVO excelTemplateVO : excelTemplateVOS)
            {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(excelTemplateVO.getEmployeeId());
                row.createCell(1).setCellValue(excelTemplateVO.getEmployeeName());
                row.createCell(2).setCellValue(excelTemplateVO.getAddress());
                row.createCell(3).setCellValue(excelTemplateVO.getCountry());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
