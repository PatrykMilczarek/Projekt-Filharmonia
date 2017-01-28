package main;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class Employee {
	
	private String name_worker, surname_worker, address_worker, house_num_worker, town_worker, pesel_worker, profession_worker, symphony_worker;

	public Employee(String name_worker, String surname_worker, String address_worker, String house_num_worker, String town_worker,String pesel_worker,String profession_worker,String symphony_worker)
	{
		this.name_worker=name_worker;
		this.surname_worker=surname_worker;
		this.address_worker=address_worker; 
		this.house_num_worker= house_num_worker;
		this.town_worker=town_worker;
		this.pesel_worker= pesel_worker;
		this.profession_worker=profession_worker;
		this.symphony_worker=symphony_worker;
	}

	public String getName_worker() {
		return name_worker;
	}

	public void setName_worker(String name_worker) {
		this.name_worker = name_worker;
	}

	public String getSurname_worker() {
		return surname_worker;
	}

	public void setSurname_worker(String surname_worker) {
		this.surname_worker = surname_worker;
	}

	public String getAddress_worker() {
		return address_worker;
	}

	public void setAddress_worker(String address_worker) {
		this.address_worker = address_worker;
	}

	public String getHouse_num_worker() {
		return house_num_worker;
	}

	public void setHouse_num_worker(String house_num_worker) {
		this.house_num_worker = house_num_worker;
	}

	public String getTown_worker() {
		return town_worker;
	}

	public void setTown_worker(String town_worker) {
		this.town_worker = town_worker;
	}

	public String getPesel_worker() {
		return pesel_worker;
	}

	public void setPesel_worker(String pesel_worker) {
		this.pesel_worker = pesel_worker;
	}

	public String getProfession_worker() {
		return profession_worker;
	}

	public void setProfession_worker(String profession_worker) {
		this.profession_worker = profession_worker;
	}

	public String getSymphony_worker() {
		return symphony_worker;
	}

	public void setSymphony_worker(String symphony_worker) {
		this.symphony_worker = symphony_worker;
	}
}
