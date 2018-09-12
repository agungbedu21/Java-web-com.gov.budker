/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gov.budker.common;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author agung
 */
public class MenuUtils {
    private static String menuHtml = "";


    public static String menuList2HTML(List<Map> menu, HttpServletRequest request) {
        menuHtml = "<ul class=\"sidebar-menu\" data-widget=\"tree\">" +
                "<li class=\"header\">MENU UTAMA</li>";
        //Recursive
        _menuList2HTML(menu, request);
        return menuHtml;
    }

    private static String _menuList2HTML(List<Map> menuList, HttpServletRequest request) {
        for (Map<String, Object> menu : menuList) {
            List<Map> childMenu = (List<Map>) menu.get("childs");

            String webContext = request.getContextPath();

            String menuName = ((String) menu.get("menuName")).isEmpty() ? "" : (String) menu.get("menuName");
            String menuIcon = (menu.get("menuIcon")) == null ? "" : (String) menu.get("menuIcon");
            String menuLink = (!childMenu.isEmpty()) ? "#" : ((String) menu.get("menuUrl")) != null ? webContext + (String) menu.get("menuUrl") : "#";
            String menuIsParent = (!childMenu.isEmpty()) ? "" : "";


            if (!childMenu.isEmpty()) {
                menuHtml += "<li class=\"treeview\" id=" + menuLink.replaceAll("/", "") + ">";
                menuHtml += "<a href=\"" + menuLink + "\"><i class=\"fa fa-lg fa-fw " + menuIcon + "\"></i><span>" + menuName + "</span> <span class=\"pull-right-container\"><i class=\"fa fa-angle-left pull-right\"></i></span></a>";
                menuHtml += "<ul class=\"treeview-menu\">\n";
                _menuList2HTML(childMenu, request);
            }else{
                menuHtml += "<li id=" + menuLink.replaceAll("/", "") + ">";
                menuHtml += "<a href=\"" + menuLink + "\"><i class=\"fa fa-lg fa-fw " + menuIcon + "\"></i><span>" + menuName + "</span> <span class=\"pull-right-container\"></a>";

            }
            menuHtml += "</li>";
        }
        menuHtml += "</ul>";
        return menuHtml;
    }


    public static String menuList2HTMLNew(List<Map> menu, HttpServletRequest request) {
        menuHtml = "<div class=\"navbar navbar-default\"><div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\"><ul class=\"nav navbar-nav\">";

        _menuList2HTMLNew(menu, request);
        return menuHtml;
    }

    private static String _menuList2HTMLNew(List<Map> menuList, HttpServletRequest request) {
        int i = 1;
        for (Map<String, Object> menu : menuList) {
            List<Map> childMenu = (List<Map>) menu.get("childs");

            String webContext = request.getContextPath();

            String menuName = ((String) menu.get("menuName")).isEmpty() ? "" : (String) menu.get("menuName");
            String menuIcon = (menu.get("menuIcon")) == null ? "" : (String) menu.get("menuIcon");
            String menuLink = (!childMenu.isEmpty()) ? "#" : ((String) menu.get("menuUrl")) != null ? webContext + (String) menu.get("menuUrl") : "#";
            String menuIsParent = (!childMenu.isEmpty()) ? "" : "";

            if (!childMenu.isEmpty()) {
                menuHtml += "<li class=\"dropdown dropdown-large\">";
                menuHtml += "<a href=\"" + menuLink + "\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">" + menuName + " <b class=\"caret\"></b></a>";
            } else {
                menuHtml += "<li id=" + menuLink.replaceAll("/", "") + ">";
                menuHtml += "<a href=\"" + menuLink + "\">" + menuName + "</a>";
            }


            if (!childMenu.isEmpty()) {
                menuHtml += "<ul class=\"dropdown-menu dropdown-menu-large row\" style=\"width:70%;\">";
                menuHtml += "<li class=\"col-sm-12\"><ul class=\"multicolumn" + i + "\">";
                _menuList2HTMLNewTier2(childMenu, request);
                menuHtml += "</li></ul></li>";
            }
            menuHtml += "</li>";
            i++;
        }
        menuHtml += "</ul></div></div>";
        return menuHtml;
    }


    private static String _menuList2HTMLNewTier2(List<Map> menuList, HttpServletRequest request) {

        for (Map<String, Object> menu : menuList) {
            List<Map> childMenu = (List<Map>) menu.get("childs");

            String webContext = request.getContextPath();

            String menuName = ((String) menu.get("menuName")).isEmpty() ? "" : (String) menu.get("menuName");
            String menuIcon = (menu.get("menuIcon")) == null ? "" : (String) menu.get("menuIcon");
            String menuLink = (!childMenu.isEmpty()) ? "#" : ((String) menu.get("menuUrl")) != null ? webContext + (String) menu.get("menuUrl") : "#";
            String menuIsParent = (!childMenu.isEmpty()) ? "" : "";

            menuHtml += "<li>";
            menuHtml += "<a class=\"trigger right-caret\" href=\"" + menuLink + "\"><i class=\"fa fa-lg fa-fw fa-mortar-board\"></i>" + menuName + "</a>";


            if (!childMenu.isEmpty()) {
                menuHtml += "<ul class=\"treeview-menu\">";
                _menuList2HTMLNewTier3(childMenu, request);
            }
            menuHtml += "</li>";
        }
        menuHtml += "</ul>";
        return menuHtml;
    }


    private static String _menuList2HTMLNewTier3(List<Map> menuList, HttpServletRequest request) {
        for (Map<String, Object> menu : menuList) {
            List<Map> childMenu = (List<Map>) menu.get("childs");

            String webContext = request.getContextPath();

            String menuName = ((String) menu.get("menuName")).isEmpty() ? "" : (String) menu.get("menuName");
            String menuIcon = (menu.get("menuIcon")) == null ? "" : (String) menu.get("menuIcon");
            String menuLink = (!childMenu.isEmpty()) ? "#" : ((String) menu.get("menuUrl")) != null ? webContext + (String) menu.get("menuUrl") : "#";
            String menuIsParent = (!childMenu.isEmpty()) ? "" : "";

            menuHtml += "<li>";
            menuHtml += "<a href=\"" + menuLink + "\">" + menuName + " <i class=\"fa fa-circle-o\"></i></a>";


            if (!childMenu.isEmpty()) {
                menuHtml += "<ul class=\"ssub-menu\">";
                _menuList2HTMLNewTier4(childMenu, request);
            }
            menuHtml += "</li>";
        }
        menuHtml += "</ul>";
        return menuHtml;
    }

    private static String _menuList2HTMLNewTier4(List<Map> menuList, HttpServletRequest request) {
        for (Map<String, Object> menu : menuList) {
            List<Map> childMenu = (List<Map>) menu.get("childs");

            String webContext = request.getContextPath();

            String menuName = ((String) menu.get("menuName")).isEmpty() ? "" : (String) menu.get("menuName");
            String menuIcon = (menu.get("menuIcon")) == null ? "" : (String) menu.get("menuIcon");
            String menuLink = (!childMenu.isEmpty()) ? "#" : ((String) menu.get("menuUrl")) != null ? webContext + (String) menu.get("menuUrl") : "#";
            String menuIsParent = (!childMenu.isEmpty()) ? "" : "";

            menuHtml += "<li>";
            menuHtml += "<a href=\"" + menuLink + "\">" + menuName + "</a>";


            if (!childMenu.isEmpty()) {
                menuHtml += "<ul class=\"ssub-menu\">";
                _menuList2HTMLNewTier4(childMenu, request);
            }
            menuHtml += "</li>";
        }
        menuHtml += "</ul>";
        return menuHtml;
    }

}