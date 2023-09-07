package com.learning.taskmanager.controllers;

import com.learning.taskmanager.dto.CreateNoteDTO;
import com.learning.taskmanager.dto.CreateNoteResponseDTO;
import com.learning.taskmanager.entities.NoteEntity;
import com.learning.taskmanager.services.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesControllers {
    private NotesService notesService;

    public NotesControllers(NotesService notesService){
        this.notesService=notesService;
    }
    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId){
        var notes = notesService.getNotesForTasks(taskId);
        return ResponseEntity.ok(notes);

    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody CreateNoteDTO body
            ){
        var note = notesService.addNoteForTask(taskId, body.getTitle(), body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));


    }


}
