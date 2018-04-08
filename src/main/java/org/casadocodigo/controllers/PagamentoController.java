package org.casadocodigo.controllers;

import java.util.concurrent.Callable;

import org.casadocodigo.model.CarrinhoCompras;
import org.casadocodigo.model.DadosPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value="/finalizar", method=RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model){
		
		return ()->{
			try {
				String uri = "http://book-payment.herokuapp.com/payment";
				String resposta = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				System.out.println(resposta);
				model.addFlashAttribute("sucesso", resposta);
				return new ModelAndView("redirect:/produtos");
			}catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falhou", "Não foi possivel efetuar o pagamento, entre em contato com a central do cartão");
				return new ModelAndView("redirect:/produtos");
			}
		};
		
		
		
		
	}
}
