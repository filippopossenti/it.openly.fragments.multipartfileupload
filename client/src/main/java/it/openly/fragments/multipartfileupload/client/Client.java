package it.openly.fragments.multipartfileupload.client;

import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Client {
    private RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> upload(Resource resource, String path, String tag) {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", resource);
        body.add("tag", tag);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(String.format("http://localhost:8080/%s/upload", path), HttpMethod.POST, requestEntity, String.class);
        if(!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException(String.format("Server returned %d", responseEntity.getStatusCode().value()));
        }
        return new HashMap<>();

    }
}
