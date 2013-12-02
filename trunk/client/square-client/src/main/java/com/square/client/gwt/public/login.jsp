<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>D&eacute;monstration Square CRM</title>
<link href="login-box.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form name="f" action="j_spring_security_check" method="POST">
<table align="center"><tr><td valign="center">
<div id="login-box">
<img src="images/logo_square_solutions1.png" style="margin-left: 80px"/>
<br />
<%
                if (request.getParameter("login_error") != null) {
                %>
                <font color="red">Your login attempt was not successful, try again.</font>
                <%
            }
        %>
<br />
<div id="login-box-name" style="margin-top:20px;">Login :</div><div id="login-box-field" style="margin-top:20px;"><input name="j_username" class="form-login" title="Username" value="" size="30" maxlength="2048" /></div>
<div id="login-box-name">Mot de passe :</div><div id="login-box-field"><input name="j_password" type="password" class="form-login" title="Password" value="" size="30" maxlength="2048" /></div>
<br />
<br />
<br />
<a href="#"><input type="image" src="images/login-btn.png" width="103" height="42" style="margin-left:90px;" /></a>
</div>
</td></tr>
</table>
</form>
</body>
</html>