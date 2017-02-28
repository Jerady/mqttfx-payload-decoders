package de.jensd.addon.payloadconverter;

/**
 *
 * @author Jens Deters
 */
public abstract class AbstractPayloadConverter implements PayloadConverter {

    @Override
    public String toString() {
        return name();
    }
}
