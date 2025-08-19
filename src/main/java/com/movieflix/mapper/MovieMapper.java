package com.movieflix.mapper;

import java.util.List;

import com.movieflix.DTOrequest.MovieRequest;
import com.movieflix.DTOresponse.CategoryResponse;
import com.movieflix.DTOresponse.MovieResponse;
import com.movieflix.DTOresponse.StreamingResponse;
import com.movieflix.model.*;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){

        List<Category> categories = request.categories().stream()
        .map(categoryId -> Category.builder().id(categoryId).build())
        .toList();

        List<Streaming> streamings = request.streamings().stream().map(streamingId -> Streaming.builder().Id(Long.valueOf(streamingId)).build()).toList();

        return Movie.builder()
        .title(request.title())
        .description(request.description())
        .releaseDate(request.releaseDate())
        .rating(request.rating())
        .categories(categories)
        .streamings(streamings)
        .build();

    }


    public static MovieResponse toMovieResponse(Movie movie){

        List<CategoryResponse> categories = movie.getCategories()
        .stream()
        .map(category -> CategoryMapper.toCategoryResponse(category))
        .toList();

        List<StreamingResponse> streamings = movie.getStreamings()
        .stream()
        .map(streaming -> StreamingMapper.toStreamingResponse(streaming))
        .toList();


        return MovieResponse.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .description(movie.getDescription())
        .releaseDate(movie.getReleaseDate())
        .rating(movie.getRating())
        .categories(categories)
        .streamings(streamings)
        .build();
    }

}
