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
import org.junit.Test;
import static org.junit.Assert.*;
import de.jensd.addon.decoder.PayloadDecoder;

/**
 *
 * @author Jens Deters
 */
public class DecoderRegistryTest {

    @Test
    public void testLoadPayloadDecoders() {
        AddOnRegistryServiceLoader registry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> decoders = registry.getAddOns(PayloadDecoder.class);
        assertTrue("Expected to find 4 payload decoders", decoders.size() == 4);
    }

    
}
