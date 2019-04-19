package cn.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction extends BaseAction {

	/* 首页 */
	@RequestMapping("/index")
	public String getIndexPage() {
		return "main/indexHome";
	}
}
