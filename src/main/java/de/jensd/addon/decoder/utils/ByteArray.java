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
package de.jensd.addon.decoder.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Jens Deters (jens.deters@codecentric.de)
 */
public class ByteArray {

    private ByteArray(){}

    public static String asString(byte[] payload) {
        return new String(payload, StandardCharsets.UTF_8);
    }

    public static String asHex(byte[] payload) {
        return Hex.encodeHexString(payload);
    }

    public static String asBase64(byte[] payload) {
        return Base64.encodeBase64String(payload);
    }

    public static String asHexFormatted(byte[] payload) {
        StringBuilder builder = new StringBuilder(payload.length * 2);
        for (int i = 0; i < payload.length; i++) {
            builder.append(String.format("%02X", payload[i]));
            if (i % 2 == 1) {
                builder.append(" ");
            }
            if (i % 16 == 15) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public static String asJSONFormatted(byte[] payload) {
        String jsonString = asString(payload);
        ObjectMapper mapper = new ObjectMapper();
        String result;
        try {
            Object json = mapper.readValue(jsonString, Object.class);
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        } catch (IOException ex) {
            result = "*** PAYLOAD IS NOT VALID JSON DATA *** \n\n" + ex.getMessage();
        }
        return result;
    }
}
