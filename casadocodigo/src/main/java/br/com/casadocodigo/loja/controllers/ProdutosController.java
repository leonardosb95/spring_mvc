package br.com.casadocodigo.loja.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
@RequestMapping("produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		return "/produtos/form";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String gravar(Produto produto) {

		System.out.println(produto);
		this.produtoDao.gravar(produto);

		return "/produtos/ok";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {

		List<Produto> produtos = produtoDao.listar();
		ModelAndView modelAndView = new ModelAndView("/produtos/lista");
		modelAndView.addObject("produtos", produtos);
		return modelAndView;
	}

}
