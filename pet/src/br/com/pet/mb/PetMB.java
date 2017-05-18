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

import br.com.pet.dao.PetDAO;
import br.com.pet.dao.DAOException;
import br.com.pet.model.Pet;
import br.com.pet.util.Filtro;
import br.com.pet.util.FiltroPet;



@ManagedBean
@SessionScoped
public class PetMB implements Serializable {

	private static final long serialVersionUID = 7247920166232548053L;

	private Pet pet;
	private PetDAO petDao;

	private List<Pet> pets = new ArrayList<Pet>();

	private FiltroPet petsF = new FiltroPet();

	private Filtro filtro = new Filtro();
	private LazyDataModel<Pet> model;
	private SelectableDataModel<Pet> select;
	private List<Pet> petsAtivos;
	
	public PetMB() {
		model = new LazyDataModel<Pet>() {

			private static final long serialVersionUID = 1L;

			@Override
			public List<Pet> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				filtro.setPrimeiroRegistro(first);
				filtro.setQuantidadeRegistros(pageSize);
				filtro.setAscendente(SortOrder.ASCENDING.equals(sortOrder));
				filtro.setPropriedadeOrdenacao(sortField);

				setRowCount(petsF.quantidadeFiltrados(filtro));

				return petsF.filtrados(filtro);
			}
		};
		pet = new Pet();
		petDao = new PetDAO();
	}
	
	public Filtro getFiltro() {
		return filtro;
	}

	public LazyDataModel<Pet> getModel() {
		return model;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public List<Pet> getPetsAtivos() {
		if(pets == null){
			pets = new ArrayList<Pet>();
		}
		
		if(pets.isEmpty()){
			try{
				pets = petDao.listarTodos();
			
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return pets;
	}


	public String salvar() throws DAOException {

		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (pet == null) {
				// enviar mensagem de alerta/erro

			} else if (pet.getId() == null) {
				petDao.salvar(pet);

			} else {
				petDao.editar(pet);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		pet = new Pet();

		return "/buscaPet.faces?faces-redirect=true";

	}

	public String editar() throws DAOException {

		pet = petDao.getPrimaryKey(pet);
		return "/pages/cadastraPet.faces?faces-redirect=true";
	}

	public String cadastrar() {
		pet = new Pet();
		return "/cadastraPet.faces?faces-redirect=true";
	}

	public String visualizar() throws DAOException {

		pet = petDao.getPrimaryKey(pet);

		return "/pages/visualizaPet.faces";
	}


	public String excluir() throws DAOException {

		try {

			if (pet == null) {
				// enviar mensagem de alerta erro
			} else {
				petDao.excluir(pet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/buscaPet.faces?faces-redirect=true";

	}


	public String voltar() {

		return "/pages/buscaPet.faces";
	}

}
