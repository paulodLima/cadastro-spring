package com.person.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void insert() throws Exception {

        mockMvc.perform(post("/v1/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"fullName\": \"Foo Name\",\n" +
                        "    \"birthDate\": \"1996-05-22\",\n" +
                        "    \"phone\": \"99999999\",\n" +
                        "    \"email\": \"foo@foo.com.br\",\n" +
                        "    \"documentNumber\": \"789.789.879-99\",\n" +
                        "    \"address\": {\n" +
                        "        \"zipCode\": \"73080100\",\n" +
                        "        \"city\": \"Brasília\",\n" +
                        "        \"street\": \"QMS 55A\",\n" +
                        "        \"complement\": \"LA MANOS\",\n" +
                        "        \"neighborhood\": \"Setor de Mansões de Sobradinho\",\n" +
                        "        \"state\": \"DF\"\n" +
                        "    }\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDate", is("1996-05-22")))
                .andExpect(jsonPath("$.phone", is("99999999")))
                .andExpect(jsonPath("$.email", is("foo@foo.com.br")))
                .andExpect(jsonPath("$.documentNumber", is("789.789.879-99")))
                .andExpect(jsonPath("$.fullName", is("Foo Name")))
                .andExpect(jsonPath("$.address.zipCode", is("73080100")))
                .andExpect(jsonPath("$.address.city", is("Brasília")))
                .andExpect(jsonPath("$.address.street", is("QMS 55A")))
                .andExpect(jsonPath("$.address.complement", is("LA MANOS")))
                .andExpect(jsonPath("$.address.neighborhood", is("Setor de Mansões de Sobradinho")))
                .andExpect(jsonPath("$.address.state", is("DF")));


    }

    @Test
    void insertWithoutAddress() throws Exception {

        mockMvc.perform(post("/v1/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\n" +
                        "\t\"fullName\" : \"Foo Name\",\n" +
                        "\t\"birthDate\" : \"1996-05-22\",\n" +
                        "\t\"sexType\" : \"M\",\n" +
                        "\t\"phone\" : \"99999999\",\n" +
                        "\t\"email\" : \"foo@foo.com.br\",\n" +
                        "\t\"documentNumber\" : \"789.789.879-99\" \n" +
                        "\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDate", is("1996-05-22")))
                .andExpect(jsonPath("$.phone", is("99999999")))
                .andExpect(jsonPath("$.email", is("foo@foo.com.br")))
                .andExpect(jsonPath("$.documentNumber", is("789.789.879-99")))
                .andExpect(jsonPath("$.fullName", is("Foo Name")));

    }


    @Test
    void insertWithInvalidProperties() throws Exception {

        mockMvc.perform(post("/v1/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\n" +
                        "\t\"fullName\" : \"\",\n" +
                        "\t\"documentNumber\" : \"789.789.879-999999\", \n" +
                        "    \"address\": {\n" +
                        "        \"complement\": \"LA MANOS\",\n" +
                        "        \"neighborhood\": \"Setor de Mansões de Sobradinho\",\n" +
                        "        \"state\": \"DF\"\n" +
                        "    }\n" +
                        "\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.path", is("/v1/api/persons")))
                .andExpect(jsonPath("$.messages.size()", is(8)));

    }

    @Test
    void listPerson() throws Exception {

        mockMvc.perform(get("/v1/api/persons")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].birthDate", is("1996-05-22")))
                .andExpect(jsonPath("$[0].email", is("brancante@foo.com")))
                .andExpect(jsonPath("$[0]documentNumber", is("52278512048")))
                .andExpect(jsonPath("$[0].fullName", is("brincante da silva")))
                .andExpect(jsonPath("$[0].address.zipCode", is("73080100")))
                .andExpect(jsonPath("$[0].address.city", is("Brasilia")))
                .andExpect(jsonPath("$[0].address.street", is("qms 30a BSB")))
                .andExpect(jsonPath("$[0].address.complement", is("la manos")))
                .andExpect(jsonPath("$[0].address.neighborhood", is("Setor de Mansões de Sobradinho")))
                .andExpect(jsonPath("$[0].address.state", is("DF")))

                .andExpect(jsonPath("$[1].birthDate", is("1998-03-24")))
                .andExpect(jsonPath("$[1].email", is("brancantedasilva@foo.com")))
                .andExpect(jsonPath("$[1].documentNumber", is("15286351085")))
                .andExpect(jsonPath("$[1].fullName", is("mister")))
                .andExpect(jsonPath("$.address").doesNotExist());

    }

    @Test
    void getPersonError() throws Exception {

        mockMvc.perform(get("/v1/api/persons/101")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDate", is("1996-05-22")))
                .andExpect(jsonPath("$.email", is("brancante@foo.com")))
                .andExpect(jsonPath("$.documentNumber", is("52278512048")))
                .andExpect(jsonPath("$.fullName", is("brincante da silva")))
                .andExpect(jsonPath("$.address.zipCode", is("73080100")))
                .andExpect(jsonPath("$.address.city", is("Brasilia")))
                .andExpect(jsonPath("$.address.street", is("qms 30a BSB")))
                .andExpect(jsonPath("$.address.complement", is("la manos")))
                .andExpect(jsonPath("$.address.neighborhood", is("Setor de Mansões de Sobradinho")))
                .andExpect(jsonPath("$.address.state", is("DF")));

    }


    @Test
    void getPersonWithoutAddress() throws Exception {

        mockMvc.perform(get("/v1/api/persons/102")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthDate", is("1998-03-24")))
                .andExpect(jsonPath("$.email", is("brancantedasilva@foo.com")))
                .andExpect(jsonPath("$.documentNumber", is("15286351085")))
                .andExpect(jsonPath("$.fullName", is("mister")))
                .andExpect(jsonPath("$.address").doesNotExist());

    }

    @Test
    void getPersonNotFound() throws Exception {

        mockMvc.perform(get("/v1/api/persons/19?lang=pt")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.path", is("/v1/api/persons/19")))
                .andExpect(jsonPath("$.messages[0]", is("Pessoa não encontrado(a)!")));

    }

    @Test
    void deletePerson()  throws Exception{
        mockMvc.perform(delete("/v1/api/persons/52278512048?lang=pt")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON));
    }
}