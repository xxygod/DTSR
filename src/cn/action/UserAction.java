package cn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.Cache.HostChannelList;
import cn.Cache.TemperatureList;
import cn.core.Constants;
import cn.model.AlarmRecord;
import cn.model.Host;
import cn.model.Temperature;
import cn.model.User;
import cn.service.HostService;
import cn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserAction extends BaseAction {

	@Autowired
	private UserService userService;

	@Autowired
	private HostService hostService;

	/** 测试代码 */
	@ResponseBody
	@RequestMapping("/test")
	public Map<String, String> xc() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("test", "yes");
		return m;
	}

	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	public String login(User user, HttpSession session, HttpServletRequest request, Model model) {
		User dbUser = userService.existsUser(user);
		if (dbUser != null) {
			session.setAttribute(Constants.LOGIN_USER, dbUser);
			session.setAttribute(Constants.LOGIN_USER_NAME, dbUser.getUserCommonName());
			if (dbUser.getUserRank() == 1) {
				return "/user/root";
			} else {
				List<Host> hostList = new ArrayList<Host>();
				hostList = HostChannelList.getHostList();
				session.setAttribute("hostList", hostList);
				// 获取温度记录
				Host host = new Host();
				host.setHostGuid(hostList.get(0).getHostGuid());
				host.setHostName(hostList.get(0).getHostName());
				getTemperature(host, session, request, model);

				return "/main/hostList";
			}

		} else {
			// this.addMessage(Constants.LOGIN_ERR);
			// this.addRedirURL("返回登录页面", INDEX_PAGE);
			session.setAttribute("reg", "账号名或者密码错误，请重新登陆！");
		}
		return "user/resultReg";

	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout() throws Exception {
		getSession().invalidate();
		return "../views/index";
	}

	/**
	 * 用户注册
	 */
	@RequestMapping("/reg")
	public String reg(User user, HttpSession session) throws Exception {
		/*
		 * user.setUserPass(Md5Util.getMD5Str(user.getUserName() +
		 * Md5Util.getMD5Str(user.getUserPass())));
		 */
		if (userService.addUser(user) == 1)
			session.setAttribute("reg", "注册成功，请登陆");
		else
			session.setAttribute("reg", "注册失败，用户名重复，请重新注册");
		return "user/resultReg";
	}

	/** 获取用户信息 */
	@RequestMapping("/getUser")
	public String getUser(HttpSession session, Map<String, User> m) throws Exception {
		User user = new User();
		user = (User) session.getAttribute(Constants.LOGIN_USER);
		User u = userService.getUser(user);
		m.put("userMassage", u);
		return "/user/massage";
	}

	/** 修改用户昵称 */
	@RequestMapping("/setUserCommonName")
	public String setUserCommonName(HttpSession session, User ucm, Map<String, User> m) throws Exception {
		User user = new User();
		user = (User) session.getAttribute(Constants.LOGIN_USER);
		user.setUserCommonName(ucm.getUserCommonName());
		userService.setUserCommonName(user);
		User u = userService.getUser(user);
		m.put("userMassage", u);
		session.setAttribute("msg", "修改成功！");
		return "/user/result";
	}

	/** 修改用户密码 */
	@RequestMapping("/setUserPass")
	public String setUserPass(HttpSession session, User ucm, String userPass2, Map<String, User> m) throws Exception {
		User user = new User();
		user = (User) session.getAttribute(Constants.LOGIN_USER);
		User u = userService.getUser(user);
		if (userPass2.equals(u.getUserPass())) {
			session.setAttribute("msg", "修改成功！");
			user.setUserPass(ucm.getUserPass());
			userService.setUserPass(user);
		} else {
			session.setAttribute("msg", "修改失败，输入原密码错误！");
		}
		u = userService.getUser(user);
		m.put("userMassage", u);
		return "/user/result";
	}

	public void getTemperature(Host host, HttpSession session, HttpServletRequest request, Model model) {
		List<Temperature> tl = new ArrayList<Temperature>();
		List<Integer> channelidList = hostService.getChannelidList(host);
		for (int c : channelidList) {
			for (Temperature t : TemperatureList.getTemperatureList())
				if (t.getChannelId() == c) {
					for (int n = 0; n < HostChannelList.getChannelList().size(); ++n) {
						if (HostChannelList.getChannelList().get(n).getChannelId() == c) {
							t.setChannelName(HostChannelList.getChannelList().get(n).getChannelName());
						}
					}
					tl.add(t);
				}
		}

		request.setAttribute("TempertureList", tl);

		for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
			if (HostChannelList.getHostList().get(n).getHostGuid() == host.getHostGuid()) {
				host.setHostCode(HostChannelList.getHostList().get(n).getHostCode());
			}
		}

		List<AlarmRecord> arListToDay = new ArrayList<AlarmRecord>();
		arListToDay = hostService.getAlarmRecordToDay(host);

		session.setAttribute("Host", host);
		session.setAttribute("arListToDay", arListToDay);
	}

}
