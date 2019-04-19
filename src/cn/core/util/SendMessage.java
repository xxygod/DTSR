package cn.core.util;

import java.util.List;
import javax.swing.JOptionPane;
import cn.core.util.serialException.*;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SendMessage {
	private static SendMessage instance = null;
	private List<String> commList = null; // 保存可用端口号
	private static SerialPort serialPort = null; // 保存串口对象
	private String responseDate = null;// 返回数据
	private static int type = 0;// 执行状态
	private static List<String> phoneNumberList = null;
	private static int number = 0;
	private static int numberSize = 0;
	private static String phoneNumber = null;
	private static String messageDate = null;

	public static SendMessage getInstance() throws TooManyListeners {
		if (instance == null)
			instance = new SendMessage();
		return instance;
	}
	
	public SendMessage() throws TooManyListeners {
		super();
		startPort();
	}

	public SendMessage(List<String> phoneNumberList, String messageDate) throws TooManyListeners {
		super();
		startPort();
		SendMessage.phoneNumberList = phoneNumberList;
		if (phoneNumberList.get(0) != null)
			phoneNumber = phoneNumberList.get(0);
		numberSize = phoneNumberList.size();
		SendMessage.messageDate = messageDate;
	}

	public static void setPhoneNumberMessageDate(List<String> phoneNumberList, String messageDate) {
		SendMessage.phoneNumberList = phoneNumberList;
		if (phoneNumberList.get(0) != null)
			phoneNumber = phoneNumberList.get(0);
		numberSize = phoneNumberList.size();
		SendMessage.messageDate = messageDate;
		number = 0;
	}

	public void startPort() throws TooManyListeners {
		// 获取串口名称
		String commName = null;
		commList = SerialTool.findPort(); // 程序初始化时就扫描一次有效串口
		if (commList == null || commList.size() < 1) {
			JOptionPane.showMessageDialog(null, "没有搜索到有效串口！", "错误", JOptionPane.INFORMATION_MESSAGE);
		} else {
			commName = commList.get(0);
		}
		// 获取指定端口名及波特率的串口对象
		try {
			serialPort = SerialTool.openPort(commName, 9600);
			SerialTool.addListener(serialPort, new SerialListener());
			System.out.println("串口打开成功");
		} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 在该串口对象上添加监听器
	}

	public void closePort() {
		SerialTool.closePort(serialPort);
		System.out.println("关闭成功");
	}

	public static void startSendDate() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure,
			ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure, TooManyListeners {
		sendDate0();
	}

	private static void sendDate0() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
		// System.out.println("设置发信方式");
		String aT = "AT+CMGF=1\n";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
		type = 0;
	}

	private void sendDate1() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
		// System.out.println("设置发信地址");
		String aT = "AT+CSMP=17,167,2,25\n";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
		type = 1;
	}

	private void sendDate2() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
		// System.out.println("设置字符编码");
		String aT = "AT+CSCS=\"UCS2\"\n";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
		type = 2;
	}

	private void sendDate3() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
		if (number < numberSize) {
			phoneNumber = phoneNumberList.get(number);
		} else {
			return;
		}
		// System.out.println("设置发送号码");
		String aT = "AT+CMGS=\"" + cnToUnicode(phoneNumber) + "\"\n";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
		type = 3;

	}

	private void sendDate4() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
		// System.out.println("设置发送短信");
		String aT = cnToUnicode(messageDate) + "";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
		if (number < numberSize) {
			type = 4;
		} else {
			type = 5;
			// System.out.println("发送完毕");
		}
	}

	public static void sendRestart() throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure{
		String aT ="";
		byte[] byteAT = aT.getBytes();
		SerialTool.sendToPort(serialPort, byteAT);
	}
	
	private class SerialListener implements SerialPortEventListener {
		String dateStr = "";

		/**
		 * 处理监控到的串口事件
		 */
		public void serialEvent(SerialPortEvent serialPortEvent) {

			switch (serialPortEvent.getEventType()) {

			case SerialPortEvent.BI: // 10 通讯中断
				JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
				break;

			case SerialPortEvent.OE: // 7 溢位（溢出）错误

			case SerialPortEvent.FE: // 9 帧错误

			case SerialPortEvent.PE: // 8 奇偶校验错误

			case SerialPortEvent.CD: // 6 载波检测

			case SerialPortEvent.CTS: // 3 清除待发送数据

			case SerialPortEvent.DSR: // 4 待发送数据准备好了

			case SerialPortEvent.RI: // 5 振铃指示

			case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
				break;

			case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据

				// System.out.println("found data");
				byte[] data = null;

				try {
					if (serialPort == null) {
						JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
					} else {
						data = SerialTool.readFromPort(serialPort); // 读取数据，存入字节数组
						dateStr += new String(data);
						 System.out.println(new String(data));

						// 自定义解析过程
						if (data == null || data.length < 1) { // 检查数据是否读取正确
							JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误",
									JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						} else {
							String dataValid = dateStr; // 有效数据（用来保存原始数据字符串去除最开头*号以后的字符串）
							//System.out.println(dataValid);
							if (dataValid != null) {
								responseDate = dataValid;
								switch (type) {
								case 0: {
									if (responseDate.indexOf("OK\r") != -1) {
										dateStr = null;
										sendDate1();
									} else if (responseDate.indexOf("ERROR") != -1)
										sendDate0();
								}
									break;
								case 1: {
									if (responseDate.indexOf("OK\r") != -1) {
										dateStr = null;
										sendDate2();
									} else if (responseDate.indexOf("ERROR") != -1)
										sendDate1();
								}
									break;
								case 2: {
									if (responseDate.indexOf("OK\r") != -1) {
										dateStr = null;
										sendDate3();
									} else if (responseDate.indexOf("ERROR") != -1)
										sendDate2();
								}
									break;
								case 3: {
									if (responseDate.indexOf(">") != -1) {
										dateStr = null;
										sendDate4();
									} else if (responseDate.indexOf("ERROR") != -1)
										sendDate3();
								}
									break;
								case 4: {
									if (responseDate.indexOf("OK\r") != -1) {
										dateStr = null;
										++number;
										sendDate3();
									} else if (responseDate.indexOf("ERROR") != -1) {
										sendDate3();
									}
								}
									break;
								}
							}
						}
					}

				} catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
					JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0); // 发生读取错误时显示错误信息后退出系统
				} catch (SendDataToSerialPortFailure e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;

			}
		}
	}

	// 工具方法
	private static String cnToUnicode(String cn) {
		char[] chars = cn.toCharArray();
		String returnStr = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] < 255 && chars[i] >= 0) {
				returnStr += "00" + Integer.toString(chars[i], 16);
			} else
				returnStr += "" + Integer.toString(chars[i], 16);
		}
		return returnStr;
	}

	public static void main(String[] asdf) throws SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure,
			ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure, TooManyListeners {
/*		List<String> numberList = new ArrayList<String>();
		numberList.add("17674031815");
		SendMessage.setPhoneNumberMessageDate(numberList, "6条");
		SendMessage.startSendDate();*/
		// demo.closePort();
	}
}
