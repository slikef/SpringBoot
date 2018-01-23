//package JUnit;
//
//
//import dcsp.domain.biz.Scale;
//import dcsp.domain.data.EntryData;
//import dcsp.fl.common.Field;
//import dcsp.fl.common.Fields;
//import dcsp.utils.DcspHelper;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by fangr on 17-9-13.
// */
//@Component
//public class ExcelImport {
//    protected static Logger logger = LoggerFactory.getLogger(ExcelImport.class);
//
//    DataFormatter formatter = new DataFormatter();
//
//    /**
//     * @param file excel路径
//     *             return excel文件中包含的库信息和表信息
//     */
//    public Summary getSummary(File file) {
//        FileInputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(file);
//        } catch (IOException e) {
//            logger.info("文件获取异常:{}",file.getAbsolutePath());
//        }
//        XSSFWorkbook workbook = null;
//        int sheets = 0;
//        try {
//            workbook = new XSSFWorkbook(inputStream);
//            sheets = workbook.getNumberOfSheets();
//            if (sheets < 2 || !workbook.getSheetAt(0).getSheetName().equals("Summary")) {
//                throw new RuntimeException("格式不对");
//            }
//        } catch (IOException e) {
//            logger.info("获取excel文件失败");
//        }
//
//        /**
//         * 获得库的信息
//         */
//        Summary summary = getSummary(workbook);
//        /**
//         * 获得所有表的信息和字段信息
//         */
//
//        List<Table> list = new ArrayList<>();
//        for (int i = 1; i < sheets; i++) {
//            XSSFSheet sheetAt = workbook.getSheetAt(i);
//            int rowNum = sheetAt.getLastRowNum();
//            Table field = new Table();
//            Map<String, String> map = new HashMap<>();
//            /**
//             *获取表的信息
//             */
//            for (int j = 0; j < rowNum; j++) {
//                String key = getCellData(j, 0, sheetAt);
//                String value = getCellData(j, 1, sheetAt, key);
//
//                if (key.equals("表名")) {
//                    field.setName(value);
//                } else if (key.equals("表中文名")) {
//                    field.setTitle(value);
//                } else if(key.equals("数据条数")){
//                    field.setNumber(value);
//                } else if(key.equals("表容量")){
//                    field.setCapacity(value);
//                } else if (key.equals("字段列表")) {
//                    /**
//                     * 加入所有字段信息
//                     */
//                    field.setFields(getFields(j + 2, sheetAt, rowNum));
//                    break;
//                } else {
//                    map.put(DcspHelper.guessCode(key), value);
//                    field.setAttributes(map);
//                }
//            }
//            list.add(field);
//        }
//        /**
//         * 在Summary中加入表的信息
//         */
//        summary.setTables(list);
//        return summary;
//
//    }
//
//    /**
//     * @param i      excel第几行
//     * @param rowNum excel 总共有多少行
//     * @param sheet  当前标签页
//     *               return 字段的所有信息
//     */
//    List<Field2> getFields(Integer i, XSSFSheet sheet, Integer rowNum) {
//        List<Field2> list = new ArrayList<>();
//        for (int j = i; j <= rowNum; j++) {
////            XSSFRow row = sheet.getRow(j);
//            Field2 information = new Field2();
//            information.setName(getCellData(j, 0, sheet, "字段"));
//            information.setTitle(getCellData(j, 1, sheet, "字段中文名称"));
//            information.setType(getCellData(j, 2, sheet, "类型"));
//            information.setLength(getCellData(j, 3, sheet, "长度"));
//            list.add(information);
//        }
//        return list;
//    }
//
//    /**
//     * @param workbook XSSFWorkbook实例
//     *                 return Summary实例
//     */
//    Summary getSummary(XSSFWorkbook workbook) {
//        XSSFSheet sheet = workbook.getSheetAt(0);
//        Summary summary = new Summary();
//        summary.setSchema(getCellData(0, 1, sheet, "数据库名"));
//        summary.setTitle(getCellData(1, 1, sheet, "中文名"));
//        summary.setLibrary(getCellData(2, 1, sheet, "库容量"));
//        summary.setType(getCellData(3, 1, sheet, "数据库类型"));
//        return summary;
//    }
//
//    /**
//     * 将Summary实例转换成EntryData实例
//     *
//     * @param summary 实例
//     *                return EntryData 集合
//     */
//    public List<EntryData> getEntryDatas(Summary summary) {
//        ArrayList<EntryData> list = new ArrayList<>();
//        EntryData entryData = new EntryData();
//        List<Table> tables = summary.getTables();
//        for (int i = 0; i < tables.size(); i++) {
//            //将库信息放入entryData实例中
//            entryData.setOwnerTitle(summary.getTitle());
//            String type = summary.getType();
//            entryData.putExtraAttribute("contentType", type);
//            String schema = summary.getSchema();
//            entryData.setOwnerGroup(schema);
//            //获取表其他表信息
//            EntryData data = getTable(tables, i, entryData,summary);
//            list.add(data);
//        }
//        return list;
//    }
//
//    /**
//     * 获取表的部分信息，放入entryData实例中
//     *
//     * @param tables    对象
//     * @param i         第几张表
//     * @param entryData 实例
//     *                  return EntryData 实例
//     */
//    EntryData getTable(List<Table> tables, int i, EntryData entryData,Summary summary) {
//        Table table = tables.get(i);
//        entryData.setResTitle(table.getTitle());
//        entryData.setResTotal(Scale.create(Float.valueOf(table.getNumber()), Scale.Unit.TOTAL));
//        entryData.setResSize(Scale.create(Float.valueOf(table.getCapacity()),Scale.Unit.SG));
//
//        //获取字段信息放入Field中
//        Fields fields=  new Fields();
//        for (int j = 0; j < table.getFields().size(); j++) {
//            Field2 field2 = table.getFields().get(j);
//            Field field = new Field();
//            field.setName(field2.getName());
//            field.setTitle(field2.getTitle());
//            field.setDataLength(Integer.parseInt(field2.getLength()));
//            field.putAtrribute("dataType",summary.getType()+"."+field2.getType());
//            fields.addField(field);
//        }
//        entryData.setFields(fields);
//        return getAttributes(entryData, table.getAttributes());
//    }
//
//    /**
//     * 获取预留表的信息集合的数据，放入entryData 实例中
//     *
//     * @param entryData  实例
//     * @param attributes 预留表信息集合
//     *                   return EntryData 实例
//     */
//    EntryData getAttributes(EntryData entryData, Map<String, String> attributes) {
//        if(attributes == null || attributes.size()==0) return  entryData;
//        for (Map.Entry<String, String> entry :
//                attributes.entrySet()) {
//           logger.info("预留的表信息集合，暂时没有");
//
//        }
//        return entryData;
//    }
//
//    /**
//     * 获取单元格里的值
//     *
//     * @param row   第几行
//     * @param cell  第几列
//     * @param sheet 标签页
//     * @param title 名称
//     *              return String 值
//     */
//    public String getCellData(int row, int cell, XSSFSheet sheet, String title) {
//        try {
//            return formatter.formatCellValue(sheet.getRow(row).getCell(cell));
//        } catch (Exception e) {
//            logger.info("无法正确获取单元格:{}", title);
//        }
//        return null;
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
//     String getCellData(int row, int cell, XSSFSheet sheet) {
//        try {
//            return formatter.formatCellValue(sheet.getRow(row).getCell(cell));
//        } catch (Exception e) {
//            logger.info("格式不对");
//        }
//        return null;
//    }
//}
