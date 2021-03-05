package com.tomcat.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomcat.dto.SearchDTO;
import com.tomcat.service.ISearchService;

@RestController
@RequestMapping("/api")
public class SearchAPI {
	
	@Autowired
	ISearchService searchService;
	
	@GetMapping("/search/{di}/{den}/{date}/{hang}")
	public ResponseEntity<List<SearchDTO>> getSeachFlight(@PathVariable("di") String di,@PathVariable("den") String den,@PathVariable("date") String date,@PathVariable("hang") String hang) {
		//"DAD","HCM","2021-01-03(yyyy/MM/dd)","id seat")
		if(di == null || den == null || date == null || hang == null)
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else
		{
			List<SearchDTO> searchDTOs = searchService.getSearchs(di,den,date,hang);
			if (searchDTOs != null) {
				return new ResponseEntity<List<SearchDTO>>(searchDTOs, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}	
	}

}
