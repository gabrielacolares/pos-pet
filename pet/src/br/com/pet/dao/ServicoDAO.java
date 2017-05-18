package br.com.pet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.pet.model.Servico;

public class ServicoDAO extends GenericDAO<Servico> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Servico> pesquisar(String nome){
		

		StringBuffer sql = new StringBuffer ("Select c from Servico c where c.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Servico> lista = query.getResultList(); 
		return lista;
	}
	
	public List<Servico> listar(Servico tipo){
		List<Servico> servicos = new ArrayList<Servico>();

		StringBuffer sql = new StringBuffer("Select c from Servico c ");
		
		
//		sql.append(" WHERE c.ativo = ").append(tipo.isAtivo());
		
		if (tipo.getData() != null) {
			sql.append(" AND c.data LIKE '%").append(tipo.getData()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		servicos = query.getResultList();

		return servicos;
	} 

}
