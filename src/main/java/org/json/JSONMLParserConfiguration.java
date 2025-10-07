package org.json;
/*
Public Domain.
*/

/**
 * Configuration object for the XML to JSONML parser. The configuration is immutable.
 */
@SuppressWarnings({""})
public class JSONMLParserConfiguration extends ParserConfiguration {

    /**
     * We can override the default maximum nesting depth if needed.
     */
    public static final int DEFAULT_MAXIMUM_NESTING_DEPTH = ParserConfiguration.DEFAULT_MAXIMUM_NESTING_DEPTH;

    /** Original Configuration of the XML to JSONML Parser. */
    public static final JSONMLParserConfiguration ORIGINAL
        = new JSONMLParserConfiguration();
    /** Original configuration of the XML to JSONML Parser except that values are kept as strings. */
    public static final JSONMLParserConfiguration KEEP_STRINGS
        = new JSONMLParserConfiguration().withKeepStrings(true);

    /**
     * Flag to indicate whether LinkedHashMap should be used to preserve order when parsing XML.
     * The default behaviour is to use HashMap which does not preserve insertion order.
     * When this is set to true, LinkedHashMap will be used to preserve the order of elements.
     */
    private boolean useLinkedHashMap;

    /**
     * Default parser configuration. Does not keep strings (tries to implicitly convert values).
     */
    public JSONMLParserConfiguration() {
        super();
        this.maxNestingDepth = DEFAULT_MAXIMUM_NESTING_DEPTH;
        this.useLinkedHashMap = false;
    }

    /**
     * Configure the parser string processing and use the default CDATA Tag Name as "content".
     * @param keepStrings <code>true</code> to parse all values as string.
     *      <code>false</code> to try and convert XML string values into a JSON value.
     * @param maxNestingDepth <code>int</code> to limit the nesting depth
     * @param useLinkedHashMap <code>true</code> to use LinkedHashMap to preserve element order.
     */
    protected JSONMLParserConfiguration(final boolean keepStrings, final int maxNestingDepth, final boolean useLinkedHashMap) {
        super(keepStrings, maxNestingDepth);
        this.useLinkedHashMap = useLinkedHashMap;
    }

    /**
     * Provides a new instance of the same configuration.
     */
    @Override
    protected JSONMLParserConfiguration clone() {
        // future modifications to this method should always ensure a "deep"
        // clone in the case of collections. i.e. if a Map is added as a configuration
        // item, a new map instance should be created and if possible each value in the
        // map should be cloned as well. If the values of the map are known to also
        // be immutable, then a shallow clone of the map is acceptable.
        return new JSONMLParserConfiguration(
                this.keepStrings,
                this.maxNestingDepth,
                this.useLinkedHashMap
        );
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONMLParserConfiguration withKeepStrings(final boolean newVal) {
        return super.withKeepStrings(newVal);
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONMLParserConfiguration withMaxNestingDepth(int maxNestingDepth) {
        return super.withMaxNestingDepth(maxNestingDepth);
    }

    /**
     * When parsing the XML into JSONML, specifies if LinkedHashMap should be used to preserve element order.
     * By default, HashMap is used which does not preserve order.
     *
     * @return The <code>useLinkedHashMap</code> configuration value.
     */
    public boolean isUseLinkedHashMap() {
        return this.useLinkedHashMap;
    }

    /**
     * When parsing the XML into JSONML, specifies if LinkedHashMap should be used to preserve element order.
     * By default, HashMap is used which does not preserve order.
     *
     * @param useLinkedHashMap
     *      new value to use for the <code>useLinkedHashMap</code> configuration option.
     *
     * @return The existing configuration will not be modified. A new configuration is returned.
     */
    public JSONMLParserConfiguration withUseLinkedHashMap(final boolean useLinkedHashMap) {
        JSONMLParserConfiguration newConfig = this.clone();
        newConfig.useLinkedHashMap = useLinkedHashMap;
        return newConfig;
    }
}
