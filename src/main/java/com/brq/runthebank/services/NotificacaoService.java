package com.brq.runthebank.services;

import com.brq.runthebank.dto.response.NotificacaoResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificacaoService {

    private final String NOTIFICACAO_ENDPOINT = "https://run.mocky.io/v3/9769bf3a-b0b6-477a-9ff5-91f63010c9d3";

    public NotificacaoResponse enviarNotificacao() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(NOTIFICACAO_ENDPOINT, null, NotificacaoResponse.class);
    }
}
