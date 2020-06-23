package br.com.trixsolucao.mkws.controller;

import br.com.trixsolucao.mkws.controller.response.DefaultResponseEnum;
import br.com.trixsolucao.mkws.controller.response.GenericResponse;
import br.com.trixsolucao.mkws.exception.HeaderException;
import br.com.trixsolucao.mkws.mkapi.ConnectionBean;
import br.com.trixsolucao.mkws.mkapi.Mikrotik;
import br.com.trixsolucao.mkws.model.*;
import br.com.trixsolucao.mkws.model.mapping.MapToObject;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.trixsolucao.mkws.controller.util.ControllerUtil.validaHeaderConnection;

@RestController
@RequestMapping({"/pppoe"})
public class PPPoeController {



/* Sessão de métodos de usuários */

    @GetMapping("/users")
    public ResponseEntity listar(@RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);
            System.out.println("Request -->>> " + connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            List<Map<String, String>> pppUsersMap = Mikrotik.getInstance().onEnviarComando("/ppp/secret/print");
            var pppUsers = MapToObject.mapToObject(pppUsersMap, PPPUsers.class);

            List<Map<String, String>> pppActivesMap = Mikrotik.getInstance().onEnviarComando("/ppp/active/print");
            var pppActiveUsers = MapToObject.mapToObject(pppActivesMap, PPPActiveUser.class);

            //Definindo online/offline conforme a lista de ativos buscada anteriormente
            // incluido sorted ordenando por nome de usuário
            List<PPPUsers> pppSorted = pppUsers.stream().peek(u ->
                    u.setOnline(pppActiveUsers.contains(new PPPActiveUser(u.getUser())))
            ).sorted(Comparator.comparing(PPPUsers::getUser)).collect(Collectors.toList());


            return ResponseEntity.ok().body(pppSorted);

        }catch(HeaderException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity editar(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader, @RequestBody PPPUsers pppoeUser) {
        try {
            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
            String valores = MapToObject.getStringFromObject(pppoeUser);
            Optional<Object> optionalID = Optional.ofNullable(id);

            if (optionalID.isPresent()) {
                Mikrotik.getInstance().onEnviarComando("/ppp/secret/remove .id=" + optionalID.get());
                Mikrotik.getInstance().onEnviarComando("/ppp/secret/add " + valores);
                return ResponseEntity.ok().body("PPPoe editado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Id não encontrado!");
            }

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch(MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/users")
    public ResponseEntity adicionar(@RequestBody PPPUsers pppoeUser, @RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            String valores = MapToObject.getStringFromObject(pppoeUser);

            System.out.println("ADD >>>> " + valores);

              Mikrotik.getInstance().onEnviarComando("/ppp/secret/add " + valores);


            return ResponseEntity.ok().body("PPPoe cadastrado com sucesso");

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());

        }
    }

    @DeleteMapping("/users/{name}")
    public ResponseEntity remover(@PathVariable("name") String name, @RequestHeader Map<String, String> connectionHeader) {
        try {

            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            Optional<Object> optionalName = Optional.ofNullable(name);
            if (optionalName.isPresent()) {
                List<Map<String, String>> user = Mikrotik.getInstance().onEnviarComando("/ppp/secret/print where name="+optionalName.get());
                if(!user.isEmpty()) {
                    Mikrotik.getInstance().onEnviarComando("/ppp/secret/remove .id=" + user.get(0).get(".id"));
                    return ResponseEntity.ok().body("PPPoe removido com sucesso");
                } else {
                    return ResponseEntity.badRequest().body("Nome não encontrado no Mikrotik");
                }

            }

            return ResponseEntity.badRequest().body("Requisição inválida, nome não encontrado");

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch(MikrotikApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    /* Sessão de métodos de usuários ativos */

    @GetMapping("/actives")
    public ResponseEntity listarAtivos(@RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);
            System.out.println("Request -->>> " + connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            List<Map<String, String>> pppActivesMap = Mikrotik.getInstance().onEnviarComando("/ppp/active/print");
            return ResponseEntity.ok().body(MapToObject.mapToObject(pppActivesMap, PPPActiveUser.class));

        }catch(HeaderException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }



    @DeleteMapping(path ={"/actives/{name}"})
    public ResponseEntity removerActive(@PathVariable("name") String name, @RequestHeader Map<String, String> connectionHeader) {

        try {

            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            Optional<Object> optionalName = Optional.ofNullable(name);
            if (optionalName.isPresent()) {
                List<Map<String, String>> user = Mikrotik.getInstance().onEnviarComando("/ppp/active/print where name="+optionalName.get());
                if(!user.isEmpty()) {
                    Mikrotik.getInstance().onEnviarComando("/ppp/active/remove .id=" + user.get(0).get(".id"));
                    return ResponseEntity.ok().body("Usuário desconectado com sucesso");
                } else {
                    return ResponseEntity.badRequest().body("Usuário não logado");
                }

            }

            return ResponseEntity.badRequest().body("Requisição inválida, nome não encontrado");

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch(MikrotikApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }



    /* Sessão de métodos de planos de usuário */

    @GetMapping("/plans")
    public ResponseEntity listarPlanos(@RequestHeader Map<String, String> connectionHeader) {
        try {
            System.out.println("Request -->>> " + connectionHeader);
            validaHeaderConnection(connectionHeader);

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            List<Map<String, String>> pppUsersMap = Mikrotik.getInstance().onEnviarComando("/ppp/profile/print");
            return ResponseEntity.ok().body(MapToObject.mapToObject(pppUsersMap, PPPProfiles.class));

        }catch(HeaderException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }

    }

    @PutMapping(value="/plans/{id}")
    public ResponseEntity editarPlanos(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader, @RequestBody PPPUsers pppoeUser) {
        try {
            validaHeaderConnection(connectionHeader);

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
            String valores = MapToObject.getStringFromObject(pppoeUser);
            Optional<Object> optionalID = Optional.ofNullable(id);

            if (optionalID.isPresent()) {
                Mikrotik.getInstance().onEnviarComando("/ppp/profile/set " + valores);
                return ResponseEntity.ok().body("Plano editado com sucesso");
            } else {
                return ResponseEntity.badRequest().body("Id não encontrado!");
            }

        } catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch(MikrotikApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/plans")
    public ResponseEntity adicionarPlano(@RequestBody PPPUsers pppoeUser, @RequestHeader Map<String, String> connectionHeader) {
        try {
            validaHeaderConnection(connectionHeader);

            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            String valores = MapToObject.getStringFromObject(pppoeUser);

            Mikrotik.getInstance().onEnviarComando("/ppp/profile/add " + valores);


            return ResponseEntity.ok().body("Plano cadastrado com sucesso");

        } catch(HeaderException ex) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());

        }
    }


    @DeleteMapping(path ={"/plans/{id}"})
    public ResponseEntity removerPlano(@PathVariable("id") String id, @RequestHeader Map<String, String> connectionHeader) {
        try {

            validaHeaderConnection(connectionHeader);
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));

            Optional<Object> optionalId = Optional.ofNullable(id);
            if (optionalId.isPresent()) {

                    Mikrotik.getInstance().onEnviarComando("/ppp/profile/remove .id=" + optionalId.get());
                    return ResponseEntity.ok().body("Plano removido com sucesso");


            }

            return ResponseEntity.badRequest().body("Requisição inválida, plano não encontrado");

        }catch(HeaderException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Header Connection Exception: "+ex.getMessage());
        } catch(MikrotikApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }




}
