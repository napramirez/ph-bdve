package com.helger.bdve.execute;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.w3c.dom.Node;

import com.helger.bdve.result.ValidationResult;
import com.helger.bdve.result.ValidationResultList;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.ReturnsMutableCopy;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;

/**
 * Execute multiple {@link IValidationExecutor}s at once.
 *
 * @author Philip Helger
 */
public class ValidationExecutionManager
{
  private final ICommonsList <IValidationExecutor> m_aExecutors = new CommonsArrayList<> ();
  private ClassLoader m_aClassLoader;

  public ValidationExecutionManager ()
  {}

  public ValidationExecutionManager (@Nullable final IValidationExecutor... aExecutors)
  {
    addExecutors (aExecutors);
  }

  public ValidationExecutionManager (@Nullable final Iterable <? extends IValidationExecutor> aExecutors)
  {
    addExecutors (aExecutors);
  }

  @Nonnull
  public final ValidationExecutionManager addExecutor (@Nonnull final IValidationExecutor aExecutor)
  {
    ValueEnforcer.notNull (aExecutor, "Executor");
    m_aExecutors.add (aExecutor);
    return this;
  }

  @Nonnull
  public final ValidationExecutionManager addExecutors (@Nullable final IValidationExecutor... aExecutors)
  {
    if (aExecutors != null)
      for (final IValidationExecutor aExecutor : aExecutors)
        addExecutor (aExecutor);
    return this;
  }

  @Nonnull
  public final ValidationExecutionManager addExecutors (@Nullable final Iterable <? extends IValidationExecutor> aExecutors)
  {
    if (aExecutors != null)
      for (final IValidationExecutor aExecutor : aExecutors)
        addExecutor (aExecutor);
    return this;
  }

  @Nonnegative
  public int getExecutorCount ()
  {
    return m_aExecutors.size ();
  }

  @Nonnull
  @ReturnsMutableCopy
  public ICommonsList <IValidationExecutor> getAllExecutors ()
  {
    return m_aExecutors.getClone ();
  }

  @Nullable
  public ClassLoader getClassLoader ()
  {
    return m_aClassLoader;
  }

  @Nonnull
  public ValidationExecutionManager setClassLoader (@Nullable final ClassLoader aClassLoader)
  {
    m_aClassLoader = aClassLoader;
    return this;
  }

  @Nonnull
  public ValidationResultList executeValidation (@Nonnull final Node aNode)
  {
    final ValidationResultList ret = new ValidationResultList ();
    final ClassLoader aClassLoader = getClassLoader ();

    for (final IValidationExecutor aExecutor : getAllExecutors ())
    {
      final ValidationResult aResult = aExecutor.applyValidation (aNode, aClassLoader);
      assert aResult != null;
      ret.add (aResult);
    }
    return ret;
  }
}
