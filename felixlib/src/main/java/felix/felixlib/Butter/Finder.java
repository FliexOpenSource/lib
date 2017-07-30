package felix.felixlib.Butter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

/**
 * Created by huangmf on 2017/7/29.
 */

public enum Finder {
    VIEW {
        @Override
        protected View findView(Object source, int id) {
            return ((View) source).findViewById(id);
        }

        @Override
        public Context getContext(Object source) {
            return ((View) source).getContext();
        }
    },
    ACTIVITY {
        @Override
        protected View findView(Object source, int id) {
            return ((Activity) source).findViewById(id);
        }

        @Override
        public Context getContext(Object source) {
            return (Activity) source;
        }
    },
    DIALOG {
        @Override
        protected View findView(Object source, int id) {
            return ((Dialog) source).findViewById(id);
        }

        @Override
        public Context getContext(Object source) {
            return ((Dialog) source).getContext();
        }
    };

    protected abstract View findView(Object source, int id);

    public abstract Context getContext(Object source);
}

