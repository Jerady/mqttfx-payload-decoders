/**
 * Copyright (c) 2019 fos4X GmbH https://fos4x.de
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
package de.jensd.addon.decoder.preset;

import java.io.IOException;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ByteArray;

import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;
import org.msgpack.value.ValueType;

/**
 *
 * Decodes the payload data into plain text
 *
 * @author Philipp Tölke (fos4X GmbH)
 * @version 1.0.0
 */
public class MsgPackDecoder extends AbstractPayloadDecoder {

    public MsgPackDecoder() {
        idProperty().set("msgpack_decoder");
        nameProperty().set("Msgpack Decoder");
        versionProperty().set("1.0.0");
        descriptionProperty().set("Decodes the payload data into plain text");
    }

    private void unpackOne(MessageUnpacker unpacker, StringBuffer sb) throws IOException {
        int length;
        switch (unpacker.getNextFormat().getValueType()) {
            case INTEGER:
                sb.append(unpacker.unpackBigInteger());
                break;
            case FLOAT:
                sb.append(unpacker.unpackDouble());
                break;
            case STRING:
                sb.append(unpacker.unpackString());
                break;
            case MAP:
                sb.append("{");
                length = unpacker.unpackMapHeader();
                for (int i = 0; i < length; i++) {
                    unpackOne(unpacker, sb);
                    sb.append(": ");
                    unpackOne(unpacker, sb);
                    if (i != length - 1) sb.append(", ");
                }
                sb.append("}");
                break;
            case ARRAY:
                sb.append("[");
                length = unpacker.unpackArrayHeader();
                for (int i = 0; i < length; i++) {
                    unpackOne(unpacker, sb);
                    if (i != length - 1) sb.append(", ");
                }
                sb.append("]");
                break;
        }
    }

    private void readUnpacker(MessageUnpacker unpacker, StringBuffer sb) throws IOException {
        while (unpacker.hasNext()) {
            unpackOne(unpacker, sb);
        }
    }

    @Override
    public String decode(byte[] payload) {
        try {
            MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(payload);
            StringBuffer sb = new StringBuffer();
            readUnpacker(unpacker, sb);
            return sb.toString();
        } catch (IOException ex) {
            return "*** PAYLOAD IS NOT VALID MSGPACK DATA *** \n\n" + ex.getMessage();
        }
    }
}
