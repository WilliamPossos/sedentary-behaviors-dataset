package com.example.william.data_set;

import org.greenrobot.greendao.annotation.*;

import java.util.List;
import com.example.william.data_set.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "ACTIVITY".
 */
@Entity(active = true)
public class Activity {

    @Id
    private Long id;
    private String status;
    private long userId;
    private long actionId;
    private long locationId;
    private long postureId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient ActivityDao myDao;

    @ToMany(joinProperties = {
        @JoinProperty(name = "id", referencedName = "activityId")
    })
    private List<UserData> userDataList;

    @Generated
    public Activity() {
    }

    public Activity(Long id) {
        this.id = id;
    }

    @Generated
    public Activity(Long id, String status, long userId, long actionId, long locationId, long postureId) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.actionId = actionId;
        this.locationId = locationId;
        this.postureId = postureId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getActivityDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getPostureId() {
        return postureId;
    }

    public void setPostureId(long postureId) {
        this.postureId = postureId;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    @Generated
    public List<UserData> getUserDataList() {
        if (userDataList == null) {
            __throwIfDetached();
            UserDataDao targetDao = daoSession.getUserDataDao();
            List<UserData> userDataListNew = targetDao._queryActivity_UserDataList(id);
            synchronized (this) {
                if(userDataList == null) {
                    userDataList = userDataListNew;
                }
            }
        }
        return userDataList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated
    public synchronized void resetUserDataList() {
        userDataList = null;
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}