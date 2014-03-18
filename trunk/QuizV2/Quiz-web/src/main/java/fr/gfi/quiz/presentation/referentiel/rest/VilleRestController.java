package fr.gfi.quiz.presentation.referentiel.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.gfi.quiz.entite.Ville;
import fr.gfi.quiz.metier.service.VilleBusinessService;
import fr.gfi.quiz.presentation.commun.dto.AutoComplete;
import fr.gfi.quiz.presentation.commun.dto.ExceptionDTO;
import fr.gfi.quiz.presentation.exception.VilleInconnueException;
import fr.gfi.quiz.presentation.referentiel.dto.VilleDto;

@Controller
@RequestMapping("/rest/ville")
public class VilleRestController {

	private static final Logger LOGGER= LoggerFactory.getLogger(VilleRestController.class);
	
	@Resource(name = "villeBusinessService")
	private VilleBusinessService villeBusinessService;
	
	@Resource(name = "mapper")
	private Mapper mapper;	
	
	@Value("${caractere.nonvalide.msg}")
	protected String villeNonValideError;	
	
	/**
	 * 
	 * This method will return set of villes corresponding to <code>nomVille</code> request param.
	 * 
	 * @return Map<String, List<VilleDTO>> represents a JSON name value
	 *         pair
	 */

	@RequestMapping(value = "/search", method = RequestMethod.GET , produces = "application/json")
	public @ResponseBody Map<String, List<VilleDto>> getVilles(@Valid AutoComplete autoComplete,BindingResult result ) throws VilleInconnueException

	{	
		if(result.hasErrors()){
			LOGGER.debug(result.toString());
			throw new VilleInconnueException(villeNonValideError,null);
		}
		List<Ville> villes = villeBusinessService.findVilles(autoComplete.getStartsWith(), autoComplete.getMaxSize());
		
		List<VilleDto> villesDTO = new ArrayList<VilleDto>(villes.size());
		for (Ville ville : villes) {
			VilleDto villeDTO = mapper.map(ville, VilleDto.class);
			villesDTO.add(villeDTO);
		}		

		Map<String, List<VilleDto>> villeMap = new HashMap<String, List<VilleDto>>();

		villeMap.put("villes", villesDTO);

		return villeMap;

	}
	
	
	@ExceptionHandler(VilleInconnueException.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ExceptionDTO handleVilleInconnueException(VilleInconnueException ex) throws IOException
	{
		ExceptionDTO exceptionDTO = new ExceptionDTO("getVilles()", "VilleRestController", ex.getMessage());

		return exceptionDTO;

	}	
}
