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

import br.com.pet.dao.TipoDAO;
import br.com.pet.dao.DAOException;
import br.com.pet.model.Tipo;
import br.com.pet.util.Filtro;
import br.com.pet.util.FiltroTipo;



@ManagedBean
@SessionScoped
public class TipoMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Tipo tipo;
	private TipoDAO tipoDao;

	private List<Tipo> tipos = new ArrayList<Tipo>();

	private FiltroTipo tiposF = new FiltroTipo();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Tipo> model;
	private SelectableDataModel<Tipo> select;
	private List<Tipo> tiposAtivos;
	private Integer tipoSelecionado;
	public TipoMB() {
		model = new LazyDataModel<Tipo>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Tipo> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(tiposF.quantidadeFiltrados(filtro));

				return tiposF.filtrados(filtro);
			}
		};
		tipo = new Tipo();
		tipoDao = new TipoDAO();		

	}
	
	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Tipo> getModel() {
		return model;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getTiposAtivos() {
		if(tipos == null){
			tipos = new ArrayList<Tipo>();
		}
		
		if(tipos.isEmpty()){
			try{
				List<Tipo> clientes = tipoDao.listarTodos();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return tipos;
	}

	

	public Integer getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(Integer tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
	}

	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (tipo == null) {
				// enviar mensagem de alerta erro

			} else if (tipo.getId() == null) {
				tipoDao.salvar(tipo);

			} else {
				tipoDao.editar(tipo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		tipo = new Tipo();

		return "/buscaTipo.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		tipo = tipoDao.getPrimaryKey(tipo);
		return "/pages/cadastraTipo.faces?faces-redirect=true";
	}

	public String cadastrar() {
		tipo = new Tipo();
		return "/cadastraTipo.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		tipo = tipoDao.getPrimaryKey(tipo);

		return "/pages/visualizaPet.faces";
	}


	public String excluir() throws DAOException {

		try {

			if (tipo == null) {
				// enviar mensagem de alerta erro
			} else {
				tipoDao.excluir(tipo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaTipo.faces?faces-redirect=true";

	}


	public String voltar() {

		return "/pages/buscaTipo.faces";
	}

}
