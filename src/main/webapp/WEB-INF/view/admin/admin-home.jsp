<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    Integer totalUsers = (Integer) request.getAttribute("totalUsers");
    if (totalUsers == null) {
        totalUsers = 0;
    }
    String username = (session != null) ? (String) session.getAttribute("user") : "Admin";
%>

<section class="section banner banner-section">
    <div class="container">
        <div class="login-card padding-horizontal--48 padding-top--24 padding-bottom--24">
            <h2 class="heading-md margin-bottom--12">
                Welcome Admin, <span style="color: var(--color-green-600);"><%= username %></span>
            </h2>
            <p class="paragraph margin-bottom--24">This is the admin dashboard. You have special privileges.</p>
            
            <!-- Total Users Card -->
            <div class="table-card padding-horizontal--24 padding-top--24 padding-bottom--24 margin-bottom--24" 
                 style="background: linear-gradient(135deg, var(--color-blue-400) 0%, var(--color-blue-600) 100%); color: white;">
                <div style="display: flex; align-items: center; justify-content: space-between;">
                    <div>
                        <h3 style="margin: 0 0 8px 0; font-size: 0.9rem; font-weight: 500; opacity: 0.9; text-transform: uppercase; letter-spacing: 1px;">
                            Total Users in System
                        </h3>
                        <p style="margin: 0; font-size: 2.5rem; font-weight: 700; line-height: 1;">
                            <%= totalUsers %>
                        </p>
                    </div>
                    <div style="font-size: 3rem; opacity: 0.3;">
                        👥
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

