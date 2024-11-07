package ma.xproce.videoservice;

import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import ma.xproce.videoservice.dao.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VideoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
		return args -> {
			// Vérifier si les créateurs existent déjà dans la base de données
			if (creatorRepository.count() == 0) {
				// Si aucun créateur n'existe, on crée des créateurs par défaut
				Creator creator1 = new Creator();
				creator1.setName("John Doe");
				creator1.setEmail("john@example.com");
				creatorRepository.save(creator1);

				Creator creator2 = new Creator();
				creator2.setName("Jane Smith");
				creator2.setEmail("jane@example.com");
				creatorRepository.save(creator2);
			}

			// Vérifier si les vidéos existent déjà dans la base de données
			if (videoRepository.count() == 0) {
				// Si aucune vidéo n'existe, on crée des vidéos par défaut
				Creator creator1 = creatorRepository.findByName("John Doe"); // Assurez-vous d'utiliser un critère pour récupérer le créateur
				Creator creator2 = creatorRepository.findByName("Jane Smith");

				Video video1 = new Video();
				video1.setName("Introduction to Spring Boot");
				video1.setUrl("http://example.com/video1");
				video1.setDescription("Learn the basics of Spring Boot.");
				video1.setCreator(creator1);
				videoRepository.save(video1);

				Video video2 = new Video();
				video2.setName("Advanced Spring Boot");
				video2.setUrl("http://example.com/video2");
				video2.setDescription("Deep dive into Spring Boot features.");
				video2.setCreator(creator1);
				videoRepository.save(video2);

				Video video3 = new Video();
				video3.setName("Spring Boot with Thymeleaf");
				video3.setUrl("http://example.com/video3");
				video3.setDescription("Build web applications with Thymeleaf.");
				video3.setCreator(creator2);
				videoRepository.save(video3);
			}
		};
	}
}
