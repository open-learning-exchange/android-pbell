package pbell.offline.ole.org.pbell;

import android.util.Log;

import com.couchbase.lite.Database;
import com.couchbase.lite.Emitter;
import com.couchbase.lite.Mapper;
import com.couchbase.lite.View;

import java.util.Map;

/**
 * Created by leonardmensah on 23/05/16.
 */
public class CouchViews {
    public View CreateLoginByIdView(Database db) {
        View LoginByIdView = db.getView("MembersByLoginID");
            LoginByIdView.setMap(
                    new Mapper(){
                        @Override
                        public void map(Map<String, Object> document,Emitter emitter) {
                    /* Emit data to matieralized view */
                            if ("Member".equals(document.get("kind"))) {
                                emitter.emit((String) document.get("login"), (String) document.get("_id"));
                            }
                        }
                    }, "8"
            );
        return LoginByIdView;
    }

    public View ReadShelfByIdView(Database db) {
        View shelfListByIdView = db.getView("ShelfByID");
        //if (LoginByIdView == null) {
        shelfListByIdView.setMap(
                new Mapper(){
                    @Override
                    public void map(Map<String, Object> document,Emitter emitter) {
                    /* Emit data to matieralized view */
                        ///if ("Member".equals(document.get("kind"))) {
                            emitter.emit((String) document.get("memberId"), (String) document.get("_id"));
                        //}
                    }
                }, "4"
        );
        return shelfListByIdView;
    }
}
