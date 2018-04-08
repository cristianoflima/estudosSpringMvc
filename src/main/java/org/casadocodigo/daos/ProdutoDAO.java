package org.casadocodigo.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.model.Produto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProdutoDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void grava(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return	manager.createQuery("select p from Produto p", Produto.class).getResultList();
		
	}

	public Produto find(Long id) {
		return	manager.createQuery("select distinct(p) from Produto p "
				+ "join fetch p.precos preco where p.id = :id", Produto.class)
		.setParameter("id", id)
		.getSingleResult();
	}

}
