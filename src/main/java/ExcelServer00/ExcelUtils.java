//package ExcelServer00;
//
//import com.start.ExcelBeen.hbResult;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//
//public class ExcelUtils {
//
//    private hbResult isMergedRegion(Sheet sheet, int row , int column) {
//        int sheetMergeCount = sheet.getNumMergedRegions();
//        for (int i = 0; i < sheetMergeCount; i++) {
//            CellRangeAddress range = sheet.getMergedRegion(i);
//            int firstColumn = range.getFirstColumn();
//            int lastColumn = range.getLastColumn();
//            int firstRow = range.getFirstRow();
//            int lastRow = range.getLastRow();
//            if(row >= firstRow && row <= lastRow){
//                if(column >= firstColumn && column <= lastColumn){
//                    return new hbResult(true,firstRow+1,lastRow+1,firstColumn+1,lastColumn+1);
//                }
//            }
//        }
//        return new hbResult(false,0,0,0,0);
//    }
//
//    /**
//     * 获取单元格里的值
//     *
//     * @param row   第几行
//     * @param cell  第几列
//     * @param sheet 标签页
//     *              return String 值
//     */
//    public String getCellData(int row, int cell, XSSFSheet sheet) {
//        DataFormatter formatter = new DataFormatter();
//        try {
//            return formatter.formatCellValue(sheet.getRow(row).getCell(cell));
//        } catch (Exception e) {
//            System.out.println("无法正确获取单元格:{}");
//        }
//        return null;
//    }
//
//    /**
//     * 取单元格值并转化为String类型
//     *
//     * @param cell
//     * @return
//     */
//    String getStringValue(XSSFCell cell) {
//        DataFormatter formatter = new DataFormatter();
//        String val = formatter.formatCellValue(cell);
//        return val;
//    }
//}
