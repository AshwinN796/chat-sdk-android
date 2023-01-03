package sdk.chat.core.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CACHED_FILE".
*/
public class CachedFileDao extends AbstractDao<CachedFile, Long> {

    public static final String TABLENAME = "CACHED_FILE";

    /**
     * Properties of entity CachedFile.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property EntityID = new Property(1, String.class, "entityID", false, "ENTITY_ID");
        public final static Property Identifier = new Property(2, String.class, "identifier", false, "IDENTIFIER");
        public final static Property Hash = new Property(3, String.class, "hash", false, "HASH");
        public final static Property Type = new Property(4, Integer.class, "type", false, "TYPE");
        public final static Property LocalPath = new Property(5, String.class, "localPath", false, "LOCAL_PATH");
        public final static Property RemotePath = new Property(6, String.class, "remotePath", false, "REMOTE_PATH");
        public final static Property Name = new Property(7, String.class, "name", false, "NAME");
        public final static Property MessageKey = new Property(8, String.class, "messageKey", false, "MESSAGE_KEY");
        public final static Property ReportProgress = new Property(9, boolean.class, "reportProgress", false, "REPORT_PROGRESS");
        public final static Property StartTime = new Property(10, java.util.Date.class, "startTime", false, "START_TIME");
        public final static Property FinishTime = new Property(11, java.util.Date.class, "finishTime", false, "FINISH_TIME");
        public final static Property MimeType = new Property(12, String.class, "mimeType", false, "MIME_TYPE");
        public final static Property Status = new Property(13, Integer.class, "status", false, "STATUS");
    }

    private DaoSession daoSession;


    public CachedFileDao(DaoConfig config) {
        super(config);
    }
    
    public CachedFileDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CACHED_FILE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ENTITY_ID\" TEXT," + // 1: entityID
                "\"IDENTIFIER\" TEXT," + // 2: identifier
                "\"HASH\" TEXT," + // 3: hash
                "\"TYPE\" INTEGER," + // 4: type
                "\"LOCAL_PATH\" TEXT," + // 5: localPath
                "\"REMOTE_PATH\" TEXT," + // 6: remotePath
                "\"NAME\" TEXT," + // 7: name
                "\"MESSAGE_KEY\" TEXT," + // 8: messageKey
                "\"REPORT_PROGRESS\" INTEGER NOT NULL ," + // 9: reportProgress
                "\"START_TIME\" INTEGER," + // 10: startTime
                "\"FINISH_TIME\" INTEGER," + // 11: finishTime
                "\"MIME_TYPE\" TEXT," + // 12: mimeType
                "\"STATUS\" INTEGER);"); // 13: status
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_CACHED_FILE_ENTITY_ID ON \"CACHED_FILE\"" +
                " (\"ENTITY_ID\" ASC);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_CACHED_FILE_IDENTIFIER ON \"CACHED_FILE\"" +
                " (\"IDENTIFIER\" ASC);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_CACHED_FILE_HASH ON \"CACHED_FILE\"" +
                " (\"HASH\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CACHED_FILE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CachedFile entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String entityID = entity.getEntityID();
        if (entityID != null) {
            stmt.bindString(2, entityID);
        }
 
        String identifier = entity.getIdentifier();
        if (identifier != null) {
            stmt.bindString(3, identifier);
        }
 
        String hash = entity.getHash();
        if (hash != null) {
            stmt.bindString(4, hash);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(5, type);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(6, localPath);
        }
 
        String remotePath = entity.getRemotePath();
        if (remotePath != null) {
            stmt.bindString(7, remotePath);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(8, name);
        }
 
        String messageKey = entity.getMessageKey();
        if (messageKey != null) {
            stmt.bindString(9, messageKey);
        }
        stmt.bindLong(10, entity.getReportProgress() ? 1L: 0L);
 
        java.util.Date startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindLong(11, startTime.getTime());
        }
 
        java.util.Date finishTime = entity.getFinishTime();
        if (finishTime != null) {
            stmt.bindLong(12, finishTime.getTime());
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(13, mimeType);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(14, status);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CachedFile entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String entityID = entity.getEntityID();
        if (entityID != null) {
            stmt.bindString(2, entityID);
        }
 
        String identifier = entity.getIdentifier();
        if (identifier != null) {
            stmt.bindString(3, identifier);
        }
 
        String hash = entity.getHash();
        if (hash != null) {
            stmt.bindString(4, hash);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(5, type);
        }
 
        String localPath = entity.getLocalPath();
        if (localPath != null) {
            stmt.bindString(6, localPath);
        }
 
        String remotePath = entity.getRemotePath();
        if (remotePath != null) {
            stmt.bindString(7, remotePath);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(8, name);
        }
 
        String messageKey = entity.getMessageKey();
        if (messageKey != null) {
            stmt.bindString(9, messageKey);
        }
        stmt.bindLong(10, entity.getReportProgress() ? 1L: 0L);
 
        java.util.Date startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindLong(11, startTime.getTime());
        }
 
        java.util.Date finishTime = entity.getFinishTime();
        if (finishTime != null) {
            stmt.bindLong(12, finishTime.getTime());
        }
 
        String mimeType = entity.getMimeType();
        if (mimeType != null) {
            stmt.bindString(13, mimeType);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(14, status);
        }
    }

    @Override
    protected final void attachEntity(CachedFile entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CachedFile readEntity(Cursor cursor, int offset) {
        CachedFile entity = new CachedFile( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // entityID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // identifier
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // hash
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // type
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // localPath
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // remotePath
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // name
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // messageKey
            cursor.getShort(offset + 9) != 0, // reportProgress
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)), // startTime
            cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)), // finishTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // mimeType
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CachedFile entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setEntityID(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIdentifier(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHash(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setType(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setLocalPath(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRemotePath(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMessageKey(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setReportProgress(cursor.getShort(offset + 9) != 0);
        entity.setStartTime(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
        entity.setFinishTime(cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)));
        entity.setMimeType(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setStatus(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CachedFile entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CachedFile entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CachedFile entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
