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

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import de.jensd.addon.decoder.utils.ContentType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import de.jensd.addon.decoder.PayloadDecoder;

/**
 *
 * @author Jens Deters
 */
class PlainTextDecoderTest {

    @Test
    void testPlainTextDecoder() {
        final String CONTENT = "Hello World";
        final byte[] PAYLOAD = CONTENT.getBytes();
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        Map<String, PayloadDecoder> decodersMap = decoders.stream().collect(
                Collectors.toMap(c -> c.getId(), c -> c));
        PayloadDecoder decoder = decodersMap.get("plain_text_decoder");
        assertNotNull(decoder, "PlainTextDecoder must not be null");
        String decoded = decoder.decode(PAYLOAD);
        assertEquals(decoded, CONTENT);

        String contentType = decoder.getContentType();
        assertEquals(ContentType.PLAIN_TEXT.getMimeType(), contentType);
    }

}
