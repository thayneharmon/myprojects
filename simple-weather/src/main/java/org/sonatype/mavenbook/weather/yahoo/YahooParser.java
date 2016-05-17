package org.sonatype.mavenbook.weather.yahoo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class YahooParser {

  private static Logger log = Logger.getLogger(YahooParser.class);

  public Weather parse(InputStream inputStream) throws Exception {
    Weather weather = new Weather();

    log.info( "Creating XML Reader" );
    SAXReader xmlReader = createXmlReader();
    Document doc = xmlReader.read( inputStream );

    log.info( "Parsing XML Response" );
    weather.setCity(doc.valueOf("/rss/channel/y:location/@city") );
    weather.setRegion(doc.valueOf("/rss/channel/y:location/@region") );
    weather.setCountry(doc.valueOf("/rss/channel/y:location/@country") );
    weather.setCondition(doc.valueOf("/rss/channel/item/y:condition/@text") );
    weather.setTemp(doc.valueOf("/rss/channel/item/y:condition/@temp") );
    weather.setWindChill(doc.valueOf("/rss/channel/y:wind/@chill"));
    weather.setWindDir(doc.valueOf("/rss/channel/y:wind/@direction"));
    weather.setWindSpeed(doc.valueOf("/rss/channel/y:wind/@speed"));
    weather.setAirHumidity(doc.valueOf("/rss/channel/y:atmosphere/@humidity"));
    weather.setAirVisibility(doc.valueOf("/rss/channel/y:atmosphere/@visibility") );
    weather.setAirPressure(doc.valueOf("/rss/channel/y:atmosphere/@pressure"));
    weather.setAirRising(doc.valueOf("/rss/channel/y:atmosphere/@rising"));
    weather.setSunRise(doc.valueOf("/rss/channel/y:astronomy/@sunrise"));
    weather.setSunSet(doc.valueOf("/rss/channel/y:astronomy/@sunset"));

    List<Node> temp = doc.selectNodes("/rss/channel/item/y:forecast");
    String[] temp1 = new String[5];
    for (int i = 0; i < 5; i++) {
      Node j = temp.get(i);
      temp1[i] = String.format ("%s, %s,   %s,   %s,  %s",
        j.valueOf("@day"),
        j.valueOf("@date"),
        j.valueOf("@low"),
        j.valueOf("@high"),
        j.valueOf("@text"));
    }
    weather.setForcastDay(temp1);

    return weather;
  }

  private SAXReader createXmlReader() {
    Map<String,String> uris = new HashMap<String,String>();
    uris.put( "y", "http://xml.weather.yahoo.com/ns/rss/1.0" );

    DocumentFactory factory = new DocumentFactory();
    factory.setXPathNamespaceURIs( uris );

    SAXReader xmlReader = new SAXReader();
    xmlReader.setDocumentFactory( factory );
    return xmlReader;
  }
}