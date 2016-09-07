package service;

import java.util.Date;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	public String gerarToken(final String idContrato, final Long idUser, final Date data){
		if(idContrato == null || idContrato.isEmpty() || idUser == null || data == null){
			throw new IllegalArgumentException("Campo obrigatório não informado");
		}
		return UUID.randomUUID().toString().substring(0, 6);
	}
}
