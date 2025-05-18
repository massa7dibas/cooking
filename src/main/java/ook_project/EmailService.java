package ook_project;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import static ook_project.Application.logger;

public class EmailService {
    public static void sendInvoice(Invoice invoice, String email) {
        String msg = "Your order " + invoice.getOrderId() + " status is : " + invoice.getStatus() + " with total : "
                + invoice.getTotal();
        sendNotification(email, msg);
    }

    public static void sendNotification(String recipientEmail, String messageText) {
        final String senderEmail = System.getenv("EMAIL_SENDER");
        final String senderPassword = System.getenv("EMAIL_PASSWORD");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            message.setSubject("Notification");
            message.setText(messageText);

            Transport.send(message);

            logger.info("Email sent successfully to " + recipientEmail);
        } catch (MessagingException e) {
            logger.info("Failed to send email.");
        }
    }
}
