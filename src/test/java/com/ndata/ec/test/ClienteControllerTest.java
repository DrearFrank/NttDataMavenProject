package com.ndata.ec.test;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.sql.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndata.ec.entities.Cliente;
import com.ndata.ec.service.impl.ClienteService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    ObjectMapper mapper;
    @Autowired
    ClienteService clienteServiceImpl;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Ignore
    void testGetAll() throws Exception {

        Cliente resultSearch = clienteServiceImpl.findById((long) 1);
        if (resultSearch != null) {
            log.info("Nombre = {}", resultSearch.getNombre());
        }
    }

    @Test
    @Ignore
    void testNewClienteIsExists() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose Lsssema");
        cliente.setDireccion("Otavalo sn y principal ");
        cliente.setEstado("Act");
        cliente.setGenero("M");
        cliente.setEdad("25");
        cliente.setFeCreacion(Date.valueOf("2022-02-26"));
        cliente.setClave("154752");
        cliente.setIdentificacion("8161");
        cliente.setTelefono("0988425083");

    }


}
