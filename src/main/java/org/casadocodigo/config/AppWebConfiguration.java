package org.casadocodigo.config;

import org.casadocodigo.controllers.HomeController;
import org.casadocodigo.daos.ProdutoDAO;
import org.casadocodigo.infra.FileSaver;
import org.casadocodigo.model.CarrinhoCompras;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class, ProdutoDAO.class, FileSaver.class, CarrinhoCompras.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	/*
	 * Metodo para configurar path onde será armazenadas as paginas e os tipos de arquivo.
	 * Para não precisar ficar digitando em .jsp dentro do servidor. 
	 */
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
	    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	    resolver.setPrefix("/WEB-INF/views/");
	    resolver.setSuffix(".jsp");	 
	    
	    resolver.setExposedContextBeanNames("carrinhoCompras");
	    
	    return resolver;
	}
	
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("resources/");
    }
	
	
	/*
	 * Metodo para configurar o arquivo de message properties
	 */
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	/*
	 * Metodo para configurar o formato da data sem precisar 
	 * adicionar anotação em todos os campos de data da aplicação
	 */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
		
	}
	
	/*
	 * Metodo que permite a ultilização do Multipart, 
	 * para poder enviar um formulario com mais de um tipo de informação
	 * neste caso um objeto e um arquivo.
	 */	
	@Bean
    public MultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }
	
	@Bean
	public RestTemplate resTemplate() {
		return new RestTemplate();
	}

}
