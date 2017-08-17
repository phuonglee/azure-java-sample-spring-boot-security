package fuonglee.azure.sample.adal;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private OAuth2RestTemplate oAuth2RestTemplate;

//    @Autowired
//    private RestTemplate restTemplate;

    private static String LIST_USERS = "https://graph.windows.net/42a361ca-9860-439b-ae1e-9794ac0552b0/users?api-version=1.6";

    public String listUsers() {
        RequestEntity<Void> entity = RequestEntity
                .get(URI.create(LIST_USERS))
//                .header("Authorization", "Bearer " + oAuth2RestTemplate.getAccessToken())
                .build();
        ResponseEntity<String> exchange = oAuth2RestTemplate.exchange(entity, String.class);
        return exchange.getBody();
    }

}