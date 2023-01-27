/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rastip.resource;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.rastip.config.JwtTokenUtil;
import br.com.rastip.core.Mail;
import br.com.rastip.core.RetornoRequest;
import br.com.rastip.model.Constants;
import br.com.rastip.model.Mensagem;
import br.com.rastip.model.Usuario;
import br.com.rastip.model.wrapper.CadastroUsuarioWrapper;
import br.com.rastip.model.wrapper.LoginUser;
import br.com.rastip.model.wrapper.RespostaCaptchaWrapper;
import br.com.rastip.service.IUsuarioSessao;
import br.com.rastip.service.UsuarioService;

@RestController()
@RequestMapping("/token")
@CrossOrigin("${origem-permitida}")
public class LoginResource {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private Mail mail;
	
	@Autowired
	private IUsuarioSessao sessionUser;


	@PostMapping("/generatetoken")
	public RetornoRequest register(@RequestBody LoginUser loginUser) {
		
		RetornoRequest retorno = RetornoRequest.success();

		try {

			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final Usuario user = usuarioService.findOne(loginUser.getUsername());
			
			if(user != null && user.getAtivo().equals("S")){
				final String token = jwtTokenUtil.generateToken(user);
				retorno.setData(token);
			} else {
				retorno = RetornoRequest.warn("Usuário ainda não foi liberado para acesso! Entre em contato com administrador do sistema.");
			}
			
			
			
			
		} catch (AuthenticationException e) {
			retorno = RetornoRequest.warn("Usuário ou senha inválido!");
		} catch (Exception e) {
			e.printStackTrace();
			retorno = RetornoRequest.error("Erro ao fazer login. Entre em contato com administrador do sistema.");
		}
		
		return retorno;
	}

	@PostMapping("/cadastroUsuario")
	public RetornoRequest cadastroUsuario(@RequestBody CadastroUsuarioWrapper usuario, HttpServletRequest request) {
		RetornoRequest retorno = RetornoRequest.success();
		Usuario user = new Usuario();
		try {
			if(this.validar(usuario.getUsuario().getLogin())){
				user = usuarioService.findByLogin(usuario.getUsuario().getLogin());
				if(user == null) {
					StringBuffer params = new StringBuffer();
					params.append("response=");
					params.append(usuario.getRecaptcha());
					params.append("&secret=");
					params.append(Constants.RECAPTCHA_KEY);
		
					URL urlRelatorio = new URL(Constants.RECAPTCHA_API);
					HttpURLConnection uc = (HttpURLConnection) urlRelatorio.openConnection();
		
					uc.setRequestMethod("POST");
					uc.setDoOutput(true);
					DataOutputStream wr = new DataOutputStream(uc.getOutputStream());
					wr.writeBytes(params.toString());
					wr.flush();
					wr.close();
		
					uc.connect();
		
					InputStream in = uc.getInputStream();
		
					String respostaJSON = IOUtils.toString(in, "UTF-8");
		
					in.close();
		
					Gson gson = new GsonBuilder().create();
		
					RespostaCaptchaWrapper resposta = gson.fromJson(respostaJSON, RespostaCaptchaWrapper.class);
		
					if (resposta.isSuccess()) {
						usuario.getUsuario().setPass(encoder.encode(usuario.getUsuario().getPass()));
						usuarioService.save(usuario.getUsuario());
					} else {
						retorno = RetornoRequest.warn("Captcha inválido!");
					}
				} else {
					 retorno = RetornoRequest.warn("Email/login já cadastrado!");
				}
		  } else {
			  retorno = RetornoRequest.warn("Email inválido");
		  }
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}

		return retorno;
	}
	
	@RequestMapping(value = "/verificarLogin", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest verificarLogin(@RequestParam("userAcesso")  String userAcesso, HttpServletRequest request) {
			
		RetornoRequest retorno = RetornoRequest.success();
		Usuario user = new Usuario();
		String novaSenha;
		try {
			user = usuarioService.findByLogin(userAcesso);
			if (user != null){
				novaSenha =  this.gerarSenhaAux();
				Mensagem msg = new Mensagem("RastIp", user.getLogin(), "Recuperação de senha", this.template(novaSenha));
				user.setPass(encoder.encode(novaSenha));
				usuarioService.save(user);
				mail.enviar(msg);
				retorno.setData(user);
				retorno.success();
				return retorno;
			} else {
				retorno = RetornoRequest.warn();
				retorno.setMensagem("Login de acesso informado não é válido!");
				return retorno;
			}
			
		} catch (Exception e) {
			retorno = RetornoRequest.error("Email de Recuperação de senha não pode ser enviado. Ocorreu um erro ao redefinir a sua senha de acesso.");
		}

		return retorno;
	}
	
	public String gerarSenhaAux() {
	 String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	    String senha="";


	    for (int x=0; x<10; x++){
	        int j = (int) (Math.random()*carct.length);
	        senha += carct[j];
	    }
	    
		return senha;
	        
	 }
	
	public String template(String novaSenha){
		return " <div class='ui-g-12 ui-md-12' > " +
			" <div style='background: black; text-align:center'> <h1 style='color: white'>RastIp</h1> </div> " +
			" <h1> Sua Senha foi Redefinida com sucesso. </h1> " +
			" <hr/>" +
			" <h2> Nova senha de acesso: " + novaSenha + " </h2>" +
			" <h3> Altere sua senha no próximo acesso, atráves do menu perfil. </h3>" +
			" <hr/>" +
			" <h5> <span><i class='fa fa-registered'></i>RASTIP - Tecnology</span></h5>" +
			"</div>";	
	}
	
	@RequestMapping(value = "/getUsuario", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest getUsuario() {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			Usuario usuario = sessionUser.getUser();
			retorno.setData(usuario);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
	
	@RequestMapping(value = "/alterarUsuario", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest alterarUsuario(@RequestBody CadastroUsuarioWrapper usuario) {
			
		RetornoRequest retorno = RetornoRequest.success();
		Usuario user = new Usuario();
		try {
			if(this.validar(usuario.getUsuario().getLogin())){
				user = usuarioService.findByLogin(usuario.getUsuario().getLogin());
				if(user == null) {
					usuario.getUsuario().setPass(encoder.encode(usuario.getUsuario().getPass()));
					usuarioService.save(usuario.getUsuario());
				} else {
					if(user.getId() == usuario.getUsuario().getId()) {
						usuario.getUsuario().setPass(encoder.encode(usuario.getUsuario().getPass()));
						usuarioService.save(usuario.getUsuario());
					} else {
						 retorno = RetornoRequest.warn("Email/login já cadastrado!");
					}
				}
				
			} else {
				retorno = RetornoRequest.warn("Email inválido");
			}
		} catch (Exception e) {
			retorno = RetornoRequest.error("Não foi possível realizar a alteração. Por favor, entre em contao com a equipe RastIP.");
		}
		
		return retorno;
	}
	
	public boolean validar(String email)
    {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
	
	@RequestMapping(value = "/getUsuarioAll", method = RequestMethod.GET)
	public @ResponseBody RetornoRequest getUsuarioAll() {
		RetornoRequest retorno = RetornoRequest.success();
		
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			retorno.setData(usuarios);
			
		} catch (Exception e) {
			retorno = RetornoRequest.error(e.getMessage());
		}
		
		return retorno;
	}
	
	@RequestMapping(value = "/bloquearDesbloquearAcesso", method = RequestMethod.POST)
	public @ResponseBody RetornoRequest bloquearDesbloquearAcesso(@RequestBody Usuario usuario) {
		RetornoRequest retorno = RetornoRequest.success();
		try {
			usuarioService.save(usuario);
			retorno.success();
		} catch (Exception e) {
			retorno = RetornoRequest.error("Não foi possível realizar a operação!");
			retorno.error();
		}
		return retorno;
	}
}
