package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


import entity.Produto;

public class ProdutoDao {

	
	EntityManager manager;
	EntityTransaction transaction;
	
	static EntityManagerFactory factory =
			Persistence.createEntityManagerFactory("hilton");
	
	public ProdutoDao(){
		manager = factory.createEntityManager();
	}

    public void create(Produto p) throws Exception{
    	 transaction = manager.getTransaction();
    	   transaction.begin();
    	     manager.persist(p);
    	 transaction.commit();
    }

    public void  update(Produto p) throws Exception{
   	 transaction = manager.getTransaction();
   	   transaction.begin();
   	     manager.merge(p);
   	 transaction.commit();
   }
	
    public void delete(Produto p) throws Exception{
      	 transaction = manager.getTransaction();
      	   transaction.begin();
      	     manager.remove(p);
      	 transaction.commit();
      }
	
    public List<Produto> findAll(){
    	return (List<Produto>) manager.
      createQuery("select obj from Produto as obj").getResultList();
    }
	

}
