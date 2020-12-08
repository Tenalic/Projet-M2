package projet.m2.back;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projet.m2.back.service.IAccountService;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(IAccountService service){
        return (args -> {
//            Account a = new Account("test");
//            repo.save(a);
            String email = "test@test.fr";
            String password = "test";
            String lastname = "test";
            String firstname = "test";
            String nickname = "test";
            //Account a = new Account( lastname,  firstname,  nickname,  email,  password);
            //service.creationAccount(email, lastname, firstname, nickname, password);

        });
    }

//    @Bean
//    public CommandLineRunner demo2(){
//        return (args -> {
//            service.createPrize("test");
//            Iterable<Prize> listePrize = service.getAllPrize();
//            for(Prize p : listePrize) {
//                System.out.println(p.getReward());
//            }
//        });
//    }

}
