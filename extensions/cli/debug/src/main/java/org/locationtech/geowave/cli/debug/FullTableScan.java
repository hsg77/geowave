/**
 * Copyright (c) 2013-2019 Contributors to the Eclipse Foundation
 *
 * <p> See the NOTICE file distributed with this work for additional information regarding copyright
 * ownership. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Apache License, Version 2.0 which accompanies this distribution and is
 * available at http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package org.locationtech.geowave.cli.debug;

import com.beust.jcommander.Parameters;
import org.locationtech.geowave.core.cli.annotations.GeowaveOperation;
import org.locationtech.geowave.core.geotime.store.GeotoolsFeatureDataAdapter;
import org.locationtech.geowave.core.store.CloseableIterator;
import org.locationtech.geowave.core.store.api.DataStore;
import org.locationtech.geowave.core.store.api.QueryBuilder;
import org.locationtech.geowave.core.store.cli.remote.options.DataStorePluginOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GeowaveOperation(name = "fullscan", parentOperation = DebugSection.class)
@Parameters(commandDescription = "fulltable scan")
public class FullTableScan extends AbstractGeoWaveQuery {
  private static Logger LOGGER = LoggerFactory.getLogger(FullTableScan.class);

  @Override
  protected long runQuery(
      final GeotoolsFeatureDataAdapter adapter,
      final String typeName,
      final String indexName,
      final DataStore dataStore,
      final boolean debug,
      DataStorePluginOptions pluginOptions) {
    long count = 0;
    try (final CloseableIterator<Object> it =
        dataStore.query(
            QueryBuilder.newBuilder().addTypeName(typeName).indexName(indexName).build())) {
      while (it.hasNext()) {
        if (debug) {
          System.out.println(it.next());
        } else {
          it.next();
        }
        count++;
      }
    }
    return count;
  }
}
