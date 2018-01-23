package JUnit;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by mozs on 17-9-8.
 */
public class Workbook {
    public static void main(String[] args) throws IOException {
        String filePath = "/home/mozs/share/doc/sample.xls";//文件路径
        HSSFWorkbook workbook = new HSSFWorkbook();//创建Excel文件(Workbook)
        //创建单元格
        HSSFSheet sheet = workbook.createSheet("Test");//创建工作表(Sheet)
        HSSFRow row = sheet.createRow(0);// 创建行,从0开始
        HSSFCell cell = row.createCell(0);// 创建行的单元格,也是从0开始
        cell.setCellValue("李志伟");// 设置单元格内容
        row.createCell(1).setCellValue(true);// 设置单元格内容,重载
        //设置日期格式--使用Excel内嵌的格式
        cell = row.createCell(2);
        cell.setCellValue(new Date());
//        row.createCell(2).setCellValue(new Date());// 设置单元格内容,重载
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        cell.setCellStyle(style);
        //设置保留2位小数--使用Excel内嵌的格式
        cell = row.createCell(3);
        cell.setCellValue(12.123456789);
//        row.createCell(3).setCellValue(12.34523);// 设置单元格内容,重载
        style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(style);

        //设置货币格式--使用自定义的格式
        cell = row.createCell(4);
        cell.setCellValue(12345.4789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("￥#,##0"));
        cell.setCellStyle(style);
//设置百分比格式--使用自定义的格式
        cell = row.createCell(5);
        cell.setCellValue(0.123456789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        cell.setCellStyle(style);
//设置中文大写格式--使用自定义的格式
        cell = row.createCell(6);
        cell.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
        cell.setCellStyle(style);
//设置科学计数法格式--使用自定义的格式
        cell = row.createCell(7);
        cell.setCellValue(12355);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00E+00"));
        cell.setCellStyle(style);
        //合并列
        cell = row.createCell(8);
        cell.setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(0, 0, 8, 9);
        sheet.addMergedRegion(region);
//合并行
        cell = row.createCell(10);
        cell.setCellValue("合并行");
        region = new CellRangeAddress(0, 5, 10, 10);
        sheet.addMergedRegion(region);

        HSSFRow row1 = sheet.createRow(1);
        cell=row1.createCell(0);
        cell.setCellValue("单元格对齐");
        style=workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);//自动换行
        style.setIndention((short)5);//缩进
        style.setRotation((short)0);//文本旋转，这里的取值是从-90到90，而不是0-180度。
        cell.setCellStyle(style);

        cell.setCellValue("设置边框");
        style=workbook.createCellStyle();
//        style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);//上边框
        style.setBorderTop(BorderStyle.DOTTED);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THICK);//下边框
        style.setBorderBottom(BorderStyle.THICK);
//        style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);//左边框
        style.setBorderLeft(BorderStyle.DOUBLE);
//        style.setBorderRight(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);//右边框
        style.setBorderRight(BorderStyle.SLANTED_DASH_DOT);
        style.setTopBorderColor(HSSFColor.RED.index);//上边框颜色
//        style.setTopBorderColor();
        style.setBottomBorderColor(HSSFColor.BLUE.index);//下边框颜色
        style.setLeftBorderColor(HSSFColor.GREEN.index);//左边框颜色
        style.setRightBorderColor(HSSFColor.PINK.index);//右边框颜色
        cell.setCellStyle(style);

        //创建文档摘要信息
        workbook.createInformationProperties();//创建文档信息
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();//摘要信息
        dsi.setCategory("类别:Excel文件");//类别
        dsi.setManager("管理者:李志伟");//管理者
        dsi.setCompany("公司:--");//公司
        SummaryInformation si = workbook.getSummaryInformation();//摘要信息
        si.setSubject("主题:--");//主题
        si.setTitle("标题:测试文档");//标题
        si.setAuthor("作者:李志伟");//作者
        si.setComments("备注:POI测试文档");//备注

        //创建批注
        HSSFPatriarch pat = sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = pat.createAnchor(2, 2, 2, 7, 5, 1, 8, 3);//创建批注位置
        HSSFComment comment = pat.createCellComment(anchor);//创建批注
        comment.setString(new HSSFRichTextString("这是一个批注段落！"));//设置批注内容
        comment.setAuthor("李志伟");//设置批注作者
        comment.setVisible(true);//设置批注默认显示
        HSSFCell cell2 = sheet.createRow(2).createCell(1);
        cell2.setCellValue("测试");
        cell2.setCellComment(comment);//把批注赋值给单元格

        //创建页眉和页脚
        HSSFHeader header = sheet.getHeader();//得到页眉
        header.setLeft("页眉左边");
        header.setRight("页眉右边");
        header.setCenter("页眉中间");
        HSSFFooter footer = sheet.getFooter();//得到页脚
        footer.setLeft("页脚左边");
        footer.setRight("页脚右边");
        footer.setCenter("页脚中间");
        HSSFHeader.tab();

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }
}