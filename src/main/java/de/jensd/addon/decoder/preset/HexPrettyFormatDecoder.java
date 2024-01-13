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
package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;
import de.jensd.addon.decoder.utils.ContentType;

/**
 * Converts the payload data into formatted hex code
 *
 * @author Jens Deters
 * @version 1.1.0
 */
public class HexPrettyFormatDecoder extends AbstractPayloadDecoder {

    public HexPrettyFormatDecoder() {
        idProperty().set("hex_pretty_format_decoder");
        nameProperty().set("Hex Pretty Format Decoder");
        versionProperty().set("1.2.0");
        descriptionProperty().set("Decodes the payload data into a hex readable formatted string.");
        contentTypeProperty().set(ContentType.HEX.getMimeType());
    }

    @Override
    public String decode(byte[] payload) {
        return ByteArray.asHexPrettyFormatted(payload);
    }

}
