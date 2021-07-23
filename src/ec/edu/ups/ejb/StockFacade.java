package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Provincia;
import ec.edu.ups.entidades.Stock;
@Stateless

public class StockFacade extends AbstractFacade<Stock> {
	@PersistenceContext(unitName = "Practica-de-laboratorio-04")
	private EntityManager em;

	public StockFacade() {
		super(Stock.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<Stock> findby(int bodega) {
		String jpql = "SELECT s from Stock s join s.bodega b  WHERE b.id = " + bodega;
		return em.createQuery(jpql,Stock.class).getResultList();
	}
}
