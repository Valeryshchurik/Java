package com.shchuryk.custom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.shchuryk.model.Tour;
import com.shchuryk.model.User;
import com.shchuryk.util.AttributeParameterEnum;

/**
 *
 * @author Egor Piankov
 */
public class OrderTableTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
//        Locale locale = (Locale)session.getAttribute("locale");
//        ResourceBundle bundle;
//        if (locale != null) {
//            bundle = ResourceBundle.getBundle("text", locale);
//        }
//        else{
//            bundle = ResourceBundle.getBundle("text");
//        }
//        String product = bundle.getString("productlist.product");
//        String cost = bundle.getString("productlist.cost");
//        String edit = bundle.getString("productlist.edit");
        User loginedUser = (User) session.getAttribute(AttributeParameterEnum.USER.toString().toLowerCase());
        boolean isAdmin=false;
        if (loginedUser!=null)
            isAdmin = (loginedUser.getUserType()==1);
        List<Tour> listProduct;
        listProduct=(List<Tour>) pageContext.getRequest().getAttribute("result");
        if (listProduct==null)
            listProduct= new ArrayList<>();
        String str = "<table border=\"1\" cellpadding=\"5\" cellspacing=\"1\" >\n";
        str += "<tr>\n"
                + "          <th>" + "ID" + "</th>\n"
                + "          <th>" + "Название" + "</th>\n"
                + "          <th>" + "Страна" + "</th>\n"
                + "          <th>" + "Дата начала" + "</th>\n"
                + "          <th>" + "Дата окончания" + "</th>\n"
                + "          <th>" + "Тип" + "</th>\n"
                + "          <th>" + "Цена" + "</th>\n"
                + "          <th>" + "Скидки обладателям карты друга" + "</th>\n";
        if (isAdmin) {
            str += "<th>" + "Изменить" + "</th>\n";
        }
        str += "</tr>\n";
        for (Tour d : listProduct) {
            str += "<tr>\n"
                    + "          <th>" + d.getTourId() + "</th>\n"
                    + "          <th>" + d.getName() + "</th>\n"
                    + "          <th>" + d.getCountry() + "</th>\n"
                    + "          <th>" + d.getStartDate() + "</th>\n"
                    + "          <th>" + d.getEndDate() + "</th>\n"
                    + "          <th>" + d.getType() + "</th>\n"
                    + "          <th>" + d.getPrice() + "</th>\n"
                    + "          <th>" + d.getDiscounts() + "</th>\n";
            if (isAdmin) {
                str += "<th>" + "<a href=\"?command=editTour&id="+ ((d.getTourId()+1)%4+1) +"\">" + "Заменить тур на следующий" +"</a>" + "</th>\n";
            }
            str += "</tr>\n";
        }
        
        str += "</table>\n";
        try {
            JspWriter out = pageContext.getOut();
            out.write(str);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
