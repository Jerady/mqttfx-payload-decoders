/*
 * Licensed Materials - Property of Cirrus Link Solutions
 * Copyright (c) 2018 Cirrus Link Solutions LLC - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package de.jensd.addon.decoder.preset;

import com.cirruslink.sparkplug.message.PayloadDecoder;
import com.cirruslink.sparkplug.message.SparkplugBPayloadDecoder;
import com.cirruslink.sparkplug.message.model.SparkplugBPayload;
import com.cirruslink.sparkplug.util.PayloadUtil;
import de.jensd.addon.decoder.AbstractPayloadDecoder;
import de.jensd.addon.decoder.utils.ContentType;
import de.jensd.addon.decoder.utils.Formatter;

public class SparkplugDecoderPrettyFormat extends AbstractPayloadDecoder {

	public SparkplugDecoderPrettyFormat() {
		idProperty().set("sparkplug_decoder_pretty_format");
		nameProperty().set("Sparkplug Decoder (JSON formatter)");
		versionProperty().set("1.1.0");
		descriptionProperty().set("Decodes the binary Sparkplug payload data into a formatted JSON representation");
		contentTypeProperty().set(ContentType.SPARKPLUG.getMimeType());
	}

	@Override
	public String decode(byte[] payload) {
		try {
			PayloadDecoder<SparkplugBPayload> decoder = new SparkplugBPayloadDecoder();
			SparkplugBPayload sparkplugPayload = decoder.buildFromByteArray(payload);
			return Formatter.asJSONFormatted(PayloadUtil.toJsonString(sparkplugPayload));
		} catch(Exception e) {
			e.printStackTrace();
			return "Failed to parse the payload";
		}
	}
}