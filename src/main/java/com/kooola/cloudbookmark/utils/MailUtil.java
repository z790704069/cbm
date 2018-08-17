package com.kooola.cloudbookmark.utils;

import com.kooola.cloudbookmark.common.constants.NormalConstant;
import org.omg.CORBA.NO_IMPLEMENT;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by march on 2018/8/16.
 */
public class MailUtil {
    /**
     *
     * @param receiveMailAccount 接收方邮箱账号 example:****@gmail.com
     * @param msg 邮件内容
     */
    public static void sendMailMessage(String receiveMailAccount, String msg) throws Exception{
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", NormalConstant.CBM_MAIL_STMP_ADDRESS);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);

        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(NormalConstant.CBM_MAIL_ACCOUNT, NormalConstant.CBM_MAIL_NICKNAME, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMailAccount, "尊敬的云书签用户", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(NormalConstant.CBM_MAIL_NICKNAME, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）

        message.setContent(cbmMailMsg(msg), "text/html;charset=UTF-8");

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        Transport transport = session.getTransport();

        transport.connect(NormalConstant.CBM_MAIL_ACCOUNT, NormalConstant.CBM_MAIL_ACCOUNT_PASSWD);

        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    /**
     * 修饰原始邮件内容，增加用户体验
     * @param msg
     * @return
     */
    private static String cbmMailMsg(String msg){
        String content = "<title>云书签</title>";
        content = content + "<p>您好，欢迎使用云书签！此邮件为官方激活邮件，在开始使用前，请点击下面的链接进行激活。</p>";
        content = content + "<p><a href=" + msg + ">点击这里激活</a></p>";
        return content;
    }

    public static void main(String[] args) throws Exception{
        MailUtil.sendMailMessage("644041867@qq.com", "云书签mail测试。  have fun tek");
    }
}
