package offers_analyzer.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public abstract class CsvAccessConfig<T> {

    private final CsvMapper mapper;

    private final CsvSchema schema;

    public CsvAccessConfig() {
        this.mapper = new CsvMapper();
        configureMapper();
        this.schema = configureSchema();
    }

    protected abstract CsvSchema configureSchema();

    private void configureMapper() {
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public ObjectWriter getWriter() {
        return mapper.writerFor(getClassType())
                .with(schema);
    }

    public ObjectReader getReader() {
        return mapper.readerFor(getClassType())
                .with(schema);
    }

    protected abstract Class<T> getClassType();
}
