package nc.unc.ama.tests.jsf.faces;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class LoginValidator implements Validator<String> {

  /**
   * <p><span class="changed_modified_2_0">Perform</span> the
   * correctness checks implemented by this {@link Validator} against
   * the specified {@link UIComponent}.  If any violations are found,
   * a {@link ValidatorException} will be thrown containing the {@link
   * FacesMessage} describing the failure.
   *
   * <div class="changed_added_2_0">
   *
   * <p>For a validator to be fully compliant with Version 2 and later
   * of the specification, it must not fail validation on
   * <code>null</code> or empty values unless it is specifically
   * intended to address <code>null</code> or empty values.  An
   * application-wide <code>&lt;context-param&gt;</code> is provided
   * to allow validators designed for JSF 1.2 to work with JSF 2 and
   * later. The <code>javax.faces.VALIDATE_EMPTY_FIELDS</code>
   * <code>&lt;context-param&gt;</code> must be set to
   * <code>false</code> to enable this backwards compatibility
   * behavior.</p>
   *
   * </div>
   *
   * @param context   FacesContext for the request we are processing
   * @param component UIComponent we are checking for correctness
   * @param value     the value to validate
   * @throws ValidatorException   if validation fails
   * @throws NullPointerException if <code>context</code>
   *                              or <code>component</code> is <code>null</code>
   */
  @Override
  public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {

  }
}
