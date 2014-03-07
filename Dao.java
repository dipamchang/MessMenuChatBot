package com.dchang.messmenu.dao;

import com.dchang.messmenu.entity.MessMenu;
import java.util.*;
import javax.jdo.*;

public final class Dao extends Enum
{

    private Dao(String s, int i)
    {
        super(s, i);
    }

    public void add(String day, String mess, String time, String menu)
    {
        synchronized(this)
        {
            PersistenceManager PM = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
            MessMenu aaa = new MessMenu(day, mess, time, menu);
            PM.makePersistent(aaa);
        }
    }

    public List getToday(String time)
    {
        String cday;
        List finalResults;
        PersistenceManager pm;
        Query query;
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        String t1 = "breakfast";
        Calendar javaCalendar = null;
        String currentDate = "";
        javaCalendar = Calendar.getInstance();
        currentDate = (new StringBuilder(String.valueOf(javaCalendar.get(5)))).append("/").append(javaCalendar.get(2) + 1).append("/").append(javaCalendar.get(1)).toString();
        cday = Integer.toString(javaCalendar.get(7));
        String cday1 = Integer.toString(date.getDay());
        finalResults = new ArrayList();
        List searchResults1 = new ArrayList();
        pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
        query = pm.newQuery(com/dchang/messmenu/entity/MessMenu);
        query.setFilter("day == param");
        query.declareParameters("String param");
        List list;
        List searchResults1 = (List)query.execute(cday);
        for(Iterator iterator = searchResults1.iterator(); iterator.hasNext();)
        {
            MessMenu abw = (MessMenu)iterator.next();
            if(abw.getTime().equals(time))
                finalResults.add(abw);
        }

        list = finalResults;
        pm.close();
        return list;
        Exception exception;
        exception;
        pm.close();
        throw exception;
    }

    public List getToday(String time, String day)
    {
        String t1;
        List finalResults;
        PersistenceManager pm;
        Query query;
        t1 = "1";
        if(time.equalsIgnoreCase("monday") || time.equalsIgnoreCase("mon"))
            t1 = "2";
        else
        if(time.equalsIgnoreCase("tuesday") || time.equalsIgnoreCase("tue"))
            t1 = "3";
        else
        if(time.equalsIgnoreCase("wednesday") || time.equalsIgnoreCase("wed"))
            t1 = "4";
        else
        if(time.equalsIgnoreCase("thursday") || time.equalsIgnoreCase("thu"))
            t1 = "5";
        else
        if(time.equalsIgnoreCase("friday") || time.equalsIgnoreCase("fri"))
            t1 = "6";
        else
        if(time.equalsIgnoreCase("saturday") || time.equalsIgnoreCase("sat"))
            t1 = "7";
        else
        if(time.equalsIgnoreCase("sunday") || time.equalsIgnoreCase("sun"))
            t1 = "1";
        else
            t1 = "8";
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        Calendar javaCalendar = null;
        String currentDate = "";
        javaCalendar = Calendar.getInstance();
        currentDate = (new StringBuilder(String.valueOf(javaCalendar.get(5)))).append("/").append(javaCalendar.get(2) + 1).append("/").append(javaCalendar.get(1)).toString();
        String cday = Integer.toString(javaCalendar.get(7));
        String cday1 = Integer.toString(date.getDay());
        finalResults = new ArrayList();
        List searchResults1 = new ArrayList();
        pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
        query = pm.newQuery(com/dchang/messmenu/entity/MessMenu);
        query.setFilter("day == param");
        query.declareParameters("String param");
        List list;
        List searchResults1 = (List)query.execute(t1);
        for(Iterator iterator = searchResults1.iterator(); iterator.hasNext();)
        {
            MessMenu abw = (MessMenu)iterator.next();
            if(abw.getTime().equals(day))
                finalResults.add(abw);
        }

        list = finalResults;
        pm.close();
        return list;
        Exception exception;
        exception;
        pm.close();
        throw exception;
    }

    public List getTotal(String time, String mess)
    {
        String t1;
        List finalResults;
        PersistenceManager pm;
        Query query;
        t1 = "1";
        if(time.equalsIgnoreCase("monday") || time.equalsIgnoreCase("mon"))
            t1 = "2";
        else
        if(time.equalsIgnoreCase("tuesday") || time.equalsIgnoreCase("tue"))
            t1 = "3";
        else
        if(time.equalsIgnoreCase("wednesday") || time.equalsIgnoreCase("wed"))
            t1 = "4";
        else
        if(time.equalsIgnoreCase("thursday") || time.equalsIgnoreCase("thu"))
            t1 = "5";
        else
        if(time.equalsIgnoreCase("friday") || time.equalsIgnoreCase("fri"))
            t1 = "6";
        else
        if(time.equalsIgnoreCase("saturday") || time.equalsIgnoreCase("sat"))
            t1 = "7";
        else
        if(time.equalsIgnoreCase("sunday") || time.equalsIgnoreCase("sun"))
            t1 = "1";
        else
            t1 = "8";
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        Calendar javaCalendar = null;
        String currentDate = "";
        javaCalendar = Calendar.getInstance();
        currentDate = (new StringBuilder(String.valueOf(javaCalendar.get(5)))).append("/").append(javaCalendar.get(2) + 1).append("/").append(javaCalendar.get(1)).toString();
        String cday = Integer.toString(javaCalendar.get(7));
        String cday1 = Integer.toString(date.getDay());
        finalResults = new ArrayList();
        List searchResults1 = new ArrayList();
        pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
        query = pm.newQuery(com/dchang/messmenu/entity/MessMenu);
        query.setFilter("day == param");
        query.declareParameters("String param");
        List list;
        List searchResults1 = (List)query.execute(t1);
        for(Iterator iterator = searchResults1.iterator(); iterator.hasNext();)
        {
            MessMenu abw = (MessMenu)iterator.next();
            if(abw.getMess().equals(mess))
                finalResults.add(abw);
        }

        list = finalResults;
        pm.close();
        return list;
        Exception exception;
        exception;
        pm.close();
        throw exception;
    }

    public List getTotal(String mess)
    {
        String cday;
        List finalResults;
        PersistenceManager pm;
        Query query;
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        String t1 = "breakfast";
        MessMenu m1 = new MessMenu();
        Calendar javaCalendar = null;
        String currentDate = "";
        javaCalendar = Calendar.getInstance();
        currentDate = (new StringBuilder(String.valueOf(javaCalendar.get(5)))).append("/").append(javaCalendar.get(2) + 1).append("/").append(javaCalendar.get(1)).toString();
        cday = Integer.toString(javaCalendar.get(7));
        String cday1 = Integer.toString(date.getDay());
        finalResults = new ArrayList();
        List searchResults1 = new ArrayList();
        pm = JDOHelper.getPersistenceManagerFactory("transactions-optional").getPersistenceManager();
        query = pm.newQuery(com/dchang/messmenu/entity/MessMenu);
        query.setFilter("day == param");
        query.declareParameters("String param");
        List list;
        List searchResults1 = (List)query.execute(cday);
        for(Iterator iterator = searchResults1.iterator(); iterator.hasNext();)
        {
            MessMenu abw = (MessMenu)iterator.next();
            if(abw.getMess().equals(mess))
                finalResults.add(abw);
        }

        list = finalResults;
        pm.close();
        return list;
        Exception exception;
        exception;
        pm.close();
        throw exception;
    }

    public static Dao[] values()
    {
        Dao adao[];
        int i;
        Dao adao1[];
        System.arraycopy(adao = ENUM$VALUES, 0, adao1 = new Dao[i = adao.length], 0, i);
        return adao1;
    }

    public static Dao valueOf(String s)
    {
        return (Dao)Enum.valueOf(com/dchang/messmenu/dao/Dao, s);
    }

    public static final Dao INSTANCE;
    private static final Dao ENUM$VALUES[];

    static 
    {
        INSTANCE = new Dao("INSTANCE", 0);
        ENUM$VALUES = (new Dao[] {
            INSTANCE
        });
    }
}