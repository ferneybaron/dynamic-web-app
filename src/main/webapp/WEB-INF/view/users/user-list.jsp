<%@ page import="java.util.List" %>
<%@ page import="com.fbaron.model.UserModel" %>

<section class="section banner banner-section">
  <div class="container">
    <div class="login-card padding-horizontal--48">

      <% 
        List<UserModel> users = (List<UserModel>) request.getAttribute("users");
        String errorMessage = (String) request.getAttribute("errorMessage");
        String successMessage = (String) request.getAttribute("successMessage");

        if (errorMessage != null) {
      %>
        <p style="color: red;"><%= errorMessage %></p>
      <% } else if (successMessage != null) { %>
        <p style="color: green;"><%= successMessage %></p>
      <% } %>

      <!-- User List -->
      <div class="user-list-container">
        <h3>User List</h3>
        <table class="user-table">
          <thead>
            <tr>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Username</th>
            </tr>
          </thead>
          <tbody>
            <% if (users != null) {
                 for (UserModel user : users) { %>
              <tr>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getUsername() %></td>
              </tr>
            <%   }
               } %>
          </tbody>
        </table>
      </div>

    </div>
  </div>
</section>
