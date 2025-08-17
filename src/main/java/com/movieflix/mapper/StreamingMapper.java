package com.movieflix.mapper;

import com.movieflix.DTOrequest.StreamingRequest;
import com.movieflix.DTOresponse.StreamingResponse;
import com.movieflix.model.Streaming;

public class StreamingMapper {
    public static Streaming toStreaming(StreamingRequest StreamingRequest){
        return Streaming
        .builder()
        .name(StreamingRequest.name())
        .build();
    }

    public static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
        .builder()
        .id(streaming.getId())
        .name(streaming.getName())
        .build();
    }
}
