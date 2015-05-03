package com.application.utility;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class MailUtility {
	final static String username = "vpc.cloudworks";
	final static String password = "cmpe28312!@qwQW";

	/*public static void main(String[] args) {
		sendMail("VM-Logstash-Test", "CPU", "jainik_vora@live.in", true);
		sendMail("VM-Logstash-Test", "CPU", "jainik_vora@live.in", false);
	}*/
	
	public static void sendMail(String vmName, String property, String emailId, boolean usageHigh) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		String subjectHighUsage = vmName + ": high "+ property + " usage";
		String subjectNormalUsage = vmName + ": back to normal "+ property + " usage";
		
		String emailBodyHighUsage = "Dear User,"
				+ "\n\n We have observed high usage of " + property + " on your virtual machine named: " + vmName + "."
				+ " Please login to the VPC application to see the realtime performace of your virtual machine."
				+ "\n\n Regards, \n VPC Performance Monitoring Team, \n VPC - CloudWorks.";

		String emailBodyNormalUsage = "Dear User,"
				+ "\n\n We have observed that the usage of " + property + " on your virtual machine named: " 
				+ vmName + " is back to normal."
				+ " You may login to the VPC application to see the realtime performace of your virtual machine."
				+ "\n\n Regards, \n VPC Performance Monitoring Team, \n VPC - CloudWorks.";
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {
			if(isValidEmailAddress(emailId)) {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("vpc.cloudworks@gmail.com","VPC-CloudWorks"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
				if(usageHigh) {
					message.setSubject(subjectHighUsage);
					message.setText(emailBodyHighUsage);
				} else {
					message.setSubject(subjectNormalUsage);
					message.setText(emailBodyNormalUsage);
				}
				
				Transport.send(message);
				System.out.println("Email sent successfully to " + emailId);
			} else {
				System.out.println(emailId + " is not a valid email address");
			}
		} catch (MessagingException e) {
			System.out.println("Messaging Exception");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unsupported Encoding Exception");
			e.printStackTrace();
		}
	}
	
	private static boolean isValidEmailAddress(String emailId) {
		boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(emailId);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
	}
}
