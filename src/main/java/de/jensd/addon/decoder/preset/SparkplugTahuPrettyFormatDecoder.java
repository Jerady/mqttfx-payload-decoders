/*
 * Licensed Materials - Property of Cirrus Link Solutions
 * Copyright (c) 2018 Cirrus Link Solutions LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package de.jensd.addon.decoder.preset;

import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ContentType;
import de.jensd.addon.decoder.utils.Formatter;
import org.eclipse.tahu.message.PayloadDecoder;
import org.eclipse.tahu.message.SparkplugBPayloadDecoder;
import org.eclipse.tahu.message.model.SparkplugBPayload;
import org.eclipse.tahu.util.PayloadUtil;

public class SparkplugTahuPrettyFormatDecoder extends AbstractPayloadDecoder {

	public SparkplugTahuPrettyFormatDecoder() {
		idProperty().set("sparkplug_tahu_decoder_pretty_format");
		nameProperty().set("Sparkplug Pretty Format Decoder (Eclipse Tahu)");
		versionProperty().set("1.1.0");
		descriptionProperty().set("Decodes the binary Sparkplug payload data into a JSON representation.");
		contentTypeProperty().set(ContentType.SPARKPLUG.getMimeType());
	}

	@Override
	public String decode(byte[] payload) {
		try {
			PayloadDecoder<SparkplugBPayload> decoder = new SparkplugBPayloadDecoder();
			SparkplugBPayload sparkplugPayload = decoder.buildFromByteArray(payload);
			return Formatter.asJSONFormatted(PayloadUtil.toJsonString(sparkplugPayload));
		} catch(Exception e) {
			return "Failed to parse the payload";
		}
	}
}