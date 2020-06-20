package br.com.trixsolucao.mkws.controller.util;

import br.com.trixsolucao.mkws.exception.HeaderException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ControllerUtil {


    public static void validaHeaderConnection(Map<String, String> connectionHeader) throws HeaderException {
        List<String> mensagemDeErro = new ArrayList<>();
        Optional.ofNullable(connectionHeader.get("hostmk")).ifPresentOrElse((value) ->{}, () -> {
            mensagemDeErro.add("attribute hostmk not accept null");
        });
        Optional.ofNullable(connectionHeader.get("portmk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute portmk not accept null");});
        Optional.ofNullable(connectionHeader.get("usuariomk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute usuariomk not accept null");});
        Optional.ofNullable(connectionHeader.get("senhamk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute senhamk not accept null");});

        if(!mensagemDeErro.isEmpty()){
            throw new HeaderException(mensagemDeErro.toString());
        }

    }
}
