package cn.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.Cache.CabinetDiagramPositionList;
import cn.Cache.HostChannelList;
import cn.Cache.TemperatureList;
import cn.Cache.ThreadTime;
import cn.core.Constants;
import cn.core.util.SendMessage;
import cn.core.util.ReadWriteLock;
import cn.core.util.serialException.ReadDataFromSerialPortFailure;
import cn.core.util.serialException.SendDataToSerialPortFailure;
import cn.core.util.serialException.SerialPortInputStreamCloseFailure;
import cn.core.util.serialException.SerialPortOutputStreamCloseFailure;
import cn.core.util.serialException.TooManyListeners;
import cn.model.AlarmRecord;
import cn.model.CabinetDiagram;
import cn.model.Channel;
import cn.model.Condition;
import cn.model.Host;
import cn.model.Position;
import cn.model.PositionList;
import cn.model.Temperature;
import cn.model.User;
import cn.service.HostService;
import cn.service.UserService;

@Controller
@RequestMapping("/host")
public class  HostAction extends BaseAction {

	@Autowired
	private UserService userService;

	@Autowired
	private HostService hostService;

	ReadWriteLock enterGameQueue = new ReadWriteLock();

	List<Temperature> temperatureList;

	/**
	 * 测试代码
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public Map<String, String> xc(HttpServletResponse response) {
		System.out.println("test");
		Map<String, String> m = new HashMap<String, String>();
		m.put("test", "yes");
		return m;
	}

	@ResponseBody
	@RequestMapping("/login")
	public String login(User user, HttpSession session) {
		System.out.println("hostLogin");
		User dbUser = userService.existsUser(user);
		if (dbUser != null) {
			session.setAttribute(Constants.LOGIN_USER, dbUser);
			session.setAttribute(Constants.LOGIN_USER_NAME, dbUser.getUserCommonName());
			session.setMaxInactiveInterval(-1);
			return "login:1";
		} else {
			return "login:0";
		}
	}

	@ResponseBody
	@RequestMapping("/logout")
	public String logout(Host host) throws Exception {
		int i = 0;
		for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
			if (HostChannelList.getHostList().get(n).getHostGuid() == host.getHostGuid()) {
				HostChannelList.getHostList().get(n).setHostCode(0);
				i = 1;
			}
		}

		getSession().invalidate();
		return "logout:" + i;
	}

	@ResponseBody
	@RequestMapping("/getHostChannelList")
	public Map<String, List<Channel>> getHostChannelList(Host host) {

		List<Channel> channelList = new ArrayList<Channel>();
		for (Channel tem : HostChannelList.getChannelList()) {
			if (tem.getHostGuid() == host.getHostGuid())
				channelList.add(tem);
		}
		Map<String, List<Channel>> m = new HashMap<String, List<Channel>>();
		m.put("ChannelList", channelList);
		return m;
	}

	@RequestMapping("/getHost")
	public String getHost(Model model) {
		List<Host> hostList = new ArrayList<Host>();
		hostList = HostChannelList.getHostList();
		model.addAttribute("hostList", hostList);
		return "/main/hostList";
	}

	@ResponseBody
	@RequestMapping("/addHost")
	public String addHost(HttpSession session, Host host) {
		User user = new User();
		user = (User) session.getAttribute(Constants.LOGIN_USER);
		host.setUserId(user.getUserId());
		host.setThreadTime(new ThreadTime(host.getHostGuid()));
		hostService.addHost(host);
		HostChannelList.getHostList().add(host);
		return "addHost:1";
	}

	@ResponseBody
	@RequestMapping("/setHostName")
	public String setHostName(Host host) {
		hostService.setHostName(host);
		for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
			if (HostChannelList.getHostList().get(n).getHostGuid() == host.getHostGuid()) {
				HostChannelList.getHostList().get(n).setHostName(host.getHostName());
			}
		}
		return "setHostName:1";
	}

	@ResponseBody
	@RequestMapping("/setHostCode")
	public String setHostCode(Host host) {
		int i = 0;
		for (int n = 0; n < HostChannelList.getHostList().size(); ++n) {
			if (HostChannelList.getHostList().get(n).getHostGuid() == host.getHostGuid()) {
				if (HostChannelList.getHostList().get(n).getHostCode() != 1)
					HostChannelList.getHostList().get(n).getThreadTime().start();
				try {
					enterGameQueue.lockWrite();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				HostChannelList.getHostList().get(n).setHostCode(host.getHostCode());
				try {
					enterGameQueue.unlockWrite();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i = 1;
			}
		}
		return "setHostCode:" + i;
	}

	@ResponseBody
	@RequestMapping("/addChannel")
	public String addChannel(Channel channel) {
		hostService.addChannel(channel);
		HostChannelList.getChannelList().add(channel);
		return "addChannel:1";
	}
	// 写入内存??????   没看懂
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/deleteChannel") public String deleteChannel(Channel
	 * channel) { hostService.deleteChannel(channel); return "deleteChannel:1";
	 * }
	 */

	@ResponseBody
	@RequestMapping("/setChannelName")
	public String setChannelName(Channel channel) {
		hostService.setChannelName(channel);
		for (int n = 0; n < HostChannelList.getChannelList().size(); ++n) {
			if (HostChannelList.getChannelList().get(n).getChannelId() == channel.getChannelId()) {
				HostChannelList.getChannelList().get(n).setChannelName(channel.getChannelName());
			}
		}
		return "setChannelName:1";
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/setChannelColor") public String setChannelColor(Channel
	 * channel) { int i = 0; for (int n = 0; n <
	 * HostChannelList.getChannelList().size(); ++n) { if
	 * (HostChannelList.getChannelList().get(n).getChannelId() ==
	 * channel.getChannelId()) {
	 * HostChannelList.getChannelList().get(n).setChannelColor(channel.
	 * getChannelColor()); i = 1; } } return "setChannelColor:" + i; }
	 */

	@ResponseBody
	@RequestMapping("/setChannelCode")
	public String setChannelCode(Channel channel) {
		hostService.setChannelCode(channel);
		int i = 0;
		for (int n = 0; n < HostChannelList.getChannelList().size(); ++n) {
			if (HostChannelList.getChannelList().get(n).getChannelId() == channel.getChannelId()) {
				HostChannelList.getChannelList().get(n).setChannelCode(channel.getChannelCode());
				i = 1;
			}
		}
		return "setChannelCode:" + i;
	}

	@ResponseBody
	@RequestMapping("/setTemperature")
	public String setTemperature(Temperature temperature) {
		// 判断主机是否在线
		/*
		 * for (int n = 0; n < HostChannelList.getHostList().size(); ++n) { if
		 * (HostChannelList.getHostList().get(n).getHostGuid() ==
		 * temperature.getHostGuid()) {
		 * if(HostChannelList.getHostList().get(n).getHostCode()!=1) return
		 * "setTemperature:-1"; } }
		 */

		int i = 0, n = 0;
		temperature.initializationTemperatureStr();
		for (n = 0; n < TemperatureList.getTemperatureList().size(); ++n) {
			if (TemperatureList.getTemperatureList().get(n).getChannelId() == temperature.getChannelId()) {
				temperature.setNumber(TemperatureList.getTemperatureList().get(n).getNumber() + 1);
				if (temperature.getNumber() % 2 == 1) {
					List<Temperature> temList = new ArrayList<Temperature>();
					temList.add(temperature);
					Host host = new Host();
					host.setHostGuid(temperature.getHostGuid());
					hostService.deleteTemperature(host);
					hostService.addTemperature(temList);
				}
				TemperatureList.getTemperatureList().set(n, temperature);
				++i;
			}
		}
		if (i == 0) {
			temperature.setNumber(0);
			TemperatureList.getTemperatureList().add(temperature);
			++i;
		}

		for (n = 0; n < HostChannelList.getHostList().size(); ++n) {
			if (HostChannelList.getHostList().get(n).getHostGuid() == temperature.getHostGuid()) {
				if (HostChannelList.getHostList().get(n).getThreadTime().getCode() == false)
					HostChannelList.getHostList().get(n).getThreadTime().start();
				else
					HostChannelList.getHostList().get(n).getThreadTime().refresh();
			}
		}
		return "setTemperature:" + i;
	}

	@RequestMapping("/getTemperature")
	public String getTemperature(Host host, HttpSession session, HttpServletRequest request, Model model) {
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
		return "main/hostList";
	}

	@ResponseBody
	@RequestMapping("/addTemperature")
	public String addTemperature(Host host) {
		/*
		 * List<Temperature> temList = new ArrayList<Temperature>(); for
		 * (Temperature tem : TemperatureList.getTemperatureList()) { if
		 * (tem.getHostGuid() == host.getHostGuid()) temList.add(tem); }
		 * hostService.deleteTemperature(host);
		 * hostService.addTemperature(temList);
		 */
		return "addTemperature:1";
	}

	@ResponseBody
	@RequestMapping("/addAlarmRecord")
	public String addAlarmRecord(AlarmRecord alarmRecord) {
		hostService.addAlarmRecord(alarmRecord);
		return "addAlarmRecord:1";
	}

	@RequestMapping("/getHostHistory")
	public String getHostHistory(Host host, Model model, HttpServletResponse response) {
		List<Channel> channelList = new ArrayList<Channel>();
		for (Channel tem : HostChannelList.getChannelList()) {
			if (tem.getHostGuid() == host.getHostGuid())
				channelList.add(tem);
		}
		model.addAttribute("channelList", channelList);
		return "main/hostListHistory";
	}

	@RequestMapping("/getAlarmRecord")
	public String getAlarmRecord(Model model, Condition condition) {
		List<Channel> channelList = new ArrayList<Channel>();
		if (condition.getHostGuid().length() != 0)
			for (Channel tem : HostChannelList.getChannelList()) {
				if (tem.getHostGuid() == Integer.parseInt(condition.getHostGuid()))
					channelList.add(tem);
			}
		model.addAttribute("channelList", channelList);
		List<AlarmRecord> arList = new ArrayList<AlarmRecord>();
		arList = hostService.getAlarmRecord(condition);
		model.addAttribute("arList", arList);
		return "main/hostListHistory";
	}

	@RequestMapping("/getHostTemperatureList")
	public String getHostTemperatureList(Model model, Host host) {
		List<Temperature> temperatureList = new ArrayList<Temperature>();
		temperatureList = hostService.getHostTemperatureList(host);
		model.addAttribute("temperatureList", temperatureList);
		model.addAttribute("host", host);
		return "main/hostListTemperature";
	}

	@RequestMapping("/getHostTimeTemperatureList")
	public String getHostTimeTemperatureList(HttpServletResponse response, Model model, Condition condition) {
		List<Temperature> temperatureList = new ArrayList<Temperature>();
		temperatureList = hostService.getHostTimeTemperatureList(condition);
		Host host = new Host();
		host.setHostGuid(Integer.parseInt(condition.getHostGuid()));
		this.temperatureList = temperatureList;
		model.addAttribute("temperatureList", temperatureList);
		model.addAttribute("host", host);
		return "main/hostListTemperature";
	}

	@RequestMapping("/getHostTimeTemperatureListExcel")
	public void getHostTimeTemperatureListExcel(HttpServletResponse response) {
		writeExcel(response, "温度数据表", temperatureList);
	}

	@RequestMapping("/getHostTemperature")
	public String getHostTemperature(Model model, Temperature temId) {
		Temperature temperature = new Temperature();
		temperature = hostService.getHostTemperature(temId);
		temperature.initializationTemperatureStr();
		model.addAttribute("temperature", temperature);
		return "main/hostChannelTemperature";
	}

	@RequestMapping("/getCabinetDiagram")
	public String getCabinetDiagram(Model model, Host host) {
		CabinetDiagram cabinetDiagram = new CabinetDiagram();
		cabinetDiagram = hostService.getCabinetDiagram(host);
		model.addAttribute("cabinetDiagram", cabinetDiagram);

		List<PositionList> positionListLis = new ArrayList<PositionList>();
		List<Position> positionListA = new ArrayList<Position>();
		List<Position> positionListB = new ArrayList<Position>();
		List<Position> positionListC = new ArrayList<Position>();
		positionListLis = CabinetDiagramPositionList.getPositionListList();
		for (PositionList positionList : positionListLis) {
			if (positionList.getHostGuid() == host.getHostGuid()) {
				for (Position position : positionList.getPositionList()) {
					if (position.getType() == 2)
						positionListC.add(position);
					else if (position.getType() == 3)
						positionListB.add(position);
					else if (position.getType() == 4)
						positionListA.add(position);
				}
			}
		}
		model.addAttribute("positionListA", positionListA);
		model.addAttribute("positionListB", positionListB);
		model.addAttribute("positionListC", positionListC);

		return "main/hostListCabinetDiagram";
	}

	@ResponseBody
	@RequestMapping("/setCabinetDiagramPositionList")
	public String setCabinetDiagramPositionList(@RequestBody PositionList positionList) {
		System.out.println(positionList);
		List<PositionList> positionListLis = new ArrayList<PositionList>();
		positionListLis = CabinetDiagramPositionList.getPositionListList();
		int idCode = 0;
		for (int n = 0; n < positionListLis.size(); ++n) {
			if (positionListLis.get(n).getHostGuid() == positionList.getHostGuid()) {
				positionListLis.set(n, positionList);
				++idCode;
			}
		}
		if (idCode == 0) {
			positionListLis.add(positionList);
		}
		CabinetDiagramPositionList.setPositionListList(positionListLis);
		return "setCabinetDiagramPositionList:1";
	}
	
	@ResponseBody
	@RequestMapping("/sendMessage")
	public String sendMessage(@RequestBody AlarmRecord alarmRecord) throws TooManyListeners, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure, ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {
		SendMessage.setPhoneNumberMessageDate(alarmRecord.getPhoneNumberList(), alarmRecord.getMessageDate());
		SendMessage.sendRestart();
		SendMessage.startSendDate();
		return "sendMessage:1";
	}
	

	public void writeExcel(HttpServletResponse response, String name, List<Temperature> temperatureList) {
		// 创建HSSFWorkbook对象(excel的文档对象)
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
		// 建立新的sheet对象（excel的表单）
		HSSFSheet sheet = wb.createSheet(name);
		// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		// 设置单元格内容
		cell.setCellValue(name);
		// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
		// 在sheet里创建第二行
		HSSFRow row2 = sheet.createRow(1);
		// 创建单元格并设置单元格内容
		row2.createCell(0).setCellValue("编号");
		row2.createCell(1).setCellValue("主机号");
		row2.createCell(2).setCellValue("通道号");
		row2.createCell(3).setCellValue("通道名");
		row2.createCell(4).setCellValue("时间");
		row2.createCell(5).setCellValue("温度数据");
		// 在sheet里创建第三行
		int i = 2;
		for (Temperature t : temperatureList) {
			HSSFRow row3 = sheet.createRow(i);
			row3.createCell(0).setCellValue(t.getTemId());
			row3.createCell(1).setCellValue(t.getHostGuid());
			row3.createCell(2).setCellValue(t.getChannelId());
			row3.createCell(3).setCellValue(t.getChannelName());
			row3.createCell(4).setCellValue(t.getTemTime());
			System.out.println(t.getTemStr().toString());
			row3.createCell(5).setCellValue(t.getTemStr().toString());
			++i;
		}
		// 输出Excel文件

		try {
			ServletOutputStream output = response.getOutputStream();
			response.reset();
			response.setHeader("Content-disposition", "attachment; filename=temperatureDate.xls");
			response.setContentType("application/msexcel");
			wb.write(output);
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
