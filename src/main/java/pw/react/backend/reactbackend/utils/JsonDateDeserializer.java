package pw.react.backend.reactbackend.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JsonDateDeserializer extends JsonDeserializer<LocalDate> {
    
    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException{
        ObjectCodec oc = jp.getCodec();
        TextNode node = oc.readTree(jp);
        String dateString = node.textValue();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        return LocalDate.parse(dateString, formatter);
    }
}
