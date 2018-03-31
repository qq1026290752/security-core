package cn.yichao.security.core.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.yichao.security.core.constant.ProjectConstant;
@Component("connect/status")
public class YichaoConnectionStatusView extends AbstractView{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, List<Connection<?>>> connectionMap = (Map<String, List<Connection<?>>>) model.get("connectionMap");
		Map<String, Boolean> result = new  HashMap<String, Boolean>();
		for ( String  key : connectionMap.keySet()) {
			result.put(key, CollectionUtils.isNotEmpty(connectionMap.get(key)));
		}
		response.setContentType(ProjectConstant.CONTENTTYPE_JSON);
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
