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
package com.helger.peppol.validation.supplementary.createrules.sch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.CollectionHelper;
import com.helger.commons.io.file.FileOperations;
import com.helger.commons.string.StringHelper;
import com.helger.peppol.validation.supplementary.createrules.ESyntaxBinding;

public final class RuleSourceBusinessRule
{
  private final File m_aSourceFile;
  private final File m_aOutputDirectory;
  private final String m_sID;
  private final String m_sCodeListTransaction;
  private final List <File> m_aResultSCHFiles = new ArrayList <File> ();

  public RuleSourceBusinessRule (@Nonnull final File aSourceFilename,
                                 @Nonnull final File aOutputDirectory,
                                 @Nonnull @Nonempty final String sID,
                                 @Nullable final String sCodeListTransaction)
  {
    ValueEnforcer.notNull (aSourceFilename, "SourceFilename");
    if (!aSourceFilename.isFile ())
      throw new IllegalArgumentException ("Source file does not exist: " + aSourceFilename);
    ValueEnforcer.notNull (aOutputDirectory, "OutputDirectory");
    ValueEnforcer.notEmpty (sID, "ID");

    FileOperations.createDirIfNotExisting (aOutputDirectory);
    FileOperations.createDirIfNotExisting (new File (aOutputDirectory, "include"));
    m_aSourceFile = aSourceFilename;
    m_aOutputDirectory = aOutputDirectory;
    m_sID = sID;
    m_sCodeListTransaction = sCodeListTransaction;
  }

  @Nonnull
  public File getSourceFile ()
  {
    return m_aSourceFile;
  }

  @Nonnull
  @Nonempty
  public String getID ()
  {
    return m_sID;
  }

  @Nonnull
  public File getSchematronAbstractFile (@Nonnull @Nonempty final String sTransaction)
  {
    return new File (m_aOutputDirectory, "include/" + m_sID + "-" + sTransaction + "-abstract.sch");
  }

  @Nonnull
  public File getSchematronBindingFile (@Nonnull final ESyntaxBinding eBinding,
                                        @Nonnull @Nonempty final String sTransaction)
  {
    return new File (m_aOutputDirectory,
                     "include/" + m_sID + "-" + eBinding.getID () + "-" + sTransaction + "-test.sch");
  }

  @Nonnull
  public File getSchematronCodeListFile ()
  {
    return new File (m_aOutputDirectory, "include/" + m_sID + "-" + m_sCodeListTransaction + "-codes.sch");
  }

  @Nonnull
  public File getSchematronAssemblyFile (@Nonnull final ESyntaxBinding eBinding,
                                         @Nonnull @Nonempty final String sTransaction)
  {
    return new File (m_aOutputDirectory, m_sID + "-" + eBinding.getID () + "-" + sTransaction + ".sch");
  }

  public boolean hasCodeList ()
  {
    return StringHelper.hasText (m_sCodeListTransaction);
  }

  public String getCodeList ()
  {
    return m_sCodeListTransaction;
  }

  public void addResultSchematronFile (@Nonnull final File aSCHFile)
  {
    m_aResultSCHFiles.add (aSCHFile);
  }

  @Nonnull
  @ReturnsMutableCopy
  public List <File> getAllResultSchematronFiles ()
  {
    return CollectionHelper.newList (m_aResultSCHFiles);
  }
}
