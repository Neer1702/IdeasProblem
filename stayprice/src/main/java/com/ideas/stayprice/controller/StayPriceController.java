package com.ideas.stayprice.controller;




import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ideas.stayprice.service.StayPriceService;

@RestController
public class StayPriceController {
	
	StayPriceService serv = new StayPriceService();;
	@RequestMapping("/")
	public String display()
	{
			return "Success";
	}

	@GetMapping("/calculateStayPrice")
	public double calculateStayPrice
	(@RequestParam String checkInDate,@RequestParam String checkOutDate,@RequestParam int numberOfRooms,@RequestParam double roomRate,@RequestParam int rating)
	{
		return serv.getFinalPrice(checkInDate,checkOutDate, numberOfRooms, roomRate, rating);
	}
}
