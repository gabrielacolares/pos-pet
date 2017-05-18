package br.com.pet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.pet.model.Categoria;

public class CategoriaDAO extends GenericDAO<Categoria> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Categoria> pesquisar(String nome){
		
		StringBuffer sql = new StringBuffer ("Select c from Tipo c where c.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Categoria> lista = query.getResultList(); 
		return lista;
	}
	
	public List<Categoria> listar(Categoria categoria){
		List<Categoria> categorias = new ArrayList<Categoria>();
		StringBuffer sql = new StringBuffer("Select c from Categoria c ");
		
		
//		sql.append(" WHERE c.ativo = ").append(tipo.isAtivo());
		
		if (categoria.getDescricao() != null) {
			sql.append(" AND c.descricao LIKE '%").append(categoria.getDescricao()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		categorias = query.getResultList();
	
		return categorias;
	} 

}
