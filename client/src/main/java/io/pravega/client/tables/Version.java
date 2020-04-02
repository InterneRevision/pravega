/**
 * Copyright (c) Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.client.tables;

import io.pravega.client.tables.impl.TableSegmentKeyVersion;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * Version of a Key in a Table.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Version implements Serializable {
    /**
     * {@link Version} that indicates no specific version is desired. Using this will result in an unconditional
     * update or removal being performed. See {@link KeyValueTable} for details on conditional/unconditional updates.
     */
    public static final Version NO_VERSION = new Version(null, TableSegmentKeyVersion.NO_VERSION);
    /**
     * {@link Version} that indicates the {@link TableKey} must not exist. Using this will result in an conditional
     * update or removal being performed, conditioned on the {@link TableKey} not existing at the time of the operation.
     * See {@link KeyValueTable} for details on conditional/unconditional updates.
     */
    public static final Version NOT_EXISTS = new Version(null, TableSegmentKeyVersion.NOT_EXISTS);

    /**
     * The Segment where this Key resides. May be null if this is a {@link #NOT_EXISTS} or {@link #NO_VERSION}
     * {@link Version}.
     */
    private final String segmentName;
    /**
     * The internal version inside the Table Segment for this Key.
     */
    private final TableSegmentKeyVersion segmentVersion;

    /**
     * Creates a new instance of the {@link Version} class.
     *
     * @param segmentName    The name of the Table Segment that contains the {@link TableKey}.
     * @param segmentVersion The version within the Table Segment for the {@link TableKey}.
     */
    Version(String segmentName, long segmentVersion) {
        this.segmentName = segmentName;
        this.segmentVersion = TableSegmentKeyVersion.from(segmentVersion);
    }

    /**
     * The internal version inside the Table Segment for this Key.
     *
     * @return The Segment Version
     */
    long getSegmentVersion() {
        return this.segmentVersion.getSegmentVersion();
    }
}