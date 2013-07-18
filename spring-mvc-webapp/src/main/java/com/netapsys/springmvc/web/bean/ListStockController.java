package com.netapsys.springmvc.web.bean;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.netapsys.springmvc.web.Customer;
import com.netapsys.springmvc.web.Stock;
import com.netapsys.springmvc.web.StockBo;

@Controller
public class ListStockController {

	/**
	 * Handler de la méthode Get pour l'URL /helloSpringMVC.html.
	 * 
	 * @param name
	 *            le nom que l'on doit afficher dans la vue.
	 * @param model
	 *            une map de toutes les données qui seront utilisables dans la
	 *            vue
	 * @return le nom de la vue qu'il faudra utiliser.
	 */
	@RequestMapping(value = "/ListStock.html", method = RequestMethod.GET)
	public ModelAndView sayHelloWithSpringMVC(@RequestParam(value = "name", required = false) String name, ModelMap model,HttpServletRequest request) {
		model.addAttribute("name", name);
		ModelAndView modelAndView = new ModelAndView("bootstrap");
		HttpSession session = request.getSession();
		ApplicationContext appContext = ConnectionHibernate.getInstance().getAppContext();

		StockBo stockBo = (StockBo) appContext.getBean("stockBo");

		List<Stock> listeStock = new ArrayList<Stock>();

		/** select **/
		List<Stock> list = stockBo.findByStockCode("7668");
		listeStock.addAll(list);
		modelAndView.addObject("listeStock", listeStock);
		session.setAttribute("listeStock", listeStock);
		modelAndView.addObject("stockForm", new Stock());
		return modelAndView;
		// on utilisera donc le fichier /WEB-INF/jsp/hello.jsp
		// au regard de la stratégie de résolution des vues
		// utilisée dans cette application.
		// return "hello";
	}

	@RequestMapping(value = "/ajouterStock.html", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("stockForm") @Valid Stock stockForm, BindingResult result,HttpServletRequest request) {

		ApplicationContext appContext = ConnectionHibernate.getInstance().getAppContext();

		StockBo stockBo = (StockBo) appContext.getBean("stockBo");
		
		HttpSession session = request.getSession();
		session.getAttributeNames();

		
		 URL url;
		 try {
		 url = new URL(Context.getInstance().getUrlWebService());
		 QName qname = new QName("http://ws.mkyong.com/",
		 "HelloWorldImplService");
		
		 Service service = Service.create(url, qname);
		 } catch (Exception e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		if (!result.hasErrors()) {
			stockBo.save(stockForm);
			return "redirect:ListStock.html";
		}
		else {
			return "bootstrap";
		}

		

		// HelloWorld hello;

		// System.out.println(hello.getHelloWorldAsString());

		
	}

	@RequestMapping(value = "/signup.html", method = RequestMethod.POST)
	public String addCustomer(@Valid Customer customer, BindingResult result) {

		/*
		 * for (Object object : result.getAllErrors()) { if (object instanceof
		 * FieldError) { FieldError fieldError = (FieldError) object;
		 * 
		 * System.out.println(fieldError.getField() + ":" +
		 * fieldError.getCode());
		 * 
		 * }
		 * 
		 * if (object instanceof ObjectError) { ObjectError objectError =
		 * (ObjectError) object;
		 * 
		 * } }
		 */
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(customer);
		
		
		if (constraintViolations.size() > 0) {
			return "SignUpForm";
		} else {
			return "Done";
		}
	}
	@RequestMapping(value = "/signup.html",method = RequestMethod.GET)
	public String displayCustomerForm(ModelMap model) {

		model.addAttribute("customer", new Customer());
		return "SignUpForm";

	}
}