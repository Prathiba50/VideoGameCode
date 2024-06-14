package Utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Properties;

public class HelperBase {

   public RequestSpecification req;
   public RequestSpecification reqSpec(){
        try{
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getBaseURI("baseUrl"))
                    .setContentType("application/json")
                    .addHeader("accept","application/json")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .build();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return req;
   }


    public static Properties prop;
    public static String getBaseURI(String baseUrlKey){
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream("C:\\VedioGame\\src\\test\\local.properties");
            prop.load(fis);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return prop.getProperty(baseUrlKey);
    }

    public static String getJsonPath(Response resp, String key){

        String res = resp.asString();
        JsonPath js = new JsonPath(res);
        return js.get(key).toString();
    }


}
