package util.excelfile;

import model.Client;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadExcelClient {

    private static final int COLUMN_INDEX_ID = 0;
    private static final int COLUMN_INDEX_IDCARD = 1;
    private static final int COLUMN_INDEX_FULLNAME = 2;
    private static final int COLUMN_INDEX_ADDRESS = 3;
    private static final int COLUMN_INDEX_TELEPHONE = 4;
    private static final int COLUMN_INDEX_EMAIL = 5;
    private static final int COLUMN_INDEX_NOTE = 6;

    public static List<Client> readExcel(String excelFilePath) throws IOException {
        List<Client> listClients = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Client client = new Client();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();

                Object cellValue = getCellValue(cell);

                if (cellValue == " ") {
                    System.out.println("123");
                    break;
                }
                if (cellValue == null || cellValue.toString().trim().isEmpty() || cellValue.equals(0)) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();

                switch (columnIndex) {
                    case COLUMN_INDEX_ID:
                        client.setClientId(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_IDCARD:
                        client.setIdCard((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_FULLNAME:
                        client.setFullName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ADDRESS:
                        client.setAddress((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_TELEPHONE:
                        client.setTell((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_EMAIL:
                        client.setEmail((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_NOTE:
                        client.setNote((String) getCellValue(cell));
                        break;
                    default:
                        break;
                }

            }
            listClients.add(client);
        }

        workbook.close();
        inputStream.close();

        return listClients;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:

            default:
                break;
        }

        return cellValue;
    }
}
