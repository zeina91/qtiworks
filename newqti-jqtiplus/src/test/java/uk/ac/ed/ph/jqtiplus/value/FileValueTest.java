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
 * Tests <code>FileValue</code> implementation of <code>equals</code> and <code>hashCode</code> methods.
 *
 * @see uk.ac.ed.ph.jqtiplus.value.FileValue
 */
@RunWith(Parameterized.class)
public class FileValueTest extends ValueTest
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
            {false, new FileValue("file"), null},
            // NullValue
            {false, new FileValue("file"), NullValue.INSTANCE},
            // IdentifierValue
            {false, new FileValue("file"), new IdentifierValue("identifier")},
            // BooleanValue
            {false, new FileValue("file"), BooleanValue.TRUE},
            {false, new FileValue("file"), BooleanValue.FALSE},
            // IntegerValue
            {false, new FileValue("file"), new IntegerValue(1)},
            // FloatValue
            {false, new FileValue("file"), new FloatValue(1)},
            // StringValue
            {false, new FileValue("file"), new StringValue("string")},
            // PointValue
            {false, new FileValue("file"), new PointValue(1, 1)},
            // PairValue
            {false, new FileValue("file"), new PairValue("ident1", "ident2")},
            // DirectedPairValue
            {false, new FileValue("file"), new DirectedPairValue("ident1", "ident2")},
            // DurationValue
            {false, new FileValue("file"), new DurationValue(1)},
            // FileValue
            {true, new FileValue("file"), new FileValue("file")},
            {false, new FileValue("file"), new FileValue("File")},
            {false, new FileValue("file"), new FileValue("FILE")},
            // UriValue
            {false, new FileValue("file"), new UriValue("uri")},
            // MultipleValue
            {false, new FileValue("file"), new MultipleValue()},
            {false, new FileValue("file"), new MultipleValue(new FileValue("file"))},
            // OrderedValue
            {false, new FileValue("file"), new OrderedValue()},
            {false, new FileValue("file"), new OrderedValue(new FileValue("file"))},
            // RecordValue
            {false, new FileValue("file"), new RecordValue()},
            {false, new FileValue("file"), new RecordValue("identifier", new FileValue("file"))},
        });
    }

    /**
     * Constructs this test.
     *
     * @param equals true if given values are equal; false otherwise
     * @param value1 first value
     * @param value2 second value
     */
    public FileValueTest(boolean equals, Value value1, Value value2)
    {
        super(equals, value1, value2);
    }
}
