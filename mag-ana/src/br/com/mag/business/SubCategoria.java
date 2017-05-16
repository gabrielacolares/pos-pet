package br.com.mag.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class SubCategoria {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoSubCategoria;
	private String descricao;
	@ManyToOne
	private Categoria categoria;
		
	public SubCategoria(String descricao, Categoria categoria) {
		super();
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	public Integer getCodigoCategoria() {
		return codigoSubCategoria;
	}
	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoSubCategoria = codigoCategoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	

}
