package com.journalapp.services;

import com.journalapp.entries.JournalEntry;
import com.journalapp.repositories.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Component
public class JournalService {

    @Autowired()
    private JournalRepository journalRepository;


    public void saveJournalEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDate.now());
        journalRepository.save(journalEntry);
    }

    public List<JournalEntry> getJournalEntry (){
        return  journalRepository.findAll();
    }

    public Optional<JournalEntry> findJournalEntryById(ObjectId id){
        return  journalRepository.findById(id);
    }

    public void deleteJournalEntry(ObjectId id){
        journalRepository.deleteById(id);
    }

}
