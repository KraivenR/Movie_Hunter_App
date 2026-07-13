package com.example.moviehunter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchUrlUnitTest {

    @Test
    public void searchUrl_isCorrect() {
        String query = "The Rip";
        query = query.replace(" ", "%20");
        String expected = "https://api.themoviedb.org/3/search/movie?api_key=f67059b8d5e75b5738c264fcf958e2ff&query=The%20Rip";
        String actual = "https://api.themoviedb.org/3/search/movie?api_key=f67059b8d5e75b5738c264fcf958e2ff&query=" + query;
        assertEquals(expected, actual);
    }
}
