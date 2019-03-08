package hello.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BadModel implements Serializable {
    public ArrayList<ArrayList<LinkedHashMap<String,String>>> usages;
    public ArrayList<ArrayList<LinkedHashMap<String,String>>> statuses;
}
