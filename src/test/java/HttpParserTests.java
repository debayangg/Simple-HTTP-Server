
import com.debayan.http.*;

import com.debayan.httpServer.HttpServer;
import com.debayan.httpServer.config.Configuration;
import com.debayan.httpServer.config.ConfigurationManager;
import com.debayan.httpServer.core.ServerSocketThread;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HttpParserTests{

    private HttpParser httpParser=null;

    @Before
    public void setUp() {
        httpParser = new HttpParser();
    }

    @Test
    public void parseRequest(){
        HttpRequest request = null;
        InputStream in = getGoodHttpRequest();
        try{
            request = httpParser.parseHttpRequest(in);
        }
        catch (Exception e)
        {
            if(httpParser==null)
                fail("httpParser is null");
            else
                fail(e);
        }


        assertNotNull(request);
        assertEquals(request.getMethod(), HttpMethod.GET);
        assertEquals(request.getRequestTarget(), "/");
        assertEquals(request.getOriginalHttpVersion(), "HTTP/1.1");
    }

    private InputStream getGoodHttpRequest(){
        String httprequest="GET / HTTP/1.1\r\n" +
                "Host: localhost:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Upgrade-Insecure-Requests: 1\r\n" +
                "User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36\r\n" +
                "Sec-Fetch-User: ?1\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3\r\n" +
                "Sec-Fetch-Site: none\r\n" +
                "Sec-Fetch-Mode: navigate\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Accept-Language: en-US,en;q=0.9,es;q=0.8,pt;q=0.7,de-DE;q=0.6,de;q=0.5,la;q=0.4\r\n" +
                "\r\n";

        InputStream in = new ByteArrayInputStream(httprequest.getBytes(StandardCharsets.US_ASCII));

        return in;
    }
}
