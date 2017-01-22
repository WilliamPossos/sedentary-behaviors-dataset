package com.example.william.data_set;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Lastname = new Property(2, String.class, "lastname", false, "LASTNAME");
        public final static Property Username = new Property(3, String.class, "username", false, "USERNAME");
        public final static Property Age = new Property(4, Integer.class, "age", false, "AGE");
        public final static Property Gender = new Property(5, String.class, "gender", false, "GENDER");
        public final static Property Weight = new Property(6, Integer.class, "weight", false, "WEIGHT");
        public final static Property Stature = new Property(7, Integer.class, "stature", false, "STATURE");
        public final static Property Profession = new Property(8, String.class, "profession", false, "PROFESSION");
        public final static Property Mail = new Property(9, String.class, "mail", false, "MAIL");
        public final static Property Waist = new Property(10, Integer.class, "waist", false, "WAIST");
        public final static Property SmokeFrec = new Property(11, String.class, "smokeFrec", false, "SMOKE_FREC");
        public final static Property DrinkFrec = new Property(12, String.class, "drinkFrec", false, "DRINK_FREC");
        public final static Property Transport = new Property(13, String.class, "transport", false, "TRANSPORT");
    }

    private DaoSession daoSession;


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"LASTNAME\" TEXT," + // 2: lastname
                "\"USERNAME\" TEXT," + // 3: username
                "\"AGE\" INTEGER," + // 4: age
                "\"GENDER\" TEXT," + // 5: gender
                "\"WEIGHT\" INTEGER," + // 6: weight
                "\"STATURE\" INTEGER," + // 7: stature
                "\"PROFESSION\" TEXT," + // 8: profession
                "\"MAIL\" TEXT," + // 9: mail
                "\"WAIST\" INTEGER," + // 10: waist
                "\"SMOKE_FREC\" TEXT," + // 11: smokeFrec
                "\"DRINK_FREC\" TEXT," + // 12: drinkFrec
                "\"TRANSPORT\" TEXT);"); // 13: transport
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String lastname = entity.getLastname();
        if (lastname != null) {
            stmt.bindString(3, lastname);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
 
        Integer weight = entity.getWeight();
        if (weight != null) {
            stmt.bindLong(7, weight);
        }
 
        Integer stature = entity.getStature();
        if (stature != null) {
            stmt.bindLong(8, stature);
        }
 
        String profession = entity.getProfession();
        if (profession != null) {
            stmt.bindString(9, profession);
        }
 
        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(10, mail);
        }
 
        Integer waist = entity.getWaist();
        if (waist != null) {
            stmt.bindLong(11, waist);
        }
 
        String smokeFrec = entity.getSmokeFrec();
        if (smokeFrec != null) {
            stmt.bindString(12, smokeFrec);
        }
 
        String drinkFrec = entity.getDrinkFrec();
        if (drinkFrec != null) {
            stmt.bindString(13, drinkFrec);
        }
 
        String transport = entity.getTransport();
        if (transport != null) {
            stmt.bindString(14, transport);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String lastname = entity.getLastname();
        if (lastname != null) {
            stmt.bindString(3, lastname);
        }
 
        String username = entity.getUsername();
        if (username != null) {
            stmt.bindString(4, username);
        }
 
        Integer age = entity.getAge();
        if (age != null) {
            stmt.bindLong(5, age);
        }
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(6, gender);
        }
 
        Integer weight = entity.getWeight();
        if (weight != null) {
            stmt.bindLong(7, weight);
        }
 
        Integer stature = entity.getStature();
        if (stature != null) {
            stmt.bindLong(8, stature);
        }
 
        String profession = entity.getProfession();
        if (profession != null) {
            stmt.bindString(9, profession);
        }
 
        String mail = entity.getMail();
        if (mail != null) {
            stmt.bindString(10, mail);
        }
 
        Integer waist = entity.getWaist();
        if (waist != null) {
            stmt.bindLong(11, waist);
        }
 
        String smokeFrec = entity.getSmokeFrec();
        if (smokeFrec != null) {
            stmt.bindString(12, smokeFrec);
        }
 
        String drinkFrec = entity.getDrinkFrec();
        if (drinkFrec != null) {
            stmt.bindString(13, drinkFrec);
        }
 
        String transport = entity.getTransport();
        if (transport != null) {
            stmt.bindString(14, transport);
        }
    }

    @Override
    protected final void attachEntity(User entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // username
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // age
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // gender
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // weight
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // stature
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // profession
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // mail
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // waist
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // smokeFrec
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // drinkFrec
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13) // transport
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setLastname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUsername(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAge(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setGender(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setWeight(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setStature(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setProfession(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMail(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWaist(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setSmokeFrec(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDrinkFrec(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setTransport(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
