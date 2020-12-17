package com.apinbp.nbp.repository;

import com.apinbp.nbp.model.CrossCourse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossConvertRepository extends MongoRepository<CrossCourse, String> {
    CrossCourse findByCodeCurrency(String code);
}
