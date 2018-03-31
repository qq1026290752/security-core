package cn.yichao.security.core.properties;
 
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ValidataProperties extends SmsValidateProperties {
	/**
	 * 
	 */
	public ValidataProperties() {
		setCodeLen(4);
	}
	
	//图片
	private int imgHeight = 40; 
    // 图片宽度
	private final int imgWidth = 120;  
}
