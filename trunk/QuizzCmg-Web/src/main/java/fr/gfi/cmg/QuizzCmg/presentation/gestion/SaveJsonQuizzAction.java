package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("SaveJsonQuizzAction")
public class SaveJsonQuizzAction extends AbstractMonAction{
	
	@RequestMapping(method = RequestMethod.POST)
	public void execute(HttpServletRequest request) {
		
	}
	
}
