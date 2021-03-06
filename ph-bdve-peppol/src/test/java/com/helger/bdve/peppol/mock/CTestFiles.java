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
package com.helger.bdve.peppol.mock;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;

import com.helger.bdve.executorset.VESID;
import com.helger.bdve.executorset.ValidationExecutorSetRegistry;
import com.helger.bdve.mock.MockFile;
import com.helger.bdve.peppol.PeppolValidation;
import com.helger.bdve.peppol.PeppolValidation370;
import com.helger.bdve.peppol.PeppolValidation380;
import com.helger.bdve.peppol.PeppolValidation381;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.impl.CommonsArrayList;
import com.helger.commons.collection.impl.ICommonsList;
import com.helger.commons.io.resource.FileSystemResource;
import com.helger.commons.io.resource.IReadableResource;
import com.helger.peppol.testfiles.official.OfficialTestFiles;

@Immutable
public final class CTestFiles
{
  public static final ValidationExecutorSetRegistry VES_REGISTRY = new ValidationExecutorSetRegistry ();
  static
  {
    PeppolValidation.initStandard (VES_REGISTRY);
    PeppolValidation.initThirdParty (VES_REGISTRY);
  }

  private CTestFiles ()
  {}

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <MockFile> getAllTestFiles ()
  {
    final ICommonsList <MockFile> ret = new CommonsArrayList <> ();
    for (final VESID aESID : new VESID [] { PeppolValidation.VID_OPENPEPPOL_BIS3_SG_UBL_INVOICE,
                                            PeppolValidation.VID_OPENPEPPOL_BIS3_SG_UBL_CREDIT_NOTE,

                                            PeppolValidation370.VID_OPENPEPPOL_T19_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T58_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T01_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T10_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T14_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T76_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_T16_V2,
                                            PeppolValidation370.VID_OPENPEPPOL_BIS3_UBL_INVOICE,
                                            PeppolValidation370.VID_OPENPEPPOL_BIS3_UBL_CREDIT_NOTE,

                                            PeppolValidation380.VID_OPENPEPPOL_INVOICE_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_CREDIT_NOTE_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T01_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T16_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T19_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T58_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T71_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T76_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T77_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T110_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T111_V3,

                                            PeppolValidation381.VID_OPENPEPPOL_INVOICE_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_CREDIT_NOTE_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T01_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T16_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T19_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T58_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T71_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T76_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T77_V3,
                                            PeppolValidation381.VID_OPENPEPPOL_T110_V3,
                                            PeppolValidation380.VID_OPENPEPPOL_T111_V3, })
      for (final IReadableResource aRes : getAllMatchingTestFiles (aESID))
        ret.add (MockFile.createGoodCase (aRes, aESID));

    return ret;
  }

  @Nonnull
  @ReturnsMutableCopy
  public static ICommonsList <? extends IReadableResource> getAllMatchingTestFiles (@Nonnull final VESID aVESID)
  {
    ValueEnforcer.notNull (aVESID, "VESID");

    // Special
    if (aVESID.equals (PeppolValidation.VID_OPENPEPPOL_BIS3_SG_UBL_INVOICE))
      return new CommonsArrayList <> (new FileSystemResource ("src/test/resources/test-files/sg-peppol/Singapore invoice valid 1.xml"));
    if (aVESID.equals (PeppolValidation.VID_OPENPEPPOL_BIS3_SG_UBL_CREDIT_NOTE))
      return new CommonsArrayList <> ();

    // 3.7.0
    {
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T19_V2))
        return OfficialTestFiles.getAllTestFilesCatalogue_01_T19 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T58_V2))
        return OfficialTestFiles.getAllTestFilesCatalogue_01_T58 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T01_V2))
        return OfficialTestFiles.getAllTestFilesOrder_03_T01 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T10_V2))
        return OfficialTestFiles.getAllTestFilesInvoice_04_T10 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T14_V2))
        return OfficialTestFiles.getAllTestFilesBilling_05_T14 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T76_V2))
        return OfficialTestFiles.getAllTestFilesOrdering_28_T76 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_T16_V2))
        return OfficialTestFiles.getAllTestFilesDespatchAdvice_30_T16 ();
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_BIS3_UBL_INVOICE))
        return new CommonsArrayList <> (new FileSystemResource ("src/test/resources/test-files/billingbis3/base-example.xml"),
                                        new FileSystemResource ("src/test/resources/test-files/billingbis3/base-negative-inv-correction.xml"));
      if (aVESID.equals (PeppolValidation370.VID_OPENPEPPOL_BIS3_UBL_CREDIT_NOTE))
        return new CommonsArrayList <> (new FileSystemResource ("src/test/resources/test-files/billingbis3/base-creditnote-correction.xml"));
    }

    // 3.8.0
    {
      final String sBase = "src/test/resources/test-files/3.8.0/";
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_INVOICE_V3))
        return new CommonsArrayList <> ();
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_CREDIT_NOTE_V3))
        return new CommonsArrayList <> ();
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T01_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "Order_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T16_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "DespatchAdvice_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T19_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "Catalogue_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T58_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "CatalogueResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T71_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "MessageLevelResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T76_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "OrderResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T77_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "PunchOut_Example.xml"));
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T110_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "OrderAgreement_Example.xml"));

      final String sBase2 = sBase + "Invoice reponse use cases/";
      if (aVESID.equals (PeppolValidation380.VID_OPENPEPPOL_T111_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "InvoiceResponse_Example.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc001-Invoice in process.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc002a-Additional reference data.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc002b-In process but postponed.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc003-Invoice is accepted.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc004a-Invoice is rejected.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc004b-Rejected requesting reissue.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc004c-Rejected requesting replacement.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc005-Invoice is conditionally accepted.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc006a-Under query missing information.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc006b-Missing PO.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc006c-Wrong detail partial credit.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc007-Payment has been initiated.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc008-Invoice is accepted by third party.xml"));
    }

    // 3.8.1
    {
      final String sBase = "src/test/resources/test-files/3.8.1/";
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_INVOICE_V3))
        return new CommonsArrayList <> ();
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_CREDIT_NOTE_V3))
        return new CommonsArrayList <> ();
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T01_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "Order_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T16_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "DespatchAdvice_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T19_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "Catalogue_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T58_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "CatalogueResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T71_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "MessageLevelResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T76_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "OrderResponse_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T77_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "PunchOut_Example.xml"));
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T110_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "OrderAgreement_Example.xml"));

      final String sBase2 = sBase + "Invoice reponse use cases/";
      if (aVESID.equals (PeppolValidation381.VID_OPENPEPPOL_T111_V3))
        return new CommonsArrayList <> (new FileSystemResource (sBase + "InvoiceResponse_Example.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc001-Invoice in process.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc002a-Additional reference data.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc002b-In process but postponed.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc003-Invoice is accepted.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc004a-Invoice is rejected.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc004b-Rejected requesting reissue.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc004c-Rejected requesting replacement.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc005-Invoice is conditionally accepted.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc006a-Under query missing information.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc006b-Missing PO.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc006c-Wrong detail partial credit.xml"),
                                        new FileSystemResource (sBase2 + "T111-uc007-Payment has been initiated.xml"),
                                        new FileSystemResource (sBase2 +
                                                                "T111-uc008-Invoice is accepted by third party.xml"));
    }

    throw new IllegalArgumentException ("Invalid VESID: " + aVESID);
  }
}
