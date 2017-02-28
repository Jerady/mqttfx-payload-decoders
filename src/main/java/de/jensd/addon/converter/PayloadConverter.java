package de.jensd.addon.converter;

import de.jensd.addon.AddOn;

/**
 *
 * @author Jens Deters
 */
public interface PayloadConverter extends AddOn {
    String convert(byte[] payload);
    String name();
    String version();
}
