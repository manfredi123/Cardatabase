package com.packt.cardatabase.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"}) //Ignora i campi generati da Hibernate
public class Owner {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long owner;
	
	private String firstName, lastName;
	
	@JsonIgnore //Ignora il campo cars nel processo di serializzazione. Serializzando la classe Car, verrà serializzata la classe owner (perchè attributo di Car). La classe Owner contiene l'attributo Cars, che per evitare loop viene ignorato
	@OneToMany(cascade=CascadeType.ALL, mappedBy="owner") //Owner è la chiave esterna (vincolo integrità referenziale)
	private List<Car> cars;								  //Cascade è l'azione compensativa da intraprendere
	

	public Owner(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Owner(){}

	public long getOwnerId() {
		return owner;
	}

	public void setOwnerId(long ownerId) {
		this.owner = ownerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<Car> getCars(){
		return this.cars;
	}
	
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
}
