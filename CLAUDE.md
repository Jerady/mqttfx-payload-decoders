# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

This is the default preset of payload decoders for [MQTT.fx](http://mqttfx.org). Each decoder converts a raw MQTT message payload (`byte[]`) into a human-readable `String`. Decoders are plugins built on top of the `de.jensd:addon-commons` SPI and are discovered at runtime by MQTT.fx via Java's `ServiceLoader`.

## Build & Test

Uses the Gradle wrapper (Gradle 7.3.3). JUnit 5 (Jupiter).

```bash
./gradlew build              # compile + test + jar
./gradlew test               # run all tests
./gradlew clean

# Run a single test class or method:
./gradlew test --tests de.jensd.addon.BinaryDecoderTest
./gradlew test --tests 'de.jensd.addon.BinaryDecoderTest.testBinaryDecoder'

# Publish artifact to local Maven repo:
./gradlew publishToMavenLocal
```

Note: `build.gradle` declares `wrapper { gradleVersion = '4.6' }`, but the checked-in wrapper actually runs 7.3.3 — do not run `./gradlew wrapper` unless you intend to downgrade.

## Architecture

The plugin contract is `de.jensd.addon.decoder.PayloadDecoder` (extends `AddOn` and `Comparable` from addon-commons). It exposes `decode(byte[])` plus metadata getters (`getId`, `getName`, `getVersion`, `getDescription`, `getContentType`).

`AbstractPayloadDecoder` is the base class for all decoders. It backs every metadata field with a JavaFX `StringProperty` (so MQTT.fx can bind to them), implements `compareTo`/`equals`/`hashCode`, and defaults `contentType` to plain text. Concrete decoders set their `id`/`name`/`version`/`description`/`contentType` in the constructor and implement only `decode(byte[])`.

Concrete decoders live in `de.jensd.addon.decoder.preset.*`. Shared decoding logic lives in `de.jensd.addon.decoder.utils.*`:
- `ByteArray` — hex / pretty-hex / binary formatting of byte arrays.
- `ContentType` — enum of MIME types decoders advertise.
- `Formatter` — JSON pretty-printing (Jackson).

Sparkplug has two generations of decoders: the original `SparkplugDecoder` / `SparkplugPrettyFormatDecoder`, and the newer `SparkplugTahuDecoder` / `SparkplugTahuPrettyFormatDecoder` based on Eclipse Tahu (`org.eclipse.tahu:tahu-core`).

### SPI registration (critical)

A decoder is only visible to MQTT.fx if it is listed in:

```
src/main/resources/META-INF/services/de.jensd.addon.decoder.PayloadDecoder
```

**When adding a new decoder, you must append its fully-qualified class name to that file**, otherwise `ServiceLoader` won't pick it up. Tests load decoders the same way (via addon-commons `AddOnRegistryServiceLoader` / `AddOnRegistry`), so an unregistered decoder also won't appear in registry-based tests.

## Versioning

The published artifact version lives in `deploy.gradle` (`artifactVersion`). Individual decoders also carry their own `version` string set in their constructor (currently `1.2.0`), independent of the artifact version. The git branch name tends to track the release (e.g. `19.0.0`, `20.0.1`).
