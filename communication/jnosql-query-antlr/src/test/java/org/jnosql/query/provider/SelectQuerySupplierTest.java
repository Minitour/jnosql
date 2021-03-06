/*
 *  Copyright (c) 2018 Otávio Santana and others
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *  The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *  and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *  You may elect to redistribute this code under either of these licenses.
 *  Contributors:
 *  Otavio Santana
 */
package org.jnosql.query.provider;

import org.jnosql.aphrodite.antlr.cache.CachedSelectQuerySupplier;
import org.jnosql.query.SelectQuerySupplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SelectQuerySupplierTest {

    @Test
    public void shouldGetSupplier() {
        SelectQuerySupplier supplier = SelectQuerySupplier.getSupplier();
        Assertions.assertNotNull(supplier);
        Assertions.assertTrue(supplier instanceof CachedSelectQuerySupplier);
    }
}
