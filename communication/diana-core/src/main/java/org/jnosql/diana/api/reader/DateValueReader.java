/*
 *
 *  Copyright (c) 2017 Otávio Santana and others
 *   All rights reserved. This program and the accompanying materials
 *   are made available under the terms of the Eclipse Public License v1.0
 *   and Apache License v2.0 which accompanies this distribution.
 *   The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 *   and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 *   You may elect to redistribute this code under either of these licenses.
 *
 *   Contributors:
 *
 *   Otavio Santana
 *
 */

package org.jnosql.diana.api.reader;


import org.jnosql.diana.api.ValueReader;

import java.util.Date;

/**
 * Class to reads and converts Date type
 */
@SuppressWarnings("unchecked")
public final class DateValueReader implements ValueReader {

    @Override
    public <T> boolean isCompatible(Class<T> clazz) {
        return Date.class.equals(clazz);
    }

    @Override
    public <T> T read(Class<T> clazz, Object value) {

        if (Date.class.isInstance(value)) {
            return (T) value;
        }

        if (Number.class.isInstance(value)) {
            return (T) new Date(((Number) value).longValue());
        }

        return (T) new Date(value.toString());
    }
}
