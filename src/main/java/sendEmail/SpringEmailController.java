package sendEmail;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringEmailController {

	public SpringEmailController() {

	}

	public void sendWellcomeEmail(String userEmail) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringEmailController.class.getPackage().getName());

		Mailer mailer = applicationContext.getBean(Mailer.class);
		mailer.enviar(new Message("UCDb <classificaccucdb@gmail.com>",
				Arrays.asList("Giovana <giovana.oliveira@ccc.ufcg.edu.br>"), "Boas vindas UCDb",
				"Olá! Seja bem vindo ao Classifica CC! \nObrigado por se cadastrar, aproveite a estadia. \n\n\nAtt, equipe UCDb"));

		applicationContext.close();
	}
}