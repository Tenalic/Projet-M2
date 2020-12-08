package projet.m2.back.service.implementations;

import org.springframework.stereotype.Service;
import projet.m2.back.service.interfaces.IUtils;

@Service
public class UtilsImpl implements IUtils {

    @Override
    public int rand(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }
}
