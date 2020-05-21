package br.com.casadocodigo.loja.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.enumm.TipoPreco;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;
	
	
	@RequestMapping("/produtos/form")
	public ModelAndView form() {
		
		ModelAndView modelAndView= new ModelAndView("produtos/form");//Atribui o destino no construtor do Objeto
		modelAndView.addObject("tipos",TipoPreco.values());//cria um parametro que vai ser atribuindo a lista de preços
		
		return modelAndView;// retorna para pagina JSP
	}
	
	
	@RequestMapping("/produtos")
	public String gravar(Produto produto) {

		System.out.println(produto);
		this.produtoDao.gravar(produto);

		return "produtos/ok";
	}
	


}
