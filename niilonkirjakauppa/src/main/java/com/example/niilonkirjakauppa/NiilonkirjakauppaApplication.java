package com.example.niilonkirjakauppa;

import com.example.niilonkirjakauppa.domain.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class NiilonkirjakauppaApplication {

    // MAIN CLASS
    public static void main(String[] args) {
        SpringApplication.run(NiilonkirjakauppaApplication.class, args);
    }





    @Primary
    @Bean
    public CommandLineRunner books(BookRepository repository, CategoryRepository drepository, UserRepository userRepository) {
        return (args) -> {

            drepository.save(new Category("History"));
            drepository.save(new Category("Novel"));
            drepository.save(new Category("Scifi"));
            drepository.save(new Category("Learning"));
            drepository.save(new Category("Art"));

            repository.save(new Book("asdasdasd", "qwe", "666", 1990, 20.50, drepository.findByName("wasdasdasd").get(0)));

            /*luo käyttäjän/salasanan

            admin/admin, user/user */

            User user1 =new User("user","$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","USER");
            User user2 =new User("admin","$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","ADMIN");

            userRepository.save(user1);
            userRepository.save(user2);

        };
    }
}
