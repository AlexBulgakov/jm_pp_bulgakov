package ru.bulgakov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.bulgakov.model.User;

import java.util.List;


@Component
public class Communication {
    @Autowired
    private RestTemplate restTemplate;
    private String sessionId;
    private final String URL = "http://91.241.64.178:7081/api/users";

    User newUser = new User(3, "James", "Brown", (byte) 33);

    public void setConnection() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(URL, String.class);
        sessionId = forEntity.getHeaders().get("Set-Cookie").get(0);
        System.out.println(sessionId);
    }

    public void addUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie",sessionId);
        HttpEntity<User> requestBody = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.POST, requestBody, String.class);
        System.out.println(result.getBody());

        newUser.setName("Thomas");
        newUser.setLastName("Shelby");
        ResponseEntity<String> result1 = restTemplate.exchange(URL,
                HttpMethod.PUT, requestBody, String.class);
        System.out.println(result1.getBody());

        ResponseEntity<String> result2 = restTemplate.exchange("http://91.241.64.178:7081/api/users/3",
                HttpMethod.DELETE, requestBody, String.class);
        System.out.println(result2.getBody());
        System.out.println(result.getBody() + result1.getBody() + result2.getBody());
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
                });

        List<User> allUsers = responseEntity.getBody();
        return allUsers;
    }

}
