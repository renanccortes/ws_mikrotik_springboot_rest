package br.com.trixsolucao.mkws.controller;

import br.com.trixsolucao.mkws.mkapi.Mikrotik;
import br.com.trixsolucao.mkws.model.*;
import br.com.trixsolucao.mkws.model.mapping.MapToObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/hotspot"})
public class HotSpotController {


    /* Sessão de métodos de usuários */

    @GetMapping("/users")
    public ResponseEntity listar(@RequestHeader Map<String, String> connectionHeader) {
        try {

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            List<Map<String, String>> hotSpotUsersMap = Mikrotik.getInstance().onEnviarComando("/ip/hotspot/user/print");
            var hotSpotUsers = MapToObject.mapToObject(hotSpotUsersMap, HotspotUser.class);

            List<Map<String, String>> hotspotActivesMap = Mikrotik.getInstance().onEnviarComando("/ip/hotspot/active/print");
            var hotspotActiveUsers = MapToObject.mapToObject(hotspotActivesMap, HotspotActiveUser.class);

            //Definindo online/offline conforme a lista de ativos buscada anteriormente
            // incluido sorted ordenando por nome de usuário
            List<HotspotUser> hotSpotSorted = hotSpotUsers.stream().peek(u ->
                    u.setOnline(hotspotActiveUsers.contains(new HotspotActiveUser(u.getName())))
            ).sorted(Comparator.comparing(HotspotUser::getName)).collect(Collectors.toList());


            return ResponseEntity.ok().body(hotSpotSorted);

        } catch (MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity editar(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader, @RequestBody HotspotUser hotSpotUser) {
        try {

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
            hotSpotUser.setId(null);
            String valores = MapToObject.getStringFromObject(hotSpotUser);
            Optional<Object> optionalID = Optional.ofNullable(id);

            if (optionalID.isPresent()) {
                Mikrotik.getInstance().onEnviarComando("/ip/hotspot/user/set numbers=" + optionalID.get() + valores);
                return ResponseEntity.ok().body("HotSpot editado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Id não encontrado!");
            }

        } catch (MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/users")
    public ResponseEntity adicionar(@RequestBody HotspotUser hotspotUser, @RequestHeader Map<String, String> connectionHeader) {
        try {

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            String valores = MapToObject.getStringFromObject(hotspotUser);


            Mikrotik.getInstance().onEnviarComando("/ip/hotspot/user/add " + valores);
            return ResponseEntity.ok().body("HotSpot cadastrado com sucesso");

        } catch (MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());

        }
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity remover(@PathVariable("name") String name, @RequestHeader Map<String, String> connectionHeader) {
        try {

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            Optional<Object> optionalName = Optional.ofNullable(name);
            if (optionalName.isPresent()) {
                List<Map<String, String>> user = Mikrotik.getInstance().onEnviarComando("/ip/hotspot/user/print where name=" + optionalName.get());
                if (!user.isEmpty()) {
                    Mikrotik.getInstance().onEnviarComando("/ip/hotspot/user/remove .id=" + user.get(0).get(".id"));
                    return ResponseEntity.ok().body("Hotspot removido com sucesso");
                } else {
                    return ResponseEntity.badRequest().body("Nome não encontrado no Mikrotik");
                }

            }

            return ResponseEntity.badRequest().body("Requisição inválida, nome não encontrado");

        } catch (MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



    /* Sessão de métodos de planos de usuário */
//
//    @GetMapping("/plans")
//    public ResponseEntity listarPlanos(@RequestHeader Map<String, String> connectionHeader) {
//        try {
//
//
//            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
//
//            List<Map<String, String>> pppUsersMap = Mikrotik.getInstance().onEnviarComando("/ip/hotspot/profile/print");
//            return ResponseEntity.ok().body(MapToObject.mapToObject(pppUsersMap, PPPProfiles.class));
//
//        } catch (MikrotikApiException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//        }
//
//    }
//
//    @PutMapping(value = "/plans/{id}")
//    public ResponseEntity editarPlanos(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader, @RequestBody PPPUsers pppoeUser) {
//        try {
//
//            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
//            String valores = MapToObject.getStringFromObject(pppoeUser);
//            Optional<Object> optionalID = Optional.ofNullable(id);
//
//            if (optionalID.isPresent()) {
//                Mikrotik.getInstance().onEnviarComando("/ppp/profile/set " + valores);
//                return ResponseEntity.ok().body("Plano editado com sucesso");
//            } else {
//                return ResponseEntity.badRequest().body("Id não encontrado!");
//            }
//
//        } catch (MikrotikApiException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//        }
//    }
//
//    @PostMapping("/plans")
//    public ResponseEntity adicionarPlano(@RequestBody PPPUsers pppoeUser, @RequestHeader Map<String, String> connectionHeader) {
//        try {
//
//
//            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
//
//            String valores = MapToObject.getStringFromObject(pppoeUser);
//
//            Mikrotik.getInstance().onEnviarComando("/ppp/profile/add " + valores);
//
//
//            return ResponseEntity.ok().body("Plano cadastrado com sucesso");
//
//        } catch (MikrotikApiException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//
//        }
//    }
//
//
//    @DeleteMapping(path = {"/plans/{id}"})
//    public ResponseEntity removerPlano(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader) {
//        try {
//
//
//            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
//
//            Optional<Object> optionalId = Optional.ofNullable(id);
//            if (optionalId.isPresent()) {
//
//                Mikrotik.getInstance().onEnviarComando("/ppp/profile/remove .id=" + optionalId.get());
//                return ResponseEntity.ok().body("Plano removido com sucesso");
//
//
//            }
//
//            return ResponseEntity.badRequest().body("Requisição inválida, plano não encontrado");
//
//        } catch (MikrotikApiException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
//        }
//    }


}
