package felix.felixlib.Base.adp;

import android.content.Context;

import java.util.List;

import felix.felixlib.Base.adp.adp.BaseAdp;
import felix.felixlib.Base.adp.eadp.BaseEAdp;

/**
 * Created by huangmf on 8/14/2017.
 */

public class AdpFactory {
    public static <ADP extends BaseAdp, T> ADP createADP(Class<? extends BaseAdp> type, Context context, List<T> cell) {
        try {
            Object obj = type.newInstance();
            ADP adp = ((ADP) obj);
            adp.init(context, cell);
            return adp;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <EADP extends BaseEAdp, T> EADP createEADP(Class<? extends BaseEAdp> type, Context context, List<T> cell) {
        try {
            Object obj = type.newInstance();
            EADP eadp = ((EADP) obj);
            eadp.init(context, cell);
            return eadp;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
