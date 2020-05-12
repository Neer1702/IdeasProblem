package com.ideas.stayprice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestParam;

public class StayPriceService {
	
	public double getFinalPrice(String checkInDate,String checkOutDate,int numberOfRooms,double roomRate,int rating)
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
