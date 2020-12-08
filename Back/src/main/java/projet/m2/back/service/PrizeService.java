package projet.m2.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.m2.back.entity.Prize;
import projet.m2.back.repository.PrizeRepository;

import javax.transaction.Transactional;

@Service
public class PrizeService implements IprizeService{

    @Autowired
    PrizeRepository repo;

    @Transactional
    public void createPrize(String reward) {
        repo.save(new Prize(reward));
    }

    @Override
    public Iterable<Prize> getAllPrize() {
        return repo.findAll();
    }
}
