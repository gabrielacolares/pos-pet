package br.com.pet.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import br.com.pet.model.Pet;


public class FiltroPet implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private EntityManager manager = new JPAUtil().getEntityManager();
	
	@SuppressWarnings("unchecked")
	public List<Pet> filtrados(Filtro filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setFirstResult(filtro.getPrimeiroRegistro());
		criteria.setMaxResults(filtro.getQuantidadeRegistros());
		
		if (filtro.isAscendente() && filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.asc(filtro.getPropriedadeOrdenacao()));
		} else if (filtro.getPropriedadeOrdenacao() != null) {
			criteria.addOrder(Order.desc(filtro.getPropriedadeOrdenacao()));
		}
		
		return criteria.list();
	}
	
	public int quantidadeFiltrados(Filtro filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);
		
		criteria.setProjection(Projections.rowCount());
		
		return ((Number) criteria.uniqueResult()).intValue();
	}
	
	private Criteria criarCriteriaParaFiltro(Filtro filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Pet.class);
		
		//if (StringUtils.isNotEmpty(filtro.getDescricao())) {
		if (filtro.getNome()!= null && filtro.getNome().equals("") == false) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria;
	}
	
}
