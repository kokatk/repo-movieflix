package com.movieflix.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.DTOrequest.StreamingRequest;
import com.movieflix.DTOresponse.StreamingResponse;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.model.Streaming;
import com.movieflix.service.StreamingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/movieflix/streaming")
public class StreamingController {

    private final StreamingService StreamingService;


    public StreamingController(StreamingService streamingService) {
        this.StreamingService = streamingService;
    }



    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllCategories(){
        List<Streaming> categories = StreamingService.findAllCategories();
        List<StreamingResponse> list = categories.stream()
        .map(StreamingMapper::toStreamingResponse)
        .toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping()
    public ResponseEntity<StreamingResponse> createStreaming(@RequestBody StreamingRequest request) {
        Streaming createdStreaming = StreamingService.createdStreaming(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(createdStreaming));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getIdStreaming(@PathVariable Long id){
        return StreamingService.findIdStreaming(id)
        .map(Streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(Streaming)))
        .orElse(ResponseEntity.notFound().build());
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletedStreaming(@PathVariable Long id) {
        StreamingService.deletedStreaming(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updatedStreaming(@PathVariable Long id, @Valid @RequestBody Streaming request){
        return StreamingService.updateStreaming(id, request)
        .map(Streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(Streaming)))
        .orElse(ResponseEntity.notFound().build());
    
    }
}
