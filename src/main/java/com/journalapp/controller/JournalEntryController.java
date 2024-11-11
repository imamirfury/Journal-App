package com.journalapp.controller;


import com.journalapp.entries.JournalEntry;
import com.journalapp.services.JournalService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired(required = true)
    private JournalService journalService;

    @PostMapping
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalService.saveJournalEntry(journalEntry);
        return true;
    }

    @GetMapping
    public List<JournalEntry> getJournalEntry() {
        return journalService.getJournalEntry();

    }

    @GetMapping("id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id) {
        return journalService.findJournalEntryById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId id) {
        journalService.deleteJournalEntry(id);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry journalEntry) {
        JournalEntry oldEntry = journalService.findJournalEntryById(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setTitle(journalEntry != null && !journalEntry.getTitle().isEmpty() ? journalEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(journalEntry != null && !journalEntry.getContent().isEmpty() ? journalEntry.getContent() : oldEntry.getContent());
        }
        journalService.saveJournalEntry(Objects.requireNonNull(oldEntry));
        return  oldEntry;
    }

}
