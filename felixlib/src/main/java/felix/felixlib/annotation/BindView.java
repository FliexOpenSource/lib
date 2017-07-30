package felix.felixlib.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by huangmf on 2017/7/29.
 */
@Retention(CLASS)
@Target(FIELD)
public @interface BindView {
    int value();
}
