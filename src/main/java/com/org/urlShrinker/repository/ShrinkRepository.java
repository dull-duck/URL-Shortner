package com.org.urlShrinker.repository;

import com.org.urlShrinker.model.ShrinkModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShrinkRepository extends CrudRepository<ShrinkModel, String> {

}
