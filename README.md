 [ ![Download](https://api.bintray.com/packages/jerady/maven/mqttfx-payload-decoders/images/download.svg) ](https://bintray.com/jerady/maven/mqttfx-payload-decoders/_latestVersion)
 
# Default MQTT.fx Payload Decoders

![](images/MQTTfx_icon_256.png)

The default preset of [MQTT.fx](http://mqttfx.org) message payload decoder implementations based on 
[addon-commons](https://github.com/Jerady/addon-commons).

## Available Decoders

| Name          | Classname     | Version | Pupose|
| :------------- |:-------------| :-----: | :----- |
| Plain Text Decoder     | ```de.jensd.addon.decoder.preset.PlainTextDecoder``` | 1.0.0 | Decodes the payload data into plain text |
| Base64 Decoder        | ```de.jensd.addon.decoder.preset.Base64Decoder``` | 1.0.0 | Decodes the payload data into base64 endcoding |
| JSON Pretty Fomat Decoder     | ```de.jensd.addon.decoder.preset.JsonPrettyFormatDecoder``` | 1.0.0 | Decodes JSON payload data into a readable format|
| Hex Format Decoder    | ```de.jensd.addon.decoder.preset.HexFormatDecoder``` | 1.0.0 |Decodes the payload data into a hex formatted string |
| Sparkplug Decoder     | ```de.jensd.addon.decoder.preset.SparkplugDecoder``` | 1.0.0 | Decodes the binary Sparkplug payload data into a JSON representation |
| MsgPack Decoder | ```de.jensd.addon.decoder.preset.MsgPackDecoder``` | 1.0.0 | Decodes the msgpack payload data into plain text |

## Sparkplug Decoder

Sparkplug is a specification for MQTT enabled devices and applications to send and receive messages in a stateful way. 

A decoder for the binary Sparkplug payload data has been added recently.
Many Thanks to Wes Johnson from [Cirrus Link](https://www.cirrus-link.com) for contribution!

You can get more information about Sparkplug [here](https://github.com/Cirrus-Link/Sparkplug)!

