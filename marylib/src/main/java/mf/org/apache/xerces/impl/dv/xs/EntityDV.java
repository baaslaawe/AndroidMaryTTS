/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;


/**
 * Represent the schema type "entity"
 *
 * @author Neeraj Bajaj, Sun Microsystems, inc.
 * @author Sandy Gao, IBM
 * @version $Id: EntityDV.java 446745 2006-09-15 21:43:58Z mrglavas $
 * @xerces.internal
 */
public class EntityDV extends TypeValidator {

    @Override
    public short getAllowedFacets() {
        return (XSSimpleTypeDefinition.FACET_LENGTH | XSSimpleTypeDefinition.FACET_MINLENGTH | XSSimpleTypeDefinition.FACET_MAXLENGTH | XSSimpleTypeDefinition.FACET_PATTERN | XSSimpleTypeDefinition.FACET_ENUMERATION | XSSimpleTypeDefinition.FACET_WHITESPACE);
    }

    @Override
    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        if (!XMLChar.isValidNCName(content)) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "NCName"});
        }

        return content;
    }

    @Override
    public void checkExtraRules(Object value, ValidationContext context) throws InvalidDatatypeValueException {
        if (!context.isEntityUnparsed((String) value)) {
            throw new InvalidDatatypeValueException("UndeclaredEntity", new Object[]{value});
        }
    }

} // class EntityDV