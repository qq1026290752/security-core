package cn.yichao.security.core.vlidate.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.ServletRequestUtils;

import cn.yichao.security.core.properties.SecurityPeoperties;
import cn.yichao.security.core.vlidate.ImageCode;
import lombok.extern.slf4j.Slf4j;
@Slf4j 
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {
	
	@Autowired
	private SecurityPeoperties securityPeoperties;
	
	@Override
	public ImageCode createImageCode(ServletRequest request) {
		int IMG_HEIGHT = ServletRequestUtils.getIntParameter(request, "height", securityPeoperties.getImage().getImgHeight());
        // 图片宽度
        final int IMG_WIDTH = ServletRequestUtils.getIntParameter(request, "width", securityPeoperties.getImage().getImgWidth());  
        // 验证码长度
        int CODE_LEN =  ServletRequestUtils.getIntParameter(request, "code", securityPeoperties.getImage().getCodeLen());
        // 用于绘制图片，设置图片的长宽和图片类型（RGB)
        BufferedImage bi = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 获取绘图工具
        Graphics graphics = bi.getGraphics();
        graphics.setColor(new Color(100, 230, 200)); // 使用RGB设置背景颜色
        graphics.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT); // 填充矩形区域
        // 验证码中所使用到的字符
        char[] codeChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456".toCharArray();
        String captcha = ""; // 存放生成的验证码
        Random random = new Random();
        for(int i = 0; i < CODE_LEN; i++) { // 循环将每个验证码字符绘制到图片上
            int index = random.nextInt(codeChar.length);
            // 随机生成验证码颜色
            graphics.setColor(new Color(random.nextInt(150), random.nextInt(200), random.nextInt(255)));
            // 将一个字符绘制到图片上，并制定位置（设置x,y坐标）
            graphics.drawString(codeChar[index] + "", (i * 20) + 15, 20);
            captcha += codeChar[index];
        }

        log.info("生成的验证码为:" + captcha);
        return new ImageCode(bi,captcha,securityPeoperties.getImage().getExpireIn());
    }

	public SecurityPeoperties getSecurityPeoperties() {
		return securityPeoperties;
	}

	public void setSecurityPeoperties(SecurityPeoperties securityPeoperties) {
		this.securityPeoperties = securityPeoperties;
	}
	
	
	
}
