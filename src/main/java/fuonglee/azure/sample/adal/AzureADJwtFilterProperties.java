package fuonglee.azure.sample.adal;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties("azure.activedirectory")
public class AzureADJwtFilterProperties {
	private static final String aadSignInUri = "https://login.microsoftonline.com/";
    private static final String aadGraphAPIUri = "https://graph.windows.net/";
    /**
     * Registered application ID in Azure AD.
     */
    @NotEmpty
    private String clientId;
    /**
     * API Access Key of the registered application.
     */
    @NotEmpty
    private String clientSecret;
    /**
     * Allowed roles and groups in Azure AD.
     */
    @NotEmpty
    private List<String> allowedRolesGroups;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAadSignInUri() {
        return aadSignInUri;
    }

    public String getAadGraphAPIUri() {
        return aadGraphAPIUri;
    }

    public List<String> getAllowedRolesGroups() {
        return allowedRolesGroups;
    }

    public void setAllowedRolesGroups(List<String> allowedRolesGroups) {
        this.allowedRolesGroups = allowedRolesGroups;
    }

}