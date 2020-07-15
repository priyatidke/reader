package io.litmusblox.aiml.resumeparser.util;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import io.litmusblox.aiml.resumeparser.domain.FinalResultWrapper;
import io.litmusblox.aiml.resumeparser.domain.KeywordResult;

public class XMLWriter {

	public void writeToFile(String fileLocation, List<KeywordResult> result) throws JAXBException {
		File file = new File(fileLocation);
		JAXBContext jaxbContext = null;
        FinalResultWrapper finalResultWrapper= new FinalResultWrapper();
        finalResultWrapper.setResult(result);
		jaxbContext = JAXBContext.newInstance(finalResultWrapper.getClass());

		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(finalResultWrapper, file);// this line create customer.xml
												// file in specified path.

		StringWriter sw = new StringWriter();
		jaxbMarshaller.marshal(finalResultWrapper, sw);
		String xmlString = sw.toString();

		System.out.println(xmlString);
	}

}
