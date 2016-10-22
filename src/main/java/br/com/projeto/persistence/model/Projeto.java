package br.com.projeto.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by jaels on 20/10/2016.
 */
@Entity
@Table(name = "projeto", schema = "public")
public class Projeto implements  Serializable {

    @Id
    @SequenceGenerator(name="PROJETO_ID_GENERATOR", sequenceName="projeto_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROJETO_ID_GENERATOR")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String nome;




    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {


        this.id=id;

    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public  boolean equals(Object obj){
        if(obj instanceof AbstractEntity){
            if(obj.getClass()==this.getClass()){
                if( getId() != null && ((AbstractEntity) obj).getId().equals(getId())){
                    return  true;
                }
            }
        }

        return false;
    }


    @Override
    public String toString() {
        return "Projeto{" +
                "id=" + id +
                '}';
    }
}
