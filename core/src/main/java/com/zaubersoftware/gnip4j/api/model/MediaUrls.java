/**
 * Copyright (c) 2011-2012 Zauber S.A. <http://www.zaubersoftware.com/>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zaubersoftware.gnip4j.api.model;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 */
public class MediaUrls extends Urls {
    private static final long serialVersionUID = 1L;
    
    @JsonProperty(value = "id_str")
    private String id;
    @JsonProperty(value = "media_url")
    private String mediaUrl;
    @JsonProperty("media_url_https")
    private String mediaUrlHttps;
    private String type;    
    private Sizes sizes;

    public String getId() {
        return id;
    }
    
    public String getMediaURL() {
        return mediaUrl;
    }
    
    public void setMediaURL(final String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaURLHttps() {
        return mediaUrlHttps;
    }
    
    public void setMediaURLHttps(final String mediaUrlHttps) {
        this.mediaUrlHttps = mediaUrlHttps;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Sizes getSizes() {
        return sizes;        
    }

    public void setSizes(Sizes sizes) {
        this.sizes = sizes;
    }

    

}
