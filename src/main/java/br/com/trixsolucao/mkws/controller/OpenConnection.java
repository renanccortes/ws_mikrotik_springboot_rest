package br.com.trixsolucao.mkws.controller;


import br.com.trixsolucao.mkws.exception.HeaderException;
import br.com.trixsolucao.mkws.mkapi.Mikrotik;
import br.com.trixsolucao.mkws.model.FirewallNat;
import br.com.trixsolucao.mkws.model.mapping.MapToObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.legrange.mikrotik.MikrotikApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.function.Function;


@RestController
@RequestMapping({"/open"})
public class OpenConnection {


    public ResponseEntity testarConexao(@RequestHeader Map<String, String> connectionHeader) {
        try {
            Mikrotik.getInstance().onConectar(connectionHeader.get("hostmk"), connectionHeader.get("portmk"), connectionHeader.get("usuariomk"), connectionHeader.get("senhamk"));
            return ResponseEntity.ok().build();

        }  catch (MikrotikApiException ex) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    public static void main(String[] args) {


    }
    /*
    *  Método criado para uso pontual
    *  Criado para atualizar todas regras setando o in-interface
    * */
    public static void changeFirewallNatRules(String host, String port, String user, String password, String pppoeIn) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Mikrotik.getInstance().onConectar(host, port, user, password);
            List<Map<String, String>> maps = Mikrotik.getInstance().onEnviarComando("/ip/firewall/nat/print");

            Function<FirewallNat, FirewallNat> functionFirewallNat = item -> {
                item.setInInterface(pppoeIn);
                item.setIdAux(item.getId());
                item.setId(null);
                return item;
            };

            MapToObject.mapToObject(maps, FirewallNat.class).stream().filter(item -> item.getAction().equals("dst-nat"))
                    .map(functionFirewallNat).forEach(
                    item ->
                    {
                        String itemString = MapToObject.getStringFromObject(item);
                        try {
                            Mikrotik.getInstance().onEnviarComando("/ip/firewall/nat/set numbers=" + item.getIdAux() + itemString);
                        } catch (MikrotikApiException e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (MikrotikApiException e) {
            e.printStackTrace();
        }
    }
}
