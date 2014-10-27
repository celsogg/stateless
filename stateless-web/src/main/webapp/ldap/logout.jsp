<%
    session.invalidate();
    String redirectURL = "../index.xhtml";
    response.sendRedirect(redirectURL);
%>

