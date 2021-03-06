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
package com.helger.bdve.peppol;

import java.time.LocalDate;
import java.time.Month;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.bdve.EValidationType;
import com.helger.bdve.artefact.ValidationArtefact;
import com.helger.bdve.execute.IValidationExecutor;
import com.helger.bdve.execute.ValidationExecutorSchematron;
import com.helger.bdve.execute.ValidationExecutorXSD;
import com.helger.bdve.executorset.VESID;
import com.helger.bdve.executorset.ValidationExecutorSet;
import com.helger.bdve.executorset.ValidationExecutorSetRegistry;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.datetime.PDTFactory;
import com.helger.commons.io.resource.ClassPathResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.commons.version.Version;
import com.helger.ubl21.EUBL21DocumentType;
import com.helger.ubl21.UBL21NamespaceContext;

/**
 * OpenPEPPOL validation artefacts release 3.8.0. Spring 2018 release. Valid per
 * May 29th, 2019
 *
 * @author Philip Helger
 */
@Immutable
public final class PeppolValidation380
{
  // Standard resources
  public static final Version PEPPOL_VALIDATION_ARTEFACT_VERSION = new Version (3, 8, 0);
  public static final String VERSION_STR = PEPPOL_VALIDATION_ARTEFACT_VERSION.getAsString (true);
  public static final LocalDate VALID_PER = PDTFactory.createLocalDate (2019, Month.MAY, 29);

  // Standard
  public static final VESID VID_OPENPEPPOL_INVOICE_V3 = new VESID ("eu.peppol.bis3", "invoice", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_CREDIT_NOTE_V3 = new VESID ("eu.peppol.bis3", "creditnote", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T01_V3 = new VESID ("eu.peppol.bis3", "t01", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T16_V3 = new VESID ("eu.peppol.bis3", "t16", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T19_V3 = new VESID ("eu.peppol.bis3", "t19", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T58_V3 = new VESID ("eu.peppol.bis3", "t58", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T71_V3 = new VESID ("eu.peppol.bis3", "t71", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T76_V3 = new VESID ("eu.peppol.bis3", "t76", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T77_V3 = new VESID ("eu.peppol.bis3", "t77", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T110_V3 = new VESID ("eu.peppol.bis3", "t110", VERSION_STR);
  public static final VESID VID_OPENPEPPOL_T111_V3 = new VESID ("eu.peppol.bis3", "t111", VERSION_STR);

  private static final String PREFIX = "openpeppol/" + VERSION_STR + "/";
  private static final String PREFIX_XSLT = PREFIX + "xslt/";

  @Nonnull
  private static ClassLoader _getCL ()
  {
    return PeppolValidation380.class.getClassLoader ();
  }

  // Previously T10 and T14
  public static final IReadableResource INVOICE_CEN = new ClassPathResource (PREFIX_XSLT + "CEN-EN16931-UBL.xslt",
                                                                             _getCL ());
  public static final IReadableResource INVOICE_PEPPOL = new ClassPathResource (PREFIX_XSLT + "PEPPOL-EN16931-UBL.xslt",
                                                                                _getCL ());

  public static final IReadableResource ORDER = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T01.xslt", _getCL ());

  public static final IReadableResource DESPATCH_ADVICE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T16.xslt",
                                                                                 _getCL ());

  public static final IReadableResource CATALOGUE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T19.xslt",
                                                                           _getCL ());

  public static final IReadableResource CATALOGUE_RESPONSE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T58.xslt",
                                                                                    _getCL ());

  public static final IReadableResource MLR = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T71.xslt", _getCL ());

  public static final IReadableResource ORDER_RESPONSE = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T76.xslt",
                                                                                _getCL ());

  public static final IReadableResource PUNCH_OUT = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T77.xslt",
                                                                           _getCL ());

  public static final IReadableResource ORDER_AGREEMENT = new ClassPathResource (PREFIX_XSLT + "PEPPOLBIS-T110.xslt",
                                                                                 _getCL ());

  public static final IReadableResource INVOICE_MESSAGE_RESPONSE = new ClassPathResource (PREFIX_XSLT +
                                                                                          "PEPPOLBIS-T111.xslt",
                                                                                          _getCL ());

  private PeppolValidation380 ()
  {}

  @Nonnull
  private static IValidationExecutor _createXSLT (@Nonnull final IReadableResource aRes)
  {
    return new ValidationExecutorSchematron (new ValidationArtefact (EValidationType.SCHEMATRON_XSLT, _getCL (), aRes),
                                             null,
                                             UBL21NamespaceContext.getInstance ());
  }

  public static void init (@Nonnull final ValidationExecutorSetRegistry aRegistry)
  {
    ValueEnforcer.notNull (aRegistry, "Registry");

    final String sVersion = " (" + VERSION_STR + ")";
    final boolean bNotDeprecated = false;
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_INVOICE_V3,
                                                                           "OpenPEPPOL Invoice" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.INVOICE),
                                                                           _createXSLT (INVOICE_CEN),
                                                                           _createXSLT (INVOICE_PEPPOL)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_CREDIT_NOTE_V3,
                                                                           "OpenPEPPOL Credit Note" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CREDIT_NOTE),
                                                                           _createXSLT (INVOICE_CEN),
                                                                           _createXSLT (INVOICE_PEPPOL)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T01_V3,
                                                                           "OpenPEPPOL Order" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER),
                                                                           _createXSLT (ORDER)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T16_V3,
                                                                           "OpenPEPPOL Despatch Advice" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.DESPATCH_ADVICE),
                                                                           _createXSLT (DESPATCH_ADVICE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T19_V3,
                                                                           "OpenPEPPOL Catalogue" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CATALOGUE),
                                                                           _createXSLT (CATALOGUE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T58_V3,
                                                                           "OpenPEPPOL Catalogue Response" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (CATALOGUE_RESPONSE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T71_V3,
                                                                           "OpenPEPPOL MLR" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (MLR)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T76_V3,
                                                                           "OpenPEPPOL Order Response" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER_RESPONSE),
                                                                           _createXSLT (ORDER_RESPONSE)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T77_V3,
                                                                           "OpenPEPPOL Punch Out" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.CATALOGUE),
                                                                           _createXSLT (PUNCH_OUT)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T110_V3,
                                                                           "OpenPEPPOL Order Agreement" + sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.ORDER_RESPONSE),
                                                                           _createXSLT (ORDER_AGREEMENT)));
    aRegistry.registerValidationExecutorSet (ValidationExecutorSet.create (VID_OPENPEPPOL_T111_V3,
                                                                           "OpenPEPPOL Invoice Message Response" +
                                                                                                   sVersion,
                                                                           bNotDeprecated,
                                                                           ValidationExecutorXSD.create (EUBL21DocumentType.APPLICATION_RESPONSE),
                                                                           _createXSLT (INVOICE_MESSAGE_RESPONSE)));
  }
}
