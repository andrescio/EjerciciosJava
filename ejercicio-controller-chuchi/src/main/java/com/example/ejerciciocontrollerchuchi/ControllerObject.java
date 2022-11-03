package com.example.ejerciciocontrollerchuchi;

import java.util.List;
import java.util.Map;

public class ControllerObject {
    List<String> paths;
    Map<String,String[]> queries;
    Map<String,String> headers;
    String urlOrigen;

    public ControllerObject(){

    }

    public ControllerObject(List<String> paths, Map<String,String[]> queries, Map<String,String> headers, String urlOrigen) {
        this.paths = paths;
        this.queries = queries;
        this.headers = headers;
        this.urlOrigen = urlOrigen;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }

    public Map<String,String[]> getQueries() {
        return queries;
    }

    public void setQueries(Map<String,String[]> queries) {
        this.queries = queries;
    }

    public Map<String,String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String,String> headers) {
        this.headers = headers;
    }

    public String getUrlOrigen() {
        return urlOrigen;
    }

    public void setUrlOrigen(String urlOrigen) {
        this.urlOrigen = urlOrigen;
    }
}
