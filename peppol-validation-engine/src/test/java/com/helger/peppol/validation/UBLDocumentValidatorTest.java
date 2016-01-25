/**
 * Copyright (C) 2014-2016 Philip Helger (www.helger.com)
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
package com.helger.peppol.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.helger.peppol.validation.api.result.ValidationLayerResult;
import com.helger.peppol.validation.api.result.ValidationLayerResultList;
import com.helger.peppol.validation.mock.CTestFiles;
import com.helger.peppol.validation.mock.MockFile;
import com.helger.peppol.validation.peppol.PeppolValidationConfiguration;
import com.helger.peppol.validation.peppol.ValidationBootstraper;

/**
 * Test class for class {@link UBLDocumentValidator}.
 *
 * @author Philip Helger
 */
public final class UBLDocumentValidatorTest
{
  static
  {
    ValidationBootstraper.run ();
  }

  @Test
  public void testApplyXSDValidationPeppol ()
  {
    for (final MockFile aTestFile : CTestFiles.getAllTestFiles ())
    {
      // Build validator
      final UBLDocumentValidator aValidator = new UBLDocumentValidator (PeppolValidationConfiguration.createDefault (aTestFile.getTransactionKey ()));

      // Read as desired type
      final ValidationLayerResult aXSDErrors = aValidator.applyXSDValidation (aTestFile.getResource ());
      if (aTestFile.isGoodCase ())
        assertTrue (aXSDErrors.toString (), aXSDErrors.isSuccess ());
      else
        assertFalse (aXSDErrors.isSuccess ());
    }
  }

  @Test
  public void testApplySchematronValidationPeppol ()
  {
    for (final MockFile aTestFile : CTestFiles.getAllTestFiles ())
    {
      final UBLDocumentValidator aValidator = new UBLDocumentValidator (PeppolValidationConfiguration.createDefault (aTestFile.getTransactionKey ()));

      // Read as desired type
      final ValidationLayerResultList aSCHErrors = aValidator.applySchematronValidation (aTestFile.getResource ());
      if (aTestFile.isGoodCase ())
        assertTrue (aSCHErrors.getAllErrors ().toString (), aSCHErrors.containsNoError ());
      else
        assertTrue (aSCHErrors.containsAtLeastOneError ());
    }
  }

  @Test
  public void testApplyCompleteValidationPeppol ()
  {
    for (final MockFile aTestFile : CTestFiles.getAllTestFiles ())
    {
      final UBLDocumentValidator aValidator = new UBLDocumentValidator (PeppolValidationConfiguration.createDefault (aTestFile.getTransactionKey ()));

      // Read as desired type
      final ValidationLayerResultList aErrors = aValidator.applyCompleteValidation (aTestFile.getResource ());
      if (aTestFile.isGoodCase ())
        assertTrue (aErrors.getAllErrors ().toString (), aErrors.containsNoError ());
      else
        assertTrue (aErrors.containsAtLeastOneError ());
    }
  }
}