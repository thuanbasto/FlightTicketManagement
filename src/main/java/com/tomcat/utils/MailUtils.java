package com.tomcat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.tomcat.dto.BookingDTO;
import com.tomcat.dto.TicketDTO;

@PropertySource(value = { "classpath:email.properties" })
@Component
public class MailUtils {
	
	@Autowired
	 Environment environment;
	
	public  void sendEmail(BookingDTO bookingDTO) {
		
        final String from = environment.getProperty("email.from");
        final String password = environment.getProperty("email.password");

        Properties props = new Properties();
        props.put("mail.smtp.host", environment.getProperty("mail.smtp.host"));
        props.put("mail.smtp.socketFactory.port",
        		environment.getProperty("mail.smtp.socketFactory.port"));
        props.put("mail.smtp.socketFactory.class",
        		environment.getProperty("mail.smtp.socketFactory.class"));
        props.put("mail.smtp.auth",environment.getProperty("mail.smtp.auth"));
        props.put("mail.smtp.port", environment.getProperty("mail.smtp.host"));
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(bookingDTO.getEmail()));
            message.setSubject("Tomcat has received the booking");
            message.setText("text...");
            message.setContent(createBookingSuccessContent(bookingDTO),
                    "text/html");
            //send message  
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String createBookingSuccessContent(BookingDTO bookingDTO) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	String htmlMsg = "";
        htmlMsg = "<h4>Booking Success</h4>"
                + "<h4>Booking Number: #" + bookingDTO.getBooking_Id() + "</h4>"
                + "<h4>Guest: " + bookingDTO.getEmail() + "</h4>"
                + "<h4>Phone: " + bookingDTO.getPhone() + "</h4>"
                + "<h4>Booking Date: " + sdf.format(new Date()) + "</h4>"
        		+ "<h4>Payment Method: " + bookingDTO.getPaymentMethod() + "</h4>"
				+ "<h4>Total Booking Price: " + bookingDTO.getTotalPrice() + "</h4>"
                
                + "<h4>Click to view booking: " + "<a href=\"http://localhost:8080/FlightTicketManagement/bill?email=" + bookingDTO.getEmail() + "&booking_Id=" + bookingDTO.getBooking_Id() + "\">Click here</a></h4>"
                +  "<table border ='1' style=\"border: 1px solid black;  width: 100%;\n"
                + "  border-collapse: collapse; \"><tr>"
                + "<td><b>Code </b></td>"
                + "<td><b>From </b></td>"
                + "<td><b>To </b></td>"
                + "<td><b>Departure Date</b></td>"
                + "<td><b>Seat</b></td>"
                + "<td><b>Passenger</b></td>"
                + "<td><b>Total Price</b></td>"
                + "</tr>";
        for (TicketDTO ticketDTO : bookingDTO.getTickets()) {
            String temp = "<tr>"
            		+ "<td>" + ticketDTO.getTicket_Id()+   "</td>"
                    + "<td>" + ticketDTO.getFlight().getFromAirport().getName() + "</td>"
                    + "<td>" + ticketDTO.getFlight().getToAirport().getName() + "</td>"
                    + "<td>" + ticketDTO.getFlight().getDepartureDate() + "</td>"
            		+ "<td>" + ticketDTO.getSeat().getSeat_Id() +" - "+ ticketDTO.getSeat().getTravelClass().getName() + "</td>"
            		+ "<td>" + ticketDTO.getCustomer().getFirstName()+" "+ticketDTO.getCustomer().getLastName() + "</td>"
            		+ "<td>" + ticketDTO.getTicket_PriceTotal()+ " VND" +  "</td>"
                    + "</tr>";
            htmlMsg = htmlMsg.concat(temp);
        }
        String temp1 = "</table><br>";
        htmlMsg.concat(temp1);
        return htmlMsg;
    }

}
