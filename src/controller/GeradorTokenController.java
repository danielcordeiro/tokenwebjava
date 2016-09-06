package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import service.TokenService;
import service.UsuarioService;
import entity.User;

@RestController
public class GeradorTokenController {
	
	@Autowired private TokenService service;
	@Autowired private UsuarioService usuarioService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/obtertoken", method = RequestMethod.GET, headers="Accept=application/json", produces="application/json")
	public @ResponseBody Map<String, String> gerar(final HttpServletRequest request) throws ParseException{
		final String tokenUser = request.getParameter("tokenUser");
		final String idContrato = request.getParameter("idContrato");
		final String dataRequisicao = request.getParameter("dataRequisicao");
		final Map<String, String> token = new HashMap<String, String>();
		
		try{
			final User user = this.usuarioService.get(tokenUser);
			final Date data = new SimpleDateFormat("dd/MM/yyyy").parse(dataRequisicao);
			final String tokenContrato = this.service.gerarToken(idContrato, user.getId(), data);

			token.put("sucesso", true + "");
			token.put("token", tokenContrato);
		}catch (IllegalArgumentException e){
			token.put("sucesso", false + "");
			token.put("mensagem", e.getMessage());
		}

		return token;
	}
}
