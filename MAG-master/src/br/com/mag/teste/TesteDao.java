package br.com.mag.teste;

import br.com.mag.business.Categoria;
import br.com.mag.business.dao.CategoriaDAO;

public class TesteDao {

	public static void main(String[] args) {
		
		Categoria categoria = new Categoria("vestu�rio");
		
		CategoriaDAO dao = new CategoriaDAO();
		dao.salvar(categoria);
	}

}
