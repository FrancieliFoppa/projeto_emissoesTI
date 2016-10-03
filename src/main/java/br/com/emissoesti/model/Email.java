package br.com.emissoesti.model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
	public class Email extends HttpServlet {
 
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
			PrintWriter out = res.getWriter();
			res.setContentType("text/html");
			try {
			 
				String to = req.getParameter("to");
				 
				String from = "teste@dominio.com.br";
				 
				Properties props = new Properties();
				props.put("mail.smtp.host", "localhost");
				Session session = Session.getInstance(props, null);
				 
				MimeMessage message = new MimeMessage(session);
				 
				message.setFrom(new InternetAddress(from));
				Address toAddress = new InternetAddress(to);
				message.addRecipient(Message.RecipientType.TO, toAddress);
				 
				message.setSubject("teste de envio de e-mails");
				 
				message.setContent("este eh um teste de envio", "text/plain");
				 
				Transport.send(message);
				 
				out.println("E-mail enviado");
			}
				catch (MessagingException e) {
				out.println("Email nao pode ser enviado! " + e.getMessage());
				}
		}
}
