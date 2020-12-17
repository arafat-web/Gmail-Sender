/*
                        Universal Gmail Sender  
                         by Arafat Hossian Ar    
                            Version: 1.0       
                               Enjoy :) 

Name: Uniersal Gmail Sender
Author: Arafat Hossain Ar
Date: 17/12/2020



/////////////////////////////////////////////////////////////////////

            YOUR INFORMATION IS TOTLAY SAFE AND SOUND
    MAIL IS SENDING BY SSL SOCKET. SO THERE IS NO ANY SECUIRUTY ISSUE.

      Before sending messagae without any issue you have to give permission to your google account
      Click on this link and click on turn on radio button to allow users to send mail from unknown location. 
             https://www.google.com/settings/security/lesssecureapps
//////////////////////////////////////////////////////////////////

 */
package gmailsender;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Arafat Hossain Ar
 */
public class GmailSender {

    public void send() throws IOException {

        System.out.println("/////////////////////////////////"
                + "\n///   Universal Gmail Sender  ///"
                + "\n///   by Arafat Hossian Ar    ///"
                + "\n///       Version: 1.0        ///"
                + "\n///         Enjoy :)          ///"
                + "\n//////////////////////////////////");

        Scanner input = new Scanner(System.in);
/////////////////////////////////////////////////////////////////////////////////////////
        //Time and Date//
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        LocalDate currentDate = LocalDate.parse(date);
        int day = currentDate.getDayOfMonth();
        Month month = currentDate.getMonth();
        int year = currentDate.getYear();

        //Get Time and Date
        String getdate = "Date: " + day + " " + month + " " + year + " ";
        String gettime = "Time: " + DateTimeFormatter.ofPattern("hh.mm a").format(LocalTime.now());
////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////
// Get data from user
        //user mail id
        System.out.println("Enter your Gmail ID(full): ");
        String fromMail = input.nextLine();
        //user mail password
        System.out.println("Enter the Password of your Gmail ID: ");
        String password = input.nextLine();
        //mail of user to send
        System.out.println("Enter the Recipient Gmail ID: ");
        String toMail = input.nextLine();
        //mail subject
        System.out.println("Subject: ");
        String mailSubject = input.nextLine();
        //mail Messagae
        System.out.println("Your Messagae: ");
        String setMailText = input.nextLine();
//////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////// 
//Properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
/////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////
//Create session
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail, password);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////////////////////////////////////////////////////////
//Creating mails to send
        System.out.println("Sending.......");
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
            message.setFrom(new InternetAddress(fromMail));
            message.setSubject(mailSubject);
            message.setText(setMailText);

            Transport.send(message);

            System.out.println("Message sent successfully");
            System.out.println("To: " + toMail
                    + "\nFrom: " + fromMail
                    + "\nSubject: " + mailSubject
                    + "\nMessage: " + setMailText
                    + "\n" + gettime
                    + "\n" + getdate
            );

        } catch (Exception ex) {
            System.out.println("Failed! Please check your information or internet conncection.");
        }
        input.close();
    }

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        //Class object and Method Calling
        GmailSender gmailSender = new GmailSender();
        gmailSender.send();

    }

}
