package com.ideas.stayprice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StayPriceController {
	
	@RequestMapping("/")
	public String display()
	{
			return "Success";
	}

	@GetMapping("/calculateStayPrice")
	public double calculateStayPrice
	(@RequestParam String checkInDate,@RequestParam String checkOutDate,@RequestParam int numberOfRooms,@RequestParam double roomRate,@RequestParam int rating)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		  double finalprice=1d;
        try {

            Date checkIn = formatter.parse(checkInDate);
            Date checkOut = formatter.parse(checkOutDate);
                        
            LocalDate checkOutLocalDate = checkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
            LocalDate checkInLocalDate = checkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			
            long numberOfDays=  ChronoUnit.DAYS.between(checkInLocalDate,checkOutLocalDate);	
         
            double price= numberOfDays* numberOfRooms * roomRate ;   
            
            //Final Price = (Price)  +  (All Applicable Tax)
            
            if(rating==5)
            {
            	finalprice = price+(price*0.28)+(price*0.06);
            	
            }else if(rating==3)
            {
            	finalprice = price+(price*0.18)+(price*0.05); 
            	
            }else if(rating==2)
            {
            	finalprice = price+(price*0.12)+(price*0.03);			//16728
            }
            
            
            
        
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return finalprice;
	}
}
//16,456