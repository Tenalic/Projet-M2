package projet.m2.back.service;

import projet.m2.back.entity.Prize;

public interface IprizeService {

    void createPrize(String reward);

    Iterable<Prize> getAllPrize();
}
