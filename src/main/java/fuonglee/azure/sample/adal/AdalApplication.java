package fuonglee.azure.sample.adal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;

@SpringBootApplication
public class AdalApplication {

    @Autowired
    private AzureRequestEnhancer azureRequestEnhancer;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AdalApplication.class, args);
    }

    @Bean
    public OAuth2RestTemplate oAuth2RestTemplate(UserInfoRestTemplateFactory factory) {
        OAuth2RestTemplate template = factory.getUserInfoRestTemplate();
        AuthorizationCodeAccessTokenProvider authorizationCodeAccessTokenProvider = new AuthorizationCodeAccessTokenProvider();
        authorizationCodeAccessTokenProvider.setTokenRequestEnhancer(azureRequestEnhancer);
        template.setAccessTokenProvider(authorizationCodeAccessTokenProvider);
        return template;
    }
}