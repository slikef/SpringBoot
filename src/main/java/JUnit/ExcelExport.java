//package JUnit;
//
//import dcsp.domain.biz.Scale;
//import dcsp.domain.data.EntryData;
//import dcsp.domain.dictionary.DictionaryItem;
//import dcsp.fl.common.Fields;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellRangeAddress;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.util.NumberUtils;
//
//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//public class ExcelExport {
//    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日");
//    public static final String NUMBER_PREFIX = "(number)";
//    protected static Logger logger = LoggerFactory.getLogger(ExcelImport.class);
//    DataFormatter formatter = new DataFormatter();
//
//    /**
//     * 将数据库信息按照模版样式导出到Excel表
//     *
//     * @param all          数据库信息对象
//     * @param templateFile 模版文件
//     */
//    public Map<String, Boolean> exportEntry(List<Map<String, Object>> all, File templateFile, File exportFile) {
//        Map<String, Boolean> uuids = new HashMap<>();
//        XSSFWorkbook wb1 = null;
//        XSSFWorkbook wb2 = null;
//        InputStream is1 = null;
//        InputStream is2 = null;
//
//        boolean exists = exportFile.exists();
//        if (!exists) {
//            try {
//                //复制模版打开文件流
//                FileUtils.copyFile(templateFile, exportFile);
//            } catch (IOException e) {
//                logger.info("拷贝模版失败");
//            }
//        }
//
//        try {
//            logger.info("打开文件");
//            is2 = new FileInputStream(exportFile);
//            wb2 = new XSSFWorkbook(is2);
//            is1 = new FileInputStream(templateFile);
//            wb1 = new XSSFWorkbook(is1);
//        } catch (IOException e) {
//            logger.info("IO读取失败", templateFile, e);
//        }
//        XSSFSheet sheet1 = wb1.getSheetAt(0);
//        XSSFSheet sheet2 = wb2.getSheetAt(0);
//
//        if (!exists) {
//            //去掉模版副本英文标识行
//            cleanRow(sheet2);
//        }
//
//        //取得所有行的列对应值
//        List<Map<Integer, Object>> allValues = readCell(sheet1, all);
//
//        //将所有值写入相应的位置
//        writeCell(sheet2, allValues);
//
//        //流关闭
//        FileOutputStream out = null;
//        try {
//            out = new FileOutputStream(exportFile);
//            wb2.write(out);//保存Excel文件
//        } catch (IOException e) {
//            logger.info("保存文件：{},失败", exportFile, e);
//        } finally {
//            try {
//                logger.info("关闭文件");
//                out.close();
//                is1.close();
//                wb1.close();
//                is2.close();
//                wb2.close();
//            } catch (IOException e) {
//                logger.info("输出流关闭失败");
//            }
//        }
//
//        //标示处理过的对象
//        int size = all.size();
//        for (int i = 0; i < size; i++) {
//            Map<String, Object> map = all.get(i);
//            uuids.put(((EntryData) map.get(EntryData.class.getName())).getId(), true);
//        }
//        return uuids;
//    }
//
//    /**
//     * 模版字段的值匹配
//     *
//     * @param stList
//     * @param appendDefaultValues
//     * @return
//     */
//    public Map<String, Object> flagString(List<String> stList, Map<String, Object> appendDefaultValues) {
//
//        Map<String, Object> map = new HashMap<>();
//        EntryData entry = (EntryData) appendDefaultValues.get(EntryData.class.getName());
//        for (int i = 0; i < stList.size(); i++) {
//            List<Object> list = new ArrayList<>();
//            Object objectValue = null;
//            Object o = null;
//            String st = stList.get(i);
//            switch (st) {
////            case "类分类":
////                stringValue = entry.
////                list.add(stringValue);
////                break;
////            case "项分类":
////                break;
//                case "resTitle":
//                    o = appendDefaultValues.get("resTitle");
//                    objectValue = (o == null ? entry.getResTitle() : o);
//                    map.put("resTitle", objectValue);
//                    break;
//                case "rpOrgName":
//                    o = appendDefaultValues.get("rpOrgName");
//                    objectValue = (o == null ? entry.getRpOrgName() : o);
//                    map.put("rpOrgName", objectValue);
//                    break;
//                case "providerCode":
//                    o = appendDefaultValues.get("providerCode");
//                    objectValue = (o == null ? entry.getProviderCode() : o);
//                    map.put("providerCode", objectValue);
//                    break;
//                case "abstracts":
//                    o = appendDefaultValues.get("abstracts");
//                    objectValue = (o == null ? entry.getAbstracts() : o);
//                    map.put("abstracts", objectValue);
//                    break;
//                case "format":
//                    o = appendDefaultValues.get("format");
//                    if (o == null) {
//                        DictionaryItem format = entry.getFormat();
//                        if (format != null) objectValue = format.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("format", objectValue);
//                    break;
//                case "contentType":
//                    o = appendDefaultValues.get("contentType");
//                    if (o == null) {
//                        DictionaryItem contentType = entry.getContentType();
//                        if (contentType != null) objectValue = contentType.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("contentType", objectValue);
//                    break;
//                case "resSize":
//                    o = appendDefaultValues.get("resSize");
//                    if (o == null) {
//                        Scale resSize = entry.getResSize();
//                        if (resSize != null) objectValue = resSize.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("resSize", objectValue);
//                    break;
//                case "resTotal":
//                    o = appendDefaultValues.get("resTotal");
//                    if (o == null) {
//                        Scale resTotal = entry.getResTotal();
//                        if (resTotal != null) objectValue = resTotal.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//
//                    map.put("resTotal", objectValue);
//                    break;
//                case "resTotal1":
//                    o = appendDefaultValues.get("resTotal1");
//                    if (o == null) {
//                        Scale resTotal1 = entry.getResTotal1();
//                        if (resTotal1 != null) objectValue = resTotal1.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("resTotal1", objectValue);
//                    break;
//                case "resSize1":
//                    o = appendDefaultValues.get("resSize1");
//                    if (o == null) {
//                        Scale resSize1 = entry.getResSize1();
//                        if (resSize1 != null) objectValue = resSize1.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("resSize1", objectValue);
//                    break;
//                case "resSize2":
//                    o = appendDefaultValues.get("resSize2");
//                    if (o == null) {
//                        Scale resSize2 = entry.getResSize2();
//                        if (resSize2 != null) objectValue = resSize2.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("resSize2", objectValue);
//                    break;
//                case "resTotal2":
//                    o = appendDefaultValues.get("resTotal2");
//                    if (o == null) {
//                        Scale resTotal2 = entry.getResTotal2();
//                        if (resTotal2 != null) objectValue = resTotal2.getValue();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("resTotal2", objectValue);
//                    break;
//                case "extraGXTJ":
//                    o = appendDefaultValues.get("extraGXTJ");
//                    objectValue = (o == null ? entry.getExtraGXTJ() : o);
//                    map.put("extraGXTJ", objectValue);
//                    break;
//                case "gxfs":
//                    o = appendDefaultValues.get("gxfs");
//                    if (o == null) {
//                        DictionaryItem gxfs = entry.getGxfs();
//                        if (gxfs != null) objectValue = gxfs.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("gxfs", objectValue);
//                    break;
//                case "gxlx":
//                    o = appendDefaultValues.get("gxlx");
//                    if (o == null) {
//                        DictionaryItem gxlx = entry.getGxlx();
//                        if (gxlx != null) objectValue = gxlx.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("gxlx", objectValue);
//                    break;
////            case "GXFS":
////                stringValue = entry.getGXFS().getTitle();
////                list.add(stringValue);
////                break;
//                case "kflx":
//                    o = appendDefaultValues.get("kflx");
//                    if (o == null) {
//                        DictionaryItem kflx = entry.getKflx();
//                        if (kflx != null) objectValue = kflx.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("kflx", objectValue);
//                    break;
//                case "gxzq":
//                    o = appendDefaultValues.get("gxzq");
//                    if (o == null) {
//                        DictionaryItem gxzq = entry.getGxzq();
//                        if (gxzq != null) objectValue = gxzq.getTitle();
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("gxzq", objectValue);
//                    break;
//                case "pubDate":
//                    o = appendDefaultValues.get("pubDate");
//                    if (o == null) {
//                        Date pubDate = entry.getPubDate();
//                        if (pubDate != null) objectValue = DATE_FORMAT.format(pubDate);
//                    } else {
//                        objectValue = o;
//                    }
//                    map.put("pubDate", objectValue);
//                    break;
//                case "fields.title":
//                    list = getFieldList("title", list, entry);
//                    map.put("fields.title", list);
//
//                    break;
//                case "fields.dataType":
//                    list = getFieldList("dataType", list, entry);
//                    map.put("fields.dataType", list);
//                    break;
//                case "fields.length":
//                    list = getFieldList("length", list, entry);
//                    map.put("fields.length", list);
//                    break;
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 根据对象的Datasets[0]属性，获取表字段的值
//     *
//     * @param flag  表字段的属性名，不区分大小写
//     * @param list  用于存放表字段的值
//     * @param entry 查寻的对象
//     * @return 放表字段的值的list
//     */
//    public List<Object> getFieldList(String flag, List<Object> list, EntryData entry) {
//        Object objectValue = null;
//
//        //获取相应字段的list
//        Fields fields = entry.getFields();
//        if (fields == null) {
//            list.add(null);
//            return list;
//        }
//        List<dcsp.fl.common.Field> items = fields.getItems();
//        int size = items.size();
//        String st = flag.toLowerCase();
//
//        //根据字段名取得相应字段值
//        for (int i = 0; i < size; i++) {
//            dcsp.fl.common.Field field = items.get(i);
//            if ("title".equals(st)) {
//                objectValue = field.getTitle();
//            } else if ("datatype".equals(st)) {
//                objectValue = field.getDataType();
//            } else if ("length".equals(st)) {
//                objectValue = field.getDataLength();
//            }
//            list.add(objectValue);
//        }
//        return list;
//    }
//
//    /**
//     * 取得所有值
//     *
//     * @param sheet
//     * @param all
//     * @return
//     */
//    public List<Map<Integer, Object>> readCell(XSSFSheet sheet, List<Map<String, Object>> all) {
//
//        List<Map<Integer, Object>> allValues = new ArrayList<>();
//
//        //获取模版所有字段
//        List<String> keyWords = findKeyWords(sheet);
//        for (int h = 0; h < all.size(); h++) {
//            Map<String, Object> appendDefaultValues = all.get(h);
//
//            //得到字段值与对应的字段名
//            Map<String, Object> stringObjectMap = flagString(keyWords, appendDefaultValues);
//
//            //取模版的字段名与列号
//            List<Map<String, Integer>> maps = cellKeyValue(sheet);
//
//            //将字段名转化为对应的列号
//            Map<Integer, Object> integerObjectMap = columnIndexValueMap(maps, stringObjectMap);
//
//            //将Map中的List全部重组成多个
//            List<Map<Integer, Object>> list = iteratorMap(integerObjectMap);
//            allValues.addAll(list);
//        }
//        return allValues;
//    }
//
//    /**
//     * 将Map中的value值为List的Map对象去List化
//     *
//     * @param map
//     * @return
//     */
//    List<Map<Integer, Object>> iteratorMap(Map<Integer, Object> map) {
//        List<Map<Integer, Object>> integerObjectList = new ArrayList<>();
//        int size = 0;
//        List<Map<Integer, Object>> list1 = new ArrayList<>();
//        //获取Value为List的Map所有List的对象
//        for (Map.Entry<Integer, Object> entry : map.entrySet()) {
//            Object objectValues = entry.getValue();
//            Integer key = entry.getKey();
//            if (!(objectValues instanceof List)) continue;
//            List<Object> objectList = (List<Object>) objectValues;
//            size = objectList.size();
//            for (int i = 0; i < size; i++) {
//                Map<Integer, Object> objectMap = new HashMap<>();
//                Object o = objectList.get(i);
//                objectMap.put(key, o);
//                list1.add(objectMap);
//            }
//        }
//
//        //将取到的对象覆盖原来的Map的对象并按层次添加
//        int size1 = list1.size();
//        for (int i = 0; i < size; i++) {
//            Map<Integer, Object> iteratorMap = new HashMap<>();
//            iteratorMap.putAll(map);
//            for (int j = i; j < size1; j += size) {
//                iteratorMap.putAll(list1.get(j));
//            }
//            integerObjectList.add(iteratorMap);
//        }
//        return integerObjectList;
//    }
//
//    /**
//     * 取得标识关键字List
//     *
//     * @param sheet
//     * @return
//     */
//    List<String> findKeyWords(XSSFSheet sheet) {
//        int firstRowNum = sheet.getFirstRowNum();
//        int lastRowNum = sheet.getLastRowNum();
//        List<String> stList = new ArrayList<>();
//
//        for (int i = firstRowNum; i < lastRowNum; i++) {
//            XSSFRow sheetRow = sheet.getRow(i);
//            int rowNum = sheetRow.getLastCellNum();
//            for (int j = 0; j < rowNum; j++) {
//                XSSFCell cell = sheetRow.getCell(j);
//                if (cell == null) continue;
//                String stringCellValue = getStringValue(cell);
//                if (!StringUtils.isEmpty(stringCellValue)) stList.add(stringCellValue);
//            }
//        }
//        return stList;
//    }
//
//    /**
//     * 按行将对应列号的单元格写入相应值
//     *
//     * @param sheet
//     * @param list
//     */
//
//    public void writeCell(XSSFSheet sheet, List<Map<Integer, Object>> list) {
//        int firstRowNum = sheet.getFirstRowNum();
//        int size = list.size();
//        for (int f = 0; f < size; f++) {
//            Map<Integer, Object> map = list.get(f);
//            int lastRowNum = sheet.getLastRowNum();
//            for (int h = firstRowNum; h <= lastRowNum + 1; h++) {
//                XSSFRow row = sheet.getRow(h);
//                if (row == null) row = sheet.createRow(h);
//                boolean rowEmpty = isRowEmpty(row);
//                Boolean mergedRegion = isMergedRegion(sheet, h);
//                if (!(rowEmpty && !mergedRegion)) continue;
//                firstRowNum = row.getRowNum();
//                for (Map.Entry<Integer, Object> entry1 : map.entrySet()) {
//                    int ColumnIndex = entry1.getKey();
//                    XSSFCell cell = row.getCell(ColumnIndex);
//                    if (cell == null) cell = row.createCell(ColumnIndex);
//                    Object entry1Value = entry1.getValue();
//                    if (entry1Value == null) continue;
//                    String s = String.valueOf(entry1Value);
//                    smartCellValue(cell, s);
//                }
//                break;
//            }
//        }
//    }
//
//    void smartCellValue(XSSFCell cell, Object value) {
//        if (value == null) return;
//
//        if (value instanceof String) { // 支持字符串类型中强制指定格式
//            String str = (String) value;
//            if (StringUtils.startsWith(str, NUMBER_PREFIX)) {
//                cell.setCellValue(castTypedValue(str, Double.class));
//            } else {
//                cell.setCellValue(str);
//            }
//        } else { // 支持更多类型
//            throw new UnsupportedOperationException("不支持的格式");
//        }
//    }
//
//    private <T extends Number> T castTypedValue(String str, Class<T> clazz) {
//        return NumberUtils.parseNumber(StringUtils.substringAfter(str, NUMBER_PREFIX), clazz);
//    }
//
//    /**
//     * 判断一行是否为空
//     *
//     * @param row 判断的行号
//     * @return true表示空
//     */
//    Boolean isRowEmpty(Row row) {
//        boolean flag = true;
//        int firstCellNum = row.getFirstCellNum();
//        int lastCellNum = row.getLastCellNum();
//
//        //获取已有行的已存在的单元格进行判断
//        for (int i = firstCellNum; i < lastCellNum; i++) {
//            Cell cell = row.getCell(i);
//            if (cell != null) {
//                String stringCellValue1 = formatter.formatCellValue(cell);
//                if (!(null == stringCellValue1 || "".equals(stringCellValue1.trim()))) {
//                    flag = false;
//                }
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 判断一行是否为合并行
//     *
//     * @param sheet 查询的标签页名
//     * @param row   判断的行号
//     * @return true表示是合并行
//     */
//    Boolean isMergedRegion(Sheet sheet, int row) {
//
//        //取得标签页中所有合并单元格的数量
//        int sheetMergeCount = sheet.getNumMergedRegions();
//        for (int i = 0; i < sheetMergeCount; i++) {
//
//            //获取具体的合并单元格的起始行与结束行
//            CellRangeAddress range = sheet.getMergedRegion(i);
//            int firstRow = range.getFirstRow();
//            int lastRow = range.getLastRow();
//            if (row >= firstRow && row <= lastRow) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * 去掉英文标示行
//     *
//     * @param sheet
//     */
//    public void cleanRow(XSSFSheet sheet) {
//        sheet.removeRow(sheet.getRow(3));  //TODO@mozs
//    }
//
//    /**
//     * 取单元格值并转化为String类型
//     *
//     * @param cell
//     * @return
//     */
//    String getStringValue(XSSFCell cell) {
//        String val = formatter.formatCellValue(cell);
//        return val;
//    }
//
//    /**
//     * 将Map<列字段,列值>转化为<列号,列值>
//     *
//     * @param maps
//     * @param stringObjectMap
//     * @return
//     */
//
//    Map<Integer, Object> columnIndexValueMap(List<Map<String, Integer>> maps, Map<String, Object> stringObjectMap) {
//        Map<Integer, Object> integerObjectMap = new HashMap<>();
//        Iterator<String> iterator = stringObjectMap.keySet().iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            Object o = stringObjectMap.get(next);
//            //取得字段对应的列号
//            Integer integer = cellValue1(maps, next);
//            integerObjectMap.put(integer, o);
//        }
//        return integerObjectMap;
//    }
//
//    /**
//     * 获取模版的字段与列号
//     *
//     * @param sheet
//     * @return
//     */
//    List<Map<String, Integer>> cellKeyValue(XSSFSheet sheet) {
//        List<Map<String, Integer>> list = new ArrayList<>();
//        int firstRowNum = sheet.getFirstRowNum();
//        int lastRowNum = sheet.getLastRowNum();
//        for (int i = firstRowNum; i <= lastRowNum; i++) {
//            XSSFRow row = sheet.getRow(i);
//            if (row == null) continue;
//            int firstCellNum = row.getFirstCellNum();
//            int lastCellNum = row.getLastCellNum();
//            Map<String, Integer> map = new HashMap<>();
//            for (int j = firstCellNum; j < lastCellNum; j++) {
//                XSSFCell cell = row.getCell(j);
//                if (cell == null) continue;
//                String stringValue = getStringValue(cell);
//                if (StringUtils.isEmpty(stringValue)) continue;
//                map.put(stringValue, j);
//            }
//            list.add(map);
//        }
//        return list;
//    }
//
//    /**
//     * 获取字段的唯一列号
//     *
//     * @param maps
//     * @param key
//     * @return
//     */
//    Integer cellValue1(List<Map<String, Integer>> maps, String key) {
//        Integer integer = null;
//        for (int i = 0; i < maps.size(); i++) {
//            Map<String, Integer> integerStringMap = maps.get(i);
//            boolean b = integerStringMap.containsKey(key);
//            if (b) {
//                integer = integerStringMap.get(key);
//            }
//        }
//        return integer;
//    }
//
//}
