package com.dchang.messmenu;

import com.dchang.messmenu.dao.Dao;
import com.dchang.messmenu.entity.MessMenu;
import com.google.appengine.api.xmpp.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class MessMenuBotServlet extends HttpServlet
{

    public MessMenuBotServlet()
    {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws IOException
    {
        Date date = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));
        String strCallResult = "";
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        resp.setContentType("text/plain");
        XMPPService xmpp = null;
        JID fromJid = null;
        try
        {
            xmpp = XMPPServiceFactory.getXMPPService();
            Message msg = xmpp.parseMessage(req);
            fromJid = msg.getFromJid();
            String msgBody = msg.getBody();
            String str11 = msgBody;
            String strCommand = str11.toLowerCase();
            _log.info((new StringBuilder("Received a message : ")).append(strCommand).append(" from ").append(fromJid).toString());
            if(strCommand == null)
                throw new Exception("You must give a command.");
            strCommand = strCommand.trim();
            if(strCommand.length() == 0)
                throw new Exception("You must give a command.");
            String words[] = strCommand.split(" ");
            if(words[0].equalsIgnoreCase("help") || words[0].equalsIgnoreCase("<help>"))
            {
                StringBuffer SB = new StringBuffer();
                SB.append("\n***** Welcome to Mess Menu Bot ***** \n");
                SB.append((new StringBuilder("Date :- ")).append(date.toString()).toString());
                SB.append("\r\nI understand the following commands: \n");
                SB.append("---------------------------------------");
                SB.append("\r\n1.) Type <breakfast> or <lunch> or <snacks> or <dinner>");
                SB.append("\r\n    (without brackets) to get todays menu \n");
                SB.append("---------------------------------------");
                SB.append("\r\n2.) Type <mon><space><snacks> (without brackets) to get ");
                SB.append("\r\n    menu of monday snacks. Likewise for other days and time \n");
                SB.append("---------------------------------------");
                SB.append("\r\n3.) Type <a> / <a><space><mess> (without brackets) to get ");
                SB.append("\r\n    entire A Mess Menu for today. And Same for C Mess \n");
                SB.append("---------------------------------------");
                SB.append("\r\n4.) Type <mon><space><a> (without brackets) to get ");
                SB.append("\r\n    entire A Mess Menu for Monday. And Same for C Mess \n");
                SB.append("---------------------------------------\n");
                SB.append("Type <about> to know more about bot");
                strCallResult = SB.toString();
            } else
            if(words[0].equalsIgnoreCase("about") || words[0].equalsIgnoreCase("<about>"))
            {
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Hello! I am the BITS Mess Menu Bot version 1.0\r\n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Developed By ~Deal_Hunter~").toString();
            } else
            if(words[0].equalsIgnoreCase("hi") || words[0].equalsIgnoreCase("hello"))
            {
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("\n Hello! I am the BITS Mess Menu Gtalk Bot \r\n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Type <help> to know the commands. \n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Developed By ~Deal_Hunter~").toString();
            } else
            if(words[0].equalsIgnoreCase("breakfast") || words[0].equalsIgnoreCase("lunch") || words[0].equalsIgnoreCase("snacks") || words[0].equalsIgnoreCase("dinner"))
            {
                List ER = Dao.INSTANCE.getToday(words[0]);
                Iterator iter = ER.iterator();
                strCallResult = (new StringBuilder("\nTodays ")).append(words[0].toUpperCase()).append(" menu in both Messes :- \n").toString();
                MessMenu item;
                for(strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString(); iter.hasNext(); strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("\n").append(item).append("\n").toString())
                    item = (MessMenu)iter.next();

                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Thank you for using the Mess menu Bot\r\n").toString();
            } else
            if(words[0].equalsIgnoreCase("a") || words[0].equalsIgnoreCase("c") || words[0].equalsIgnoreCase("a mess") || words[0].equalsIgnoreCase("c mess"))
            {
                List ER = Dao.INSTANCE.getTotal(words[0]);
                Iterator iter = ER.iterator();
                strCallResult = (new StringBuilder("\nTodays Entire  Menu in ")).append(words[0].toUpperCase()).append(" Mess :- \n").toString();
                MessMenu item;
                for(strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString(); iter.hasNext(); strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("\n").append(item.Total()).append("\n").toString())
                    item = (MessMenu)iter.next();

                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Thank you for using the Mess menu Bot\r\n").toString();
            } else
            if(words[0].equalsIgnoreCase("mon") || words[0].equalsIgnoreCase("monday") || words[0].equalsIgnoreCase("tue") || words[0].equalsIgnoreCase("tuesday") || words[0].equalsIgnoreCase("wed") || words[0].equalsIgnoreCase("wednesday") || words[0].equalsIgnoreCase("thu") || words[0].equalsIgnoreCase("thursday") || words[0].equalsIgnoreCase("fri") || words[0].equalsIgnoreCase("friday") || words[0].equalsIgnoreCase("sat") || words[0].equalsIgnoreCase("saturday") || words[0].equalsIgnoreCase("sun") || words[0].equalsIgnoreCase("sunday"))
            {
                if(words[1].equalsIgnoreCase("a") || words[1].equalsIgnoreCase("c"))
                {
                    List ER = Dao.INSTANCE.getTotal(words[0], words[1]);
                    Iterator iter = ER.iterator();
                    strCallResult = (new StringBuilder("\n")).append(words[0].toUpperCase()).append("'s ").append("Entire Menu in ").append(words[1].toUpperCase()).append(" Mess :- \n").toString();
                    MessMenu item;
                    for(strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString(); iter.hasNext(); strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("\n").append(item.Total()).append("\n").toString())
                        item = (MessMenu)iter.next();

                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString();
                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Thank you for using the Mess Menu Bot\r\n").toString();
                } else
                if(words[1].equalsIgnoreCase("breakfast") || words[1].equalsIgnoreCase("lunch") || words[1].equalsIgnoreCase("snacks") || words[1].equalsIgnoreCase("dinner"))
                {
                    List ER = Dao.INSTANCE.getToday(words[0], words[1]);
                    Iterator iter = ER.iterator();
                    strCallResult = (new StringBuilder("\n")).append(words[0].toUpperCase()).append("'s ").append(words[1].toUpperCase()).append(" menu in both Messes :- \n").toString();
                    MessMenu item;
                    for(strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString(); iter.hasNext(); strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("\n").append(item).append("\n").toString())
                        item = (MessMenu)iter.next();

                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("=================================\r\n").toString();
                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Thank you for using the Mess Menu Bot\r\n").toString();
                } else
                {
                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Sorry! Could not understand your command \n").toString();
                    strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Type <help> to understand commands.").toString();
                }
            } else
            {
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Sorry! Could not understand your command \n").toString();
                strCallResult = (new StringBuilder(String.valueOf(strCallResult))).append("Type <help> to understand commands.").toString();
            }
            Message replyMessage = (new MessageBuilder()).withRecipientJids(new JID[] {
                fromJid
            }).withBody(strCallResult).build();
            boolean messageSent = false;
            if(xmpp.getPresence(fromJid).isAvailable())
            {
                SendResponse status = xmpp.sendMessage(replyMessage);
                boolean flag1 = status.getStatusMap().get(fromJid) == com.google.appengine.api.xmpp.SendResponse.Status.SUCCESS;
            }
        }
        catch(Exception ex)
        {
            _log.info((new StringBuilder("Something went wrong. Please try again!")).append(ex.getMessage()).toString());
            Message replyMessage = (new MessageBuilder()).withRecipientJids(new JID[] {
                fromJid
            }).withBody("Mess Menu Bot could not understand your command. Please try again or type <help>.").build();
            boolean messageSent = false;
            if(xmpp.getPresence(fromJid).isAvailable())
            {
                SendResponse status = xmpp.sendMessage(replyMessage);
                boolean flag = status.getStatusMap().get(fromJid) == com.google.appengine.api.xmpp.SendResponse.Status.SUCCESS;
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException
    {
        doGet(req, resp);
    }

    public static final Logger _log = Logger.getLogger(com/dchang/messmenu/MessMenuBotServlet.getName());

}