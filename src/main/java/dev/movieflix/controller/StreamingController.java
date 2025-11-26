package dev.movieflix.controller;

import dev.movieflix.controller.request.StreamingRequest;
import dev.movieflix.controller.response.StreamingResponse;
import dev.movieflix.entity.Streaming;
import dev.movieflix.mapper.StreamingServiceMapper;
import dev.movieflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService service;

    @GetMapping
    public ResponseEntity<List<StreamingResponse>> findAll() {
        List<StreamingResponse> streamings = service.findAll()
                .stream()
                .map(StreamingServiceMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(category -> ResponseEntity.ok(StreamingServiceMapper.toStreamingResponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StreamingResponse> save(@RequestBody StreamingRequest request) {
        Streaming newStreaming = StreamingServiceMapper.toStreaming(request);
        Streaming savedStreaming = service.save(newStreaming);
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingServiceMapper.toStreamingResponse(savedStreaming));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
