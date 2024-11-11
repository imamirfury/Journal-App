package com.journalapp.repositories;

import com.journalapp.entries.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {
}
