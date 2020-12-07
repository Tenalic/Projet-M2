package projet.m2.back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projet.m2.back.entity.Account;
import projet.m2.back.repository.AccountRepository;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(AccountRepository repo){
        return (args -> {
//            Account a = new Account("test");
//            repo.save(a);
            System.out.println( repo.findAccountByEmail("test"));
        });
    }

}
