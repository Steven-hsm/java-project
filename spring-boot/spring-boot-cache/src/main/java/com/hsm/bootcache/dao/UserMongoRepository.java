package com.hsm.bootcache.dao;

import com.hsm.bootcache.entity.UserMongoPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author senming.huang@lachesis-mh.com
 * @classname: UserMongoRepository
 * @description: TODO
 * @date 2020/10/22 11:10
 */
@Repository
public interface UserMongoRepository extends MongoRepository<UserMongoPO, Integer> {


}
