package com.cibertec.test;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.dao.impl.UsuarioDaoImpl;
import com.cibertec.model.Usuario;

public class RegistroUsuarioTest {

	public static void main(String[] args) {

		//IMPORTAMOS DAO para llamar al metodo
		UsuarioDao dao=new UsuarioDaoImpl();
		//Llamamos al objeto usuario para llamar a los campos
		Usuario	 usuario=new Usuario("Juan","123456",0);
		String mensaje=dao.registrarUsuario(usuario);
		System.out.println(mensaje);

	}

}
