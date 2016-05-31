package com.concretesolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;




@SpringBootApplication
public class DesafioApplication {

	public final static long UM_MINUTO_EM_MILISEGUNDOS = 60000;//milisegundos
	public final static String SECRET_KEY = "4W966UpRB5P7e2u5z8UE2YNf0AlT974705O8J68rV46ea433X~71o25R9N5d925KlOH1e4^94229iH6Nf741oH32M2P8U49N48NI";

	public static void main(String[] args) {

		// System.out.println("Encryption key is " + DesafioApplication.SECRET_KEY);
		ApplicationContext ctx = SpringApplication.run(DesafioApplication.class, args);


		// String compactJwt = Jwts.builder().setSubject("Joe").setExpiration(new Date((new Date()).getTime() + 30 * UM_MINUTO_EM_MILISEGUNDOS)).signWith(SignatureAlgorithm.HS512, DesafioApplication.SECRET_KEY).compact();
		// System.out.println(compactJwt);
		// Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));

		// try {
		//
		//     System.out.println(Jwts.parser().setSigningKey("secret").parseClaimsJws(compactJwt).getBody().getExpiration());
		//
		//     //OK, we can trust this JWT
		// 	System.out.println("Parse ok");
		//
		// } catch (SignatureException e) {
		//
		//     //don't trust the JWT!
		// 	System.out.println("Parse not ok!!!");
		// }

        // CadastroRepository cadastro_repo = ctx.getBean(CadastroRepository.class);
        // CadastroPhoneRepository cadastrophone_repo = ctx.getBean(CadastroPhoneRepository.class);
        //
        // Cadastro cad = new Cadastro();
        // cad.setName("João");
        // CadastroPhone phone;
        // // CadastroPhone phones = new CadastroPhone();
        // cad.addToCadastroPhone(new CadastroPhone("16", "92183231"));
        // cad.addToCadastroPhone(new CadastroPhone("17", "30213433"));
        // cadastro_repo.save(cad);
        // System.out.println(cadastrophone_repo.findAll());

            // Gson gson = new Gson();
            // System.out.println(gson.fromJson("{'ddd': '17', 'number': '213172', 'cadastro': {'name': 'Jão'}}", CadastroPhone.class));
	}
}
