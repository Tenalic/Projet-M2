package projet.m2.back.entity;

import javax.persistence.*;

/**
 * Entité réprésentant les codes qui permettent l'acquisition de  dés et soient des crédits ou un cadeau
 *
 *
 * Attributs :
 *
 * code : suite de chiffres générés aléatoirement représentant le code qui sera utilisé
 * used : booléen permettant de vérifier si le code est déjà utilisé ou pas .
 */
@Entity
public class Code {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private Long code;
    private boolean used;

    public Code() {
    }

    public Code(Long code, boolean used){
        this.code = code;
        this.used = used;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", code=" + code +
                ", used=" + used +
                '}';
    }
}
