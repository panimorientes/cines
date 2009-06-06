package javi.http.view.applicationobjects;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.udc.fbellas.j2ee.util.struts.view.LabelValue;

public class Countries {
/* 
 * In a more realistic application, these values could be read from a 
 * database in the "static" block.
 */
 
     private final static List COUNTRY_CODES = Arrays.asList(
        new String[] { "ES", "US" });

    private final static List COUNTRIES_en = Arrays.asList(new LabelValue[] {
        new LabelValue("Spain", "ES"),
        new LabelValue("USA", "US") });
        
    private final static List COUNTRIES_es = Arrays.asList(new LabelValue[] {
        new LabelValue("España", "ES"),
        new LabelValue("Estados Unidos", "US") });
        
    private final static List COUNTRIES_gl = Arrays.asList(new LabelValue[] {
        new LabelValue("España", "ES"),
        new LabelValue("Estados Unidos", "US") });
        
    private final static Map COUNTRIES;
        
    static {
    
        COUNTRIES = new HashMap();
        COUNTRIES.put("en", COUNTRIES_en);
        COUNTRIES.put("es", COUNTRIES_es);
        COUNTRIES.put("gl", COUNTRIES_gl);
    
    }    
    
    private Countries() {}

    public final static Collection getCountryCodes() {
        return COUNTRY_CODES;
    }

    public final static List getCountries(String languageCode) {
    
        List countries = (List) COUNTRIES.get(languageCode);
        
        if (countries != null) {
            return countries;
        } else {
            return (List) COUNTRIES.get("en");
        }
        
    }

}
