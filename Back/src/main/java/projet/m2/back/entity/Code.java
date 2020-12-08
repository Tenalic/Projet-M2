package projet.m2.back.entity;

import javax.persistence.*;

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

    public Code(Long code, int value, boolean used){
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
