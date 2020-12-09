package projet.m2.back.service.interfaces;

import projet.m2.back.entity.Account;
import projet.m2.back.entity.Prize;

public interface IPrizeService {

    void createPrize(String reward, int quantity, int weight);

    Iterable<Prize> getAllPrize();

    Integer gain();

    void createDatasetPrize();

    String randomPrize(Account account);


}
