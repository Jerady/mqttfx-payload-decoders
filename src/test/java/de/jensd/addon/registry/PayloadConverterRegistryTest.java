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
package de.jensd.addon.registry;

import de.jensd.addon.AddOnRegistryServiceLoader;
import java.util.List;
import de.jensd.addon.decoder.PayloadDecoder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jens Deters
 */
class PayloadConverterRegistryTest {

    public PayloadConverterRegistryTest() {
    }

    @Test
    void testLoadExtensions() {
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> converters = extensionRegistry.getAddOns(PayloadDecoder.class);
        assertEquals(true, converters.size() == 11, "Expected to find 11 payload decoders");
    }

    @Test
    void testLoadExtensionsFromJar() {
        String lookupPath = "build/libs/";
        System.setProperty(AddOnRegistryServiceLoader.ADDON_LOOKUP_PATH_PROPERTY_NAME, lookupPath);
        AddOnRegistryServiceLoader extensionRegistry = new AddOnRegistryServiceLoader();
        List<PayloadDecoder> converters = extensionRegistry.getAddOns(PayloadDecoder.class);
        converters.forEach(c -> {
            System.out.println(String.format("%-30s %-30s %-10s %s", c.getId(), c.getName(), c.getVersion(), c.getDescription()));
        });
        assertEquals( true , converters.size() == 11, "Expected to find 11 payload decoders");
    }
}
