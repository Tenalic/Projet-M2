package projet.m2.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.AccountRepository;
import projet.m2.back.repository.PrizeRepository;
import projet.m2.back.service.IaccountService;
import projet.m2.back.service.IprizeService;
import projet.m2.back.service.PrizeServiceImpl;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(IaccountService service){
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
