package br.com.trixsolucao.mkws.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest  httpRequest = (HttpServletRequest)request;
        Map<String,String> erros = new HashMap<>();

        Optional.ofNullable(httpRequest.getHeader("hostmk")).ifPresentOrElse((value) ->{}, () -> {erros.put("hostmk", "NULL not allowed");});
        Optional.ofNullable(httpRequest.getHeader("portmk")).ifPresentOrElse((value) ->{}, () -> {erros.put("portmk", "NULL not allowed");});
        Optional.ofNullable(httpRequest.getHeader("usuariomk")).ifPresentOrElse((value) ->{}, () -> {erros.put("usuariomk", "NULL not allowed");});
        Optional.ofNullable(httpRequest.getHeader("senhamk")).ifPresentOrElse((value) ->{}, () -> {erros.put("senhamk", "NULL not allowed");});

        if(!erros.isEmpty()){
            HttpServletResponse  myResponse= (HttpServletResponse) response;
            myResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            myResponse.getOutputStream().flush();
            String errosJson = new ObjectMapper().writeValueAsString(erros);
            myResponse.getOutputStream().println(errosJson);
        } else {
            chain.doFilter(request, response);
        }

    }

    /*
    *
    *   List<String> mensagemDeErro = new ArrayList<>();
        Optional.ofNullable(connectionHeader.get("hostmk")).ifPresentOrElse((value) ->{}, () -> {
            mensagemDeErro.add("attribute hostmk not accept null");
        });
        Optional.ofNullable(connectionHeader.get("portmk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute portmk not accept null");});
        Optional.ofNullable(connectionHeader.get("usuariomk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute usuariomk not accept null");});
        Optional.ofNullable(connectionHeader.get("senhamk")).ifPresentOrElse((value) ->{}, () -> {mensagemDeErro.add("attribute senhamk not accept null");});

        if(!mensagemDeErro.isEmpty()){
            throw new HeaderException(mensagemDeErro.toString());
        }
    * */
}
