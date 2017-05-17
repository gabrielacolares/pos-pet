package br.com.pet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import br.com.pet.model.Tipo;

public class TipoDAO extends GenericDAO<Tipo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Tipo> pesquisar(String nome){
		
		StringBuffer sql = new StringBuffer ("Select c from Tipo c where c.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Tipo> lista = query.getResultList(); 
		return lista;
	}
	
	public List<Tipo> listar(Tipo tipo){
		List<Tipo> tipos = new ArrayList<Tipo>();
		StringBuffer sql = new StringBuffer("Select c from Tipo c ");
		
		
//		sql.append(" WHERE c.ativo = ").append(tipo.isAtivo());
		
		if (tipo.getNome() != null) {
			sql.append(" AND c.nome LIKE '%").append(tipo.getNome()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		tipos = query.getResultList();
	
		return tipos;
	} 

}
