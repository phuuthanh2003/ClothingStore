<%-- 
    Document   : sortproducts
    Created on : Feb 27, 2024, 3:17:40 PM
    Author     : HuuThanh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${requestScope.LISTPRODUCTS}" var="p">
            <div class="col-lg-4 col-md-6">
                <div class="single_product">
                    <div class="product_thumb">
                        <a href="single-product.html"><img style="width: 250px; height:250px" src="${p.images[0]}" alt=""></a> 
                            <c:if test="${p.releasedate.getYear() == 124}">
                            <div class="img_icone">
                                <img src="assets/img/cart/span-new.png" alt="">
                            </div>
                        </c:if>
                        <div class="product_action">
                            <a href="single-product.html"> <i class="fa fa-shopping-cart"></i> Add to cart</a>
                        </div>
                    </div>
                    <div class="product_content">
                        <span class="product_price">${p.price}$</span>
                        <h3 class="product_title"><a href="#">${p.name}</a></h3>
                    </div>
                    <div class="product_info">
                        <ul>
                            <li><a href="#" title=" Add to Wishlist ">Add to Wishlist</a></li>
                            <li><a href="#" data-toggle="modal" data-target="#modal_box" title="Quick view">View Detail</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </body>
</html>
