package service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import entity.User;

@Service
public class UsuarioService {
	private static final Map<String, User> USUARIOS = new HashMap<>();

	public User login(final String login, final String senha){
		if(login == null || senha == null || !login.equals("admin") || !senha.equals("admin")){
			throw new IllegalArgumentException("Usuário ou senha inválidos");
		}
		
		final User usuario = new User();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setId(1L);
		usuario.setUuid(UUID.randomUUID().toString());
		UsuarioService.USUARIOS.put(usuario.getUuid(), usuario);
		return usuario;
	}
	
	public User get(final String tokenUser){
		return UsuarioService.USUARIOS.get(tokenUser);
	}
}
