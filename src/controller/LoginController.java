package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.UsuarioService;
import entity.User;
 
@RestController
public class LoginController {
	
	@Autowired private UsuarioService service;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/autenticacao", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public @ResponseBody Map<String, String> login(final HttpServletRequest request){
		final String login = request.getParameter("login");
		final String senha = request.getParameter("senha");
		final Map<String, String> retorno = new HashMap<String, String>();
		try{
			final User usuario = this.service.login(login, senha);
			retorno.put("sucesso", true + "");
			retorno.put("token", usuario.getUuid());
		}catch (IllegalArgumentException e){
			retorno.put("sucesso", false + "");
			retorno.put("mensagem", e.getMessage());
		}
		return retorno;
	}
}
