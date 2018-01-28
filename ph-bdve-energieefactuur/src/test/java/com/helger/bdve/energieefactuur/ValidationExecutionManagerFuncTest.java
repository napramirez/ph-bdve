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
package com.helger.bdve.energieefactuur;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import com.helger.bdve.energieefactuur.mock.CTestFiles;
import com.helger.bdve.execute.ValidationExecutionManager;
import com.helger.bdve.executorset.IValidationExecutorSet;
import com.helger.bdve.executorset.ValidationExecutorSetRegistry;
import com.helger.bdve.mock.MockFile;
import com.helger.bdve.result.ValidationResultList;
import com.helger.bdve.source.IValidationSource;
import com.helger.bdve.source.ValidationSource;
import com.helger.commons.error.IError;
import com.helger.commons.string.StringHelper;

/**
 * Test class for class {@link ValidationExecutionManager}.
 *
 * @author Philip Helger
 */
public final class ValidationExecutionManagerFuncTest
{
  private static final Logger s_aLogger = LoggerFactory.getLogger (ValidationExecutionManagerFuncTest.class);

  @Test
  public void testTestFiles () throws SAXException
  {
    final ValidationExecutorSetRegistry aRegistry = new ValidationExecutorSetRegistry ();
    EnergieEFactuurValidation.initEnergieEFactuur (aRegistry);

    for (final MockFile aTestFile : CTestFiles.getAllTestFiles ())
    {
      final IValidationExecutorSet aExecutors = aRegistry.getOfID (aTestFile.getVESID ());
      assertNotNull (aExecutors);
      final ValidationExecutionManager aValidator = aExecutors.createExecutionManager ();

      s_aLogger.info ("Validating " +
                      aTestFile.getResource ().getPath () +
                      " against " +
                      aExecutors.getExecutorCount () +
                      " validation layers using " +
                      aTestFile.getVESID ().getAsSingleID ());

      // Read as desired type
      final IValidationSource aSource = ValidationSource.createXMLSource (aTestFile.getResource ());
      final ValidationResultList aErrors = aValidator.executeValidation (aSource, Locale.US);
      if (aTestFile.isGoodCase ())
        assertTrue (aErrors.getAllCount (IError::isError) +
                    " error(s):\n" +
                    StringHelper.getImploded ('\n', aErrors.getAllErrors ()),
                    aErrors.containsNoError ());
      else
        assertTrue (aErrors.getAllCount (IError::isError) +
                    " error(s):\n" +
                    StringHelper.getImploded ('\n', aErrors.getAllErrors ()),
                    aErrors.containsAtLeastOneError ());
    }
  }
}