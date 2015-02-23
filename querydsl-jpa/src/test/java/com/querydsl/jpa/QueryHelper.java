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
package com.querydsl.jpa;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.annotation.Nullable;

import org.hibernate.hql.internal.ast.HqlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysema.commons.lang.CloseableIterator;
import com.querydsl.core.DefaultQueryMetadata;
import com.querydsl.core.QueryMetadata;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;

class QueryHelper extends JPAQueryBase<QueryHelper> {

    private static final Logger logger = LoggerFactory.getLogger(QueryHelper.class);

    public QueryHelper(JPQLTemplates templates) {
        this(new DefaultQueryMetadata(), templates);
    }
    
    public QueryHelper(QueryMetadata metadata, JPQLTemplates templates) {
        super(metadata, templates);
    }

    @Override
    protected JPQLSerializer createSerializer() {
        return new JPQLSerializer(getTemplates());
    }

    public long count() {
        return 0;
    }

    @Override
    public CloseableIterator<Tuple> iterate(Expression<?>... args) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    public <RT> CloseableIterator<RT> iterate(Expression<RT> projection) {
        throw new UnsupportedOperationException();
    }

    @Nullable
    public QueryResults<Tuple> listResults(Expression<?>... args) {
        throw new UnsupportedOperationException();
    }
    
    @Nullable
    public <RT> QueryResults<RT> listResults(Expression<RT> expr) {
        throw new UnsupportedOperationException();
    }

    public void parse() throws RecognitionException, TokenStreamException {
        try {
            String input = toString();
            logger.debug("input: " + input.replace('\n', ' '));
            HqlParser parser = HqlParser.getInstance(input);
            parser.setFilter(false);
            parser.statement();
            AST ast = parser.getAST();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            parser.showAst(ast, new PrintStream(baos));
            assertEquals("At least one error occurred during parsing " + input,
                    0, parser.getParseErrorHandler().getErrorCount());
        } finally {
            // clear();
        }
    }

    public QueryHelper select(Expression<?>... exprs) {
        queryMixin.setProjection(exprs);
        return this;
    }

    @Override
    public <RT> RT uniqueResult(Expression<RT> projection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public QueryHelper clone() {
        return new QueryHelper(getMetadata().clone(), getTemplates());
    }
    
}
