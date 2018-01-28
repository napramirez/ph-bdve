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
package com.helger.bdve.energieefactuur.mock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.bdve.energieefactuur.EnergieEFactuurValidation;
import com.helger.bdve.executorset.VESID;
import com.helger.bdve.executorset.ValidationExecutorSetRegistry;
import com.helger.bdve.mock.MockFile;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.CommonsHashSet;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;

@Immutable
public final class CTestFiles
{
  private static final ValidationExecutorSetRegistry VES_REGISTRY = new ValidationExecutorSetRegistry ();
  static
  {
    EnergieEFactuurValidation.initEnergieEFactuur (VES_REGISTRY);
  }

  private CTestFiles ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <MockFile> getAllTestFiles ()
  {
    final ICommonsList <MockFile> ret = new CommonsArrayList <> ();
    for (final VESID aVESID : new VESID [] { EnergieEFactuurValidation.VID_ENERGIE_EFACTUUR_2_0_0 })
    {
      for (final IReadableResource aRes : getAllMatchingTestFiles (aVESID))
        ret.add (MockFile.createGoodCase (aRes, aVESID));
      ret.addAll (getAllBadTestFiles (aVESID));
    }

    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <? extends IReadableResource> getAllMatchingTestFiles (@Nonnull final VESID aVESID)
  {
    ValueEnforcer.notNull (aVESID, "VESID");

    final ICommonsList <IReadableResource> ret = new CommonsArrayList <> ();
    if (aVESID.equals (EnergieEFactuurValidation.VID_ENERGIE_EFACTUUR_2_0_0))
    {
      ret.add (new ClassPathResource ("/test-files/good/20170713_SEeF - Voorbeeldfactuur 001 - levering.xml"));
      ret.add (new ClassPathResource ("/test-files/good/20170713_SEeF - Voorbeeldfactuur 002 - netbeheer.xml"));
      ret.add (new ClassPathResource ("/test-files/good/20170713_SEeF - Voorbeeldfactuur 003 - netbeheer.xml"));
      ret.add (new ClassPathResource ("/test-files/good/20170713_SEeF - Voorbeeldfactuur 004 - netbeheer credit.xml"));
      ret.add (new ClassPathResource ("/test-files/good/20170713_SEeF - Voorbeeldfactuur 005 - meetdiensten.xml"));
    }
    else
      throw new IllegalArgumentException ("Invalid VESID: " + aVESID);
    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <MockFile> getAllBadTestFiles (@Nonnull final VESID aVESID)
  {
    ValueEnforcer.notNull (aVESID, "VESID");

    final ICommonsList <MockFile> ret = new CommonsArrayList <> ();
    if (aVESID.equals (EnergieEFactuurValidation.VID_ENERGIE_EFACTUUR_2_0_0))
    {
      ret.add (new MockFile (new ClassPathResource ("/test-files/bad/bad-meternumber-twice.xml"),
                             aVESID,
                             new CommonsHashSet <> ("")));
      ret.add (new MockFile (new ClassPathResource ("/test-files/bad/bad-no-extension.xml"),
                             aVESID,
                             new CommonsHashSet <> ("")));
      ret.add (new MockFile (new ClassPathResource ("/test-files/bad/bad-two-extensions.xml"),
                             aVESID,
                             new CommonsHashSet <> ("")));
    }
    else
      throw new IllegalArgumentException ("Invalid VESID: " + aVESID);
    return ret;
  }
}