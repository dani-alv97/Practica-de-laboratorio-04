package ec.edu.ups.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidades.Bodega;
import ec.edu.ups.entidades.Producto;

@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> {

    @PersistenceContext(unitName = "Practica-de-laboratorio-04")
    private EntityManager em;

    public BodegaFacade() {
        super(Bodega.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
  
}

