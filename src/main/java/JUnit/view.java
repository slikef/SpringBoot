package JUnit;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class view {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();
            BufferedImage bi=robot.createScreenCapture(new Rectangle(900,800)); // 根据指定的 区域(1300,800)抓取屏幕的指定区域
            ImageIO.write(bi, "jpg", new File("D:/imageTest.jpg")); //把抓取到的内容写入到一个jpg文件中
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
