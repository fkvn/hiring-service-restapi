package hiringProcess.util;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

@JsonComponent
public class StringDeserializer
		extends com.fasterxml.jackson.databind.deser.std.StringDeserializer {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
   public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
       String value = super.deserialize(p, ctxt);
       return value != null ? value.trim() : null;
   }
}
