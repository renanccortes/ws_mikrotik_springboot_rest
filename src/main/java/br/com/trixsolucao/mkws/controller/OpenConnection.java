package br.com.trixsolucao.mkws.controller;


import br.com.trixsolucao.mkws.exception.HeaderException;
import br.com.trixsolucao.mkws.mkapi.Mikrotik;
import br.com.trixsolucao.mkws.model.PPPActiveUser;
import br.com.trixsolucao.mkws.model.PPPUsers;
import br.com.trixsolucao.mkws.model.mapping.MapToObject;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.trixsolucao.mkws.controller.util.ControllerUtil.validaHeaderConnection;

@RestController
@RequestMapping({"/open"})
public class OpenConnection {

    public ResponseEntity testarConexao(@RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);
            System.out.println("Request -->>> " + connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            return ResponseEntity.ok().build();

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (MikrotikApiException ex) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
