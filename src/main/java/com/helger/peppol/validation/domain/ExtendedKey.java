/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
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
package com.helger.peppol.validation.domain;

import java.util.Locale;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotations.MustImplementEqualsAndHashcode;
import com.helger.commons.annotations.Nonempty;
import com.helger.commons.hash.HashCodeGenerator;
import com.helger.commons.locale.country.CountryCache;
import com.helger.commons.string.ToStringGenerator;

/**
 * An extension to a transaction key that contains a mandatory country code and
 * an indicator whether it is a sector specific artefact or not.
 *
 * @author Philip Helger
 */
@Immutable
@MustImplementEqualsAndHashcode
public class ExtendedKey
{
  // Predefined keys
  public static final ExtendedKey AT = new ExtendedKey ("AT", false);
  public static final ExtendedKey AT_SECTOR = new ExtendedKey ("AT", true);

  private final Locale m_aCountry;
  private final boolean m_bIsSectorSpecific;

  public ExtendedKey (@Nonnull @Nonempty final String sCountryCode, final boolean bIsSectorSpecific)
  {
    ValueEnforcer.notEmpty (sCountryCode, "CountryCode");
    m_aCountry = CountryCache.getInstance ().getCountry (sCountryCode);
    if (m_aCountry == null)
      throw new IllegalArgumentException ("The passed country '" + sCountryCode + "' does not exist!");
    m_bIsSectorSpecific = bIsSectorSpecific;
  }

  /**
   * @return The country locale as specified in the constructor.
   */
  @Nonnull
  public Locale getCountryLocale ()
  {
    return m_aCountry;
  }

  /**
   * @return The country code extracted from the contained locale. Never
   *         <code>null</code>.
   */
  @Nonnull
  @Nonempty
  public String getCountryCode ()
  {
    return m_aCountry.getCountry ();
  }

  /**
   * @return <code>true</code> if sector specific is enabled, <code>false</code>
   *         if not.
   */
  public boolean isSectorSpecific ()
  {
    return m_bIsSectorSpecific;
  }

  @Override
  public boolean equals (final Object o)
  {
    if (o == this)
      return true;
    if (o == null || !getClass ().equals (o.getClass ()))
      return false;
    final ExtendedKey rhs = (ExtendedKey) o;
    return m_aCountry.equals (rhs.m_aCountry) && m_bIsSectorSpecific == rhs.m_bIsSectorSpecific;
  }

  @Override
  public int hashCode ()
  {
    return new HashCodeGenerator (this).append (m_aCountry).append (m_bIsSectorSpecific).getHashCode ();
  }

  @Override
  public String toString ()
  {
    return new ToStringGenerator (this).append ("Country", m_aCountry)
                                       .append ("IsSectorSpecific", m_bIsSectorSpecific)
                                       .toString ();
  }
}
