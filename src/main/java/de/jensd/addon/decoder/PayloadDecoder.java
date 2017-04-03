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

import de.jensd.addon.AddOn;

/**
 * A MQTT.fx payload converter.
 * 
 * 
 * @author Jens Deters
 */
public interface PayloadDecoder extends AddOn, Comparable<PayloadDecoder> {

    /**
     * Decodes the given payload into a String representation.
     * 
     * @param payload The MQTT message payload.
     * @return The converted String representation.
     */
    String decode(byte[] payload);
    /**
     * 
     * @return The converter getVersion.
     */
    String getVersion();
    
    /**
     * 
     * @return The converter getDescription. 
     */
    String getDescription();
    
    /**
     * 
     * @return The logical getName of the converter. 
     */
    String getId();

    /**
     * 
     * @return The presentation getName of the converter. 
     */
    String getName();
}
