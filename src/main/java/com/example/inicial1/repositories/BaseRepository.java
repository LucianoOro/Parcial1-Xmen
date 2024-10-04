package com.example.inicial1.repositories;

import com.example.inicial1.entities.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean             //Impide que se puedan crear instancias
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository <E, ID> {
    //La paginacion permite devolver datos en porciones pequeñas en lugar de devolver el contenido completo


}
