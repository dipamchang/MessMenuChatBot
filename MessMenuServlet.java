package com.dchang.messmenu;

import com.dchang.messmenu.dao.Dao;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.http.*;

public class MessMenuServlet extends HttpServlet
{

    public MessMenuServlet()
    {
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws IOException
    {
        day = req.getParameter("day");
        _logger.info((new StringBuilder("Received a request for day = ")).append(day).toString());
        mess = req.getParameter("mess");
        _logger.info((new StringBuilder("Received a request for mess = ")).append(mess).toString());
        time = req.getParameter("time");
        _logger.info((new StringBuilder("Received a request for time = ")).append(time).toString());
        menu = req.getParameter("menu");
        _logger.info((new StringBuilder("Received a request for menu = ")).append(menu).toString());
        Dao.INSTANCE.add(day, mess, time, menu);
        resp.sendRedirect("/index.html");
    }

    String day;
    String mess;
    String time;
    String menu;
    private static final Logger _logger = Logger.getLogger(com/dchang/messmenu/MessMenuServlet.getName());

}