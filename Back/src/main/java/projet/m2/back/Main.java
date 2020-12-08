package projet.m2.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projet.m2.back.constant.Constant;
import projet.m2.back.entity.Board;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.IBoardService;

@SpringBootApplication
public class Main {

    @Autowired
    IBoardService iBoardService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(IAccountService service) {
        return (args -> {
            //iBoardService.createBord();
            Board board = iBoardService.getBoardByName(Constant.boardName);

            System.out.println("test");

//            Account a = new Account("test");
//            repo.save(a);
            //iBoardService.createBord();
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
