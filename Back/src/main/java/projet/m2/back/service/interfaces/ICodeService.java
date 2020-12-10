package projet.m2.back.service.interfaces;

public interface ICodeService {

    /**
     * Utilise un code et ajoute les gains au compte
     *
     * @param idAccount : id du compte ou l'on souhaite appliqué le code
     * @param code      : code du code
     * @return 0 : ok, 1 : compte non trouvé, 2 : code deja utilisé, 3 : code inconue
     */
    Object useCode(final long idAccount, final String code);

    /**
     * Change si le code est utilisé ou non en base
     *
     * @param id   : id du code
     * @param used : si il est utilisé ou non
     * @return 0
     */
    int changeUsed(final long id, final boolean used);

    /**
     * Ajoute en base 200 codes générés aléatoirement
     */
    void createDatasetCode();
}
