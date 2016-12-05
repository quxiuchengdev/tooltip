package com.qxcwl.tooltip.common.utils.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;


/**
 * @Author 曲修成
 * @ClassName EmailHandle
 * @Description
 * @Date 2016-11-15 18:28:00
 */
public class EmailHandle {

	private static final Logger logger = LoggerFactory.getLogger(EmailHandle.class);

	/** 邮件对象 **/
	private MimeMessage mimeMsg;

	/** 发送邮件的Session会话 **/
	private Session session;

	/** 邮件发送时的一些配置信息的一个属性对象 **/
	private Properties props;

	/** 发件人的用户名 **/
	private String sendUserName;

	/** 发件人密码 **/
	private String sendUserPass;

	/** 附件添加的组件 **/
	private Multipart mp;

	/** 存放附件文件 **/
	private List<FileDataSource> files = new LinkedList<FileDataSource>();


	public EmailHandle(String smtp) {
		sendUserName = "";
		sendUserPass = "";
		setSmtpHost(smtp);
		createMimeMessage();
	}

	private void setSmtpHost(String hostName) {
		if (props == null){
			props = System.getProperties();
		}
		props.put("mail.smtp.host", hostName);
	}

	public boolean createMimeMessage() {
		try {
			/**用props对象来创建并初始化session对象**/
			session = Session.getDefaultInstance(props, null);
			/**用session对象来创建并初始化邮件对象**/
			mimeMsg = new MimeMessage(session);
			/**生成附件组件的实例**/
			mp = new MimeMultipart();
			return true;
		} catch (Exception e) {
			System.err.println("获取邮件会话对象时发生错误！" + e);
			return false;
		}

	}

	/**
	 * 设置SMTP的身份认证
	 */
	public void setNeedAuth(boolean need) {
		if (props == null){ props = System.getProperties();}
		if (need){
			props.put("mail.smtp.auth", "true");
		}else{
			props.put("mail.smtp.auth", "false");
		}

	}

	/**
	 * 进行用户身份验证时，设置用户名和密码
	 */
	public void setNamePass(String name, String pass) {
		sendUserName = name;
		sendUserPass = pass;
	}

	/**
	 * 设置邮件主题
	 *
	 * @param mailSubject
	 * @return
	 */
	public boolean setSubject(String mailSubject) {
		try {
			mimeMsg.setSubject(mailSubject);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * 设置邮件内容,并设置其为文本格式或HTML文件格式，编码方式为UTF-8
	 * @param mailBody
	 * @return
	 */
	public boolean setBody(String mailBody) {
		try {
			BodyPart bp = new MimeBodyPart();
			bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + mailBody, "text/html;charset=UTF-8");
			/**在组件上添加邮件文本**/
			mp.addBodyPart(bp);
		} catch (Exception e) {
			System.err.println("设置邮件正文时发生错误！" + e);
			return false;
		}
		return true;
	}

	/**
	 * 增加发送附件
	 * @param filename 邮件附件的地址，只能是本机地址而不能是网络地址，否则抛出异常
	 * @return
	 */
	public boolean addFileAffix(String filename) {
		try {
			BodyPart bp = new MimeBodyPart();
			FileDataSource fileds = new FileDataSource(filename);
			bp.setDataHandler(new DataHandler(fileds));
			/**解决附件名称乱码**/
			bp.setFileName(MimeUtility.encodeText(fileds.getName(), "UTF-8",null));
			/**添加附件**/
			mp.addBodyPart(bp);
			files.add(fileds);
			return true;
		} catch (Exception e) {
			System.err.println("增加邮件附件<" + filename + ">时发生错误：" + e);
			return false;
		}

	}

	/**
	 * 删除添加的附件
	 * @return
	 */
	public boolean delFileAffix() {
		try {
			FileDataSource fileds = null;
			for (Iterator<FileDataSource> it = files.iterator(); it.hasNext();) {
				fileds = it.next();
				if (fileds != null && fileds.getFile() != null) {
					fileds.getFile().delete();
				}
			}
			return true;
		} catch (Exception e) {
			System.err.println("删除邮件附件发生错误：" + e);
			return false;
		}

	}

	/**
	 * 设置发件人地址
	 * @param from   发件人地址
	 * @return
	 */
	public boolean setFrom(String from) {
		try {
			mimeMsg.setFrom(new InternetAddress(from));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置收件人地址
	 * @param to 收件人的地址
	 * @return
	 */
	public boolean setTo(String to) {
		try {
			if (to == null)
				return false;

			mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 设置收件人地址
	 * @param toList    收件人的地址列表
	 * @return
	 */
	public boolean setToList(String toList) {
		try {
			if (toList == null)
				return false;
			InternetAddress[] iaToList = InternetAddress.parse(toList);
			mimeMsg.setRecipients(Message.RecipientType.TO, iaToList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送抄送
	 * @param copyto
	 * @return
	 */
	public boolean setCopyTo(String copyto) {
		try {
			if (copyto == null)
				return false;
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC,InternetAddress.parse(copyto));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送抄送
	 * @param copytoList
	 * @return
	 */
	public boolean setCopyToList(String copytoList) {
		try {
			if (copytoList == null)
				return false;
			InternetAddress[] iacopytoList = InternetAddress.parse(copytoList);
			mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC, iacopytoList);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 发送邮件
	 * @return
	 */
	public boolean sendEmail() throws Exception {
		logger.debug("正在发送邮件....");

		mimeMsg.setContent(mp);
		mimeMsg.saveChanges();

		Session mailSession = Session.getInstance(props, null);
		mailSession.setDebug(true);
		Transport transport = mailSession.getTransport("smtp");

		/** 连接邮件服务器并进行身份验证 **/
		transport.connect((String) props.get("mail.smtp.host"), sendUserName, sendUserPass);

		/** 发送邮件 **/
		transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
		transport.close();
		logger.debug("发送邮件成功！");
		return true;
	}

}
