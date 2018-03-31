package cn.yichao.security.core.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.web.servlet.view.AbstractView;

import cn.yichao.security.core.constant.ProjectConstant;

public class YichaoConnectionView extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType(ProjectConstant.CONTENTTYPE_HTML);
		if(model.get("connection") == null) {			
			response.getWriter().write("<h3>解绑成功</h3>");
		}
		response.getWriter().write("<h3>绑定成功</h3>");
	}

}
