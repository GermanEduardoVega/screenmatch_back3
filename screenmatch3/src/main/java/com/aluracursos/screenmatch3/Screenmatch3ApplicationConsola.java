//package com.aluracursos.screenmatch3;
//
//import com.aluracursos.screenmatch3.principal.Principal;
//import com.aluracursos.screenmatch3.repository.SerieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Screenmatch3ApplicationConsola implements CommandLineRunner {
//
//	@Autowired
//	private SerieRepository serieRepository;
//	public static void main(String[] args) {
//		SpringApplication.run(Screenmatch3ApplicationConsola.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(serieRepository);
//		principal.muestraElMenu();
//
//
//	}
//
//
//
//}
