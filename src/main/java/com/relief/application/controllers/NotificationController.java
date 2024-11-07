package com.relief.application.controllers;

import com.relief.application.dtos.NotificationDTO;
import com.relief.application.filters.BasePage;
import com.relief.application.requests.NotificationRequest;
import com.relief.domain.services.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<BasePage<NotificationDTO>> getPage(Pageable pageable) {
        return ResponseEntity.ok(new BasePage<>(notificationService.getPage(pageable)));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NotificationRequest request) {
        this.notificationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody NotificationRequest request) {
        this.notificationService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDTO>> findAll() {
        return ResponseEntity.ok(this.notificationService.findAll());
    }
}
