/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.jqtiplus.node.content.xhtml.text;

import uk.ac.ed.ph.jqtiplus.attribute.value.UriAttribute;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;
import uk.ac.ed.ph.jqtiplus.node.content.basic.AbstractSimpleBlock;
import uk.ac.ed.ph.jqtiplus.node.content.basic.SimpleBlock;

import java.net.URI;

/**
 * blockquote
 * 
 * @author Jonathon Hare
 */
public class Blockquote extends AbstractSimpleBlock implements SimpleBlock {

    private static final long serialVersionUID = -5825718968041113996L;

    /** Name of this class in xml schema. */
    public static final String QTI_CLASS_NAME = "blockquote";

    /** Name of label attribute in xml schema. */
    public static final String ATTR_CITE_NAME = "cite";

    public Blockquote(XmlNode parent) {
        super(parent, QTI_CLASS_NAME);

        getAttributes().add(new UriAttribute(this, ATTR_CITE_NAME, false));
    }

    /**
     * Gets value of cite attribute.
     * 
     * @return value of cite attribute
     * @see #setCite
     */
    public URI getCite() {
        return getAttributes().getUriAttribute(ATTR_CITE_NAME).getComputedValue();
    }

    /**
     * Sets new value of cite attribute.
     * 
     * @param cite new value of cite attribute
     * @see #getCite
     */
    public void setCite(URI cite) {
        getAttributes().getUriAttribute(ATTR_CITE_NAME).setValue(cite);
    }
}
