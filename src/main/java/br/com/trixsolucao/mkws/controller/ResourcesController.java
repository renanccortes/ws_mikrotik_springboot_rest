package br.com.trixsolucao.mkws.controller;

import br.com.trixsolucao.mkws.exception.HeaderException;
import br.com.trixsolucao.mkws.mkapi.Mikrotik;
import br.com.trixsolucao.mkws.model.Resources;
import br.com.trixsolucao.mkws.model.mapping.MapToObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

import static br.com.trixsolucao.mkws.controller.util.ControllerUtil.validaHeaderConnection;

@RestController
public class ResourcesController {

    @GetMapping("/resources")
    public ResponseEntity listar(@RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

                List<Map<String, String>> resourcesMap = Mikrotik.getInstance().onEnviarComando("/system/resource/print");
            return ResponseEntity.ok().body(MapToObject.mapToObject(resourcesMap, Resources.class));

        }catch(HeaderException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }


}
