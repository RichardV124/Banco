package co.edu.eam.ingesoft.pa.banco.web.controladores;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.avanzada.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;

@Named("sessionControl")
@SessionScoped
	public class SessionController implements Serializable {
		
		private String user;
		private String pass;
		
		private Usuario usuario;
		
		@EJB
		private SeguridadEJB segEJB;
		
		public String login(){
			
			Usuario usuarioTemp=segEJB.buscarUsuario(user);
			if(usuarioTemp!=null){
				
				if(usuarioTemp.getPassword().equals(pass)){
					usuario=usuarioTemp;
					Faces.setSessionAttribute("user", usuario);
					return "/paginas/seguro/inicio.xhtml?faces-redirect=true";
				}else{
					Messages.addFlashGlobalError("Usuario o Pass incorrecto");
				}
			}else{
				Messages.addFlashGlobalError("Usuario o Pass incorrecto");
			}
			return null;
			
			
		}

		public String cerrarSession(){
			usuario=null;
			HttpSession sesion;
			sesion=(HttpSession)Faces.getSession();
			sesion.invalidate();
			return "/paginas/publico/login.xhtml?faces-redirect=true";

		}
		
		public boolean isSesion(){
			return usuario!=null;
		}
		
		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPass() {
			return pass;
		}

		public void setPass(String pass) {
			this.pass = pass;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		

	}
	
