package projet.m2.back.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Code {
    @Id
    @GeneratedValue
    private Long id;
    private Long code;
    private int value;
    private boolean used;

    public Code() {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
                ", value=" + value +
                ", used=" + used +
                '}';
    }
}
