package de.jensd.addon.decoder;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jens Deters
 */
public abstract class AbstractPayloadDecoder implements PayloadDecoder, Comparable<PayloadDecoder> {

    private StringProperty id;
    private StringProperty name;
    private StringProperty version;
    private StringProperty description;

    public final StringProperty idProperty() {
        if (id == null) {
            id = new SimpleStringProperty();
        }
        return id;
    }

    @Override
    public String getId() {
        return idProperty().get();
    }

    public final StringProperty nameProperty() {
        if (name == null) {
            name = new SimpleStringProperty();
        }
        return name;
    }

    @Override
    public String getName() {
        return nameProperty().get();
    }

    protected final StringProperty versionProperty() {
        if (version == null) {
            version = new SimpleStringProperty();
        }
        return version;
    }

    @Override
    public String getVersion() {
        return versionProperty().get();
    }

    protected final StringProperty descriptionProperty() {
        if (description == null) {
            description = new SimpleStringProperty();
        }
        return description;
    }

    @Override
    public String getDescription() {
        return descriptionProperty().get();
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(PayloadDecoder otherPayloadConverter) {
        if (null == otherPayloadConverter) {
            return 0;
        }
        return getName().compareTo(otherPayloadConverter.getName());
    }
    
}
