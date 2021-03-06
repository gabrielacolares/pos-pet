package br.com.pet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author gabrielacolares
 *
 */
@Entity
public class Tipo extends AbstractEntity{

	private static final long serialVersionUID = 3765442925990569951L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;


	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


}
