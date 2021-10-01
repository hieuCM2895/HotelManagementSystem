package util.excelfile;

import model.Hotel;
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

public class ReadExcelHotel {

    private static final int COLUMN_INDEX_ROOMID = 0;
    private static final int COLUMN_INDEX_NAME = 1;
    private static final int COLUMN_INDEX_STARLEVEL = 2;
    private static final int COLUMN_INDEX_ADDRESS = 3;
    private static final int COLUMN_INDEX_CITY = 4;
    private static final int COLUMN_INDEX_DESCRIPTION = 5;

    public static List<Hotel> readExcel(String excelFilePath) throws IOException {
        List<Hotel> listHotels = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(1);

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
            Hotel hotel = new Hotel();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();

                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_ROOMID:
                        hotel.setHotelId(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_NAME:
                        hotel.setHotelName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_STARLEVEL:
                        hotel.setStarLevel(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_ADDRESS:
                        hotel.setAddress((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_CITY:
                        hotel.setCity((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_DESCRIPTION:
                        hotel.setDescription((String) getCellValue(cell));
                        break;

                    default:
                        break;
                }

            }
            listHotels.add(hotel);
        }

        workbook.close();
        inputStream.close();

        return listHotels;
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
