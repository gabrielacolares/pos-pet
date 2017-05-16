package br.com.mag.teste;
import javax.persistence.EntityManager;
import br.com.mag.business.Cliente;
import br.com.mag.business.enumeration.TipoSituacaoCliente;
import br.com.mag.util.JPAUtil;

//import com.sun.security.ntlm.Client;

/**
 * @author gabrielacolares
 *
 */
public class PopulaCliente {

	public static void main(String[] args) {

		EntityManager mn = new JPAUtil().getEntityManager();
		mn.getTransaction().begin();

		Cliente cliente = new Cliente();
		
		cliente.setNome("Gabriela Colares Rodrigues");
		cliente.setCpf("00037836269");
		cliente.setRg(20689934);
		// cliente.setDataNascimento(Calendar."2014");
		cliente.setSituacaoCliente(TipoSituacaoCliente.ADIPLENTE);

		
		mn.persist(cliente);
		mn.getTransaction().commit();
		mn.close();
	}

}
