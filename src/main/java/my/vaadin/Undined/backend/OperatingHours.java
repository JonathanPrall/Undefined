package my.vaadin.Undined.backend;

//Data type to hold open and close times in 24 hour format
/*
 * Example:
 * Midnight = 0000
 * Noon		= 1200
 * 6pm		= 1800
 * 11:59pm	= 2359
 */

public class OperatingHours
{
	private int openTime = 0;
	private int closeTime = 0;
	
	
	//Set Open and Close time
	public boolean setOperatingHours(int openTime, int closeTime){
		//If both times are valid
		if(checkOpenTime(openTime) && checkCloseTime(closeTime)){
			//Set the times, return true
			this.openTime = openTime;
			this.closeTime = closeTime;
			return true;
		}else{
			//Otherwise don't set times, return false, yell at the perpetrator
			return false;
		}
	}
	
	//Check methods
	private boolean checkOpenTime(int openTime){
		//Calculate hours and minutes
		int hours = (openTime / 100);
		int minutes = (openTime - (hours * 100));
		
		//If time is in proper format
		if(openTime >=0 && hours < 24 && minutes < 60){
			return true;
		}else{
			//Otherwise, return false
			return false;
		}
	}
	
	private boolean checkCloseTime(int closeTime){
		//Calculate hours and minutes
		int hours = (closeTime / 100);
		int minutes = (closeTime - (hours * 100));
		
		//If time is in proper format
		if(closeTime >=0 && hours < 24 && minutes < 60){
			return true;
		}else{
			//Otherwise, return false
			return false;
		}
	}
	
	
	//Get open time
	public int getOpenTime(){
		return openTime;
	}
	
	
	//Get close time
	public int getCloseTime(){
		return closeTime;
	}
}
