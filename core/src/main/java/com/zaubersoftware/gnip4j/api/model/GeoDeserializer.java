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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.StdDeserializer;

/**
 * TODO Descripcion de la clase. Los comentarios van en castellano.
 *
 *
 * @author Martin Silva
 * @since Feb 15, 2012
 */
public class GeoDeserializer extends StdDeserializer<Geo> {

    /** Creates the GeoDeserializer. */
    public GeoDeserializer(final Class<Geo> clazz) {
        super(clazz);
    }

    @Override
    public Geo deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException,
            JsonProcessingException {
        final JsonNode tree = jp.readValueAsTree();

        final JsonNode coordinates = tree.findValue("coordinates");
        final JsonNode type = tree.findValue("type");


        final Geo geo = new Geo();
        geo.setType(type.getTextValue());

        if(Geometries.valueOf(type.getTextValue()) == Geometries.Polygon) {
            geo.setCoordinates(this.createPolygon(coordinates));
        } else {
            geo.setCoordinates(this.createPoint(coordinates));
        }

        return geo;
    }

    /** @return a point */
    private Point createPoint(final JsonNode coordinates) throws IOException {
        final Point ret;
        if(coordinates.isArray()) {
            ret = new Point(coordinates.get(0).getDoubleValue(), coordinates.get(1).getDoubleValue());
        } else {
            ret = new Point(coordinates.get("latitude").getDoubleValue(), 
                            coordinates.get("longitude").getDoubleValue());
        }
        return ret;
    }

    /** @return a polygon */
    private Polygon createPolygon(final JsonNode coordinates) throws IOException {
        final List<Point> points = new ArrayList<Point>();
        final Iterator<JsonNode> elements;
        if(coordinates.has("points")) {
            elements = coordinates.get("points").iterator();
        } else {
            final JsonNode values = coordinates.get(0);
            elements = values.getElements();
        }
        while(elements.hasNext()) {
            final JsonNode next = elements.next();
            points.add(createPoint(next));
        }
        return new Polygon(points);
    }

}
