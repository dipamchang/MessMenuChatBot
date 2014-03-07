package com.dchang.messmenu.entity;

import com.google.appengine.api.datastore.Key;
import java.io.*;
import javax.jdo.JDOFatalInternalException;
import javax.jdo.PersistenceManager;
import javax.jdo.identity.ObjectIdentity;
import javax.jdo.spi.*;

public class MessMenu
    implements Serializable, PersistenceCapable
{

    public MessMenu(String day, String mess, String time, String menu)
    {
        this.day = day;
        this.mess = mess;
        this.time = time;
        this.menu = menu;
    }

    public MessMenu()
    {
    }

    public String toString()
    {
        return (new StringBuilder("* * * ")).append(jdoGetmess(this).toUpperCase()).append(" Mess Menu * * * \n").append(jdoGetmenu(this)).toString();
    }

    public String Total()
    {
        return (new StringBuilder("* * * ")).append(jdoGettime(this).toUpperCase()).append(" Menu * * * \n").append(jdoGetmenu(this)).toString();
    }

    public String getDay()
    {
        return jdoGetday(this);
    }

    public void setDay(String day)
    {
        jdoSetday(this, day);
    }

    public String getMess()
    {
        return jdoGetmess(this);
    }

    public void setMess(String mess)
    {
        jdoSetmess(this, mess);
    }

    public String getTime()
    {
        return jdoGettime(this);
    }

    public void setTime(String time)
    {
        jdoSettime(this, time);
    }

    public String getMenu()
    {
        return jdoGetmenu(this);
    }

    public void setMenu(String menu)
    {
        jdoSetmenu(this, menu);
    }

    public void jdoCopyKeyFieldsFromObjectId(javax.jdo.spi.PersistenceCapable.ObjectIdFieldConsumer fc, Object oid)
    {
        if(fc == null)
            throw new IllegalArgumentException("ObjectIdFieldConsumer is null");
        if(!(oid instanceof ObjectIdentity))
        {
            throw new ClassCastException("oid is not instanceof javax.jdo.identity.ObjectIdentity");
        } else
        {
            ObjectIdentity o = (ObjectIdentity)oid;
            fc.storeObjectField(0, o.getKey());
            return;
        }
    }

    protected void jdoCopyKeyFieldsFromObjectId(Object oid)
    {
        if(!(oid instanceof ObjectIdentity))
        {
            throw new ClassCastException("key class is not javax.jdo.identity.ObjectIdentity or null");
        } else
        {
            ObjectIdentity o = (ObjectIdentity)oid;
            count = (Key)o.getKey();
            return;
        }
    }

    public void jdoCopyKeyFieldsToObjectId(Object oid)
    {
        throw new JDOFatalInternalException("It's illegal to call jdoCopyKeyFieldsToObjectId for a class with SingleFieldIdentity.");
    }

    public void jdoCopyKeyFieldsToObjectId(Object fs, Object paramObject)
    {
        throw new JDOFatalInternalException("It's illegal to call jdoCopyKeyFieldsToObjectId for a class with SingleFieldIdentity.");
    }

    public final Object jdoGetObjectId()
    {
        if(jdoStateManager != null)
            return jdoStateManager.getObjectId(this);
        else
            return null;
    }

    public final Object jdoGetVersion()
    {
        if(jdoStateManager != null)
            return jdoStateManager.getVersion(this);
        else
            return null;
    }

    protected final void jdoPreSerialize()
    {
        if(jdoStateManager != null)
            jdoStateManager.preSerialize(this);
    }

    public final PersistenceManager jdoGetPersistenceManager()
    {
        return jdoStateManager == null ? null : jdoStateManager.getPersistenceManager(this);
    }

    public final Object jdoGetTransactionalObjectId()
    {
        return jdoStateManager == null ? null : jdoStateManager.getTransactionalObjectId(this);
    }

    public final boolean jdoIsDeleted()
    {
        return jdoStateManager == null ? false : jdoStateManager.isDeleted(this);
    }

    public final boolean jdoIsDirty()
    {
        if(jdoStateManager != null)
            return jdoStateManager.isDirty(this);
        else
            return false;
    }

    public final boolean jdoIsNew()
    {
        return jdoStateManager == null ? false : jdoStateManager.isNew(this);
    }

    public final boolean jdoIsPersistent()
    {
        return jdoStateManager == null ? false : jdoStateManager.isPersistent(this);
    }

    public final boolean jdoIsTransactional()
    {
        return jdoStateManager == null ? false : jdoStateManager.isTransactional(this);
    }

    public void jdoMakeDirty(String fieldName)
    {
        if(jdoStateManager != null)
            jdoStateManager.makeDirty(this, fieldName);
    }

    public Object jdoNewObjectIdInstance()
    {
        return new ObjectIdentity(getClass(), count);
    }

    public Object jdoNewObjectIdInstance(Object key)
    {
        if(key == null)
            throw new IllegalArgumentException("key is null");
        if(!(key instanceof String))
            return new ObjectIdentity(getClass(), key);
        else
            return new ObjectIdentity(getClass(), (String)key);
    }

    public final void jdoProvideFields(int indices[])
    {
        if(indices == null)
            throw new IllegalArgumentException("argment is null");
        int i = indices.length - 1;
        if(i >= 0)
            do
                jdoProvideField(indices[i]);
            while(--i >= 0);
    }

    public final void jdoReplaceFields(int indices[])
    {
        if(indices == null)
            throw new IllegalArgumentException("argument is null");
        int i = indices.length;
        if(i > 0)
        {
            int j = 0;
            do
                jdoReplaceField(indices[j]);
            while(++j < i);
        }
    }

    public final void jdoReplaceFlags()
    {
        if(jdoStateManager != null)
            jdoFlags = jdoStateManager.replacingFlags(this);
    }

    public final synchronized void jdoReplaceStateManager(StateManager sm)
    {
        if(jdoStateManager != null)
        {
            jdoStateManager = jdoStateManager.replacingStateManager(this, sm);
        } else
        {
            JDOImplHelper.checkAuthorizedStateManager(sm);
            jdoStateManager = sm;
            jdoFlags = 1;
        }
    }

    public boolean jdoIsDetached()
    {
        return false;
    }

    public PersistenceCapable jdoNewInstance(StateManager sm)
    {
        MessMenu result = new MessMenu();
        result.jdoFlags = 1;
        result.jdoStateManager = sm;
        return result;
    }

    public PersistenceCapable jdoNewInstance(StateManager sm, Object obj)
    {
        MessMenu result = new MessMenu();
        result.jdoFlags = 1;
        result.jdoStateManager = sm;
        result.jdoCopyKeyFieldsFromObjectId(obj);
        return result;
    }

    public void jdoReplaceField(int index)
    {
        if(jdoStateManager == null)
            throw new IllegalStateException("state manager is null");
        switch(index)
        {
        case 0: // '\0'
            count = (Key)jdoStateManager.replacingObjectField(this, index);
            break;

        case 1: // '\001'
            day = jdoStateManager.replacingStringField(this, index);
            break;

        case 2: // '\002'
            menu = jdoStateManager.replacingStringField(this, index);
            break;

        case 3: // '\003'
            mess = jdoStateManager.replacingStringField(this, index);
            break;

        case 4: // '\004'
            time = jdoStateManager.replacingStringField(this, index);
            break;

        default:
            throw new IllegalArgumentException("out of field index :" + index);
        }
    }

    public void jdoProvideField(int index)
    {
        if(jdoStateManager == null)
            throw new IllegalStateException("state manager is null");
        switch(index)
        {
        case 0: // '\0'
            jdoStateManager.providedObjectField(this, index, count);
            break;

        case 1: // '\001'
            jdoStateManager.providedStringField(this, index, day);
            break;

        case 2: // '\002'
            jdoStateManager.providedStringField(this, index, menu);
            break;

        case 3: // '\003'
            jdoStateManager.providedStringField(this, index, mess);
            break;

        case 4: // '\004'
            jdoStateManager.providedStringField(this, index, time);
            break;

        default:
            throw new IllegalArgumentException("out of field index :" + index);
        }
    }

    protected final void jdoCopyField(MessMenu obj, int index)
    {
        switch(index)
        {
        case 0: // '\0'
            count = obj.count;
            break;

        case 1: // '\001'
            day = obj.day;
            break;

        case 2: // '\002'
            menu = obj.menu;
            break;

        case 3: // '\003'
            mess = obj.mess;
            break;

        case 4: // '\004'
            time = obj.time;
            break;

        default:
            throw new IllegalArgumentException("out of field index :" + index);
        }
    }

    public void jdoCopyFields(Object obj, int indices[])
    {
        if(jdoStateManager == null)
            throw new IllegalStateException("state manager is null");
        if(indices == null)
            throw new IllegalStateException("fieldNumbers is null");
        if(!(obj instanceof MessMenu))
            throw new IllegalArgumentException("object is not an object of type com.dchang.messmenu.entity.MessMenu");
        MessMenu other = (MessMenu)obj;
        if(jdoStateManager != other.jdoStateManager)
            throw new IllegalArgumentException("state managers do not match");
        int i = indices.length - 1;
        if(i >= 0)
            do
                jdoCopyField(other, indices[i]);
            while(--i >= 0);
    }

    private static final String[] __jdoFieldNamesInit()
    {
        return (new String[] {
            "count", "day", "menu", "mess", "time"
        });
    }

    private static final Class[] __jdoFieldTypesInit()
    {
        return (new Class[] {
            ___jdo$loadClass("com.google.appengine.api.datastore.Key"), ___jdo$loadClass("java.lang.String"), ___jdo$loadClass("java.lang.String"), ___jdo$loadClass("java.lang.String"), ___jdo$loadClass("java.lang.String")
        });
    }

    private static final byte[] __jdoFieldFlagsInit()
    {
        return (new byte[] {
            24, 21, 21, 21, 21
        });
    }

    protected static int __jdoGetInheritedFieldCount()
    {
        return 0;
    }

    protected static int jdoGetManagedFieldCount()
    {
        return 5;
    }

    private static Class __jdoPersistenceCapableSuperclassInit()
    {
        return null;
    }

    public static Class ___jdo$loadClass(String className)
    {
        return Class.forName(className);
        ClassNotFoundException e;
        e;
        throw new NoClassDefFoundError(e.getMessage());
    }

    private Object jdoSuperClone()
        throws CloneNotSupportedException
    {
        MessMenu o = (MessMenu)super.clone();
        o.jdoFlags = 0;
        o.jdoStateManager = null;
        return o;
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        jdoPreSerialize();
        out.defaultWriteObject();
    }

    static Key jdoGetcount(MessMenu objPC)
    {
        return objPC.count;
    }

    static void jdoSetcount(MessMenu objPC, Key val)
    {
        if(objPC.jdoStateManager == null)
            objPC.count = val;
        else
            objPC.jdoStateManager.setObjectField(objPC, 0, objPC.count, val);
    }

    static String jdoGetday(MessMenu objPC)
    {
        if(objPC.jdoFlags > 0 && objPC.jdoStateManager != null && !objPC.jdoStateManager.isLoaded(objPC, 1))
            return objPC.jdoStateManager.getStringField(objPC, 1, objPC.day);
        else
            return objPC.day;
    }

    static void jdoSetday(MessMenu objPC, String val)
    {
        if(objPC.jdoFlags != 0 && objPC.jdoStateManager != null)
            objPC.jdoStateManager.setStringField(objPC, 1, objPC.day, val);
        else
            objPC.day = val;
    }

    static String jdoGetmenu(MessMenu objPC)
    {
        if(objPC.jdoFlags > 0 && objPC.jdoStateManager != null && !objPC.jdoStateManager.isLoaded(objPC, 2))
            return objPC.jdoStateManager.getStringField(objPC, 2, objPC.menu);
        else
            return objPC.menu;
    }

    static void jdoSetmenu(MessMenu objPC, String val)
    {
        if(objPC.jdoFlags != 0 && objPC.jdoStateManager != null)
            objPC.jdoStateManager.setStringField(objPC, 2, objPC.menu, val);
        else
            objPC.menu = val;
    }

    static String jdoGetmess(MessMenu objPC)
    {
        if(objPC.jdoFlags > 0 && objPC.jdoStateManager != null && !objPC.jdoStateManager.isLoaded(objPC, 3))
            return objPC.jdoStateManager.getStringField(objPC, 3, objPC.mess);
        else
            return objPC.mess;
    }

    static void jdoSetmess(MessMenu objPC, String val)
    {
        if(objPC.jdoFlags != 0 && objPC.jdoStateManager != null)
            objPC.jdoStateManager.setStringField(objPC, 3, objPC.mess, val);
        else
            objPC.mess = val;
    }

    static String jdoGettime(MessMenu objPC)
    {
        if(objPC.jdoFlags > 0 && objPC.jdoStateManager != null && !objPC.jdoStateManager.isLoaded(objPC, 4))
            return objPC.jdoStateManager.getStringField(objPC, 4, objPC.time);
        else
            return objPC.time;
    }

    static void jdoSettime(MessMenu objPC, String val)
    {
        if(objPC.jdoFlags != 0 && objPC.jdoStateManager != null)
            objPC.jdoStateManager.setStringField(objPC, 4, objPC.time, val);
        else
            objPC.time = val;
    }

    Key count;
    String day;
    String mess;
    String time;
    String menu;
    protected transient StateManager jdoStateManager;
    protected transient byte jdoFlags;
    private static final byte jdoFieldFlags[];
    private static final Class jdoPersistenceCapableSuperclass;
    private static final Class jdoFieldTypes[];
    private static final String jdoFieldNames[];
    private static final int jdoInheritedFieldCount = __jdoGetInheritedFieldCount();
    private static final long serialVersionUID = 0xd6020bfbbf305d85L;

    static 
    {
        jdoFieldNames = __jdoFieldNamesInit();
        jdoFieldTypes = __jdoFieldTypesInit();
        jdoFieldFlags = __jdoFieldFlagsInit();
        jdoPersistenceCapableSuperclass = __jdoPersistenceCapableSuperclassInit();
        JDOImplHelper.registerClass(___jdo$loadClass("com.dchang.messmenu.entity.MessMenu"), jdoFieldNames, jdoFieldTypes, jdoFieldFlags, jdoPersistenceCapableSuperclass, new MessMenu());
    }
}