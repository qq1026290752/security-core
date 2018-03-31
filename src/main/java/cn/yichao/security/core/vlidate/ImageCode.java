package cn.yichao.security.core.vlidate;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode extends ValidateCode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 图片验证码
	 */
	private BufferedImage bufferedImage;
	 
	public ImageCode(BufferedImage bufferedImage,String code,int expireIn) {
		super(code,expireIn);
		this.bufferedImage = bufferedImage; 
	}
	
	public ImageCode(BufferedImage bufferedImage,String code, LocalDateTime expireTime) {
		super(code,expireTime);
		this.bufferedImage = bufferedImage; 
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
 
 
}
