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

public class SparkplugDecoder extends AbstractPayloadDecoder {

	public SparkplugDecoder() {
		idProperty().set("sparkplug_decoder");
		nameProperty().set("Sparkplug Decoder");
		versionProperty().set("1.0.0");
		descriptionProperty().set("Decodes the binary Sparkplug payload data into a JSON representation");
	}

	@Override
	public String decode(byte[] payload) {
		try {
			PayloadDecoder<SparkplugBPayload> decoder = new SparkplugBPayloadDecoder();
			SparkplugBPayload sparkplugPayload = decoder.buildFromByteArray(payload);
			return PayloadUtil.toJsonString(sparkplugPayload);
		} catch(Exception e) {
			e.printStackTrace();
			return "Failed to parse the payload";
		}
	}
}