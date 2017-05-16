package br.com.mag.business;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.mag.business.enumeration.TipoGenero;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoProduto;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoGenero genero;
	private String complemento;
	private BigDecimal valorVenda;
	private BigDecimal valorCusto;
	private String unidade;
	private double estoqueMinimo;
	private double qtdEstoque;
	
	@OneToOne
	private SubCategoria subcategoria;
	
	public Produto(String descricao, TipoGenero genero, String complemento,
			BigDecimal valorVenda, BigDecimal valorCusto, String unidade,
			double estoqueMinimo, double qtdEstoque, SubCategoria subcategoria) {
		super();
		this.descricao = descricao;
		this.genero = genero;
		this.complemento = complemento;
		this.valorVenda = valorVenda;
		this.valorCusto = valorCusto;
		this.unidade = unidade;
		this.estoqueMinimo = estoqueMinimo;
		this.qtdEstoque = qtdEstoque;
		this.subcategoria = subcategoria;
	}
	public TipoGenero getGenero() {
		return genero;
	}
	public void setGenero(TipoGenero genero) {
		this.genero = genero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public SubCategoria getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(SubCategoria subcategoria) {
		this.subcategoria = subcategoria;
	}
	public Integer getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(Integer codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}
	public BigDecimal getValorCusto() {
		return valorCusto;
	}
	public void setValorCusto(BigDecimal valorCusto) {
		this.valorCusto = valorCusto;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public double getEstoqueMinimo() {
		return estoqueMinimo;
	}
	public void setEstoqueMinimo(double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}
	public double getQtdEstoque() {
		return qtdEstoque;
	}
	public void setQtdEstoque(double qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
	
	
		
}
