package de.jensd.addon.payloadconverter;

/**
 *
 * @author Jens Deters
 */
public abstract class AbstractPayloadConverter implements PayloadConverter, Comparable<PayloadConverter> {

    @Override
    public String toString() {
        return name();
    }

    @Override
    public int compareTo(PayloadConverter otherPayloadConverter) {
        if (null == otherPayloadConverter) {
            return 0;
        }
        return name().compareTo(otherPayloadConverter.name());
    }
}
