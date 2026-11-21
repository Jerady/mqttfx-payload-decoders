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

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jens Deters
 */
class BinaryDecoderTest {

    @Test
    void testBinaryDecoder() {
        final String CONTENT = "Hello World";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.getId(), c -> c));
        PayloadDecoder decoder = decodersMap.get("binary_decoder");
        assertNotNull(decoder, "BinaryDecoder must not be null");
        String decoded = decoder.decode(PAYLOAD);
        assertEquals("0100100001100101011011000110110001101111001000000101011101101111011100100110110001100100",decoded);

        String contentType = decoder.getContentType();
        assertEquals(ContentType.BINARY.getMimeType(), contentType);
    }

}
