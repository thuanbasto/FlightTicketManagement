package com.tomcat.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.BookingDTO;
import com.tomcat.service.IBookingService;

@RestController
@RequestMapping("/api")
public class BookingAPI {
	
	@Autowired
	private IBookingService bookingService;
	
	@GetMapping("/bookings")
	public ResponseEntity<List<BookingDTO>> getBookings(@RequestParam(name="fromDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
			@RequestParam(name="toDate",required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate,
			@RequestParam(name="user_Id",required=false) String user_Id, @RequestParam(name="customer_Id",required=false) String customer_Id) {
		List<BookingDTO> bookingDTOs = new ArrayList<BookingDTO>();
		 
		if (fromDate != null && toDate != null) // check fromDate and toDate to return time statistics
			bookingDTOs = bookingService.getBookings(fromDate,toDate);
		else if (user_Id != null) // check user_Id to return time statistics
			bookingDTOs = bookingService.getBookings(user_Id);
		else 
			bookingDTOs = bookingService.getBookings();
		
		
		if (bookingDTOs.isEmpty()) 
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		else 
			return new ResponseEntity<List<BookingDTO>>(bookingDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{id}")
	public ResponseEntity<BookingDTO> getBooking(@PathVariable("id") String id) {
		BookingDTO bookingDTO = bookingService.getBooking(Integer.valueOf(id));
		if (bookingDTO != null) {
			return new ResponseEntity<BookingDTO>(bookingDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/bookings")
	public ResponseEntity<BookingDTO> addBooking(@RequestBody BookingDTO bookingDTO) {
		bookingService.save(bookingDTO);
		return new ResponseEntity<BookingDTO>(bookingDTO, HttpStatus.OK);
	}

	@PutMapping("/bookings/{id}")
	public ResponseEntity<BookingDTO> updateFlight(@RequestBody BookingDTO bookingDTO, @PathVariable("id") String id) {
		BookingDTO _bookingDTO = bookingService.getBooking(Integer.valueOf(id));
		if (_bookingDTO.getBooking_Id() != null) {
			bookingService.save(bookingDTO);
			return new ResponseEntity<BookingDTO>(bookingDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/bookings/{id}")
	public ResponseEntity<HttpStatus> deleteBooking(@PathVariable("id") String id) {
		BookingDTO _bookingDTO = bookingService.getBooking(Integer.valueOf(id));
		if (_bookingDTO.getBooking_Id() != null) {
			bookingService.delete(Integer.valueOf(id));
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
