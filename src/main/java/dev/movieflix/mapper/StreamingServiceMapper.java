package dev.movieflix.mapper;

import dev.movieflix.controller.request.StreamingRequest;
import dev.movieflix.controller.response.StreamingResponse;
import dev.movieflix.entity.Streaming;
import dev.movieflix.service.StreamingService;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingServiceMapper {
    public static Streaming toStreaming(StreamingRequest request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming) {
        return StreamingResponse.builder()
                .id(streaming.getId())
                .name(streaming.getName())
                .build();
    }
}
