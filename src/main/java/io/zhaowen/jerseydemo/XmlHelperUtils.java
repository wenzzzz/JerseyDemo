package io.zhaowen.jerseydemo;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by wen on 2/4/17.
 */
public class XmlHelperUtils {

    public static Document toDocument(String s) {
        try {
            return DocumentHelper.parseText(s);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> toMap(String s) {
        Map<String, String> map = new HashMap<String, String>();

        Document document = XmlHelperUtils.toDocument(s);
        Element root = document.getRootElement();

        // iterate through child elements of root
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            Element element = (Element) i.next();
            String name = element.getQName().getName();
            String value = element.getText();
            map.put(name, value);
        }
        return map;
    }

}
