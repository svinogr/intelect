package info.upump.demo.model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserXLS {

    public static List<AutoNumber> parseXLS(MultipartFile file) throws OfficeXmlFileException, NullPointerException {
        List<AutoNumber> autoNumbers = new ArrayList<>();

        InputStream in = null;
        HSSFWorkbook wb = null;
        try {
            wb = new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();

            AutoNumber autoNumber = new AutoNumber();

            for (int i = 0; i < 2; i++) {
                Cell cell = row.getCell(i);
                String s = null;
                CellType cellType = cell.getCellType();
                switch (cellType) {
                    case STRING:
                        s = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        s = String.valueOf(cell.getNumericCellValue());
                        break;
                    default:
                        s = "";
                        break;
                }
                if (i == 0) {
                    autoNumber.setNumber(s);
                } else autoNumber.setDescription(s);

            }

            autoNumbers.add(autoNumber);
        }

        return autoNumbers;
    }

}
