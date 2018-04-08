package org.casadocodigo.controllers;

import org.casadocodigo.daos.ProdutoDAO;
import org.casadocodigo.model.CarrinhoCompras;
import org.casadocodigo.model.CarrinhoItem;
import org.casadocodigo.model.Produto;
import org.casadocodigo.utils.TipoPreco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private CarrinhoCompras carrinho;

	@RequestMapping("/add")
	public ModelAndView add(Long produtoId, TipoPreco tipoPreco) {
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}

	private CarrinhoItem criaItem(Long produtoId, TipoPreco tipoPreco) {
		Produto produto = produtoDao.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(produto, tipoPreco);
		return carrinhoItem;
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Long produtoId, TipoPreco tipoPreco, RedirectAttributes model) {
		carrinho.remover(produtoId, tipoPreco);
		
		model.addFlashAttribute("sucesso", "Item removido com sucesso.");
		
		return new ModelAndView("redirect:/carrinho");
	}

}
