//January 30, 2019
//Soils Class
package Soils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//Entity Annotation
@Entity
@Table(name = "soils")

public class Soil {

	//create variables and annotations for columns
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="SMS")
	private String sms;
	@Column(name="NAME")
	private String name;
	@Column(name="CSR")
	private int csr;

	//default constructor
	public Soil() {
		super();
	}
	
	//variable constructor
	public Soil(String sms, String name, int csr) {
		super();
		this.sms = sms;
		this.name = name;
		this.csr = csr;
	}
	
	//getters and setters
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getSMS() {
		return sms;
	}
	
	public void setSMS(String sms) {
		this.sms = sms;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCSR() {
		return csr;
	}
	
	public void setCSR(int csr) {
		this.csr = csr;
	}
	
	//View Soil record
	public String returnSoilRecord() {
		return sms + ": " + name + ": CSR = " + csr;
	}
}

