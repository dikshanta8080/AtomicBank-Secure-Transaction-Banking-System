package com.banking.sathi.utils;

import com.banking.sathi.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class ServletUtil {
    private ServletUtil() {
    }

    public static User getLoggedInUser(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        return (User) session.getAttribute("user");
    }

    public static void putFlash(HttpServletRequest req, String key, String value) {
        req.getSession().setAttribute(key, value);
    }

    public static String consumeFlash(HttpServletRequest req, String key) {
        HttpSession session = req.getSession(false);
        if (session == null) {
            return null;
        }
        Object value = session.getAttribute(key);
        if (value != null) {
            session.removeAttribute(key);
            return String.valueOf(value);
        }
        return null;
    }
}
