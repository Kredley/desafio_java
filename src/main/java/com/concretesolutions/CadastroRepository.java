package com.concretesolutions;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CadastroRepository extends CrudRepository <Cadastro, Long>{

    //usando uma query derivation para fazer a contagem por email (http://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts)
    Long countByEmail(String email);

    Cadastro findByEmail(String email);
}
