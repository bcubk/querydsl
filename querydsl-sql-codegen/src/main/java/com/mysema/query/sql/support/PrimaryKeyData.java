/*
 * Copyright 2011, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mysema.query.sql.support;

import java.util.ArrayList;
import java.util.List;

import com.mysema.commons.lang.Assert;

/**
 * @author tiwe
 *
 */
public class PrimaryKeyData {

    private final String name;

    private final List<String> columns = new ArrayList<String>();

    public PrimaryKeyData(String name) {
        this.name = Assert.notNull(name,"name");
    }
    
    public PrimaryKeyData(String name, String[] c) {
        this.name = name;
        for (String column : c) {
            columns.add(column);
        }
    }

    public void add(String column) {
        columns.add(column);
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

}