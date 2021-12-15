package reto2.reto2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reto2.reto2.model.User;
import reto2.reto2.repository.crud.UserCrudRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Reto2Application implements CommandLineRunner {
	@Autowired
	private UserCrudRepository userRepo;

	public static void main(String[] args) {

		SpringApplication.run(Reto2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepo.deleteAll();

		userRepo.saveAll(List.of(
				new User(1, "123123", "CARLOS ALBERTO GARCIA", "CR 34-45", "311222222", "cgarcia@gmail.com", "Demo123.", "ZONA 2", "ADM")
				));
		System.out.println("Listado de Usuarios");
		userRepo.findAll().forEach(System.out::println);

		Optional<User> usuario = userRepo.findByEmail("cgarcia@gmail.com");

		if (usuario.isPresent()){
			System.out.println("Datos del Usuario" + usuario.get());
		}
		Optional<User> usuarioDos = userRepo.findByEmailAndPassword("cgarcia@gmail.com","Demo123");

		if (usuarioDos.isPresent()){
			System.out.println("Datos del Usuario" + usuarioDos.get());
		}

	}
}