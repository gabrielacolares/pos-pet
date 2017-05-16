package br.com.pet.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author gabrielacolares
 *
 */
public class Tipo extends AbstractEntity{

	private static final long serialVersionUID = 3765442925990569951L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private List<Pet> pets;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<Pet> getPets() {
		return pets;
	}


	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}


}
