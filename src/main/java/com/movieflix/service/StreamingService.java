package com.movieflix.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.movieflix.model.Streaming;
import com.movieflix.repository.*;

@Service
public class StreamingService {

    private final StreamingRepository streamingRepository;


    public StreamingService(StreamingRepository Streaming) {
        this.streamingRepository = Streaming;
    }

    public List<Streaming> findAllCategories() {
        return streamingRepository.findAll();
    }

    public Streaming createdStreaming(Streaming Streaming) {
        return streamingRepository.save(Streaming);
    }

    public Optional<Streaming> findIdStreaming(Long id) {
        return streamingRepository.findById(id);
    }

    public void deletedStreaming(Long id) {
        streamingRepository.deleteById(id);
    }

    public Optional<Streaming> updateStreaming(Long id, Streaming updatedStreaming) {
        if (id == null || updatedStreaming == null){
            return Optional.empty();
        }

        return streamingRepository.findById(id)
        .map(existing -> {
            if (updatedStreaming.getName() != null) {
                existing.setName(updatedStreaming.getName());
            }return streamingRepository.save(existing);
        });
    }
}
