package br.com.demo.mail;

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Código para envio de email da procedure PDM0020J
 * 
 */
public class SendMail {

	public int Send(String SMTPServer, String Sender, String Recipient,
			String CcRecipient, String BccRecipient, String Subject,
			String Body, String ErrorMessage[], String Attachments) {
		
		int ErrorStatus = 0;
		
                Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.user", "naousoemail@gmail.com");
                props.put("mail.password", "fsa!22868");
                props.put("mail.smtp.auth", "true");
                
		Session session = Session.getDefaultInstance(props);
		try {
			MimeMessage msg = new MimeMessage(session);
			{
				InternetAddress[] TheAddresses = InternetAddress.parse(Sender);
				msg.addFrom(TheAddresses);
			}
			{
				InternetAddress[] TheAddresses = InternetAddress
						.parse(Recipient);
				msg.addRecipients(Message.RecipientType.TO, TheAddresses);
			}
			if (null != CcRecipient) {
				InternetAddress[] TheAddresses = InternetAddress
						.parse(CcRecipient);
				msg.addRecipients(Message.RecipientType.CC, TheAddresses);
			}
			if (null != BccRecipient) {
				InternetAddress[] TheAddresses = InternetAddress
						.parse(BccRecipient);
				msg.addRecipients(Message.RecipientType.BCC, TheAddresses);
			}
			msg.setSubject(Subject);
			Multipart mp = new MimeMultipart();
			{
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setText(Body);
				mp.addBodyPart(mbp);
			}
			if (null != Attachments) {
				int StartIndex = 0, PosIndex = 0;
				while (-1 != (PosIndex = Attachments.indexOf("///", StartIndex))) {
					// Create and fill other message parts;
					MimeBodyPart mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(
							Attachments.substring(StartIndex, PosIndex));
					mbp.setDataHandler(new DataHandler(fds));
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
					PosIndex += 3;
					StartIndex = PosIndex;
				}
				// Last, or only, attachment file;
				if (StartIndex < Attachments.length()) {
					MimeBodyPart mbp = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(
							Attachments.substring(StartIndex));
					mbp.setDataHandler(new DataHandler(fds));
					mbp.setFileName(fds.getName());
					mp.addBodyPart(mbp);
				}
			}
			msg.setContent(mp);
			msg.setSentDate(new Date());
			Transport.send(msg);
			
		} catch (MessagingException MsgException) {
			System.out.println(MsgException);
			
			ErrorMessage[0] = MsgException.toString();
			Exception TheException = null;
			if ((TheException = MsgException.getNextException()) != null)
				ErrorMessage[0] = ErrorMessage[0] + "\n"
						+ TheException.toString();
			ErrorStatus = 1;
		}
		return ErrorStatus;
	}
}