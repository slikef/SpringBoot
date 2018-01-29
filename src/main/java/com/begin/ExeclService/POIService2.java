package com.begin.ExeclService;

import com.begin.BeanAction.SQLbean;
import com.begin.BeanAction.cellMergedRegion;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class POIService2 {

    //填报说明标签页
    public SQLbean readExcel(File file) {

        //同时支持Excel 2003、2007
        int i = 0;
        FileInputStream is = null; //文件流
        Workbook workbook = null;
        SQLbean sb = new SQLbean();
        try {
            is = new FileInputStream(file);
            workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        String namePath = StringUtils.substringAfterLast(file.getParentFile().toString(), "\\");
        Sheet sheet = workbook.getSheet("填报说明");
        if (sheet == null) System.out.println("标签页不存在：" + file);
        Row row = sheet.getRow(0);
        if (row == null || row.getCell(0) == null || !("项目名称".equals(row.getCell(0).getStringCellValue()))) return sb;
        if ("基本信息".equals(getStringValue(sheet.getRow(3).getCell(0)))) {
            sb.setSxmc(getStringValue(sheet.getRow(2).getCell(0)));
            i = 1;
        }
        int mergedRegion = isMergedRegion(sheet, 22 + i);
//        sb.setId(UUID.randomUUID().node());
        sb.setQm("111");
        sb.setSsbm("222");
        sb.setFjsx(getStringValue(sheet.getRow(1).getCell(0)));
        sb.setFwdx(getStringValue(sheet.getRow(2 + i).getCell(1)) + getStringValue(sheet.getRow(3).getCell(4)));
        sb.setFrzt(getStringValue(sheet.getRow(3 + i).getCell(1)));
        sb.setZrrzt(getStringValue(sheet.getRow(4 + i).getCell(3)));
        sb.setSxxz(getStringValue(sheet.getRow(5 + i).getCell(1)));
        sb.setSxlx(getStringValue(sheet.getRow(5 + i).getCell(4)));
        sb.setXscj(getStringValue(sheet.getRow(6 + i).getCell(1)));
        sb.setQxhf(getStringValue(sheet.getRow(6 + i).getCell(4)));
        sb.setFwxs(getStringValue(sheet.getRow(7 + i).getCell(1)));
        sb.setXsnr(getStringValue(sheet.getRow(7 + i).getCell(4)));
        sb.setSsztxz(getStringValue(sheet.getRow(8 + i).getCell(1)));
        sb.setSsjg(getStringValue(sheet.getRow(8 + i).getCell(4)));
        sb.setZrcs(getStringValue(sheet.getRow(9 + i).getCell(1)));
        sb.setZxdh(getStringValue(sheet.getRow(9 + i).getCell(4)));
        sb.setLbjg(getStringValue(sheet.getRow(10 + i).getCell(1)));
        sb.setZjfw(getStringValue(sheet.getRow(10 + i).getCell(4)));
        sb.setFdqx(getStringValue(sheet.getRow(11 + i).getCell(1)));
        sb.setCnqx(getStringValue(sheet.getRow(11 + i).getCell(4)));
        sb.setJgmc(getStringValue(sheet.getRow(12 + i).getCell(1)));
        sb.setJdtsdh(getStringValue(sheet.getRow(12 + i).getCell(4)));
        sb.setJgyb(getStringValue(sheet.getRow(13 + i).getCell(1)));
        sb.setTbfw(getStringValue(sheet.getRow(13 + i).getCell(4)));
        sb.setSlxz(getStringValue(sheet.getRow(14 + i).getCell(1)));
        sb.setBjlx(getStringValue(sheet.getRow(14 + i).getCell(4)));
        sb.setFjsxsm(getStringValue(sheet.getRow(15 + i).getCell(1)));
        sb.setYybl(getStringValue(sheet.getRow(15 + i).getCell(4)));
        sb.setDccs(getStringValue(sheet.getRow(16 + i).getCell(1)));
        sb.setWszf(getStringValue(sheet.getRow(16 + i).getCell(4)));
        sb.setYxxt(getStringValue(sheet.getRow(17 + i).getCell(1)));
        sb.setWlkd(getStringValue(sheet.getRow(17 + i).getCell(4)));
        sb.setBldd(getStringValue(sheet.getRow(18 + i).getCell(1)));
        sb.setBlsj(getStringValue(sheet.getRow(19 + i).getCell(1)));
        sb.setSltj(getStringValue(sheet.getRow(21 + i).getCell(1)));

//        sb.setSltj(getStringValue(sheet.getRow(22+i).getCell(0)));

        sb.setBllc(getStringValue(sheet.getRow(21 + mergedRegion + 2 + i).getCell(1)));
        sb.setSfsf(getStringValue(sheet.getRow(22 + mergedRegion + 2 + i).getCell(1)));
        sb.setSfbz(getStringValue(sheet.getRow(23 + mergedRegion + 2 + i).getCell(1)));
        sb.setSfyj(getStringValue(sheet.getRow(24 + mergedRegion + 2 + i).getCell(1)));
        sb.setFdyj(getStringValue(sheet.getRow(25 + mergedRegion + 2 + i).getCell(1)));
        return sb;
    }

    //办事事项上报表标签页
    public List<Map<String, String>> readExcel2(File file) {

        int index = 0;
        FileInputStream is = null; //文件流
        Workbook workbook = null;
        List<Map<String, String>> valuesList = new ArrayList<>();
        try {
            is = new FileInputStream(file);
            workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet("办事事项上报表");
        if (sheet == null) System.out.println("办事事项上报表标签页不存在：" + file);
        Row row = sheet.getRow(0);
        if (row != null && row.getCell(index) == null) index++;
        if (row == null || row.getCell(index) == null || !("父级事项名称".equals(row.getCell(index).getStringCellValue())))
            return valuesList;

        int shuRowNum = sheet.getLastRowNum();
        int lastCellNum = row.getLastCellNum();
        String namePath = StringUtils.substringAfterLast(file.getParentFile().toString(), "\\");
        String quming = StringUtils.substringBeforeLast(file.getParentFile().toString(), "\\");
        quming = StringUtils.substringAfterLast(quming, "\\");
        int mergedRegion = 0;
        int count = 0;

        for (int i = 1; i <= shuRowNum; i++) {
            Row row1 = sheet.getRow(i);
            if (row1 == null || isRowEmpty(row1)) continue;
            String hb = null;
            Map<String, String> mapString = new HashMap<>();
            count++;
            for (int k = (index + 0); k < lastCellNum; k++) {
                //取KEY值
                if (row.getCell(k) == null) continue;
                String stringKey1 = getCellValue(sheet,row.getRowNum(),k);
                String stringKey2 = quKong(stringKey1);
                String stringKey = oneToOne(stringKey2);
                if ("abc".equals(stringKey)) {
                    outFile("D:\\test\\outfile.txt", "匹配不了列字段的部门：" + namePath);
                    mapString.clear();
                    mapString.put("passname", namePath);
                    valuesList.add(mapString);
                }
                //取value值
                Cell cell1 = row1.getCell(k);
                String stringValues = null;
                if (cell1 != null) {
                    stringValues = getCellValue(sheet,row1.getRowNum(),k);
                    StringUtils.trim(stringValues);
                } else {
                    stringValues = "null";
                }
                mapString.put(stringKey, stringValues);
            }
            valuesList.add(mapString);
        }
        return valuesList;
    }

    //将单元格转为String类型
    public String getStringValue(Cell cell) {
        DataFormatter formatter = new DataFormatter();
        String val = formatter.formatCellValue(cell);
        return val;
    }

    /**
     * 获取单元格的值
     *
     * @param cell
     * @return
     */
    public String getCellValue(Cell cell) {

        if (cell == null)
            return "";

        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {

            return String.valueOf(cell.getBooleanCellValue());

        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

            return cell.getCellFormula();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }

    //合并单元格的判定
    public cellMergedRegion isMergedRegion1(Sheet sheet, int row, int column) {
        int num = 0;
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return new cellMergedRegion(true, firstRow, lastRow, firstColumn, lastColumn);
                }
            }
        }
        return new cellMergedRegion(false, row, row, column, column);
    }

    /**
     * 合并单元格
     *
     * @param sheet
     * @param firstRow 开始行
     * @param lastRow  结束行
     * @param firstCol 开始列
     * @param lastCol  结束列
     */
    private void mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    private int isMergedRegion(Sheet sheet, int row) {
        int num = 0;
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                num = lastRow - firstRow;
            }
        }
        return num;
    }

    public boolean isRowEmpty(Row row) {
        boolean flag = true;
//        Iterator<Cell> iterator = row.iterator();
        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null) {
                String stringCellValue1 = null;
                stringCellValue1 = getStringValue(cell);
                if ((!"".equals(stringCellValue1))) flag = false;
            }
        }
        return flag;
    }

    public String oneToOne(String st) {
        String one = null;
        boolean flag = true;
        List<String> list = new ArrayList<>();
        switch (st) {
            case "父级事项名称":
                one = "fjsx";
                break;
            default:
                if (!"".equals(st)) list.add(st);
                break;
        }
        int num = list.size();
        if (num > 0) {
            one = "abc";
            for (int a = 0; a < num; a++) {
                System.out.println("*******************没有的列字段:" + list.get(a));
            }
        }
        return one;
    }

    public SQLbean readMap(Map<String, String> map) {
        SQLbean sb = new SQLbean();
        sb.setQm("222");
        sb.setSsbm("333");
        sb.setFjsx(map.get("fjsx"));
        sb.setSxmc(map.get("sxmc"));
        sb.setSxxz(map.get("sxxz"));
        sb.setSxlx(map.get("sxlx"));
        sb.setFwdx(map.get("fwdx"));
        sb.setFrzt(map.get("frzt"));
        sb.setZrrzt(map.get("zrrzt"));
        sb.setXscj(map.get("xscj"));
        sb.setQxhf(map.get("qxhf"));
        sb.setFwxs(map.get("fwxs"));
        sb.setXsnr(map.get("xsnr"));
        sb.setSsztxz(map.get("ssztxz"));
        sb.setSsjg(map.get("ssjg"));
        sb.setZrcs(map.get("zrcs"));
        sb.setZxdh(map.get("zxdh"));
        sb.setLbjg(map.get("lbjg"));
        sb.setZjfw(map.get("zjfw"));
        sb.setFdqx(map.get("fdqx"));
        sb.setCnqx(map.get("cnqx"));
        sb.setJgmc(map.get("jgmc"));
        sb.setJdtsdh(map.get("jdtsdh"));
        sb.setJgyb(map.get("jgyb"));
        sb.setSlxz(map.get("slxz"));
        sb.setTbfw(map.get("tbfw"));
        sb.setBjlx(map.get("bjlx"));
        sb.setFjsxsm(map.get("fjsxsm"));
        sb.setYybl(map.get("yybl"));
        sb.setDccs(map.get("dccs"));
        sb.setWszf(map.get("wszf"));
        sb.setYxxt(map.get("yxxt"));
        sb.setWlkd(map.get("wlkd"));
        sb.setBldd(map.get("bldd"));
        sb.setBlsj(map.get("blsj"));
        sb.setSltj(map.get("sltj"));
        sb.setBlcl(map.get("blcl"));
        sb.setBllc(map.get("bllc"));
        sb.setSfsf(map.get("sfsf"));
        sb.setSfbz(map.get("sfbz"));
        sb.setSfyj(map.get("sfyj"));
        sb.setFdyj(map.get("fdyj"));
        sb.setCjwtjd(map.get("cjwtjd"));
//        sb.setSsbm(map.get("ssbm"));
//        sb.setQm(map.get("qm"));
        sb.setPassname(map.get("passname"));
        sb.setPasssheet(map.get("passsheet"));

        return sb;
    }

    //String去空格
    public String quKong(String st) {

        return st.replaceAll("[:\\\\/*\"?|<>']", "").replaceAll("\\u00A0", " ").trim();
    }

    //将flag输出到文件中
    public void outFile(String path, String flag) {
        File file = new File(path);
        PrintStream psOut = System.out;
        try {
            PrintStream newOut = new PrintStream(file);
            System.setOut(newOut);
            System.out.println(flag);
            System.setOut(psOut);
            System.out.println("输出到控制台");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public String getCellValue(Sheet sheet, int row, int column) {
        String value = null;
        cellMergedRegion mergedRegion = isMergedRegion1(sheet, row, column);
        value = getStringValue(sheet.getRow(mergedRegion.getStartRow()).getCell(mergedRegion.getStartCol()));
        return value;
    }

}
