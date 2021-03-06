package br.com.pet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.pet.model.Cliente;

public class ClienteDAO extends GenericDAO<Cliente> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Cliente> pesquisar(String nome){
		
		StringBuffer sql = new StringBuffer ("Select c from Cliente c where c.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Cliente> lista = query.getResultList();
		return lista;
	}
	
	public List<Cliente> listar(Cliente cliente){
		List<Cliente> clientes = new ArrayList<Cliente>();
		StringBuffer sql = new StringBuffer("Select c from Cliente c ");
		
//		sql.append(" WHERE c.ativo = ").append(cliente.isAtivo());
		
		if (cliente.getNome() != null) {
			sql.append(" AND c.nome LIKE '%").append(cliente.getNome()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		clientes = query.getResultList();
		
		return clientes;
	} 

}
