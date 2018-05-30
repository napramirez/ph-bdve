/**
 * Copyright (C) 2014-2018 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.bdve.key;

import java.io.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.validation.Schema;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.MustImplementEqualsAndHashcode;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.ToStringGenerator;
import com.helger.jaxb.builder.IJAXBDocumentType;
import com.helger.xml.namespace.IIterableNamespaceContext;

/**
 * A validation key that uniquely identifies a set of validation artefacts. It
 * consists of the following elements:
 * <ul>
 * <li>A JAXB document type of type {@link IJAXBDocumentType}</li>
 * <li>An optional namespace context of type
 * {@link IIterableNamespaceContext}</li>
 * </ul>
 *
 * @author Philip Helger
 */
@Immutable
@MustImplementEqualsAndHashcode
public class ValidationArtefactKey implements Serializable
{
  private final IJAXBDocumentType m_aDocType;
  private final IIterableNamespaceContext m_aNamespaceContext;
  private final XSDPartialContext m_aXSDPartialContext;

  public ValidationArtefactKey (@Nonnull final IJAXBDocumentType aDocType,
                                @Nullable final IIterableNamespaceContext aNamespaceContext,
                                @Nullable final XSDPartialContext aXSDPartialContext)
  {
    ValueEnforcer.notNull (aDocType, "DocType");

    m_aDocType = aDocType;
    m_aNamespaceContext = aNamespaceContext;
    m_aXSDPartialContext = aXSDPartialContext;
  }

  /**
   * @return The class loader to be used. Never <code>null</code>.
   */
  @Nonnull
  public ClassLoader getClassLoader ()
  {
    return m_aDocType.getImplementationClass ().getClassLoader ();
  }

  /**
   * @return All XSD resources to be used, in the correct order.
   */
  @Nonnull
  public ICommonsList <? extends IReadableResource> getAllXSDResources ()
  {
    return m_aDocType.getAllXSDResources (getClassLoader ());
  }

  /**
   * @param aClassLoader
   *        The class loader to be used. May be <code>null</code> indicating
   *        that the default class loader should be used.
   * @return The compiled {@link Schema} object retrieved by the
   *         {@link com.helger.xml.schema.XMLSchemaCache}. May be
   *         <code>null</code> if no XSD was provided.
   */
  @Nullable
  public Schema getSchema (@Nullable final ClassLoader aClassLoader)
  {
    return m_aDocType.getSchema (aClassLoader);
  }

  /**
   * @return An optional namespace prefix-to-url mapping to be used. Maybe
   *         <code>null</code>.
   */
  @Nullable
  public IIterableNamespaceContext getNamespaceContext ()
  {
    return m_aNamespaceContext;
  }

  @Nullable
  public XSDPartialContext getXSDPartialContext ()
  {
    return m_aXSDPartialContext;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ValidationArtefactKey rhs = (ValidationArtefactKey) o;
    return m_aDocType.equals (rhs.m_aDocType) && EqualsHelper.equals (m_aNamespaceContext, rhs.m_aNamespaceContext);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aDocType).append (m_aNamespaceContext).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("DocType", m_aDocType)
                                       .appendIfNotNull ("NamespaceContext", m_aNamespaceContext)
                                       .appendIfNotNull ("XSDPartialContext", m_aXSDPartialContext)
                                       .getToString ();
  }

  /**
   * Builder class for {@link ValidationArtefactKey} objects.
   * {@link #setDocType(IJAXBDocumentType)} must be filled, as this field is
   * mandatory.
   *
   * @author Philip Helger
   */
  @NotThreadSafe
  public static class Builder
  {
    private IJAXBDocumentType m_aDocType;
    private IIterableNamespaceContext m_aNamespaceContext;
    private XSDPartialContext m_aXSDPartialContext;

    /**
     * Create an empty Builder.
     */
    public Builder ()
    {}

    /**
     * Create a builder with the values of the provided validation artefact key.
     *
     * @param aOther
     *        Object to copy from. May not be <code>null</code>.
     */
    public Builder (@Nonnull final ValidationArtefactKey aOther)
    {
      ValueEnforcer.notNull (aOther, "Other");
      m_aDocType = aOther.m_aDocType;
      m_aNamespaceContext = aOther.m_aNamespaceContext;
      m_aXSDPartialContext = aOther.m_aXSDPartialContext;
    }

    /**
     * @param aDocType
     *        The JAXB document type to be used. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder setDocType (@Nullable final IJAXBDocumentType aDocType)
    {
      m_aDocType = aDocType;
      return this;
    }

    /**
     * @param aNamespaceContext
     *        The namespace context to be used. May be <code>null</code>.
     * @return this for chaining
     */
    @Nonnull
    public Builder setNamespaceContext (@Nullable final IIterableNamespaceContext aNamespaceContext)
    {
      m_aNamespaceContext = aNamespaceContext;
      return this;
    }

    /**
     * Set the optional XSD partial context to be used. Muse be present for
     * validation type "XSD partial".
     *
     * @param aXSDPartialContext
     *        Partial XSD context.
     * @return this for chaining
     */
    @Nonnull
    public Builder setXSDPartialContext (@Nullable final XSDPartialContext aXSDPartialContext)
    {
      m_aXSDPartialContext = aXSDPartialContext;
      return this;
    }

    /**
     * Build the validation artefact key from the specified fields. At least
     * business specification and transaction MUST not be <code>null</code>!
     * This method can be called many times and will return a new object every
     * time.
     *
     * @return The final validation key. Never <code>null</code>.
     * @throws IllegalStateException
     *         If a mandatory field is not filled.
     */
    @Nonnull
    public ValidationArtefactKey build ()
    {
      if (m_aDocType == null)
        throw new IllegalStateException ("The DocType must be provided");

      return new ValidationArtefactKey (m_aDocType, m_aNamespaceContext, m_aXSDPartialContext);
    }
  }
}
