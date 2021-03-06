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
package org.jnosql.diana.api.column.query;

import org.jnosql.diana.api.column.ColumnEntity;
import org.jnosql.diana.api.column.ColumnFamilyManager;
import org.jnosql.diana.api.column.ColumnObserverParser;
import org.jnosql.diana.api.column.ColumnPreparedStatement;
import org.jnosql.diana.api.column.ColumnQueryParser;
import org.jnosql.diana.api.QueryException;

import java.util.List;
import java.util.Objects;

public class DefaultColumnQueryParser implements ColumnQueryParser {

    private final SelectQueryParser select = new SelectQueryParser();
    private final DeleteQueryParser delete = new DeleteQueryParser();
    private final InsertQueryParser insert = new InsertQueryParser();
    private final UpdateQueryParser update = new UpdateQueryParser();

    @Override
    public List<ColumnEntity> query(String query, ColumnFamilyManager manager, ColumnObserverParser observer) {
        validation(query, manager, observer);
        String command = query.substring(0, 6);
        switch (command) {
            case "select":
                return select.query(query, manager, observer);
            case "delete":
                return delete.query(query, manager, observer);
            case "insert":
                return insert.query(query, manager, observer);
            case "update":
                return update.query(query, manager, observer);
            default:
                throw new QueryException(String.format("The command was not recognized at the query %s ", query));
        }
    }

    @Override
    public ColumnPreparedStatement prepare(String query, ColumnFamilyManager manager, ColumnObserverParser observer) {
        validation(query, manager, observer);
        String command = query.substring(0, 6);

        switch (command) {
            case "select":
                return select.prepare(query, manager, observer);
            case "delete":
                return delete.prepare(query, manager, observer);
            case "insert":
                return insert.prepare(query, manager, observer);
            case "update":
                return update.prepare(query, manager, observer);
            default:
                throw new QueryException(String.format("The command was not recognized at the query %s ", query));
        }
    }


    private void validation(String query, ColumnFamilyManager manager, ColumnObserverParser observer) {
        Objects.requireNonNull(query, "query is required");
        Objects.requireNonNull(manager, "manager is required");
        Objects.requireNonNull(observer, "manager is observer");
        if (query.length() < 6) {
            throw new QueryException(String.format("The query %s is invalid", query));
        }
    }
}