/**
 * Copyright (c) 2013-2019 Contributors to the Eclipse Foundation
 *
 * <p> See the NOTICE file distributed with this work for additional information regarding copyright
 * ownership. All rights reserved. This program and the accompanying materials are made available
 * under the terms of the Apache License, Version 2.0 which accompanies this distribution and is
 * available at http://www.apache.org/licenses/LICENSE-2.0.txt
 */
package org.locationtech.geowave.core.store.spi;

import org.locationtech.geowave.core.store.api.Index;
import org.locationtech.geowave.core.store.index.CommonIndexValue;

/**
 * This interface can be injected using SPI to determine which supported index for an ingest type
 * will be used.
 */
public interface DimensionalityTypeProviderSpi<T extends DimensionalityTypeOptions> {
  /**
   * return a set of classes that can be indexed by this index provider, used for compatibility
   * checking with an adapter provider
   *
   * @return the classes that are indexable by this index provider
   */
  public Class<? extends CommonIndexValue>[] getRequiredIndexTypes();

  /**
   * This will represent the name for the dimensionality type that is registered with the ingest
   * framework and presented as a dimensionality type option via the commandline. For consistency,
   * this name is preferably lower-case and without spaces, and should uniquely identify the
   * dimensionality type as much as possible.
   *
   * @return the name of this dimensionality type
   */
  public String getDimensionalityTypeName();

  /**
   * if the registered dimensionality types are listed by a user, this can provide a user-friendly
   * description for each
   *
   * @return the user-friendly description
   */
  public String getDimensionalityTypeDescription();

  /**
   * This will return the primary index that match the options
   *
   * @return the primary index
   */
  public Index createIndex(T options);

  /**
   * These are options specific to the type of index being exposed by this SPI plugin.
   *
   * @return
   */
  public T createOptions();
}
