package felix.felixlib.Base.adp.eadp;

import java.util.List;

/**
 * Created by huangmf on 8/16/2017.
 */

public class BaseEBean<T> {
    private List<T> mChildren;

    public List<T> getChildren() {
        return mChildren;
    }

    public void setChildren(List<T> children) {
        mChildren = children;
    }
}
