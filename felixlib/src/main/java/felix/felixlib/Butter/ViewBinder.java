package felix.felixlib.Butter;

/**
 * Created by huangmf on 2017/7/29.
 */

public interface ViewBinder<T> {
    void bind(Finder finder, T target, Object source);
    void unbind(T target);
}
