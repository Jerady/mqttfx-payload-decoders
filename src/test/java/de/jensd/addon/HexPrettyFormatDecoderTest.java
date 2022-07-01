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
package de.jensd.addon;

import de.jensd.addon.decoder.PayloadDecoder;
import de.jensd.addon.decoder.utils.ContentType;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author Jens Deters
 */
class HexPrettyFormatDecoderTest {

    @Test
    void testHexFormatDecoder() {
        final String CONTENT = "the quick brown fox jumps over the lazy dog";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.getId(), c -> c));
        PayloadDecoder decoder = decodersMap.get("hex_pretty_format_decoder");
        assertNotNull(decoder, "HexPrettyFormatDecoder must not be null");
        String decoded = decoder.decode(PAYLOAD);
        System.out.println(decoded);

        assertEquals("74 68 65 20 71 75 69 63 6B 20 62 72 6F 77 6E 20  | the quick brown \n" +
            "66 6F 78 20 6A 75 6D 70 73 20 6F 76 65 72 20 74  | fox jumps over t\n" +
            "68 65 20 6C 61 7A 79 20 64 6F 67 00 00 00 00 00  | he lazy dog.....\n",decoded);

        String contentType = decoder.getContentType();
        assertEquals(ContentType.HEX.getMimeType(), contentType);
    }

}
