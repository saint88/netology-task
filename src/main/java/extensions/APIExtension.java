package extensions;

import static org.junit.platform.commons.util.AnnotationUtils.findAnnotation;

import annotations.Bug;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Optional;

public class APIExtension implements ExecutionCondition {

  @Override
  public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
    final Optional<Bug> optional = findAnnotation(extensionContext.getElement(), Bug.class);
    if (optional.isPresent()) {
      return ConditionEvaluationResult.disabled("Bug assigned to test");
    }

    return ConditionEvaluationResult.enabled("Bugs not found");
  }
}
