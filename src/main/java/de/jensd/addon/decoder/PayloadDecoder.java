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
