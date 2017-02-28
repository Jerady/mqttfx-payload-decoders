package de.jensd.addon.payloadconverter;

import de.jensd.addon.AddOn;

/**
 * A MQTT.fx payload converter.
 * 
 * 
 * @author Jens Deters
 */
public interface PayloadConverter extends AddOn {
    /**
     * Converts the given payload into a String representation.
     * 
     * @param payload The MQTT message payload.
     * @return The converted String representation.
     */
    String convert(byte[] payload);
    /**
     * 
     * @return The converter version.
     */
    String version();
    
    /**
     * 
     * @return The converter description. 
     */
    String description();
    
    /**
     * 
     * @return The logical name of the converter. 
     */
    String id();

    /**
     * 
     * @return The presentation name of the converter. 
     */
    String name();
}
