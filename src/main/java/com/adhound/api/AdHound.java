package com.adhound.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Main endpoint for the AdHound API
 * @author kkelm
 *
 */

@ApplicationPath("/api")
public class AdHound extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(Locations.class );
        return h;
    }

}
