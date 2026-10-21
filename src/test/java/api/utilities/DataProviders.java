package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    private static final String FILE_PATH =
            System.getProperty("user.dir") + "//testData//userdata.xlsx";
    private static final String SHEET_NAME = "Sheet1";

    // Returns all rows and columns (header row is skipped)
    @DataProvider(name = "Data")
    public String[][] getAllData() throws IOException {
        XLUtility xl = new XLUtility(FILE_PATH);

        int rowCount = xl.getRowCount(SHEET_NAME);
        int colCount = xl.getCellCount(SHEET_NAME, 1);

        String[][] apiData = new String[rowCount][colCount];

        for (int i = 1; i <= rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                apiData[i - 1][j] = xl.getCellData(SHEET_NAME, i, j);
            }
        }
        return apiData;
    }

    // Returns only the UserName column (column index 1)
    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        XLUtility xl = new XLUtility(FILE_PATH);

        int rowCount = xl.getRowCount(SHEET_NAME);

        String[] apiData = new String[rowCount];

        for (int i = 1; i <= rowCount; i++) {
            apiData[i - 1] = xl.getCellData(SHEET_NAME, i, 1);
        }
        return apiData;
    }
}