package com.cibertec.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//Agregamos anotations de Entity y table donde el name de table es el nombre de la tabla en BD
//para la entidad Usuario la tabla en BD se llama usuario
@Entity
@Table(name="usuario")
public class Usuario {
	//Agregamos a los campos de las entidades o tablas de usuario
	//que es  idUsuario,nombre,clave,estado
		//Aca nuestra PK es idUsuario , por eso ID, luego IDENTITY por que es auto _ increment
		//column para referirnos al campo propio de la tabla
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "idUsuario")
		private int id;
		@Column(name = "nombre")
		private String nombre;
		@Column(name = "clave")
		private String clave;
		@Column(name = "estado")
		private int estado;
		//CONSTRUCTORES
		public Usuario() {
			
		}
		//No ponemos  id ya que es autogenerado
		public Usuario(String nombre, String clave, int estado) {
			this.nombre = nombre;
			this.clave = clave;
			this.estado = estado;
		}
		//GET AND SET
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getClave() {
			return clave;
		}
		public void setClave(String clave) {
			this.clave = clave;
		}
		public int getEstado() {
			return estado;
		}
		public void setEstado(int estado) {
			this.estado = estado;
		}
		//Para el test toString
		@Override
		public String toString() {
			return "Usuario [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", estado=" + estado + "]";
		}
		
		

}
