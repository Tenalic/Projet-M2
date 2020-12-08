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
import projet.m2.back.service.PrizeService;

import java.util.List;

@SpringBootApplication
public class Main {

    @Autowired
    private PrizeService service;

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
