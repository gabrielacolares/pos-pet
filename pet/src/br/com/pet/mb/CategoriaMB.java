package br.com.pet.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.SortOrder;

import org.primefaces.model.LazyDataModel;

import br.com.pet.dao.CategoriaDAO;
import br.com.pet.dao.DAOException;
import br.com.pet.model.Categoria;

@SessionScoped
@ManagedBean
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 4837148550691075343L;
	private CategoriaDAO categoriaDAO = new CategoriaDAO();
	private Categoria categoria = new Categoria();
	private List<Categoria> categoriasAtivas = new ArrayList<Categoria>();
			
	public List<Categoria> getCategorias() {
		 List<Categoria> categorias = categoriaDAO.listarTodos();
		return categorias;
	}

	public String salvar() throws DAOException {
		
		if (categoria == null) {
			//enviar mensagem de alerta/erro ("N�o � possivel salvar categoria nula!");
		} else if (categoria.getId()!= null) {
			categoriaDAO.editar(categoria);
		
		} else {
			categoriaDAO.salvar(categoria);			
		}
		categoria = null;
		return "/buscaCategoria.faces";
	}

	public String editar() throws DAOException {

		categoria = categoriaDAO.getPrimaryKey(categoria);

		return "/cadastraCategoria.faces";
	}
	
	public String cadastrar(){

		return "/cadastraCategoria.faces?faces-redirect=true";
	}
	
	public String visualizar() throws DAOException {

		categoria = categoriaDAO.getPrimaryKey(categoria);

		return "/visualizaCategoria.faces";
	}

	
	public String voltar() {
		
		return "/buscaCategoria.faces?faces-redirect=true";
	}

}
