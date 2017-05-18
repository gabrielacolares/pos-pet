package br.com.pet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.pet.model.Pet;

public class PetDAO extends GenericDAO<Pet> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public List<Pet> pesquisar(String nome){
		
		StringBuffer sql = new StringBuffer ("Select p from Pet p where p.nome like '%" + nome +"%'");
		Query query = getEntityManager().createQuery(sql.toString());
		List<Pet> lista = query.getResultList();
		return lista;
	}
	
	public List<Pet> listar(Pet pet){
		List<Pet> pets = new ArrayList<Pet>();
		StringBuffer sql = new StringBuffer("Select p from Pet p ");
		
//		sql.append(" WHERE c.ativo = ").append(cliente.isAtivo());
		
		if (pet.getNome() != null) {
			sql.append(" AND p.nome LIKE '%").append(pet.getNome()).append("%'");
		}
		
		Query query = getEntityManager().createQuery(sql.toString());
		
		pets = query.getResultList();
		
		return pets;
	} 

}
