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
package uk.ac.ed.ph.jqtiplus.node.item.interaction;

import uk.ac.ed.ph.jqtiplus.attribute.value.IdentifierAttribute;
import uk.ac.ed.ph.jqtiplus.exception2.ResponseBindingException;
import uk.ac.ed.ph.jqtiplus.internal.util.ConstraintUtilities;
import uk.ac.ed.ph.jqtiplus.node.XmlNode;
import uk.ac.ed.ph.jqtiplus.node.content.BodyElement;
import uk.ac.ed.ph.jqtiplus.node.item.AssessmentItem;
import uk.ac.ed.ph.jqtiplus.node.item.response.declaration.ResponseDeclaration;
import uk.ac.ed.ph.jqtiplus.running.ItemSessionController;
import uk.ac.ed.ph.jqtiplus.types.FileResponseData;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.types.ResponseData;
import uk.ac.ed.ph.jqtiplus.types.ResponseData.ResponseDataType;
import uk.ac.ed.ph.jqtiplus.types.StringResponseData;
import uk.ac.ed.ph.jqtiplus.validation.ValidationContext;
import uk.ac.ed.ph.jqtiplus.validation.ValidationError;
import uk.ac.ed.ph.jqtiplus.value.BaseType;
import uk.ac.ed.ph.jqtiplus.value.Cardinality;
import uk.ac.ed.ph.jqtiplus.value.FileValue;
import uk.ac.ed.ph.jqtiplus.value.MultipleValue;
import uk.ac.ed.ph.jqtiplus.value.NullValue;
import uk.ac.ed.ph.jqtiplus.value.OrderedValue;
import uk.ac.ed.ph.jqtiplus.value.SingleValue;
import uk.ac.ed.ph.jqtiplus.value.Value;


/**
 * Interactions allow the candidate to interact with the item.
 * Through an interaction, the candidate selects or constructs A response.
 * The candidate's responses are stored in the response variables. Each
 * interaction is associated with (at least) one response variable.
 * 
 * @author Jonathon Hare
 */
public abstract class Interaction extends BodyElement {

    private static final long serialVersionUID = -1653661113115253620L;

    /** Display name of this class. */
    public static final String DISPLAY_NAME = "interaction";

    /** Name of responseIdentifier attribute in xml schema. */
    public static final String ATTR_RESPONSE_IDENTIFIER_NAME = "responseIdentifier";

    public Interaction(XmlNode parent, String localName) {
        super(parent, localName);

        getAttributes().add(new IdentifierAttribute(this, ATTR_RESPONSE_IDENTIFIER_NAME, true));
    }

    /**
     * Gets value of responseIdentifier attribute.
     * 
     * @return value of responseIdentifier attribute
     * @see #setResponseIdentifier
     */
    public Identifier getResponseIdentifier() {
        return getAttributes().getIdentifierAttribute(ATTR_RESPONSE_IDENTIFIER_NAME).getComputedValue();
    }

    /**
     * Sets new value of responseIdentifier attribute.
     * 
     * @param responseIdentifier new value of responseIdentifier attribute
     * @see #getResponseIdentifier
     */
    public void setResponseIdentifier(Identifier responseIdentifier) {
        getAttributes().getIdentifierAttribute(ATTR_RESPONSE_IDENTIFIER_NAME).setValue(responseIdentifier);
    }

    /**
     * Gets the responseDeclaration for this interaction.
     * 
     * @return the responseDeclaration for this interactions responseIdentifier
     */
    public ResponseDeclaration getResponseDeclaration() {
        return getRootObject(AssessmentItem.class).getResponseDeclaration(getResponseIdentifier());
    }

    @Override
    public void validate(ValidationContext context) {
        super.validate(context);

        if (getResponseIdentifier() != null) {
            final ResponseDeclaration declaration = getResponseDeclaration();
            if (declaration == null) {
                context.add(new ValidationError(this, "Response declaration for variable (" + getResponseIdentifier() + ") not found"));
            }
        }
    }

    /**
     * Initialize the interaction.
     * Subclasses should override this method as required.
     */
    public void initialize(ItemSessionController itemController) {
        /* Let subclasses override as required */
    }

    /**
     * Given the user response to the interaction in the form of a
     * List of Strings, set the appropriate response variables.
     * <p>
     * This default implementation calls up {@link #parseResponse(ResponseDeclaration, ResponseData)}
     * and sets the value of the appropriate response declaration. You'll need to override this
     * for things that might do more, such as string interactions that might bind two variables.
     * <p>
     * (This was called <tt>processResponse</tt> previously, which I found confusing.)
     * 
     * @param responseData Response to process, which must not be null
     * @throws ResponseBindingException if the response cannot be bound to the
     *             value encoded by the responseList
     */
    public void bindResponse(ItemSessionController itemController, ResponseData responseData)
            throws ResponseBindingException {
        ConstraintUtilities.ensureNotNull(responseData, "responseData");
        final ResponseDeclaration responseDeclaration = getResponseDeclaration();
        final Value value = parseResponse(responseDeclaration, responseData);
        itemController.getItemSessionState().setResponseValue(this, value);
    }

    /**
     * Parses the raw user response to the interaction to an appropriate {@link Value}.
     * <p>
     * This default implementation is sufficient in many cases,
     * but may need overridden for certain types of Interactions.
     * <p>
     * (This was called <tt>processResponse</tt> previously, which I found confusing.)
     * 
     * @param responseData Response to process, which must not be null
     * @param resposneDeclaration underlying response declaration
     * @see #bindResponse(ItemSessionController, ResponseData)
     * @throws ResponseBindingException if the response cannot be bound to the
     *             value encoded by the responseList
     */
    @SuppressWarnings("static-method")
    protected Value parseResponse(ResponseDeclaration responseDeclaration, ResponseData responseData)
            throws ResponseBindingException {
        Value value = null;
        BaseType responseBaseType = responseDeclaration.getBaseType();
        Cardinality responseCardinality = responseDeclaration.getCardinality();
        
        if (responseBaseType==BaseType.FILE) {
            if (responseData.getType()!=ResponseDataType.FILE) {
                throw new ResponseBindingException("Attempted to bind non-file response data to a file response");
            }
            FileResponseData fileResponseData = (FileResponseData) responseData;
            value = new FileValue(fileResponseData);
        }
        else {
            if (responseData.getType()!=ResponseDataType.STRING) {
                throw new ResponseBindingException("Attempted to bind non-string response data to response with baseType " + responseBaseType);
            }
            String[] stringResponseData = ((StringResponseData) responseData).getResponseData();
            if (responseCardinality == Cardinality.SINGLE) {
                if (stringResponseData.length == 0 || stringResponseData[0].trim().length() == 0) {
                    value = NullValue.INSTANCE;
                }
                else {
                    value = responseDeclaration.getBaseType().parseSingleValue(stringResponseData[0]);
                }
            }
            else if (!(responseCardinality == Cardinality.RECORD)) {
                final SingleValue[] values = new SingleValue[stringResponseData.length];

                for (int i = 0; i < stringResponseData.length; i++) {
                    values[i] = responseBaseType.parseSingleValue(stringResponseData[i]);
                }

                if (responseCardinality == Cardinality.MULTIPLE) {
                    value = new MultipleValue(values);
                }
                else {
                    value = new OrderedValue(values);
                }
            }
        }
        return value;
    }

    /**
     * Validate the response associated with this interaction.
     * This is called after {@link #bindResponse(ItemSessionController, ResponseData)}
     * 
     * @return true if the response is valid, false otherwise
     */
    public abstract boolean validateResponse(ItemSessionController itemController, Value responseValue);
}
