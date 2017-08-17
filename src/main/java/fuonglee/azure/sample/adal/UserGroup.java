package fuonglee.azure.sample.adal;

public class UserGroup {
	private String odataType;
    private String objectType;
    private String description;
    private String displayName;

    public UserGroup(String odataType, String objectType, String description, String displayName) {
        this.odataType = odataType;
        this.objectType = objectType;
        this.description = description;
        this.displayName = displayName;
    }

    public String getOdataType() {
        return odataType;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}