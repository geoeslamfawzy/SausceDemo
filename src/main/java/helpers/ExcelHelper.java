package helpers;

import constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelHelper {
    static String fileName;
    public ExcelHelper(String fileName)
    {
        this.fileName=fileName + ".xlsx";
    }
    private FileInputStream openExcelFile() throws IOException {
        return new FileInputStream(FrameworkConstants.readDataFile(this.fileName));
    }


    private XSSFWorkbook openExcelWorkbook() throws IOException {
        return new XSSFWorkbook(openExcelFile());
    }

    public void closeFile() throws IOException {
        openExcelFile().close();
    }
    private XSSFSheet accessSheetInExcel(String sheetName) throws IOException {
        XSSFWorkbook workbook = openExcelWorkbook();
        return workbook.getSheet(sheetName);
    }
    public XSSFRow getRow(String sheetName, int rowNum) throws IOException {
        return accessSheetInExcel(sheetName).getRow(rowNum);
    }

    public int getRowCount(String sheetName) throws IOException {
        XSSFSheet sheet = accessSheetInExcel(sheetName);
        return sheet.getLastRowNum() + 1;
    }
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        XSSFSheet sheet = accessSheetInExcel(sheetName);
        XSSFRow row = sheet.getRow(rowNum);
        return row.getLastCellNum();
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        XSSFSheet sheet = accessSheetInExcel(sheetName);
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }


    public Object[][] getDataFromExcel(String sheetName) throws IOException {
        int rowCount = getRowCount(sheetName);
        int colCount = getCellCount(sheetName, 0);

        Object[][] data = new Object[rowCount - 1][colCount];
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = getCellData(sheetName, i, j);
            }
        }
        return data;
    }

    public Object[][] getColumnData(String sheetName, String columnName) throws IOException {
        int rowCount = getRowCount(sheetName);
        int colCount = getCellCount(sheetName, 0);

        int columnIdx = -1;
        XSSFRow headerRow = getRow(sheetName, 0);
        for (int col = 0; col < colCount; col++) {
            String headerValue = getCellData(sheetName, 0, col);
            if (columnName.equalsIgnoreCase(headerValue)) {
                columnIdx = col;
                break;
            }
        }

        if (columnIdx == -1) {
            throw new IllegalArgumentException("Column '" + columnName + "' not found in sheet '" + sheetName + "'.");
        }

        Object[][] columnData = new Object[rowCount - 1][1];
        for (int row = 1; row < rowCount; row++) {
            columnData[row - 1][0] = getCellData(sheetName, row, columnIdx);
        }

        return columnData;
    }

    /**
     * Reads data from all sheets in the Excel file and returns a Map of sheet names to data arrays.
     */
    public Object[][] getAllDataFromExcel() throws IOException {
        XSSFWorkbook workbook = openExcelWorkbook();
        List<Object[]> allData = new ArrayList<>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            int rowCount = sheet.getLastRowNum() + 1;
            int colCount = sheet.getRow(0).getLastCellNum();

            for (int rowIndex = 1; rowIndex < rowCount; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                Object[] rowData = new Object[colCount];

                for (int colIndex = 0; colIndex < colCount; colIndex++) {
                    XSSFCell cell = row.getCell(colIndex);
                    DataFormatter formatter = new DataFormatter();
                    rowData[colIndex] = (cell == null) ? "" : formatter.formatCellValue(cell);
                }

                allData.add(rowData);
            }
        }

        workbook.close();
        return allData.toArray(new Object[0][]);
    }


    /**
     * Generic method to retrieve data from multiple sheets in an Excel file.
     * @param sheetNames List of sheet names to retrieve data from.
     * @return 2D Object array containing combined data from all sheets.
     * @throws IOException if file not found or issue in reading data.
     */
    public Object[][] getDataFromMultipleSheets(List<String> sheetNames) throws IOException {
        XSSFWorkbook workbook = openExcelWorkbook();
        List<Object[]> allData = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        // Find the max row count among all sheets to align data correctly
        int maxRowCount = 0;
        for (String sheetName : sheetNames) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) {
                maxRowCount = Math.max(maxRowCount, sheet.getLastRowNum() + 1);
            }
        }

        // Iterate through rows to merge data from multiple sheets
        for (int rowIndex = 1; rowIndex < maxRowCount; rowIndex++) {
            List<String> rowData = new ArrayList<>();

            for (String sheetName : sheetNames) {
                XSSFSheet sheet = workbook.getSheet(sheetName);
                if (sheet != null) {
                    XSSFRow row = sheet.getRow(rowIndex);
                    if (row != null) {
                        int colCount = row.getLastCellNum();
                        for (int colIndex = 0; colIndex < colCount; colIndex++) {
                            XSSFCell cell = row.getCell(colIndex);
                            rowData.add(cell == null ? "" : formatter.formatCellValue(cell));
                        }
                    }
                }
            }

            // Add row data to the list
            allData.add(rowData.toArray(new Object[0]));
        }

        workbook.close();
        return allData.toArray(new Object[0][]);
    }

}
