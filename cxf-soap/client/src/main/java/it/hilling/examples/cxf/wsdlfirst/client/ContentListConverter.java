package it.hilling.examples.cxf.wsdlfirst.client;

import it.hilling.customerservice.Customer;
import org.apache.camel.Converter;
import org.apache.camel.TypeConversionException;
import org.apache.camel.TypeConverter;
import org.apache.camel.TypeConverters;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.cglib.core.internal.CustomizerRegistry;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContentListConverter implements TypeConverters {
    @Converter
    public String convertToString(MessageContentsList list) throws TypeConversionException {
        List<Customer> customersList = (List<Customer>) list.get(0);
        return customersList.stream()
                    .map(Customer::getName)
                    .collect(Collectors.joining("\n"));
    }
}
