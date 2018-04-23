package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import entity.Produto;
import persistence.ProdutoDao;

@ManagedBean(name="mb")
@RequestScoped
public class ProdutoBean implements Serializable{



		private static final long serialVersionUID = 1L;
		
		private Produto produto;
		
		private ProdutoDao dao;
		private List<Produto> produtos;
		
	
	
		public ProdutoBean() {
			   dao = new ProdutoDao();
			   produto = new Produto();
			   produtos = new ArrayList<>();
		}
		
		public Produto getProduto() {
			return produto;
		}



		public void setProduto(Produto produto) {
			this.produto = produto;
		}



		public ProdutoDao getDao() {
			return dao;
		}

		public void setDao(ProdutoDao dao) {
			this.dao = dao;
		}

		public void gravar() {
			FacesContext fc = FacesContext.getCurrentInstance();
			try {			
				dao.create(produto);
				produto = new Produto();
			
			fc.addMessage(null, new FacesMessage("Dados Gravados..."));		
			} catch (Exception ex) {
				fc.addMessage(null, new FacesMessage("Error: " + ex.getMessage()));
			}
		}
		
		
		public void listar() {
			FacesContext fc=FacesContext.getCurrentInstance();
			try{
				 ProdutoDao dao = new ProdutoDao();
				           dao.findAll();
		}catch (Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}
		}
		
		public void alterar() {
			FacesContext fc = FacesContext.getCurrentInstance();
			try {			
				dao.update(produto);
				listar();
			fc.addMessage(null, new FacesMessage("Dados Alterados..."));	
			
			} catch (Exception ex) {
				fc.addMessage(null, new FacesMessage("Error: " + ex.getMessage()));
			}
		}
	       

	
		
		 
			
			public String excluir(Produto produto){
				FacesContext fc = FacesContext.getCurrentInstance();
				try {
					dao.delete(produto);
					
				fc.addMessage(null, new FacesMessage("Dados Excluidos..."));
				listar();
				} catch (Exception ex) {
					fc.addMessage(null, new FacesMessage("Error: " + ex.getMessage()));
				}
				return "gravar";          
			}
			
		public List<Produto> getProdutos() {
			produtos = dao.findAll();
			return produtos;
		}



		public void setProdutos(List<Produto> produtos) {
			this.produtos = produtos;
		}

}
