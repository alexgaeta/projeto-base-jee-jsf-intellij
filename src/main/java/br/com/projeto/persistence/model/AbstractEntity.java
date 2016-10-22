package br.com.projeto.persistence.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by jaels on 20/10/2016.
 */
@MappedSuperclass
public class AbstractEntity implements Serializable {


    private Long id;

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                '}';
    }
}
