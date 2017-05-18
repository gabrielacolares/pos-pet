package br.com.pet.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;

import br.com.pet.dao.CategoriaDAO;
import br.com.pet.dao.DAOException;
import br.com.pet.model.Categoria;
import br.com.pet.util.Filtro;
import br.com.pet.util.FiltroCategoria;
import br.com.pet.util.FiltroTipo;



@ManagedBean
@SessionScoped
public class CategoriaMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Categoria categoria;
	private CategoriaDAO categoriaDao;

	private List<Categoria> categorias = new ArrayList<Categoria>();

	private FiltroCategoria categoriasF = new FiltroCategoria();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Categoria> model;
	private SelectableDataModel<Categoria> select;

	public CategoriaMB() {
		model = new LazyDataModel<Categoria>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Categoria> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(categoriasF.quantidadeFiltrados(filtro));

				return categoriasF.filtrados(filtro);
			}
		};
		categoria = new Categoria();
		categoriaDao = new CategoriaDAO();
		
/*		select = new SelectableDataModel<Cliente>() {
						
			@Override
			public Object getRowKey(Cliente cliente) {
				return cliente.getEnderecos();
			}
			
			@Override
			public Cliente getRowData(String rowKey) {
		        List<Cliente> cli = (List<Cliente>) getWrappedData();

		        for(Cliente c : cli) {
		            if(c.getCodigoCliente().equals(rowKey))
		                return c;
		        }

		        return null;
			}
		};*/

	}

	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Categoria> getModel() {
		return model;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getTiposAtivos() {
		if(categorias == null){
			categorias = new ArrayList<Categoria>();
		}
		
		if(categorias.isEmpty()){
			try{
				List<Categoria> tipos = categoriaDao.listarTodos();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return categorias;
	}


	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (categoria == null) {
				// enviar mensagem de alerta/erro

			} else if (categoria.getId() == null) {
				categoriaDao.salvar(categoria);

			} else {
				categoriaDao.editar(categoria);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		categoria = new Categoria();

		return "/buscaCategoria.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		categoria = categoriaDao.getPrimaryKey(categoria);
		return "/pages/cadastraCategoria.faces?faces-redirect=true";
	}

	public String cadastrar() {
		categoria = new Categoria();
		return "/cadastrCategoria.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		categoria = categoriaDao.getPrimaryKey(categoria);

		return "/pages/visualizaCategoria.faces";
	}


	public String excluir() throws DAOException {

		try {

			if (categoria == null) {
				// enviar mensagem de alerta/erro
			} else {
				categoriaDao.excluir(categoria);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaCategoria.faces?faces-redirect=true";

	}


	public String voltar() {

		return "/pages/buscaTipo.faces";
	}

}
