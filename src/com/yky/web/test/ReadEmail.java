package com.yky.web.test;

import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import com.yky.web.util.StringUtil;

public class ReadEmail {
	private static StringBuffer bodyText = new StringBuffer(); // 存放邮件内容的StringBuffer对象

	public static void getMailContent(Part part) throws Exception {
		String contentType = part.getContentType();
		//  获得邮件的MimeType类型

		int nameIndex = contentType.indexOf("name");
		boolean conName = false;
		if (nameIndex != -1) {
			conName = true;
		}

		if (part.isMimeType("text/plain") && conName == false) {
			// text/plain 类型
			// bodyText.append((String) part.getContent());
		} else if (part.isMimeType("text/html") && conName == false) {
			// text/html 类型
			bodyText.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			// multipart/*
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			//getMailContent((Part) part.getContent());
		} else {

		}
	}

	public static String getError() {
		String imapserver = "imap.126.com"; // 邮件服务器
		String user = "bs_632237845@126.com";
		String password = "bs514103"; // 根据自已的密码修改
		// 获取默认会话
		Properties prop = System.getProperties();
		prop.put("mail.imap.host", imapserver);

		prop.put("mail.imap.auth.plain.disable", "true");
		Session mailsession = Session.getInstance(prop, null);
		mailsession.setDebug(false); // 是否启用debug模式
		IMAPFolder folder = null;
		IMAPStore store = null;

		try {
			store = (IMAPStore) mailsession.getStore("imap"); // 使用imap会话机制，连接服务器
			store.connect(imapserver, user, password);
			folder = (IMAPFolder) store.getFolder("INBOX"); // 收件箱
			// 使用只读方式打开收件箱
			folder.open(Folder.READ_WRITE);
			// 获取总邮件数

			Message[] msgs = folder.getMessages();

			for (int i = 0; i < msgs.length; i++) {
				MimeMessage mm = (MimeMessage) msgs[i];
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
				String strSentDate = format.format(mm.getSentDate());
				String from = mm.getFrom()[0].toString();
				if (from.equals("604504102@163.com") && strSentDate.equals(StringUtil.getByCalendar(0))) {
					getMailContent((Part) msgs[i]);
					System.out.println(bodyText);
				}
			}
		} catch (Exception ex) {
			System.err.println("不能以读写方式打开邮箱!");
			ex.printStackTrace();
		} finally {
			// 释放资源
			try {
				if (folder != null)
					folder.close(true); // 退出收件箱时,删除做了删除标识的邮件
				if (store != null)
					store.close();
			} catch (Exception bs) {
				bs.printStackTrace();
			}
		}
		return bodyText.toString();
	}

	public static void main(String[] args) {
		getError();
	}
}
