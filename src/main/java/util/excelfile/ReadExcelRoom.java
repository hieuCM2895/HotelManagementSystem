package util.excelfile;

import dao.HotelDAOImpl;
import model.Hotel;
import model.Room;
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

public class ReadExcelRoom {

    private static final int COLUMN_INDEX_ROOMID = 0;
    private static final int COLUMN_INDEX_HOTELID = 1;
    private static final int COLUMN_INDEX_FULLNAME = 2;
    private static final int COLUMN_INDEX_TYPE = 3;
    private static final int COLUMN_INDEX_PRICE = 4;
    private static final int COLUMN_INDEX_DESCRIPTION = 5;
    private static final HotelDAOImpl hotelDAO = new HotelDAOImpl();

    public static List<Room> readExcel(String excelFilePath) throws IOException {
        List<Room> listRooms = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(2);

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
            Room room = new Room();
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
                        room.setRoomId(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_HOTELID:
                        Hotel hotel = hotelDAO.findById(Hotel.class, new BigDecimal((double) cellValue).intValue());
                        room.setHotel(hotel);
                        break;
                    case COLUMN_INDEX_FULLNAME:
                        room.setName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_TYPE:
                        room.setType((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PRICE:
                        room.setPrice(new BigDecimal((double) cellValue).intValue());
                        break;
                    case COLUMN_INDEX_DESCRIPTION:
                        room.setDescription((String) getCellValue(cell));
                        break;

                    default:
                        break;
                }

            }
            listRooms.add(room);
        }

        workbook.close();
        inputStream.close();

        return listRooms;
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
