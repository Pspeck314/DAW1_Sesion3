package com.cibertec.dao.impl;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cibertec.dao.UsuarioDao;
import com.cibertec.model.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	//Para comunicacion con xml persistence por medio del nombre UsuarioPU
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("UsuarioPU");
		//Este manager es para CRUD es decir, insert,update,delete,list etc
		EntityManager manager=factory.createEntityManager();
		//variable constante
		private static final String REGISTRO_OK="Se registró correctamente al usuario";
		private static final String REGISTRO_ERROR="No se registró correctamente al usuario";
		
		private static final String ACTUALIZA_OK="Se actualizó correctamente al usuario";
		private static final String ACTUALIZA_ERROR="No se actualizó correctamente al usuario";
		
		private static final String ELIMINA_OK="Se eliminó correctamente al usuario";
		private static final String ELIMINA_ERROR="No se eliminó al usuario";
		
		
	//Metodos
	public String registrarUsuario(Usuario usuario) {
		// Declaramos variable mensaje que es igual a la variable constante que contiene mensaje de error
				String mensaje=REGISTRO_ERROR; 
				//Si el objeto es diferente de nulo,
				if(!Objects.isNull(usuario)) { //es similar a if(usuario != null)
					//iniciamos transaccion e iniciamos 
					manager.getTransaction().begin();
					//Persist para la inserción de datos
					manager.persist(usuario);
					manager.getTransaction().commit();//confirmamos registro
					//mandamos mensaje
					mensaje=REGISTRO_OK;
					//cerramos la conexion
					manager.close();
					
				}
				return mensaje;
	}
	//Metodo buscar Usuario por ID,servira para el actualizar y eliminar y listaUsuarios 
		//ya que se actualiza por un id y elimina por id
	private Usuario buscarUsuarioId(int id) {
		//encuentra con find al usuario con id 
		return manager.find(Usuario.class,id);
	}
	public Usuario buscarUsuario(int id) {
		//Llamamos al metodo buscarUsuarioId y le pasamos id 
		return buscarUsuarioId(id);
	}

	public List<Usuario> listarUsuarios() {
		//Para list es con criterBuilder,Construye criterio para las consultas
				CriteriaBuilder criteriaBuilder=manager.getCriteriaBuilder();
				//Generamos la Query, de tipo Usuario con la variable criteriaQuery
				//creamos la consulta indicando la clase referencia osea Usuario
				CriteriaQuery<Usuario> criteriaQuery=criteriaBuilder.createQuery(Usuario.class);
				//Obtenemos datos de consulta con la clase Usuario especificada
				Root<Usuario> registro = criteriaQuery.from(Usuario.class);
				//Que se muestre todos los registros, se obtiene seleccion de registros de la BD
				CriteriaQuery<Usuario> todos =criteriaQuery.select(registro);
				//Obtener la lista,prepara consulta para ejecucion
				TypedQuery<Usuario> lista=manager.createQuery(todos);
				//retorna la lista de elementos
				return lista.getResultList();
	}

	public String actualizarUsuario(Usuario usuario) {
		// Declaramos variable mensaje que es igual a la variable constante que contiene mensaje de error
				String mensaje=ACTUALIZA_ERROR;
				//objeto almacena lo que se busca por metodo buscarUsuarioId
				Usuario objeto=buscarUsuarioId(usuario.getId());
				//Si el objeto es diferente de nulo,
				if(!Objects.isNull(objeto)) {
					//entonces abrimos el manager con begin
					manager.getTransaction().begin();
					//SETEAMOS
					objeto.setNombre(usuario.getNombre());
					objeto.setClave(usuario.getClave());
					objeto.setEstado(usuario.getEstado());
					//confirmamos
					manager.getTransaction().commit();
					//cerramos
					manager.close();
					//enviamos mensaje
					mensaje =ACTUALIZA_OK;
					
				}
				
				return mensaje;
	}

	public String eliminarUsuario(int id) {
		//Declaramos variable mensaje que es igual a la variable constante que contiene mensaje de error
		String mensaje=ELIMINA_ERROR;
		//objeto almacena lo que se busca por el parametro id
		Usuario objeto=buscarUsuarioId(id);
		//Si el objeto es diferente de nulo,es decir hay data
		if(!Objects.isNull(objeto)) {
			//entonces abrimos el manager con begin
			manager.getTransaction().begin();
			//eliminamos con remove el objeto
			manager.remove(objeto);
			//confirmamos con commit
			manager.getTransaction().commit();
			//cerramos
			manager.close();
			//mandamos mensaje
			mensaje=ELIMINA_OK;
		}

		return mensaje;
	}

}
