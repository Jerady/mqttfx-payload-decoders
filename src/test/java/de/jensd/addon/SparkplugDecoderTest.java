package de.jensd.addon;

import de.jensd.addon.decoder.preset.SparkplugDecoder;
import de.jensd.addon.decoder.utils.ContentType;

import java.lang.String;
import java.math.BigInteger;
import java.util.*;

import org.eclipse.tahu.SparkplugException;
import org.eclipse.tahu.SparkplugInvalidTypeException;
import org.eclipse.tahu.message.SparkplugBPayloadEncoder;
import org.eclipse.tahu.message.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.eclipse.tahu.message.model.MetricDataType.*;


/**
 *
 * @author Jens Deters
 */
class SparkplugDecoderTest {

    private static final String HW_VERSION = "Emulated Hardware";
    private static final String SW_VERSION = "v1.0.0";
    
    private int bdSeq = 0;
    private int seq = 0;

    @Test
    void testSparkplugDecoderWithDeathPayload() throws Exception {
        SparkplugBPayload.SparkplugBPayloadBuilder payload = new SparkplugBPayload.SparkplugBPayloadBuilder().setTimestamp(new Date());
        payload = addBdSeqNum(payload);
        byte[] payloadBytes = new SparkplugBPayloadEncoder().getBytes(payload.createPayload(), false);
        SparkplugDecoder sparkplugDecoder = new SparkplugDecoder();
        String decoded = sparkplugDecoder.decode(payloadBytes);
        System.out.println(decoded);
        assertFalse(decoded.isEmpty(), "Expected decodes String is not empty");

        String contentType = sparkplugDecoder.getContentType();
        assertEquals(ContentType.SPARKPLUG.getMimeType(), contentType);
    }

    @Test
    void testSparkplugDecoderDeviceBirthPayload() throws Exception {
        // Create the payload and add some metrics
        SparkplugBPayload payload = new SparkplugBPayload(
                new Date(),
                createMetrics(true),
                getSeqNum(),
                createUUID(),
                null);

        payload.addMetric(new Metric.MetricBuilder("Device Control/Rebirth", Boolean, false)
                .createMetric());

        // Only do this once to set up the inputs and outputs
        payload.addMetric(new Metric.MetricBuilder("Inputs/0", Boolean, true).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Inputs/1", Int32, 0).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Inputs/2", Double, 1.23d).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Outputs/0", Boolean, true).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Outputs/1", Int32, 0).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Outputs/2", Double, 1.23d).createMetric());

        //payload.addMetric(new MetricBuilder("New_1", Int32, 0).createMetric());
        //payload.addMetric(new MetricBuilder("New_2", Double, 1.23d).createMetric());
        // Add some properties
        payload.addMetric(new Metric.MetricBuilder("Properties/hw_version", String, HW_VERSION).createMetric());
        payload.addMetric(new Metric.MetricBuilder("Properties/sw_version", String, SW_VERSION).createMetric());

        PropertySet propertySet = new PropertySet.PropertySetBuilder()
                .addProperty("engUnit", new PropertyValue(PropertyDataType.String, "My Units"))
                .addProperty("engLow", new PropertyValue(PropertyDataType.Double, 1.0))
                .addProperty("engHigh", new PropertyValue(PropertyDataType.Double, 10.0))
                .createPropertySet();
        payload.addMetric(new Metric.MetricBuilder("MyMetric", String, "My Value")
                .properties(propertySet)
                .createMetric());
        
        byte[] payloadBytes = new SparkplugBPayloadEncoder().getBytes(payload,false);
        SparkplugDecoder sparkplugDecoder = new SparkplugDecoder();
        String decoded = sparkplugDecoder.decode(payloadBytes);
        System.out.println(decoded);
        assertFalse(decoded.isEmpty(), "Expected decodes String is not empty" );

    }

    private String createUUID() {
        return java.util.UUID.randomUUID().toString();
    }

    // Used to add the birth/death sequence number
    private SparkplugBPayload.SparkplugBPayloadBuilder addBdSeqNum(SparkplugBPayload.SparkplugBPayloadBuilder
                                                                       payload) throws Exception {
        if (payload == null) {
            payload = new SparkplugBPayload.SparkplugBPayloadBuilder();
        }
        if (bdSeq == 256) {
            bdSeq = 0;
        }
        payload.addMetric(new Metric.MetricBuilder("bdSeq", MetricDataType.Int64, (long) bdSeq).createMetric());
        bdSeq++;
        return payload;
    }

    // Used to add the sequence number
    private long getSeqNum() {
        if (seq == 256) {
            seq = 0;
        }
        return seq++;
    }

    private List<Metric> createMetrics(boolean isBirth) throws SparkplugException {
        Random random = new Random();
        List<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric.MetricBuilder("Int8", Int8, (byte) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("Int16", Int16, (short) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("Int32", Int32, random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("Int64", Int64, random.nextLong()).createMetric());
        metrics.add(new Metric.MetricBuilder("UInt8", UInt8, (short) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("UInt16", UInt16, random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("UInt32", UInt32, random.nextLong()).createMetric());
        metrics.add(new Metric.MetricBuilder("UInt64", UInt64, BigInteger.valueOf(random.nextLong())).createMetric());
        metrics.add(new Metric.MetricBuilder("Float", Float, random.nextFloat()).createMetric());
        metrics.add(new Metric.MetricBuilder("Double", Double, random.nextDouble()).createMetric());
        metrics.add(new Metric.MetricBuilder("Boolean", Boolean, random.nextBoolean()).createMetric());
        metrics.add(new Metric.MetricBuilder("String", String, createUUID()).createMetric());
        metrics.add(new Metric.MetricBuilder("DateTime", DateTime, new Date()).createMetric());
        metrics.add(new Metric.MetricBuilder("Text", Text, createUUID()).createMetric());
        metrics.add(new Metric.MetricBuilder("UUID", UUID, createUUID()).createMetric());
        //metrics.add(new MetricBuilder("Bytes", Bytes, randomBytes(20)).createMetric());
        //metrics.add(new MetricBuilder("File", File, null).createMetric());

        // DataSet
        metrics.add(new Metric.MetricBuilder("DataSet", DataSet, createDataSet()).createMetric());
        if (isBirth) {
            metrics.add(new Metric.MetricBuilder("TemplateDef", Template, createTemplate(true, null)).createMetric());
        }

        // Template
        metrics.add(new Metric.MetricBuilder("TemplateInst", Template, createTemplate(false, "TemplateDef")).createMetric());

        // Complex Template
        metrics.addAll(createComplexTemplate(isBirth));

        // Metrics with properties
        metrics.add(new Metric.MetricBuilder("IntWithProps", Int32, random.nextInt())
                .properties(new PropertySet.PropertySetBuilder()
                        .addProperty("engUnit", new PropertyValue(PropertyDataType.String, "My Units"))
                        .addProperty("engHigh", new PropertyValue(PropertyDataType.Int32, Integer.MAX_VALUE))
                        .addProperty("engLow", new PropertyValue(PropertyDataType.Int32, Integer.MIN_VALUE))
                        .createPropertySet())
                .createMetric());

        // Aliased metric
        // The name and alias will be specified in a NBIRTH/DBIRTH message.
        // Only the alias will be specified in a NDATA/DDATA message.
        Long alias = 1111L;
        if (isBirth) {
            metrics.add(new Metric.MetricBuilder("AliasedString", String, createUUID()).alias(alias).createMetric());
        } else {
            metrics.add(new Metric.MetricBuilder(alias, String, createUUID()).createMetric());
        }

        return metrics;
    }

    private PropertySet newPropertySet() throws SparkplugException {
        return new PropertySet.PropertySetBuilder()
                .addProperties(createProps(true))
                .createPropertySet();
    }

    private Map<String, PropertyValue> createProps(boolean withPropTypes) throws SparkplugException {
        Random random = new Random();
        Map<String, PropertyValue> propMap = new HashMap<>();
        propMap.put("PropInt8", new PropertyValue(PropertyDataType.Int8, (byte) random.nextInt()));
        propMap.put("PropInt16", new PropertyValue(PropertyDataType.Int16, (short) random.nextInt()));
        propMap.put("PropInt32", new PropertyValue(PropertyDataType.Int32, random.nextInt()));
        propMap.put("PropInt64", new PropertyValue(PropertyDataType.Int64, random.nextLong()));
        propMap.put("PropUInt8", new PropertyValue(PropertyDataType.UInt8, (short) random.nextInt()));
        propMap.put("PropUInt16", new PropertyValue(PropertyDataType.UInt16, random.nextInt()));
        propMap.put("PropUInt32", new PropertyValue(PropertyDataType.UInt32, random.nextLong()));
        propMap.put("PropUInt64", new PropertyValue(PropertyDataType.UInt64, BigInteger.valueOf(random.nextLong())));
        propMap.put("PropFloat", new PropertyValue(PropertyDataType.Float, random.nextFloat()));
        propMap.put("PropDouble", new PropertyValue(PropertyDataType.Double, random.nextDouble()));
        propMap.put("PropBoolean", new PropertyValue(PropertyDataType.Boolean, random.nextBoolean()));
        propMap.put("PropString", new PropertyValue(PropertyDataType.String, createUUID()));
        propMap.put("PropDateTime", new PropertyValue(PropertyDataType.DateTime, new Date()));
        propMap.put("PropText", new PropertyValue(PropertyDataType.Text, createUUID()));
        if (withPropTypes) {
            propMap.put("PropPropertySet", new PropertyValue(PropertyDataType.PropertySet, new PropertySet.PropertySetBuilder()
                    .addProperties(createProps(false))
                    .createPropertySet()));
            List<PropertySet> propsList = new ArrayList<>();
            propsList.add(new PropertySet.PropertySetBuilder().addProperties(createProps(false)).createPropertySet());
            propsList.add(new PropertySet.PropertySetBuilder().addProperties(createProps(false)).createPropertySet());
            propsList.add(new PropertySet.PropertySetBuilder().addProperties(createProps(false)).createPropertySet());
            propMap.put("PropPropertySetList", new PropertyValue(PropertyDataType.PropertySetList, propsList));
        }
        return propMap;
    }

    private List<Parameter> createParams() throws SparkplugException {
        Random random = new Random();
        List<Parameter> params = new ArrayList<>();
        params.add(new Parameter("ParamInt32", ParameterDataType.Int32, random.nextInt()));
        params.add(new Parameter("ParamFloat", ParameterDataType.Float, random.nextFloat()));
        params.add(new Parameter("ParamDouble", ParameterDataType.Double, random.nextDouble()));
        params.add(new Parameter("ParamBoolean", ParameterDataType.Boolean, random.nextBoolean()));
        params.add(new Parameter("ParamString", ParameterDataType.String, createUUID()));
        return params;
    }

    private List<Metric> createComplexTemplate(boolean withTemplateDefs) throws SparkplugInvalidTypeException {
        ArrayList<Metric> metrics = new ArrayList<>();
        if (withTemplateDefs) {

            // Add a new template "subType" definition with two primitive members
            metrics.add(new Metric.MetricBuilder("subType", Template, new Template.TemplateBuilder()
                    .definition(true)
                    .addMetric(new Metric.MetricBuilder("StringMember", String, "value").createMetric())
                    .addMetric(new Metric.MetricBuilder("IntegerMember", Int32, 0).createMetric())
                    .createTemplate()).createMetric());
            // Add new template "newType" definition that contains an instance of "subType" as a member
            metrics.add(new Metric.MetricBuilder("newType", Template, new Template.TemplateBuilder()
                    .definition(true)
                    .addMetric(new Metric.MetricBuilder("mySubType", Template, new Template.TemplateBuilder()
                            .definition(false)
                            .templateRef("subType")
                            .addMetric(new Metric.MetricBuilder("StringMember", String, "value").createMetric())
                            .addMetric(new Metric.MetricBuilder("IntegerMember", Int32, 0).createMetric())
                            .createTemplate()).createMetric())
                    .createTemplate()).createMetric());
        }

        // Add an instance of "newType
        metrics.add(new Metric.MetricBuilder("myNewType", Template, new Template.TemplateBuilder()
                .definition(false)
                .templateRef("newType")
                .addMetric(new Metric.MetricBuilder("mySubType", Template, new Template.TemplateBuilder()
                        .definition(false)
                        .templateRef("subType")
                        .addMetric(new Metric.MetricBuilder("StringMember", String, "myValue").createMetric())
                        .addMetric(new Metric.MetricBuilder("IntegerMember", Int32, 1).createMetric())
                        .createTemplate()).createMetric())
                .createTemplate()).createMetric());

        return metrics;

    }

    private Template createTemplate(boolean isDef, String templateRef) throws SparkplugException {
        Random random = new Random();
        List<Metric> metrics = new ArrayList<>();
        metrics.add(new Metric.MetricBuilder("MyInt8", Int8, (byte) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyInt16", Int16, (short) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyInt32", Int32, random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyInt64", Int64, random.nextLong()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyUInt8", UInt8, (short) random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyUInt16", UInt16, random.nextInt()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyUInt32", UInt32, random.nextLong()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyUInt64", UInt64, BigInteger.valueOf(random.nextLong())).createMetric());
        metrics.add(new Metric.MetricBuilder("MyFloat", Float, random.nextFloat()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyDouble", Double, random.nextDouble()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyBoolean", Boolean, random.nextBoolean()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyString", String, createUUID()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyDateTime", DateTime, new Date()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyText", Text, createUUID()).createMetric());
        metrics.add(new Metric.MetricBuilder("MyUUID", UUID, createUUID()).createMetric());

        return new Template.TemplateBuilder()
                .version("v1.0")
                .templateRef(templateRef)
                .definition(isDef)
                .addParameters(createParams())
                .addMetrics(metrics)
                .createTemplate();
    }

    private DataSet createDataSet() throws SparkplugException {
        Random random = new Random();
        return new DataSet.DataSetBuilder(14)
                .addColumnName("Int8s")
                .addColumnName("Int16s")
                .addColumnName("Int32s")
                .addColumnName("Int64s")
                .addColumnName("UInt8s")
                .addColumnName("UInt16s")
                .addColumnName("UInt32s")
                .addColumnName("UInt64s")
                .addColumnName("Floats")
                .addColumnName("Doubles")
                .addColumnName("Booleans")
                .addColumnName("Strings")
                .addColumnName("Dates")
                .addColumnName("Texts")
                .addType(DataSetDataType.Int8)
                .addType(DataSetDataType.Int16)
                .addType(DataSetDataType.Int32)
                .addType(DataSetDataType.Int64)
                .addType(DataSetDataType.UInt8)
                .addType(DataSetDataType.UInt16)
                .addType(DataSetDataType.UInt32)
                .addType(DataSetDataType.UInt64)
                .addType(DataSetDataType.Float)
                .addType(DataSetDataType.Double)
                .addType(DataSetDataType.Boolean)
                .addType(DataSetDataType.String)
                .addType(DataSetDataType.DateTime)
                .addType(DataSetDataType.Text)
                .addRow(new Row.RowBuilder()
                        .addValue(new Value<>(DataSetDataType.Int8, (byte) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int16, (short) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int32, random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int64, random.nextLong()))
                        .addValue(new Value<>(DataSetDataType.UInt8, (short) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.UInt16, random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.UInt32, random.nextLong()))
                        .addValue(new Value<>(DataSetDataType.UInt64, BigInteger.valueOf(random.nextLong())))
                        .addValue(new Value<>(DataSetDataType.Float, random.nextFloat()))
                        .addValue(new Value<>(DataSetDataType.Double, random.nextDouble()))
                        .addValue(new Value<>(DataSetDataType.Boolean, random.nextBoolean()))
                        .addValue(new Value<>(DataSetDataType.String, createUUID()))
                        .addValue(new Value<>(DataSetDataType.DateTime, new Date()))
                        .addValue(new Value<>(DataSetDataType.Text, createUUID()))
                        .createRow())
                .addRow(new Row.RowBuilder()
                        .addValue(new Value<>(DataSetDataType.Int8, (byte) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int16, (short) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int32, random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.Int64, random.nextLong()))
                        .addValue(new Value<>(DataSetDataType.UInt8, (short) random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.UInt16, random.nextInt()))
                        .addValue(new Value<>(DataSetDataType.UInt32, random.nextLong()))
                        .addValue(new Value<>(DataSetDataType.UInt64, BigInteger.valueOf(random.nextLong())))
                        .addValue(new Value<>(DataSetDataType.Float, random.nextFloat()))
                        .addValue(new Value<>(DataSetDataType.Double, random.nextDouble()))
                        .addValue(new Value<>(DataSetDataType.Boolean, random.nextBoolean()))
                        .addValue(new Value<>(DataSetDataType.String, createUUID()))
                        .addValue(new Value<>(DataSetDataType.DateTime, new Date()))
                        .addValue(new Value<>(DataSetDataType.Text, createUUID()))
                        .createRow())
                .createDataSet();
    }
}
