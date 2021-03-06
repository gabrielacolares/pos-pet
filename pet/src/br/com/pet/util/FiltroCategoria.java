package br.com.pet.util;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

//import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.pet.model.Categoria;



public class FiltroCategoria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//CategoriaDAO categoriaDAO = new CategoriaDAO();
	
	//	@Inject
	private EntityManager manager = new JPAUtil().getEntityManager();
	
	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(Filtro filtro) {
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
		Criteria criteria = session.createCriteria(Categoria.class);
		
		//if (StringUtils.isNotEmpty(filtro.getDescricao())) {
		if (filtro.getDescricao()!= null && filtro.getDescricao().equals("") == false) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}
		
		return criteria;
	}
	
}
