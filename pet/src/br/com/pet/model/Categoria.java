package br.com.pet.model;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;

/**
 * @author gabrielacolares
 *
 */
/**
 * @author gabrielacolares
 *
 */
public class Categoria extends AbstractEntity{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToMany(mappedBy="servico",fetch=FetchType.LAZY)
	private Servico servico;
	private String descricao;
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
