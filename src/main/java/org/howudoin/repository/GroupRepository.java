package org.howudoin.repository;

import org.howudoin.model.Group;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupRepository extends MongoRepository<Group, String> {
    Group findByMembersContaining(String email);
    Group findByGroupName(String groupName);
}
