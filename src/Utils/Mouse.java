package Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dangelo on 2016/5/24.
 */
public class Mouse {

    private Dimension dimension;
    private Robot robot;
    private Point point;
    private final static String DIRECTION_UP = "up";
    private final static String DIRECTION_DOWN = "down";

    public Mouse() {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();    //��ȡ��Ļ�ߴ�
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * �ƶ����λ��
     */
    public void move(int x,int y){
        point = MouseInfo.getPointerInfo().getLocation();
        point.x += -x;
        point.y += -y;
        robot.mouseMove(point.x,point.y);       /**�ƶ���굽x,yλ��*/
    }

    /**
     * ����
     */
    public void singleClick(){
        point = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);      //��������ϰ���
        robot.mouseRelease(InputEvent.BUTTON1_MASK);    //�ͷ�����ϰ���
    }

    /**
     * ˫��
     */
    public void doubleClick(){
        point = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);      //��������󰴼�
        robot.mouseRelease(InputEvent.BUTTON1_MASK);    //�ͷ�����󰴼�
        robot.delay(100);
        robot.mousePress(InputEvent.BUTTON1_MASK);      //�ٴΰ�������󰴼�
        robot.mouseRelease(InputEvent.BUTTON1_MASK);    //�ͷ�����󰴼�
    }

    /**
     * �һ�
     */
    public void rightClick(){
        point = MouseInfo.getPointerInfo().getLocation();
        robot.mouseMove(point.x, point.y);
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

    }

    /**
     * �����ֵĻ���
     */
    public void wheel(String direction){
        if(direction.equals(DIRECTION_UP)) {
            robot.mouseWheel(-1);                       //�����ϻ�
        } else if(direction.equals(DIRECTION_DOWN)){
            robot.mouseWheel(1);                        //�����»�
        }
    }

    public void direction(String key){
        switch (key){
            case "up" :
                robot.keyPress(KeyEvent.VK_UP);
                break;
            case "down" :
                robot.keyPress(KeyEvent.VK_DOWN);
                break;
            case "left" :
                robot.keyPress(KeyEvent.VK_LEFT);
                break;
            case "right" :
                robot.keyPress(KeyEvent.VK_RIGHT);
                break;
            case "home" :
                robot.keyPress(KeyEvent.VK_HOME);
                break;
            case "end" :
                robot.keyPress(KeyEvent.VK_END);
                break;
        }
    }

    /**
     * ��ȡ��Ļ��ͼ
     */
    public BufferedImage screenShot(){
        return robot.createScreenCapture(new Rectangle(0,0,dimension.width, dimension.height));
    }

    /**
     * ������Ļ��ͼ
     */
    public void saveScreenShot(BufferedImage image, String path){
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}