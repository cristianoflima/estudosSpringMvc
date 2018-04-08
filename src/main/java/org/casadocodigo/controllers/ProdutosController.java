package org.casadocodigo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.casadocodigo.daos.ProdutoDAO;
import org.casadocodigo.infra.FileSaver;
import org.casadocodigo.model.Produto;
import org.casadocodigo.utils.TipoPreco;
import org.casadocodigo.validation.ProdutoValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDAO produtoDao;
	
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView grava(MultipartFile sumario , @Valid Produto produto, BindingResult result, RedirectAttributes ra) {

		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		if(result.hasErrors())
			return form(produto);
		
		produtoDao.grava(produto);
		ra.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		
		return new ModelAndView("redirect:produtos");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.listar();
		ModelAndView mv = new ModelAndView("produtos/lista");
		mv.addObject("produtos", produtos);
		return mv;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView("produtos/detalhe");
		Produto produto = produtoDao.find(id);
		mv.addObject("produto", produto);
		
		return mv;
	}

}
