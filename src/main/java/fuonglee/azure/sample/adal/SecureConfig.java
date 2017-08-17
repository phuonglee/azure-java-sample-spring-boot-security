package fuonglee.azure.sample.adal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.servlet.Filter;

@Configuration
@EnableOAuth2Sso
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecureConfig extends WebSecurityConfigurerAdapter {
	public static final String AAD_LOG_OUT_URL = "https://login.microsoftonline.com/42a361ca-9860-439b-ae1e-9794ac0552b0/oauth2/logout?post_logout_redirect_uri=http://localhost:8080";

	@Autowired
	@Qualifier("AzureADJwtTokenFilter")
	private Filter aadJwtFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// allow loading our single page application by everyone. not required if the page is hosted somewhere else.
		http.authorizeRequests()
				.antMatchers("/**", "/login**", "/webjars/**")
				.permitAll();

		// all other services are protected.
		http.authorizeRequests()
				.anyRequest()
				.authenticated();

		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl(AAD_LOG_OUT_URL)
				.permitAll();

		// we are using token based authentication. csrf is not required.
		http.csrf().disable();

		// need a filter to validate the Jwt token from AzureAD and assign roles.
		// without this, the token will not be validated and the role is always ROLE_USER.
		http.addFilterBefore(aadJwtFilter, ChannelProcessingFilter.class);

		http.httpBasic();
	}

}
