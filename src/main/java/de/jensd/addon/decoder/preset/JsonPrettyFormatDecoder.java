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
 * Decodes JSON payload data into a readable format
 *
 * @author Jens Deters
 * @version 1.1.0
 */
public class JsonPrettyFormatDecoder extends AbstractPayloadDecoder {

      public JsonPrettyFormatDecoder() {
        setId("json_pretty_format_decoder");
        setName("JSON Pretty Fomat Decoder");
        setVersion("1.2.0");
        setDescription("Decodes JSON payload data into a readable format.");
        setContentType(ContentType.JSON.getMimeType());
    }
    
    @Override
    public String decode(byte[] payload) {
        return ByteArray.asJSONFormatted(payload);
    }

}
