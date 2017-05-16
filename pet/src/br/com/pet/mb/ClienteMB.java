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
import br.com.pet.model.Cliente;
import br.com.pet.util.Filtro;
import br.com.pet.util.FiltroCliente;


@ManagedBean
@SessionScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Cliente cliente;
	private ClienteDAO clienteDao;
	// private List<Cliente> clientes;
	private List<Cliente> clientes = new ArrayList<Cliente>();

	private FiltroCliente clientesF = new FiltroCliente();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Cliente> model;
	private SelectableDataModel<Cliente> select;

	public ClienteMB() {
		model = new LazyDataModel<Cliente>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Cliente> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(clientesF.quantidadeFiltrados(filtro));

				return clientesF.filtrados(filtro);
			}
		};
		cliente = new Cliente();
		clienteDao = new ClienteDAO();
		
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

	public LazyDataModel<Cliente> getModel() {
		return model;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Cliente> getClientes() {
		if(clientes == null){
			clientes = new ArrayList<Cliente>();
		}
		
		if(clientes.isEmpty()){
			try{
				List<Cliente> clientes= clienteDao.listarTodos();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return clientes;
	}

	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (cliente == null) {
				// enviar mensagem de alerta/erro

			} else if (cliente.getId() == null) {
				clienteDao.salvar(cliente);

			} else {
				clienteDao.editar(cliente);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		cliente = new Cliente();

		return "/buscaCliente.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);
		return "/cadastraCliente.faces";
	}

	public String cadastrar() {
		cliente = new Cliente();
		return "/cadastraCliente.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		cliente = clienteDao.getPrimaryKey(cliente);

		return "/visualizaCliente.faces";
	}


	public String excluir() throws DAOException {

		try {

			if (cliente == null) {
				// enviar mensagem de alerta/erro
			} else {
				clienteDao.excluir(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaCliente.faces?faces-redirect=true";

	}

	public String voltar() {

		return "/buscaCliente.faces";
	}

}
