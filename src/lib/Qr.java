package lib;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Qr {

	public static Graphics getQrCode(String path, String content) throws IOException {
		
		Qrcode qrcode = new Qrcode();																					//����һ��Qrcode����
		qrcode.setQrcodeEncodeMode('M');																				//���ö�ά��ľ�������
		qrcode.setQrcodeErrorCorrect('B');																				//�Զ����ƴ洢
		qrcode.setQrcodeVersion(7);																						//���ö����ư汾
		byte[] bt = content.getBytes("utf-8");																//�ַ�����
		BufferedImage image = new BufferedImage(140,140,BufferedImage.TYPE_INT_RGB);						//����һ��ͼ�����ݻ�����(����һ��ֽ����)
		Graphics2D g = image.createGraphics();																			//����һ֧��
		g.setBackground(Color.WHITE);																					//���ö�ά�뱳��
		g.fillRect(0,0,140,140);																		//�����ɫ
		g.setColor(Color.BLACK);																						//���ö�ά���ǰ��ɫ
		if(bt.length > 0) {
			boolean[][] b = qrcode.calQrcode(bt);
			for(int i = 0; i < b.length;i++) {
				for(int j = 0;j < b.length;j++) {
					if(b[j][i]) {
						g.fillRect(j * 3 + 2,i * 3 + 2, 3,3);
					}
				}
			}
		}
		File file = new File(path);
		ImageIO.write(image,"png", file);
		return g;
	}
	
	public static void main(String[] args) throws IOException {
		getQrCode("D:\\qrcode.png","nihao");
	}
}
