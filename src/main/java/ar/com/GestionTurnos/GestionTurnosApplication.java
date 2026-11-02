package ar.com.GestionTurnos;

import ar.com.GestionTurnos.dao.IUsuariosDao;
import ar.com.GestionTurnos.entities.Usuarios;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GestionTurnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionTurnosApplication.class, args);
	}
	@Bean
	CommandLineRunner initData(IUsuariosDao usuarioDao) {
		return args -> {
			// Reemplaza "admin" y "1234" con el usuario y contraseña que prefieras usar para entrar
			if (usuarioDao.count() == 0) {
				Usuarios admin = new Usuarios();
				admin.setUsername("admin"); // Ajusta el nombre del campo según tu entidad
				admin.setPassword("Jakarta89"); // Ajusta según tu entidad (contraseña)
				usuarioDao.save(admin);
			}
		};
	}
}
