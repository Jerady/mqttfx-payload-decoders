/**
 * Copyright (c) 2017 Jens Deters http://www.jensd.de
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package de.jensd.addon.decoder;

import de.jensd.addon.decoder.utils.ContentType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;


/**
 *
 * @author Jens Deters (jens.deters@softblade.de)
 */
public abstract class AbstractPayloadDecoder implements PayloadDecoder, Comparable<PayloadDecoder> {

    private StringProperty id;
    private StringProperty name;
    private StringProperty version;
    private StringProperty description;
    private StringProperty contentType;

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

    protected final StringProperty contentTypeProperty() {
        if (contentType == null) {
            contentType = new SimpleStringProperty(ContentType.PLAIN_TEXT.getMimeType());
        }
        return contentType;
    }

    @Override
    public String getContentType() {
        return contentTypeProperty().get();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractPayloadDecoder that = (AbstractPayloadDecoder) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(version, that.version) &&
            Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, version, description);
    }
}
