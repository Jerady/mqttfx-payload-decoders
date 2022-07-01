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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Jens Deters (jens.deters@softblade.de)
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
            if (i % 1 == 0) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }

    public static String asHexPrettyFormatted(byte[] payload) {

        List<String> hexCharList = Stream.of(asHexFormatted(payload).split(" "))
            .map (String::new)
            .collect(Collectors.toList());

        int fill = 16-hexCharList.size()%16;
        for (int j=0; j<fill;j++){
            hexCharList.add("00");
        }


        StringBuilder builder = new StringBuilder();
        int i = 0;
        StringBuilder decodedStrBuffer = new StringBuilder();
        for (String element : hexCharList) {
            if(element.equals("00")){
                decodedStrBuffer.append(".");
            }else {
                Character c = (char)Integer.parseInt(element, 16);
                if(Integer.parseInt(element, 16) == 10){
                    c = ' ';
                }
                decodedStrBuffer.append(c);
            }
            builder.append(element + " ");
            if (i % 16 == 15) {
                builder.append(" | ");
                builder.append(decodedStrBuffer);
                builder.append("\n");
                decodedStrBuffer = new StringBuilder();

            }
            i++;
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

    public static String asBinary(byte[] payload) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : payload) {
            result.append(String.format("%8s", Integer.toBinaryString(aByte & 0xFF)).replace(" ", "0"));
        }
        return result.toString();
    }

    public static String prettyBinary(String binary, int blockSize, String separator) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }
        return result.stream().collect(Collectors.joining(separator));
    }

}
