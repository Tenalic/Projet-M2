package projet.m2.back.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UtilsImpl implements IUtils {

    @Override
    public int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
