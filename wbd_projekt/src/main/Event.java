package main;

import java.sql.Date;

public class Event {
	private long id;
    private String name;
    private String hour,time;

    public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	private Date date;
    private int max_seats_number;
    private String symphony;
    
    public Event()
    {
    	
    }
    public Event(long id,String name,String hour,Date date,String time,int max_seats_number,String symphony){
        this.id=id;
        this.name=name;
        this.hour=hour;
        this.date=date;
        this.time=time;
        this.max_seats_number=max_seats_number;
        this.symphony=symphony;
        
    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getHour() {
		return hour;
	}


	public void setHour(String hour) {
		this.hour = hour;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getMax_seats_number() {
		return max_seats_number;
	}


	public void setMax_seats_number(int max_seats_number) {
		this.max_seats_number = max_seats_number;
	}


	public String getSymphony() {
		return symphony;
	}


	public void setSymphony(String symphony) {
		this.symphony = symphony;
	}
}
