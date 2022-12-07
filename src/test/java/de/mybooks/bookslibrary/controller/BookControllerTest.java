package de.mybooks.bookslibrary.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void getBooks() throws Exception {
        // Given
        String expectedJSON = """
                [
                    {
                        "isbn": "8123",
                        "title": "Dogs",
                        "autor": "Muslim",
                        "art": "E_BOOK"
                    },
                    {
                        "isbn": "887",
                        "title": "Cats",
                        "autor": "Muslim",
                        "art": "HARD_COVER"
                    },
                    {
                        "isbn": "98998",
                        "title": "Hors",
                        "autor": "Muslim",
                        "art": "SOFT_COVER"
                    }
                ]
                """;




        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }


    @Test
    void getBookById() throws Exception {

        // Given
        String expectedJSON = """
                 {
                        "isbn": "8123",
                        "title": "Dogs",
                        "autor": "Muslim",
                        "art": "E_BOOK"
                    }
                """;

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/api/books/8123"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }

    @Test
    void update() throws Exception {
        // Given
        String expectedJSON = """
                [
                    {
                        "isbn": "8123",
                        "title": "Dogs",
                        "autor": "Muslim",
                        "art": "E_BOOK"
                    },
                    {
                        "isbn": "887",
                        "title": "Cats",
                        "autor": "Muslim",
                        "art": "HARD_COVER"
                    },
                   {
                           "isbn": "98998",
                           "title": "Dogs2",
                           "autor": "muslim2",
                           "art": "E_BOOK"
                       }
                ]
                """;

        String requestBody = """
                {
                           "isbn": "98998",
                           "title": "Dogs2",
                           "autor": "muslim2",
                           "art": "E_BOOK"
                       }
                """;

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.put("/api/books/98998")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                        .andExpect(status().isOk())
                     .andExpect(content().json(expectedJSON));
    }

    @Test
    void deletBook() throws Exception {
        // Given
        String expectedJSON = """
                [
                    {
                        "isbn": "8123",
                        "title": "Dogs",
                        "autor": "Muslim",
                        "art": "E_BOOK"
                    },
                    {
                        "isbn": "887",
                        "title": "Cats",
                        "autor": "Muslim",
                        "art": "HARD_COVER"
                    }
                ]
                """;
        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.delete("/api/books/98998"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJSON));
    }
}