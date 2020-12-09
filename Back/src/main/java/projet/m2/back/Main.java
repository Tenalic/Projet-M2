package projet.m2.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import projet.m2.back.service.interfaces.IAccountService;
import projet.m2.back.service.interfaces.IBoardService;
import projet.m2.back.service.interfaces.ICodeService;
import projet.m2.back.service.interfaces.IPrizeService;

@SpringBootApplication
public class Main {

    @Autowired
    IBoardService iBoardService;

    @Autowired
    ICodeService iCodeService;

    @Autowired
    IPrizeService prizeService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(IAccountService service) {
        return (args -> {
            //iBoardService.createBord();
             //Board board = iBoardService.getBoardByName(Constant.boardName);

            //  iCodeService.createDatasetCode(); //NE PAS DECOMMENTER MERCI !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


           //System.out.println("test");

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
