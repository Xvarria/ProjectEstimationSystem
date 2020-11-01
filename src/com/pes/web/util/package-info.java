/**
 * Treat all the dates in DB as UTC
 * This will apply the TypeDef for all entities having Date attribute
 */
@TypeDefs(
        {
                @TypeDef(name = "UtcDateType", defaultForType = Date.class, typeClass = TimestampUTC.class),
                @TypeDef(name = "UtcDateType", defaultForType = Timestamp.class, typeClass = TimestampUTC.class)
        }
)
package com.pes.web.util;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;