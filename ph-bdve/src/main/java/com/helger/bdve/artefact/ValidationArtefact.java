/**
 * Copyright (C) 2014-2019 Philip Helger (www.helger.com)
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
package com.helger.bdve.artefact;

import java.lang.ref.WeakReference;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

import com.helger.bdve.EValidationType;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.equals.EqualsHelper;
import com.helger.commons.hashcode.HashCodeGenerator;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.string.ToStringGenerator;

/**
 * Stand-alone implementation of {@link IValidationArtefact}
 *
 * @author Philip Helger
 */
@Immutable
public class ValidationArtefact implements IValidationArtefact
{
  private final EValidationType m_eValidationArtefactType;
  private final WeakReference <ClassLoader> m_aClassLoader;
  private final IReadableResource m_aResource;

  public ValidationArtefact (@Nonnull final EValidationType eValidationArtefactType,
                             @Nullable final ClassLoader aClassLoader,
                             @Nonnull final IReadableResource aResource)
  {
    m_eValidationArtefactType = ValueEnforcer.notNull (eValidationArtefactType, "ValidationArtefactType");
    m_aClassLoader = new WeakReference <> (aClassLoader);
    m_aResource = ValueEnforcer.notNull (aResource, "Resource");
  }

  @Nonnull
  public EValidationType getValidationArtefactType ()
  {
    return m_eValidationArtefactType;
  }

  @Nullable
  public ClassLoader getClassLoader ()
  {
    return m_aClassLoader.get ();
  }

  @Nonnull
  public IReadableResource getRuleResource ()
  {
    return m_aResource;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ValidationArtefact rhs = (ValidationArtefact) o;
    return m_eValidationArtefactType.equals (rhs.m_eValidationArtefactType) &&
           EqualsHelper.equals (getClassLoader (), rhs.getClassLoader ()) &&
           m_aResource.equals (rhs.m_aResource);
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_eValidationArtefactType)
                                       .append (getClassLoader ())
                                       .append (m_aResource)
                                       .getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("ValidationArtefactType", m_eValidationArtefactType)
                                       .append ("ClassLoader", getClassLoader ())
                                       .append ("Resource", m_aResource)
                                       .getToString ();
  }
}
