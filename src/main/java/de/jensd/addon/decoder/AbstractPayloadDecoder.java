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

import java.util.Objects;


/**
 *
 * @author Jens Deters (jens.deters@softblade.de)
 */
public abstract class AbstractPayloadDecoder implements PayloadDecoder, Comparable<PayloadDecoder> {

    // plain fields: the decoders are data, nobody observes them, and the
    // library must not need JavaFX (MQTT.fx 6 runs without it)
    private String id;
    private String name;
    private String version;
    private String description;
    private String contentType = ContentType.PLAIN_TEXT.getMimeType();

    @Override
    public String getId() {
        return id;
    }

    protected final void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    protected final void setName(String name) {
        this.name = name;
    }

    @Override
    public String getVersion() {
        return version;
    }

    protected final void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getDescription() {
        return description;
    }

    protected final void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    protected final void setContentType(String contentType) {
        this.contentType = contentType;
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
