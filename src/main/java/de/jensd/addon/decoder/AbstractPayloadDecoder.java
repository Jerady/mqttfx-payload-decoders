package de.jensd.addon.decoder;

/**
 *
 * @author Jens Deters
 */
public abstract class AbstractPayloadDecoder implements PayloadDecoder, Comparable<PayloadDecoder> {

    @Override
    public String toString() {
        return name();
    }

    @Override
    public int compareTo(PayloadDecoder otherPayloadConverter) {
        if (null == otherPayloadConverter) {
            return 0;
        }
        return name().compareTo(otherPayloadConverter.name());
    }
}
