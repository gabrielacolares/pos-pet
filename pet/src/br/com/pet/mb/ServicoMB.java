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

import br.com.pet.dao.ClienteDAO;
import br.com.pet.dao.DAOException;
import br.com.pet.dao.ServicoDAO;
import br.com.pet.model.Servico;
import br.com.pet.util.Filtro;
import br.com.pet.util.FiltroCliente;
import br.com.pet.util.FiltroServico;



@ManagedBean
@SessionScoped
public class ServicoMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Servico servico;
	private ServicoDAO servicoDao;

	private List<Servico> servicos = new ArrayList<Servico>();

	private FiltroServico servicosF = new FiltroServico();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Servico> model;
	private SelectableDataModel<Servico> select;
	private List<Servico> clientesAtivos;
	private Integer servicoSelecionado;

	public ServicoMB() {
		model = new LazyDataModel<Servico>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Servico> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(servicosF.quantidadeFiltrados(filtro));

				return servicosF.filtrados(filtro);
			}
		};
		servico = new Servico();
		servicoDao = new ServicoDAO();
		
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
	
	public Integer getServicoSelecionado() {
		return servicoSelecionado;
	} 
	
	public void setServicoSelecionado(Integer servicoSelecionado) {
		this.servicoSelecionado = servicoSelecionado;
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Servico> getModel() {
		return model;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public List<Servico> getServicosAtivos() {
		if(servico == null){
			servicos = new ArrayList<Servico>();
		}
		
		if(servicos.isEmpty()){
			try{
				List<Servico> servicos = servicoDao.listarTodos();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return servicos;
	}


	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (servico == null) {
				// enviar mensagem de alerta/erro

			} else if (servico.getId() == null) {
				servicoDao.salvar(servico);

			} else {
				servicoDao.editar(servico);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		servico = new Servico();

		return "/buscaServico.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		servico = servicoDao.getPrimaryKey(servico);
		return "/pages/cadastraServico.faces?faces-redirect=true";
	}

	public String cadastrar() {
		servico = new Servico();
		return "/cadastraServico.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		servico = servicoDao.getPrimaryKey(servico);

		return "/pages/visualizaServico.faces";
	}


	public String excluir() throws DAOException {

		try {

			if (servico == null) {
				// enviar mensagem de alerta/erro
			} else {
				servicoDao.excluir(servico);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaServico.faces?faces-redirect=true";

	}


	public String voltar() {

		return "/pages/buscaServico.faces";
	}

}
