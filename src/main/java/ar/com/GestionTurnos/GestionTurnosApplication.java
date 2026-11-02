package ar.com.GestionTurnos;

import ar.com.GestionTurnos.dao.IRolesDao;
import ar.com.GestionTurnos.dao.IUsuariosDao;
import ar.com.GestionTurnos.entities.Roles;
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
	CommandLineRunner initData(IUsuariosDao usuarioDao, IRolesDao rolDao) {
		return args -> {
			if (usuarioDao.count() == 0) {
				// Primero asegúrate de tener o crear un rol (ej. Rol adminRol = rolRepository.findByNombre("ADMIN"); u otro método)
				// O si tu rol es una entidad que ya existe, búscala o guárdala:
				Roles rolAdmin = rolDao.findAll().stream().findFirst().orElseGet(() -> {
					Roles r = new Roles();
					r.setDescrip("Administrador"); // Ajusta según el campo de tu entidad Rol
					return rolDao.save(r);
				});

				Usuarios admin = new Usuarios();
				admin.setUsername("admin");
				admin.setPassword("1234"); // O password hasheado si usas Spring Security
				admin.setRol(rolAdmin); // ¡Asignamos el rol obligatorio!

				usuarioDao.save(admin);
			}
		};
	}
	}

