package br.com.pet.model;

import java.util.Calendar;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Servico extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4274419335294566212L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	

	
	private Categoria categoria;
	private Cliente cliente;
	private Calendar data;
	private Double hora;
	private String detalhamento;
	
	

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
