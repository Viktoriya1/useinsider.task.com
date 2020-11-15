package com.base.useinsider.utils;

import java.util.HashMap;
import java.util.Map;

public class Url {
    private String serverName;
    private String http;
    private String resource;
    private String isQa;
    private HashMap<String, String> param;

    private String url = "";

    private String params() {
        String params = "";

        for (Map.Entry<String, String> entry : param.entrySet()) {
            params = params.concat("&" + entry.getKey() + "=" + entry.getValue());
        }
        params = params + "&" + isQa;

        return "" + params.substring(1);
    }

    private Url(UrlBuilder urlBuilder) {
        this.serverName = urlBuilder.serverName;
        this.http = urlBuilder.http;
        this.resource = urlBuilder.resource;
        this.isQa = urlBuilder.isQa;
        this.param = urlBuilder.param;
    }

    public String getUrl() {
        this.url = http + "://" + serverName + "/" + resource + params();
        return this.url;
    }

    public static class UrlBuilder {
        private String http = "http";
        private String serverName;
        private String resource = "";
        private String isQa = "";
        private HashMap<String, String> param = new HashMap<>();

        public UrlBuilder(String serverName) {
            this.serverName = serverName;
        }

        public UrlBuilder withHttps(boolean isHttps) {
            http = isHttps ? "https" : "http";
            return this;
        }

        public UrlBuilder withResource(String resource) {
            this.resource = resource;
            return this;
        }

        public UrlBuilder withParam(String key, String value) {
            this.param.put(key, value);
            return this;
        }

        public UrlBuilder withIsQa(boolean isQa) {
            String partQa = "isQa=";
            this.isQa = partQa + isQa;
            return this;
        }

        public Url build() {
            return new Url(this);
        }
    }
}
