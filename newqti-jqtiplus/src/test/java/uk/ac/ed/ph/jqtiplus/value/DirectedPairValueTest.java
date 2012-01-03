/*
<LICENCE>

Copyright (c) 2008, University of Southampton
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.

  *    Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  *    Neither the name of the University of Southampton nor the names of its
    contributors may be used to endorse or promote products derived from this
    software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

</LICENCE>
*/

package uk.ac.ed.ph.jqtiplus.value;

import uk.ac.ed.ph.jqtiplus.value.BooleanValue;
import uk.ac.ed.ph.jqtiplus.value.DirectedPairValue;
import uk.ac.ed.ph.jqtiplus.value.DurationValue;
import uk.ac.ed.ph.jqtiplus.value.FileValue;
import uk.ac.ed.ph.jqtiplus.value.FloatValue;
import uk.ac.ed.ph.jqtiplus.value.IdentifierValue;
import uk.ac.ed.ph.jqtiplus.value.IntegerValue;
import uk.ac.ed.ph.jqtiplus.value.MultipleValue;
import uk.ac.ed.ph.jqtiplus.value.NullValue;
import uk.ac.ed.ph.jqtiplus.value.OrderedValue;
import uk.ac.ed.ph.jqtiplus.value.PairValue;
import uk.ac.ed.ph.jqtiplus.value.PointValue;
import uk.ac.ed.ph.jqtiplus.value.RecordValue;
import uk.ac.ed.ph.jqtiplus.value.StringValue;
import uk.ac.ed.ph.jqtiplus.value.UriValue;
import uk.ac.ed.ph.jqtiplus.value.Value;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests <code>DirectedPairValue</code> implementation of <code>equals</code> and <code>hashCode</code> methods.
 *
 * @see uk.ac.ed.ph.jqtiplus.value.DirectedPairValue
 */
@RunWith(Parameterized.class)
public class DirectedPairValueTest extends ValueTest
{
    /**
     * Creates test data for this test.
     *
     * @return test data for this test
     */
    @Parameters
    public static Collection<Object[]> data()
    {
        return Arrays.asList(new Object[][]
        {
            // null
            {false, new DirectedPairValue("ident1", "ident2"), null},
            // NullValue
            {false, new DirectedPairValue("ident1", "ident2"), NullValue.INSTANCE},
            // IdentifierValue
            {false, new DirectedPairValue("ident1", "ident2"), new IdentifierValue("identifier")},
            // BooleanValue
            {false, new DirectedPairValue("ident1", "ident2"), BooleanValue.TRUE},
            {false, new DirectedPairValue("ident1", "ident2"), BooleanValue.FALSE},
            // IntegerValue
            {false, new DirectedPairValue("ident1", "ident2"), new IntegerValue(1)},
            // FloatValue
            {false, new DirectedPairValue("ident1", "ident2"), new FloatValue(1)},
            // StringValue
            {false, new DirectedPairValue("ident1", "ident2"), new StringValue("string")},
            // PointValue
            {false, new DirectedPairValue("ident1", "ident2"), new PointValue(1, 1)},
            // PairValue
            {false, new DirectedPairValue("ident1", "ident2"), new PairValue("ident1", "ident2")},
            // DirectedPairValue
            {true, new DirectedPairValue("ident1", "ident2"), new DirectedPairValue("ident1", "ident2")},
            {false, new DirectedPairValue("ident1", "ident2"), new DirectedPairValue("ident2", "ident1")},
            {false, new DirectedPairValue("ident1", "ident2"), new DirectedPairValue("ident3", "ident2")},
            {false, new DirectedPairValue("ident1", "ident2"), new DirectedPairValue("ident1", "ident4")},
            {false, new DirectedPairValue("ident1", "ident2"), new DirectedPairValue("ident3", "ident4")},
            // DurationValue
            {false, new DirectedPairValue("ident1", "ident2"), new DurationValue(1)},
            // FileValue
            {false, new DirectedPairValue("ident1", "ident2"), new FileValue("file")},
            // UriValue
            {false, new DirectedPairValue("ident1", "ident2"), new UriValue("uri")},
            // MultipleValue
            {false, new DirectedPairValue("ident1", "ident2"), new MultipleValue()},
            {false, new DirectedPairValue("ident1", "ident2"), new MultipleValue(new DirectedPairValue("ident1", "ident2"))},
            // OrderedValue
            {false, new DirectedPairValue("ident1", "ident2"), new OrderedValue()},
            {false, new DirectedPairValue("ident1", "ident2"), new OrderedValue(new DirectedPairValue("ident1", "ident2"))},
            // RecordValue
            {false, new DirectedPairValue("ident1", "ident2"), new RecordValue()},
            {false, new DirectedPairValue("ident1", "ident2"), new RecordValue("identifier", new DirectedPairValue("ident1", "ident2"))},
        });
    }

    /**
     * Constructs this test.
     *
     * @param equals true if given values are equal; false otherwise
     * @param value1 first value
     * @param value2 second value
     */
    public DirectedPairValueTest(boolean equals, Value value1, Value value2)
    {
        super(equals, value1, value2);
    }
}
